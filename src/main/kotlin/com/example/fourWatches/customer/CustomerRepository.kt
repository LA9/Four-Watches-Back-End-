package com.example.fourWatches.customer

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface CustomerRepository : CrudRepository<CustomerDao, Int> {
    fun findCustomerByEmail(email: String): CustomerDao
}

