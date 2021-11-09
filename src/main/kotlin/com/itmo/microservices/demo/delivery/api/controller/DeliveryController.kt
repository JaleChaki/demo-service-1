package com.itmo.microservices.demo.delivery.api.controller

import com.itmo.microservices.demo.delivery.api.model.DeliveryModel
import com.itmo.microservices.demo.delivery.api.service.DeliveryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user")
class DeliveryController(private val deliveryService: DeliveryService) {

    @GetMapping("/delivery/{deliveryId}")
    @Operation(
        summary = "Get delivery by id",
        responses = [
            ApiResponse(
                description = "OK",
                responseCode = "200"),
            ApiResponse(
                description = "Unauthorized",
                responseCode = "403",
                content = [io.swagger.v3.oas.annotations.media.Content()]
            )
        ],
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun getDelivery(@PathVariable deliveryId : UUID) : DeliveryModel = deliveryService.getDelivery(deliveryId)

    @GetMapping("/delivery/{deliveryId}/done")
    @Operation(
        summary = "finish new delivery",
        responses = [
            ApiResponse(description = "OK", responseCode = "200"),
            ApiResponse(description = "Bad request", responseCode = "400", content = [Content()]),
            ApiResponse(description = "Unauthorized", responseCode = "403", content = [Content()])
        ],
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun finalizeDelivery(@PathVariable deliveryId : UUID) = deliveryService.finalizeDelivery(deliveryId)

    @PostMapping("/delivery")
    @Operation(
        summary = "Creates new delivery",
        responses = [
            ApiResponse(description = "OK", responseCode = "200"),
            ApiResponse(description = "Bad request", responseCode = "400", content = [Content()]),
            ApiResponse(description = "Unauthorized", responseCode = "403", content = [Content()])
        ],
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun createDelivery(@RequestBody delivery: DeliveryModel) = deliveryService.addDelivery(delivery)

    @DeleteMapping("/delivery/{deliveryId}")
    @Operation(
        summary = "Delete delivery by id",
        responses = [
            ApiResponse(description = "OK", responseCode = "200"),
            ApiResponse(description = "Unauthorized", responseCode = "403", content = [Content()])
        ],
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    fun deleteOrder(@PathVariable deliveryId : UUID) = deliveryService.deleteDelivery(deliveryId)


}