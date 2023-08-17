package com.example.fourWatches.customer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    @GetMapping("/get-all")
    fun getCustomers(): Any {
        return customerService.getCustomers()
    }

    @Transactional
    @PostMapping("/register")
    fun registerCustomer(@RequestBody registerRequest: RegisterRequest): ResponseEntity<Any> {
        return customerService.registerCustomer(registerRequest)
    }

    @Transactional
    @PostMapping("/login")
    fun loginCustomer(@RequestBody login: Login): CustomerDao {
        return customerService.login(login)
    }


}


