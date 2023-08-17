package com.example.fourWatches

import com.example.fourWatches.customer.RegisterRequest
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {


    @Transactional
    @GetMapping("/test")
    fun registerCustomer(): String {
     return "connected"
    }
}