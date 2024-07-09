package com.example.fourWatches.customer


import com.example.fourWatches.util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class CustomerService {


    @Autowired
    private lateinit var customerRepository: CustomerRepository

    fun signup(signupRequest: SignupRequestModel): ResponseEntity<Any> {

        if (!customerDetailsAreNotNull(signupRequest))
            return ResponseEntity<Any>(getResponseFailedMessage(signupRequest), HttpStatus.BAD_REQUEST)

        if (isEmailAlreadyRegistered(signupRequest.email))
            return ResponseEntity<Any>("Email is already registered", HttpStatus.CONFLICT  )
        if (isUsernameAlreadyRegistered(signupRequest.username))
            return ResponseEntity<Any>("Username is already been taken", HttpStatus.CONFLICT)



        val customerDao = CustomerDao(
            signupRequest.username,
            signupRequest.email,
            cryptPlainPassword(signupRequest.password)
        )

        customerRepository.save(customerDao)
        return ResponseEntity<Any>(customerDao, HttpStatus.OK)
    }


    fun login(loginRequestModel: LoginRequestModel): ResponseEntity<Any> {

        if (!(isEmailAlreadyRegistered(loginRequestModel.email)))
            return ResponseEntity("Email is not registered", HttpStatus.BAD_REQUEST)

        val customer = customerRepository.findCustomerByEmail(loginRequestModel.email).get()

        if (isPasswordMatch(loginRequestModel.password, customer.password))
            return ResponseEntity(customer, HttpStatus.ACCEPTED)
        else
            return ResponseEntity("Incorrect password , Please try again", HttpStatus.BAD_REQUEST)

    }

    private fun isUsernameAlreadyRegistered(username: String): Boolean {
        return !customerRepository.findCustomerByUsername(username).isEmpty

    }

    fun isEmailAlreadyRegistered(email: String): Boolean {
        return !customerRepository.findCustomerByEmail(email).isEmpty
    }


}