package com.pientaa.shoppingplatform.products.persistence

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "product")
class ProductEntity(
    @Id
    var id: UUID,
    @Embedded
    var price: ProductPrice,
)
