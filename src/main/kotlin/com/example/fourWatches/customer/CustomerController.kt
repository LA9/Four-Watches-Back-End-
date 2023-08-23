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


    @PostMapping("/register")
    fun registerCustomer(@RequestBody registerRequestModel: RegisterRequestModel): ResponseEntity<Any> {

        return customerService.registerCustomer(registerRequestModel)
    }

    @PostMapping("/login")
    fun loginCustomer(@RequestBody loginModel: LoginModel):ResponseEntity< Any> {
        return customerService.login(loginModel)
    }




}


