package com.phucpt.core.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Phucpt on 31/01/2024 at 21:25
 */

data class OperationPermissionDto(
    @SerializedName("Adjustment")
    val adjustment: String,
    @SerializedName("BalanceInquiry")
    val balanceInquiry: String,
    @SerializedName("Card Verification")
    val cardVerification: String,
    @SerializedName("MOTO")
    val moto: String,
    @SerializedName("Offline")
    val offline: String,
    @SerializedName("PreAuthorization")
    val preAuthorization: String,
    @SerializedName("Recurring")
    val recurring: String,
    @SerializedName("Sale")
    val sale: String,
    @SerializedName("Void")
    val void: String
)

fun String.toBooleanFromYesNo(): Boolean {
    return this.equals("Yes", ignoreCase = true)
}
