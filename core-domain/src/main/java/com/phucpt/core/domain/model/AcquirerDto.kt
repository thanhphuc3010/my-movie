package com.phucpt.core.domain.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

/**
 * Created by Phucpt on 31/01/2024 at 21:16
 */

data class AcquirerDto(
    @SerializedName("AcquirerID")
    val mid: String,
    @SerializedName("TerminalID")
    val tid: String,
    @SerializedName("TransactionCurrencyCode")
    val currencyCode: String,
    @SerializedName("BankCode")
    val bankCode: String,
    @SerializedName("MCC")
    val mcc: String,
    @SerializedName("MCCServiceName")
    val mccServiceName: String,
    val maxAmountPerDay: BigDecimal? = BigDecimal.ZERO,
    val maxAmountPerTransaction: BigDecimal? = BigDecimal.ZERO,
    val maxAmount: BigDecimal? = BigDecimal.ZERO,
    val minAmountPerTransaction: BigDecimal? = BigDecimal.ZERO,
    @SerializedName("Operation Permissions")
    val operationPermissionsDto: OperationPermissionDto? = null
) {
    fun toModel(): Acquirer {
        return Acquirer(
            mid,
            tid,
            currencyCode,
            bankCode,
            mcc,
            mccServiceName,
            maxAmountPerDay ?: BigDecimal.ZERO,
            maxAmountPerTransaction ?: BigDecimal.ZERO,
            maxAmount ?: BigDecimal.ZERO,
            minAmountPerTransaction ?: BigDecimal.ZERO
        ).let {
            val model = operationPermissionsDto?.let { permission ->
                it.copy(
                    adjustment = permission.adjustment.toBooleanFromYesNo(),
                    balanceInquiry = permission.balanceInquiry.toBooleanFromYesNo(),
                    cardVerification = permission.cardVerification.toBooleanFromYesNo(),
                    moto = permission.moto.toBooleanFromYesNo(),
                    offline = permission.offline.toBooleanFromYesNo(),
                    preAuthorization = permission.preAuthorization.toBooleanFromYesNo(),
                    recurring = permission.recurring.toBooleanFromYesNo(),
                    sale = permission.sale.toBooleanFromYesNo(),
                    void = permission.void.toBooleanFromYesNo()
                )
            } ?: it
            model
        }
    }
}
