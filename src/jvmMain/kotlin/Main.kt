import api.GoogleAuthClient
import kotlinx.coroutines.runBlocking

private fun main() {
    runBlocking {
        val client = GoogleAuthClient(GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET)
        println(client.authorizationUrl)
        val res = client.waitForOAuthIdToken()
        println(res)
    }
}

private const val GOOGLE_CLIENT_ID = "GOOGLE_CLIENT_ID"
private const val GOOGLE_CLIENT_SECRET = "GOOGLE_CLIENT_SECRET"