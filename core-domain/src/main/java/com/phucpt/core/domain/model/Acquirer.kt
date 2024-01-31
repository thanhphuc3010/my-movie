package com.phucpt.core.domain.model

import java.math.BigDecimal

/**
 * Created by Phucpt on 31/01/2024 at 22:53
 */

data class Acquirer(
    val mid: String = "",
    val tid: String = "",
    val currencyCode: String = "",
    val bankCode: String = "",
    val mcc: String = "",
    val mccServiceName: String = "",
    val maxAmountPerDay: BigDecimal = BigDecimal.ZERO,
    val maxAmountPerTransaction: BigDecimal = BigDecimal.ZERO,
    val maxAmount: BigDecimal = BigDecimal.ZERO,
    val minAmountPerTransaction: BigDecimal = BigDecimal.ZERO,
    val adjustment: Boolean = false,
    val balanceInquiry: Boolean = false,
    val cardVerification: Boolean = false,
    val moto: Boolean = false,
    val offline: Boolean = false,
    val preAuthorization: Boolean = false,
    val recurring: Boolean = false,
    val sale: Boolean = false,
    val void: Boolean = false
)