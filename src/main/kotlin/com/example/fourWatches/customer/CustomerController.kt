package com.example.fourWatches.customer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/customers")
@Suppress("unused")
class CustomerController() {


    @Autowired
    private lateinit var customerService: CustomerService


    @Transactional
    @PostMapping("/register")
    fun registerCustomer(@RequestBody registerRequestModel: RegisterRequestModel): ResponseEntity<Any> {
        return customerService.registerCustomer(registerRequestModel)
    }

    @Transactional
    @PostMapping("/login")
    fun loginCustomer(@RequestBody loginModel: LoginModel): CustomerDao {
        return customerService.login(loginModel)
    }


}


