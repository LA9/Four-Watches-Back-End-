package com.example.fourWatches.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/product")
@Suppress("unused")

class ProductController {

    @Autowired
    private lateinit var productService: ProductService
    @GetMapping("/get-products")
    fun getProducts(): ArrayList<ProductDao> {
        return productService.getProducts()
    }





}