package com.example.fourWatches.util

import com.example.fourWatches.customer.SignupRequestModel
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCrypt



class MyUtil {
    fun info(info: String) {
        var logger: org.slf4j.Logger? = LoggerFactory.getLogger(MyUtil::class.java)
        logger!!.info(info)
    }
}




    fun customerDetailsAreNotNull(signupRequestModel: SignupRequestModel): Boolean {
        val array = arrayOf(signupRequestModel.email, signupRequestModel.username, signupRequestModel.password)
        array.forEach {
            if (it.isEmpty())
                return false
        }
        return true
    }

    fun getResponseFailedMessage(signupRequestModel: SignupRequestModel): String {


       return when{
           signupRequestModel.email.isEmpty() -> "Email is required !\n"
           signupRequestModel.password.isEmpty() -> "Password is required !\n"
           signupRequestModel.username.isEmpty() -> "username is required !\n"
           else -> ""
       }
    }

    fun cryptPlainPassword(plainPassword:String):String{
        return BCrypt.hashpw(plainPassword , BCrypt.gensalt())
    }

    fun isPasswordMatch(plainPassword: String, cryptPassword :String): Boolean {
        return BCrypt.checkpw(plainPassword,cryptPassword)
    }

