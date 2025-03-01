fun main() {

    println(calcCommission("Mastercard", 70_000.0, 10_000.0))
}

val limitDay = 150_000
val limitMonth = 600_000

fun calcCommission(typeCard: String, amountTransfer: Double, sumTransfer: Double): String {
    when (typeCard) {
        "Mastercard" -> return calcCommissionMaster(amountTransfer, sumTransfer)
        "Visa" -> return calcCommiccionVisa(amountTransfer, sumTransfer)
        "Мир" -> return calcCommissionMir(amountTransfer, sumTransfer)
        else -> return "Тип карты, который вы указали, его нет! Повторите попытку заново!"
    }

}

fun calcCommissionMaster(amountTransfer: Double, sumTransfer: Double): String {
    var limitInMonth = 75_000.0
    return when {
        amountTransfer + sumTransfer > limitMonth -> {
            "Опеация невозможна, превышен месячный лимит в размере $limitMonth на: " + (amountTransfer + sumTransfer - limitMonth)
        }

        sumTransfer > limitDay -> {
            "Операция невозможна, превышен суточный лимит на операцию в размере $limitDay на: " + (sumTransfer - limitDay)
        }

        amountTransfer + sumTransfer <= limitMonth && sumTransfer <= limitDay && limitInMonth - sumTransfer >= 0 && (limitInMonth - sumTransfer >= 0 && limitInMonth > sumTransfer + amountTransfer) -> {
            limitInMonth -= sumTransfer
            "Перевод в размере $sumTransfer осуществлен."
        }

        else -> {
            limitInMonth -= amountTransfer
            if (limitInMonth < 0) {
                limitInMonth = 0.0
            }
            var commission = ((sumTransfer - limitInMonth) * 0.006 + 20)
            "Перевод в размере $sumTransfer осуществлен успешно.\n" +
                    "Комиссия за перевод составила: $commission."
        }
    }
}

fun calcCommiccionVisa(amountTransfer: Double, sumTransfer: Double): String {
    var minCommission = 35
    var commission = if (sumTransfer * 0.0075 > minCommission) (sumTransfer * 0.0075) else 35
    return when {
        amountTransfer + sumTransfer > limitMonth -> {
            "Опеация невозможна, превышен месячный лимит в размере $limitMonth на: " + (amountTransfer + sumTransfer - limitMonth)
        }

        sumTransfer > limitDay -> {
            "Операция невозможна, превышен суточный лимит на операцию в размере $limitDay на: " + (sumTransfer - limitMonth)
        }

        else -> {
            "Перевод в размере $sumTransfer осуществлен успешно.\n" +
                    "Комиссия за перевод составила: $commission."
        }
    }
}

fun calcCommissionMir(amountTransfer: Double, sumTransfer: Double): String {
    return when {
        amountTransfer + sumTransfer > limitMonth -> {
            "Опеация невозможна, превышен месячный лимит в размере $limitMonth на: " + (amountTransfer + sumTransfer - limitMonth)
        }

        sumTransfer > limitDay -> {
            "Операция невозможна, превышен суточный лимит на операцию в размере $limitDay на: " + (sumTransfer - limitMonth)
        }

        else -> {
            "Перевод в размере: $sumTransfer осуществлен успешно"
        }
    }
}