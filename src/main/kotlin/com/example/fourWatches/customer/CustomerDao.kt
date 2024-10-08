package com.example.fourWatches.customer

import jakarta.persistence.*


@Entity
@Table(name = "customer")
data class CustomerDao(
    @Column(nullable = false, length = 25, unique = true)
    var username: String = "",
    @Column(nullable = false, length = 40, unique = true)
    var email: String = "",
    @Column(length = 80)
    var password: String = "",
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
)



