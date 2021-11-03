package com.itmo.microservices.demo.orders.impl.util

import com.itmo.microservices.demo.orders.api.model.OrderModel
import com.itmo.microservices.demo.orders.impl.entity.Order

fun OrderModel.toEntity() = Order(
    id = this.id,
    basketId = this.basketId,
    date = this.date,
    userName = this.userName,
    status = this.status
)

fun Order.toModel(): OrderModel = OrderModel(
    id = this.id,
    basketId = this.basketId,
    date = this.date,
    userName = this.userName,
    status = this.status
)