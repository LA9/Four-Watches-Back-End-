package com.example.fourWatches.customer

import jakarta.persistence.*


@Entity
@Table(name = "customer")
data class CustomerDao(
    @Column(length = 20)
    var customerUsername: String = "",
    @Column(name = "email", nullable = false, length = 30, unique = true)
    var email: String = "",
    @Column(length = 80)
    var password: String = "",
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
)



