

# google-auth-desktop

Google Auth Desktop is a Kotlin Multiplatform library for authenticating Google users on desktop devices. This library provides a simple way to obtain a Google OAuth IdToken for a user.

## Installation

To use Google Auth Desktop, add the following line to your `build.gradle.kts` file:

```kotlin
implementation("io.github.niqrs:google-auth-desktop:1.1")
```

## Usage

Here is an example of how to use Google Auth Desktop:

```kotlin
private fun main() {
    runBlocking {
        val client = GoogleAuthClient(GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET)
        println(client.authorizationUrl)
        val res = client.waitForOAuthIdToken()
        println(res)
    }
}
```

This code will authenticate the user with their Google account and retrieve an OAuth IdToken.

## Contributing

Contributions to Google Auth Desktop are welcome! Please feel free to submit issues or pull requests.

## License

Google Auth Desktop is licensed under the [MIT License](https://github.com/Niqrs/google-auth-desktop/blob/master/LICENSE.md).
