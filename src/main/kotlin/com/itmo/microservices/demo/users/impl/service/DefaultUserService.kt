package com.itmo.microservices.demo.users.impl.service

import com.google.common.eventbus.EventBus
import com.itmo.microservices.commonlib.annotations.InjectEventLogger
import com.itmo.microservices.commonlib.logging.EventLogger
import com.itmo.microservices.demo.common.exception.NotFoundException
import com.itmo.microservices.demo.stock.api.model.StockItemModel
import com.itmo.microservices.demo.stock.impl.util.toModel
import com.itmo.microservices.demo.users.api.messaging.UserCreatedEvent
import com.itmo.microservices.demo.users.api.messaging.UserDeletedEvent
import com.itmo.microservices.demo.users.api.service.UserService
import com.itmo.microservices.demo.users.impl.entity.AppUser
import com.itmo.microservices.demo.users.api.model.AppUserModel
import com.itmo.microservices.demo.users.api.model.RegistrationRequest
import com.itmo.microservices.demo.users.api.model.RegistrationResult
import com.itmo.microservices.demo.users.api.model.UserDto
import com.itmo.microservices.demo.users.impl.logging.UserServiceNotableEvents
import com.itmo.microservices.demo.users.impl.repository.UserRepository
import com.itmo.microservices.demo.users.impl.util.toModel
import com.itmo.microservices.demo.users.impl.util.toUserDto
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Suppress("UnstableApiUsage")
@Service
class DefaultUserService(private val userRepository: UserRepository,
                         private val passwordEncoder: PasswordEncoder,
                         private val eventBus: EventBus
                         ): UserService {

    @InjectEventLogger
    private lateinit var eventLogger: EventLogger

    override fun findUser(name: String): AppUserModel? = userRepository
            .findByName(name)?.toModel()
        ?: throw NotFoundException("User $name not found")

    override fun registerUser(request: RegistrationRequest): UserDto {
        System.out.println("IS SYSTEM OUt printlN GOOD?")
        eventLogger.info(UserServiceNotableEvents.I_USER_REQUEST,request)
        eventLogger.info(UserServiceNotableEvents.I_USER_CREATED,request)

        val user = userRepository.findByName(request.name)
        if (user != null)
        {
            throw NotFoundException("User already exist")
        }
        else {
            val userEntity = userRepository.save(request.toEntity())
            eventBus.post(UserCreatedEvent(userEntity.toModel()))
            eventLogger.info(UserServiceNotableEvents.I_USER_CREATED, userEntity.name)
            val id = userEntity.id
            val name = userEntity.name
            return UserDto(id!!, name!!)
        }
    }


    override fun getAccountData(id: UUID): UserDto =
        userRepository.findByIdOrNull(id)?.toUserDto()
    ?: throw NotFoundException("User $id not found")




    /*override fun deleteUser(request: GetAccountDataRequest) {
        runCatching {
            userRepository.deleteById(request.id)
        }.onSuccess {
            /*eventBus.post(UserDeletedEvent(user.username))
            eventLogger.info(UserServiceNotableEvents.I_USER_DELETED, user.username)*/
        }.onFailure {
            throw NotFoundException("User ${request.id} not found", it)
        }
    }*/

    fun RegistrationRequest.toEntity(): AppUser =
        AppUser(id = this.id,
            ipaddress = this.ipaddress,
            username = this.username,
            name = this.name,
            email = this.email,
            password = passwordEncoder.encode(this.password),
            phone = this.phone,
            lastBasketId = UUID.fromString("0-0-0-0-0")
        )

}
