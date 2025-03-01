fun main() {

    println(calcCommission(typeCard = "Mastercard", amountTransfer = 70_000.0, 10_000.0))
}

val limitDay = 150_000
val limitMonth = 600_000

fun calcCommission(typeCard: String = "Мир", amountTransfer: Double = 0.0, sumTransfer: Double): String {
    when (typeCard) {
        "Mastercard" -> return calcCommissionMaster(amountTransfer, sumTransfer)
        "Visa" -> return calcCommiccionVisa(amountTransfer, sumTransfer)
        "Мир" -> return calcCommissionMir(amountTransfer, sumTransfer)
        else -> return "Тип карты, который вы указали, его нет! Повторите попытку заново!"
    }

}

fun calcCommissionMaster(amountTransfer: Double, sumTransfer: Double): String {
    var noCommissionLimit = 75_000.0

    return when {
        amountTransfer + sumTransfer > limitMonth -> {
            "Опеация невозможна, превышен месячный лимит в размере $limitMonth на: " + (amountTransfer + sumTransfer - limitMonth)
        }

        sumTransfer > limitDay -> {
            "Операция невозможна, превышен суточный лимит на операцию в размере $limitDay на: " + (sumTransfer - limitDay)
        }

        else -> {
            var commission = when {
                noCommissionLimit <= amountTransfer -> {
                    sumTransfer * 0.006 + 20
                }

                noCommissionLimit <= sumTransfer + amountTransfer -> {
                    ((sumTransfer + amountTransfer) - noCommissionLimit) * 0.006 + 20
                }

                else -> 0.0
            }
            //var commission = ((sumTransfer - noCommissionLimit) * 0.006 + 20)
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