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

    val myUtil by lazy { MyUtil() }

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    fun registerCustomer(registerRequestModel: RegisterRequestModel): ResponseEntity<Any> {

        if (!customerDetailsAreNotNull(registerRequestModel))
            return ResponseEntity<Any>(getResponseFailedMessage(registerRequestModel), HttpStatus.BAD_REQUEST)


        if (isEmailAlreadyRegistered(registerRequestModel.email))
            return ResponseEntity<Any>("Email is already registered", HttpStatus.ACCEPTED)
        if (isUsernameAlreadyRegistered(registerRequestModel.username))
            return ResponseEntity<Any>("Username is already registered", HttpStatus.BAD_REQUEST)

        val customerDao = CustomerDao().apply {
            username = registerRequestModel.username
            email = registerRequestModel.email
            password = cryptPlainPassword(registerRequestModel.password)
        }
        customerRepository.save(customerDao)
        return ResponseEntity<Any>(customerDao, HttpStatus.ACCEPTED)
    }


    fun login(loginModel: LoginModel): CustomerDao {

        return try {
            val customer = customerRepository.findCustomerByEmail(loginModel.email.lowercase()).get()

            if (comparePassword(loginModel.password, customer.password)) {
                customer
            } else
                return CustomerDao()

        } catch (e: EmptyResultDataAccessException) {
            return CustomerDao()
        }
    }

    private fun isUsernameAlreadyRegistered(username: String): Boolean {
        return !customerRepository.findCustomerByUsername(username).isEmpty

    }

    fun isEmailAlreadyRegistered(email: String): Boolean {
        return !customerRepository.findCustomerByEmail(email).isEmpty
    }


}