package auth

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import models.TokenInfo
import utils.GoogleOAuthTokenUrl

internal fun googleAuthHttpClient(
    bearerTokenStorage: MutableList<BearerTokens>,
    googleClientId: String
): HttpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json()
    }

    install(Auth) {
        bearer {
            loadTokens {
                bearerTokenStorage.last()
            }
            refreshTokens {
                val refreshTokenInfo: TokenInfo = client.submitForm(
                    url = GoogleOAuthTokenUrl,
                    formParameters = Parameters.build {
                        append("grant_type", "refresh_token")
                        append("client_id", googleClientId)
                        append("refresh_token", oldTokens?.refreshToken ?: "")
                    }
                ) { markAsRefreshTokenRequest() }.body()
                bearerTokenStorage.add(BearerTokens(refreshTokenInfo.accessToken, oldTokens?.refreshToken!!))
                bearerTokenStorage.last()
            }
            sendWithoutRequest { request ->
                request.url.host == "www.googleapis.com"
            }
        }
    }
}