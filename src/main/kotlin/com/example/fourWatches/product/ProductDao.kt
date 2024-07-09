package com.example.fourWatches.product

import jakarta.persistence.*

@Entity
@Table(name = "product")
data class ProductDao(
    @Column(length = 25) var title: String = "",
    @Column(length = 6) var price: String = "",
    @Column var image: Int = 0,
    @Column(length = 5) var sold: Int = 0,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  var id: Int = 0
)