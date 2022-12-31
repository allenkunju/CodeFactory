package com.codefactory.exception

open class UrlShortenerServiceRestException(override var message : String) : RuntimeException()

class UrlNotFoundException : UrlShortenerServiceRestException("The requested url cannot be found!")