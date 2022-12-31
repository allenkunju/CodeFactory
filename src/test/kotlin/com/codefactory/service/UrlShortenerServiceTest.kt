package com.codefactory.service

import com.codefactory.model.UrlMapping
import com.codefactory.repository.UrlShortenerRepository
import com.codefactory.service.impl.UrlShortenerServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class UrlShortenerServiceTest {
    private lateinit var urlShortenerRepository: UrlShortenerRepository

    private lateinit var urlShortenerService: UrlShortenerService

    @BeforeEach
    fun setup() {
        urlShortenerRepository = Mockito.mock(UrlShortenerRepository::class.java)
        urlShortenerService = UrlShortenerServiceImpl(urlShortenerRepository)
    }


    @Test
    fun getShortUrlIdentifier() {
        val longUrl = "http://test.com/this-is-a-test-url"
        val shortUrl = urlShortenerService.shortenUrl(longUrl)
        Assertions.assertFalse(shortUrl.contains(longUrl))
        Assertions.assertEquals(32, shortUrl.length)
    }

    @Test
    fun getOriginalUrl() {
        val longUrl = "http://test.com/this-is-a-test-url"
        val shortUrl = urlShortenerService.shortenUrl(longUrl)
        Mockito.`when`(urlShortenerRepository.findById(shortUrl)).thenReturn(Optional.of(UrlMapping(shortUrl, longUrl)))
        val resolvedUrl = urlShortenerService.resolveUrl(shortUrl)
        Assertions.assertEquals(longUrl, resolvedUrl)
    }
}