package utils

import io.ktor.http.*

internal fun buildAuthorizationUrlQuery(
    googleClientId: String,
    port: Int
): String = Parameters.build {
    append("client_id", googleClientId)
    append("scope", "email profile")
    append("response_type", "code")
    append("redirect_uri", "http://127.0.0.1:$port")
    append("access_type", "offline")
}.formUrlEncode()

internal fun buildTokenValidationUrl(
    googleClientSecret: String,
    googleClientId: String,
    code: String,
    port: Int
) = Parameters.build {
    append("grant_type", "authorization_code")
    append("code", code)
    append("client_id", googleClientId)
    append("client_secret", googleClientSecret)
    append("redirect_uri", "http://127.0.0.1:$port")
}