package ir.reza_mahmoudi.newsgram.core.domain.news_items.entity

import com.squareup.moshi.Json

data class Source(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null
)
