package com.ammarptn.accountFastTrack.presentation.card


fun isValidCardNumber(number: String): Boolean {
    return number.length in 13..16 && number.all { it.isDigit() }
}

fun isValidExpiryDate(date: String): Boolean {
    if (!date.matches(Regex("""^\d{2}/\d{2}$"""))) return false

    val (month, year) = date.split("/").map { it.toInt() }
    if (month !in 1..12) return false

    val currentYear = java.time.LocalDate.now().year % 100
    val currentMonth = java.time.LocalDate.now().monthValue

    return when {
        year < currentYear -> false
        year == currentYear && month < currentMonth -> false
        else -> true
    }
}

fun isValidCvv(cvv: String): Boolean {
    return cvv.length in 3..4 && cvv.all { it.isDigit() }
}

fun formatExpiryDate(input: String): String {
    val digits = input.filter { it.isDigit() }
    return when {
        digits.length <= 2 -> digits
        else -> "${digits.take(2)}/${digits.drop(2)}"
    }
}
