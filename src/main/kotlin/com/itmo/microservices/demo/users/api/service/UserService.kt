package com.itmo.microservices.demo.users.api.service

import com.itmo.microservices.demo.users.api.model.AppUserModel
import com.itmo.microservices.demo.users.api.model.GetAccountDataRequest
import com.itmo.microservices.demo.users.api.model.RegistrationRequest
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

interface UserService {
    fun findUser(id: UUID): AppUserModel?
    fun registerUser(request: RegistrationRequest)
    fun getAccountData(id: UUID): AppUserModel
    /*fun deleteUser(request: GetAccountDataRequest)*/
}