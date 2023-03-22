package auth

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay
import java.io.File

internal class GoogleAuthServer(
    port: Int,
    successFile: File
) {
    private var code: String? = null
    private val server = embeddedServer(Netty, port) {
        routing {
            get("/") {
                code = call.request.queryParameters["code"]
                call.respondFile(successFile)
            }
        }
    }

    suspend fun startServerForCode(): String {
        code = null
        server.start()
        while (code == null) {
            delay(500)
        }
        server.stop()
        return code!!
    }
}