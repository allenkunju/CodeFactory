package com.codefactory.cache

import com.codefactory.model.UrlMapping
import com.codefactory.repository.UrlShortenerRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
open class UrlShortenerCache : UrlShortenerRepository {

    var urlMappingCache: MutableMap<String, UrlMapping> = HashMap<String, UrlMapping>()

    override fun save(urlMapping: UrlMapping) {
        urlMappingCache[urlMapping.shortUrl] = urlMapping
    }

    override fun findById(shortUrl: String): Optional<UrlMapping> {
        return Optional.ofNullable<UrlMapping>(urlMappingCache[shortUrl])
    }
}