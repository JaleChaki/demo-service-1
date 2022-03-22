package com.itmo.microservices.demo.orders.impl.repository

import com.itmo.microservices.demo.delivery.impl.entity.Delivery
import com.itmo.microservices.demo.orders.impl.entity.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface OrderRepository : JpaRepository<Order, UUID> {

    @Query("FROM Order WHERE deliveryDuration >= ?1 AND deliveryDuration <= ?2")
    fun findInWindow(start: Int, end: Int): List<Order>

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE Order u SET u.deliveryDuration = :deliveryDuration WHERE u.id  = :id")
    fun updateDeliveryDuration(@Param("id") id: UUID, @Param("deliveryDuration") deliveryDuration: Int?)

}