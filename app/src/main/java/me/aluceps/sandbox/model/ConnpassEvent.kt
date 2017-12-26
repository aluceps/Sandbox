package me.aluceps.sandbox.model

import com.google.gson.annotations.SerializedName

data class ConnpassEvent(
        @SerializedName("results_returned")
        val resultsReturned: Int = 0,
        val events: List<Event>? = null,
        @SerializedName("results_start")
        val resultsStart: Int = 0,
        @SerializedName("results_available")
        val resultsAvailable: Int = 0
) {

    data class Event(
            @SerializedName("event_url")
            val eventUrl: String? = "",
            @SerializedName("event_type")
            val eventType: String? = "",
            @SerializedName("owner_nickname")
            val ownerNickname: String?,
            val series: Series? = null,
            @SerializedName("updated_at")
            val updatedAt: String?,
            val lat: String? = "",
            @SerializedName("started_at")
            val startedAt: String?,
            @SerializedName("hash_tag")
            val hashTag: String? = null,
            val title: String? = null,
            @SerializedName("event_id")
            val eventId: Int = 0,
            val lon: String? = null,
            val waiting: Int = 0,
            val limit: Int = 0,
            @SerializedName("owner_id")
            val ownerId: Int = 0,
            @SerializedName("owner_display_name")
            val ownerDisplayName: String? = null,
            val description: String? = null,
            val address: String? = null,
            @SerializedName("catch")
            val catchcopy: String? = null,
            val accepted: Int = 0,
            @SerializedName("ended_at")
            val endedAt: String? = null,
            val place: String? = null
    )

    data class Series(
            val url: String? = null,
            val id: Int = 0,
            val title: String? = null
    )
}
