package com.example.fourWatches.product

import com.example.fourWatches.customer.CustomerDao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository

interface ProductRepository : CrudRepository<ProductDao, Int> {
}
