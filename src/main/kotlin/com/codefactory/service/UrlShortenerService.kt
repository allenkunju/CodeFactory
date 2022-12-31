package com.codefactory.service

interface UrlShortenerService {
    fun shortenUrl(longUrl: String): String

    fun resolveUrl(shortUrl: String): String
}
