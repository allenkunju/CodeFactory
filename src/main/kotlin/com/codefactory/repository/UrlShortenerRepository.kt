package com.codefactory.repository

import com.codefactory.model.UrlMapping
import java.util.*

/**
 * the repository should be implemented based on the persistence layer
 */
interface UrlShortenerRepository {
    fun save(urlMapping: UrlMapping)

    fun findById(shortUrl: String): Optional<UrlMapping>
}
