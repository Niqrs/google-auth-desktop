package models

import kotlinx.serialization.*

@Serializable
internal data class ErrorInfo(val error: ErrorDetails)

@Serializable
internal data class ErrorDetails(
    val code: Int,
    val message: String,
    val status: String,
)
