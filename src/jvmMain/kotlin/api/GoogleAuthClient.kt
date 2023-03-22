package api

import auth.GoogleAuthServer
import auth.googleAuthHttpClient
import io.ktor.client.call.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.request.forms.*
import models.TokenInfo
import utils.*
import utils.GoogleOAuthAuthUrl
import utils.GoogleOAuthTokenUrl
import utils.buildAuthorizationUrlQuery
import utils.buildTokenValidationUrl
import java.io.File

class GoogleAuthClient(
    private val googleClientId: String,
    private val googleClientSecret: String,
    private val port: Int = 7878
) {
    private val authorizationUrlQuery = buildAuthorizationUrlQuery(googleClientId, port)
    private val bearerTokenStorage = mutableListOf<BearerTokens>()
    private val client = googleAuthHttpClient(bearerTokenStorage, googleClientId)

    val authorizationUrl: String = "$GoogleOAuthAuthUrl?$authorizationUrlQuery"

    suspend fun waitForOAuthIdToken(
        successPagePath: String = getResource("/success.html") ?: ""
    ): String {
        val server = GoogleAuthServer(port, File(successPagePath))
        val code = server.startServerForCode()
        val tokenInfo: TokenInfo = client.submitForm(
            url = GoogleOAuthTokenUrl,
            formParameters = buildTokenValidationUrl(googleClientSecret, googleClientId, code, port)
        ).body()
        return tokenInfo.idToken
    }
}