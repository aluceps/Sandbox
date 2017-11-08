package me.aluceps.sandbox.model

import com.google.gson.annotations.SerializedName

data class ConnpassEvent(
        @SerializedName("results_returned")
        val resultsReturned: Int = 0,
        val events: List<Event>? = listOf(),
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
            val ownerNickname: String? = "",
            val series: Series? = Series(),
            @SerializedName("updated_at")
            val updatedAt: String? = "",
            val lat: String? = "",
            @SerializedName("started_at")
            val startedAt: String? = "",
            @SerializedName("hash_tag")
            val hashTag: String? = "",
            val title: String? = "",
            @SerializedName("event_id")
            val eventId: Int = 0,
            val lon: String? = "",
            val waiting: Int = 0,
            val limit: Int = 0,
            @SerializedName("owner_id")
            val ownerId: Int = 0,
            @SerializedName("owner_display_name")
            val ownerDisplayName: String? = "",
            val description: String? = "",
            val address: String? = "",
            @SerializedName("catch")
            val catchcopy: String? = "",
            val accepted: Int = 0,
            @SerializedName("ended_at")
            val endedAt: String? = "",
            val place: String? = ""
    )

    data class Series(
            val url: String? = "",
            val id: Int = 0,
            val title: String? = ""
    )
}
