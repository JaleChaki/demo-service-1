package com.itmo.microservices.demo.stock.impl.repository

import com.itmo.microservices.demo.stock.api.model.BookingLogRecordModel
import com.itmo.microservices.demo.stock.impl.entity.BookingLogRecord
import com.itmo.microservices.demo.stock.impl.entity.StockItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface BookingRepository : JpaRepository<BookingLogRecord, UUID> {
    @Query("From BookingLogRecord WHERE bookingId = ?1")
    fun findByBookingId(id: UUID) : List<BookingLogRecord>

    @Query("From BookingLogRecord WHERE bookingId = ?1 AND itemId = ?2")
    fun findItem(bookingId : UUID, itemId : UUID) : BookingLogRecord?

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query("DELETE FROM booking_log_record WHERE booking_id = ?1"
        ,nativeQuery = true)
    fun deleteByOrderId(orderId: UUID)
}