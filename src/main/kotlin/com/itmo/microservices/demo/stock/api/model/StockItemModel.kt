package com.itmo.microservices.demo.stock.api.model

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class StockItemModel(
    val id: UUID?,
    val title: String,
    val description: String,
    val price: Int,
    val amount: Int
)
