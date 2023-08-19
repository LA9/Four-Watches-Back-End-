package com.example.fourWatches.util

import com.example.fourWatches.customer.RegisterRequestModel
import org.springframework.security.crypto.bcrypt.BCrypt




//  fun info(info: String) {
//      var logger: Logger = LoggerFactory.getLogger(MyUtil::class.java)
//        logger.info(info)
//    }

    fun customerDetailsAreNotNull(registerRequestModel: RegisterRequestModel): Boolean {
        val array = arrayOf(registerRequestModel.email, registerRequestModel.username, registerRequestModel.password)
        array.forEach {
            if (it.isEmpty())
                return false
        }
        return true
    }

    fun getResponseFailedMessage(registerRequestModel: RegisterRequestModel): String {
        var message = ""
        if (registerRequestModel.email.isEmpty())
            message = "Response Failed :  Email is required !\n"
        if (registerRequestModel.password.isEmpty())
            message = "Response Failed :  Password is required !\n"
        if (registerRequestModel.username.isEmpty())
            message = "Response Failed :  username is required !\n"
        return message
    }

    fun cryptPlainPassword(plainPassword:String):String{
        return BCrypt.hashpw(plainPassword , BCrypt.gensalt())
    }

    fun comparePassword(plainPassword: String, cryptPassword :String): Boolean {
        return BCrypt.checkpw(plainPassword,cryptPassword)
    }

