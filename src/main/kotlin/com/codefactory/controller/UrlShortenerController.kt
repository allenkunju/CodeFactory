package com.codefactory.controller

import com.codefactory.service.UrlShortenerService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("v1/api/short-url")
@Validated
open class UrlShortenerController(private val urlShortenerService: UrlShortenerService) {

    @PostMapping(value = [""], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    open fun shortenUrl(@RequestBody @NotBlank longUrl: String): String {
        return urlShortenerService.shortenUrl(longUrl)
    }

    @GetMapping(value = ["/{short-url}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.OK)
    open fun resolveUrl(@PathVariable("short-url") shortUrl: String): String {
        return urlShortenerService.resolveUrl(shortUrl)
    }
}