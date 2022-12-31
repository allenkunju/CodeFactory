package com.codefactory.service.impl

import com.codefactory.service.UrlShortenerService
import org.springframework.stereotype.Service

@Service
class UrlShortenerServiceImpl(): UrlShortenerService {
    override fun shortenUrl(longUrl: String): String {
        TODO("Not yet implemented")
    }

    override fun resolveUrl(shortUrl: String): String {
        TODO("Not yet implemented")
    }
}