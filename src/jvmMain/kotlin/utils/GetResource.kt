package utils

internal fun Any.getResource(path: String) = javaClass.getResource(path)?.file