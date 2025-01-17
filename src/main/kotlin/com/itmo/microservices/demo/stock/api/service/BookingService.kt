package com.itmo.microservices.demo.stock.api.service

import com.itmo.microservices.demo.stock.api.model.BookingLogRecordModel
import com.itmo.microservices.demo.stock.api.model.BookingStatus
import com.itmo.microservices.demo.stock.api.model.CatalogItemDto
import com.itmo.microservices.demo.stock.api.model.StockItemModel
import com.itmo.microservices.demo.stock.impl.entity.BookingLogRecord
import java.util.*

interface BookingService {
    //TODO methods
    fun getBookingById(id: UUID) : BookingLogRecord?
    fun getBookingsByBookingId(id: UUID) : List<BookingLogRecordModel>
    fun changeBookingStatus(id: UUID, status : BookingStatus)
}