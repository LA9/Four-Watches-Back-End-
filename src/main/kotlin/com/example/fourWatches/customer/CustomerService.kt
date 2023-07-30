package com.example.fourWatches.customer


import com.example.fourWatches.util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class CustomerService {

//    val myUtil by lazy { MyUtil() }

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    fun registerCustomer(registerRequest: RegisterRequest): ResponseEntity<Any> {

        if (!customerDetailsAreNotNull(registerRequest))
            return ResponseEntity<Any>(getResponseFailedMessage(registerRequest), HttpStatus.BAD_REQUEST)


        val customerDao = CustomerDao().apply {
            customerUsername = registerRequest.username
            email = registerRequest.email
            password = cryptPlainPassword(registerRequest.password)
        }
        customerRepository.save(customerDao)
        return ResponseEntity<Any>(customerDao, HttpStatus.ACCEPTED)
    }

    @Transactional
    fun login(login: Login): CustomerDao {

        return try {
            val customer = customerRepository.findCustomerByEmail(login.email.lowercase())

            if (comparePassword(login.password, customer.password)) {
                 customer
            } else
                return  CustomerDao()

        } catch (e: EmptyResultDataAccessException) {
         return  CustomerDao()
        }
    }

    fun getCustomers(): Any {

        return customerRepository.findAll()
    }

    fun deleteCustomer(id: Int): ResponseEntity<Any> {
        val customer: CustomerDao = customerRepository.findById(id).orElseThrow()
        customerRepository.delete(customer)
        return ResponseEntity<Any>(customer.email +" Deleted", HttpStatus.ACCEPTED)
    }

    fun getCustomerIdByEmail(email: String): ResponseEntity<CustomerDao> {
        val customerId =customerRepository.findCustomerByEmail(email.lowercase())

        return ResponseEntity<CustomerDao>(customerId, HttpStatus.ACCEPTED)

    }


}