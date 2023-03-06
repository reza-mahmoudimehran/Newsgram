package ir.reza_mahmoudi.newsgram.core.domain.news_items.entity

import com.squareup.moshi.Json

data class Article(
    @Json(name = "publishedAt")
    val publishedAt: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "urlToImage")
    val urlToImage: String? = null,
    @Json(name = "source")
    val source: Source? = null
)
