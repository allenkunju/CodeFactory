package com.codefactory.service.impl

import com.codefactory.exception.UrlNotFoundException
import com.codefactory.model.UrlMapping
import com.codefactory.repository.UrlShortenerRepository
import com.codefactory.service.UrlShortenerService
import org.springframework.stereotype.Service
import java.util.*

@Service
open class UrlShortenerServiceImpl(private val urlShortenerRepository: UrlShortenerRepository): UrlShortenerService {
    override fun shortenUrl(longUrl: String): String {
        return generateShortUrl(longUrl)
            .also { shortUrl ->
                urlShortenerRepository.save(UrlMapping(shortUrl, longUrl))
            }
    }

    override fun resolveUrl(shortUrl: String): String {
        return urlShortenerRepository.findById(shortUrl)
            .orElseThrow { UrlNotFoundException() }
            .longUrl
    }

    private fun generateShortUrl(longUrl: String): String {
        // Generate a unique short URL using a hash function on the input longUrl or a random number generator
        // for simplicity, we use random number generator
        return UUID.randomUUID().toString().replace("-", "")
    }
}