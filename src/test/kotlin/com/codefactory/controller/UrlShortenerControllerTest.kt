package com.codefactory.controller

import io.restassured.RestAssured
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UrlShortenerControllerTest {

    @LocalServerPort
    private val port = 0

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = port
    }

    @Test
    fun shortenUrl() {
    }

    @Test
    fun resolveUrl() {
    }
}