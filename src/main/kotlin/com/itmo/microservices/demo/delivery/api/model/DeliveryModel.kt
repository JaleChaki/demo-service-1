package com.itmo.microservices.demo.delivery.api.model

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class DeliveryModel(
    val orderId: UUID?,
    val address: String?,
    val slot: Int?)