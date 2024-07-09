package com.example.fourWatches.customer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/customers")
@Suppress("unused")
class CustomerController() {


    @Autowired
    private lateinit var customerService: CustomerService


    @PostMapping("/signup")
    fun signup(@RequestBody signupRequestModel: SignupRequestModel): ResponseEntity<Any> {
        return customerService.signup(signupRequestModel)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequestModel: LoginRequestModel):ResponseEntity< Any> {
        return customerService.login(loginRequestModel)
    }
}


