package com.example.fourWatches.util

import com.example.fourWatches.customer.RegisterRequestModel
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCrypt



class MyUtil {
    fun info(info: String) {
        var logger: org.slf4j.Logger? = LoggerFactory.getLogger(MyUtil::class.java)
        logger!!.info(info)
    }
}




    fun customerDetailsAreNotNull(registerRequestModel: RegisterRequestModel): Boolean {
        val array = arrayOf(registerRequestModel.email, registerRequestModel.username, registerRequestModel.password)
        array.forEach {
            if (it.isEmpty())
                return false
        }
        return true
    }

    fun getResponseFailedMessage(registerRequestModel: RegisterRequestModel): String {


       return when{
           registerRequestModel.email.isEmpty() -> "Email is required !\n"
           registerRequestModel.password.isEmpty() -> "Password is required !\n"
           registerRequestModel.username.isEmpty() -> "username is required !\n"
           else -> ""
       }
    }

    fun cryptPlainPassword(plainPassword:String):String{
        return BCrypt.hashpw(plainPassword , BCrypt.gensalt())
    }

    fun isPasswordMatch(plainPassword: String, cryptPassword :String): Boolean {
        return BCrypt.checkpw(plainPassword,cryptPassword)
    }

