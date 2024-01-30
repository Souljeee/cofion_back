package com.cofion.features.create_account

import com.cofion.common.database.tables.SentCodesTable
import com.cofion.common.data.dtos.UserDTO
import com.cofion.common.database.tables.ClientInfoTable
import com.cofion.common.database.tables.CoachInfoTable
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
                    confirmed = false,
                    accountType = accountInfo.accountType,
                )
            )

            val infoId = if (accountInfo.accountType == "coach")
                CoachInfoTable.createCoachAccount()
            else
                ClientInfoTable.createClientAccount()

            UsersTable.markAccountWithType(
                email = accountInfo.email,
                accountType = accountInfo.accountType,
                infoId = infoId
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
            print("Error with code sending $e")

            return false
        }
    }

    fun confirmCode(email: String, code: String): UserConfirmationStatus {
        val existingCode = SentCodesTable.getCodeByEmail(email = email)

        if (existingCode != null) {
            if (existingCode == code) {
                SentCodesTable.removeCode(email = email)
                UsersTable.confirmUserWithEmail(email = email)

                return UserConfirmationStatus.CONFIRMED
            }

            return UserConfirmationStatus.WRONG_CODE
        }

        return UserConfirmationStatus.NONEXISTENT_USER
    }

    private fun sendCode(email: String, code: String) {
        val emailSender = SimpleEmail()
        emailSender.hostName = System.getenv("EMAIL_SENDER_HOSTNAME")
        emailSender.setSmtpPort(465)
        emailSender.setAuthenticator(
            DefaultAuthenticator(
                System.getenv("EMAIL_SENDER_USER"),
                System.getenv("EMAIL_SENDER_PASSWORD")
            )
        )
        emailSender.isSSLOnConnect = true
        emailSender.setFrom(System.getenv("EMAIL_SENDER_USER"))
        emailSender.subject = "Код подтверждения"
        emailSender.setMsg("Ваш код подтверждения: $code.")
        emailSender.addTo(email)
        emailSender.send()
    }
}

enum class UserConfirmationStatus {
    CONFIRMED, WRONG_CODE, NONEXISTENT_USER
}