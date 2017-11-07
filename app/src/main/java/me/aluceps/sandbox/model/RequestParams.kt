package me.aluceps.sandbox.model

data class RequestParams(
        var total: Int = 0,
        var offset: Int = 0,
        var limit: Int = 0
) {
    companion object {
        const val LIMIT = 10
    }
}
