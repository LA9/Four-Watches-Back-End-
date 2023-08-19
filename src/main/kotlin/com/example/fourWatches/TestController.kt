package com.example.fourWatches

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {


    @Transactional
    @GetMapping("/test")
    fun registerCustomer(): String {
     return "connected"
    }
}