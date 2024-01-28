package com.cofion.features.create_account

import com.cofion.common.data.dtos.SentCodesTable
import com.cofion.common.data.dtos.UserDTO
import com.cofion.common.database.tables.UsersTable
import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.SimpleEmail
import java.time.LocalDate
import java.util.UUID
import kotlin.random.Random

class CreateAccountController {
    fun createUnconfirmedUser(accountInfo: CreateAccountPayload): Boolean {
        val user = UsersTable.getUserWithEmail(email = accountInfo.email)

        if (user == null) {
            UsersTable.saveUser(
                UserDTO(
                    id = UUID.randomUUID().toString(),
                    firstName = null,
                    lastName = null,
                    createAccountDate = LocalDate.now(),
                    email = accountInfo.email,
                    password = accountInfo.password,
                )
            )

            return true
        }

        return false
    }

    fun createCode(email: String): Boolean {
        val code = Random.nextInt(from = 100000, until = 1000000).toString()

        try {
            sendCode(email = email, code = code)
            SentCodesTable.setCode(email = email, code = code)

            return true
        } catch (e: Exception) {
            print("Error with code sending ${e.toString()}")

            return false
        }
    }

    private fun sendCode(email: String, code: String) {
        val emailSender = SimpleEmail()
        emailSender.hostName = "smtp.googlemail.com"
        emailSender.setSmtpPort(465)
        emailSender.setAuthenticator(DefaultAuthenticator("cofiontech@gmail.com", "zizr qblj uixm bnjo"))
        emailSender.isSSLOnConnect = true
        emailSender.setFrom("cofiontech@gmail.com")
        emailSender.subject = "Код подтверждения"
        emailSender.setMsg("Ваш код подтверждения: $code.")
        emailSender.addTo(email)
        emailSender.send()
    }
}