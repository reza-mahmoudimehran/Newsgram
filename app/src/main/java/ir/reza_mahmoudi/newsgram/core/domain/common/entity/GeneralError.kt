package ir.reza_mahmoudi.newsgram.core.domain.common.entity

import com.squareup.moshi.Json

data class GeneralError(
    @Json(name = "status")
    val status: String,
    @Json(name = "code")
    val code: String?,
    @Json(name = "message")
    val message: String?
)