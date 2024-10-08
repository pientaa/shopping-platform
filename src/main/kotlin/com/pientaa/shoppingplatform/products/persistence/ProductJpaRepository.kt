package com.pientaa.shoppingplatform.products.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Repository
@Transactional
interface ProductJpaRepository : JpaRepository<ProductEntity, UUID>
