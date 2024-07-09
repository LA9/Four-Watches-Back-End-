package com.example.fourWatches.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service

class ProductService {
    @Autowired
    private lateinit var productRepository: ProductRepository

    fun getProducts(): ArrayList<ProductDao> = productRepository.findAll() as ArrayList<ProductDao>

}
