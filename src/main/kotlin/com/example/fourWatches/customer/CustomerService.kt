package com.example.fourWatches.customer


import com.example.fourWatches.util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class CustomerService {

    val myUtil by lazy { MyUtil() }

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    fun registerCustomer(registerRequestModel: RegisterRequestModel): ResponseEntity<Any> {

        if (!customerDetailsAreNotNull(registerRequestModel))
            return ResponseEntity<Any>(getResponseFailedMessage(registerRequestModel), HttpStatus.BAD_REQUEST)


        if (isEmailAlreadyRegistered(registerRequestModel.email))
            return ResponseEntity<Any>("Email is already registered", HttpStatus.BAD_REQUEST)
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


    fun login(loginModel: LoginModel): ResponseEntity<Any> {

        if (!(isEmailAlreadyRegistered(loginModel.email)))
            return ResponseEntity("Email is not registered", HttpStatus.BAD_REQUEST)

        val customer = customerRepository.findCustomerByEmail(loginModel.email).get()

        if (isPasswordMatch(loginModel.password, customer.password))
            return ResponseEntity(customer, HttpStatus.ACCEPTED)
        else
            return ResponseEntity("Incorrect password , Please try again", HttpStatus.ACCEPTED)

    }

    private fun isUsernameAlreadyRegistered(username: String): Boolean {
        return !customerRepository.findCustomerByUsername(username).isEmpty

    }

    fun isEmailAlreadyRegistered(email: String): Boolean {
        return !customerRepository.findCustomerByEmail(email).isEmpty
    }


}