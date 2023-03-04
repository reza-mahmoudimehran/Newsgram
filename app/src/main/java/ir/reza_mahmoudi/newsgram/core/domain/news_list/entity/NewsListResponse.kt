package ir.reza_mahmoudi.newsgram.core.domain.news_list.entity

import com.squareup.moshi.Json
import ir.reza_mahmoudi.newsgram.core.domain.news_items.entity.Article

data class NewsListResponse(
    @Json(name = "status")
    val status: String? = null,
    @Json(name = "totalResults")
    val totalResults: Int? = null,
    @Json(name = "articles")
    val articles: List<Article>
)