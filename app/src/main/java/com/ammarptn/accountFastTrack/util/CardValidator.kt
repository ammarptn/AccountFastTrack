package com.ammarptn.accountFastTrack.util

object CardValidator {
    private val VISA_PATTERN = Regex("^4[0-9]{12}(?:[0-9]{3})?$")
    private val MASTERCARD_PATTERN = Regex("^5[1-5][0-9]{14}$")
    private val AMEX_PATTERN = Regex("^3[47][0-9]{13}$")
    private val JCB_PATTERN = Regex("^(?:2131|1800|35\\d{3})\\d{11}$")

    fun getCardType(number: String): CardType {
        val cleanNumber = number.replace("\\s".toRegex(), "")
        return when {
            cleanNumber.matches(VISA_PATTERN) -> CardType.VISA
            cleanNumber.matches(MASTERCARD_PATTERN) -> CardType.MASTERCARD
            cleanNumber.matches(AMEX_PATTERN) -> CardType.AMEX
            cleanNumber.matches(JCB_PATTERN) -> CardType.JCB
            else -> CardType.UNKNOWN
        }
    }

    fun isValidCardNumber(number: String): Boolean {
        val cleanNumber = number.replace("\\s".toRegex(), "")
        if (!cleanNumber.all { it.isDigit() }) return false

        return when (getCardType(cleanNumber)) {
            CardType.VISA -> cleanNumber.length in listOf(13, 16)
            CardType.MASTERCARD -> cleanNumber.length == 16
            CardType.AMEX -> cleanNumber.length == 15
            CardType.JCB -> cleanNumber.length == 16
            CardType.UNKNOWN -> cleanNumber.length in 13..19
        } && isValidLuhn(cleanNumber)
    }

    private fun isValidLuhn(number: String): Boolean {
        val digits = number.map { it.toString().toInt() }
        val checksum = digits.reversed()
            .mapIndexed { index, digit ->
                if (index % 2 == 1) {
                    val doubled = digit * 2
                    if (doubled > 9) doubled - 9 else doubled
                } else digit
            }.sum()
        return checksum % 10 == 0
    }

    fun formatCardNumber(number: String): String {
        val cleaned = number.replace("\\s".toRegex(), "")
        return when (getCardType(cleaned)) {
            CardType.AMEX -> cleaned.chunked(4).joinToString(" ")
            else -> cleaned.chunked(4).joinToString(" ")
        }
    }
}

enum class CardType {
    VISA, MASTERCARD, AMEX, JCB, UNKNOWN
}