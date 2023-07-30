package com.example.fourWatches.util

import com.example.fourWatches.customer.RegisterRequest
import org.springframework.security.crypto.bcrypt.BCrypt




//  fun info(info: String) {
//      var logger: Logger = LoggerFactory.getLogger(MyUtil::class.java)
//        logger.info(info)
//    }

    fun customerDetailsAreNotNull(registerRequest: RegisterRequest): Boolean {
        val array = arrayOf(registerRequest.email, registerRequest.username, registerRequest.password)
        array.forEach {
            if (it.isEmpty())
                return false
        }
        return true
    }

    fun getResponseFailedMessage(registerRequest: RegisterRequest): String {
        var message = ""
        if (registerRequest.email.isEmpty())
            message = "Response Failed :  Email is required !\n"
        if (registerRequest.password.isEmpty())
            message = "Response Failed :  Password is required !\n"
        if (registerRequest.username.isEmpty())
            message = "Response Failed :  username is required !\n"
        return message
    }

    fun cryptPlainPassword(plainPassword:String):String{
        return BCrypt.hashpw(plainPassword , BCrypt.gensalt())
    }

    fun comparePassword(plainPassword: String, cryptPassword :String): Boolean {
        return BCrypt.checkpw(plainPassword,cryptPassword)
    }

