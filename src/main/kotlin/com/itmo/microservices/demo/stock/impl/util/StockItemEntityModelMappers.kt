package com.itmo.microservices.demo.stock.impl.util

import com.itmo.microservices.demo.stock.api.model.CatalogItemDto
import com.itmo.microservices.demo.stock.api.model.StockItemModel
import com.itmo.microservices.demo.stock.impl.entity.StockItem

fun StockItemModel.toEntity(): StockItem = StockItem(
    id = this.id,
    title = this.title,
    description = this.description,
    price = this.price,
    amount = this.amount
)

fun StockItem.toDto(): CatalogItemDto = CatalogItemDto(
    id = this.id!!,
    title = this.title!!,
    description = this.description!!,
    price = this.price!!,
    amount = this.amount!!
)

fun StockItem.toModel(): StockItemModel = StockItemModel(
    id = this.id!!,
    title = this.title!!,
    description = this.description!!,
    price = this.price!!,
    amount = this.amount!!
)
