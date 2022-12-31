package com.codefactory.controller

import io.restassured.RestAssured
import org.apache.http.entity.ContentType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UrlShortenerControllerTest {

    @LocalServerPort
    private val port = 0

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = port
    }

    @Test
    fun shortenUrlWithValidInput() {
        val longUrl = "http://test.com/this-is-a-test-url"

        val response = RestAssured.given().log().all()
            .contentType(ContentType.APPLICATION_JSON.toString())
            .accept(ContentType.APPLICATION_JSON.toString())
            .body(longUrl)
            .`when`()
            .post("/v1/api/short-url")
            .then()
            .statusCode(200)
            .extract().response()

        val shortUrl = response.body().asString()
        assertFalse(shortUrl.contains(longUrl))

        val resolvedUrl = RestAssured.given().log().all()
            .contentType(ContentType.APPLICATION_JSON.toString())
            .accept(ContentType.APPLICATION_JSON.toString())
            .`when`()["/v1/api/short-url/{short-url}", shortUrl]
            .then()
            .statusCode(200)
            .extract().response()

        assertEquals(longUrl, resolvedUrl.body().asString())
    }

    @Test
    fun shortenUrlWithEmptyBodyReturns400() {
        val longUrl = ""
        RestAssured.given().log().all()
            .contentType(ContentType.APPLICATION_JSON.toString())
            .accept(ContentType.APPLICATION_JSON.toString())
            .body(longUrl)
            .`when`()
            .post("/v1/api/short-url")
            .then()
            .statusCode(400)
            .extract().response()
    }

    @Test
    fun resolveInvalidUrlReturns404() {
        val shortUrl = UUID.randomUUID().toString()
        val resolvedUrl = RestAssured.given().log().all()
            .contentType(ContentType.APPLICATION_JSON.toString())
            .accept(ContentType.APPLICATION_JSON.toString())
            .`when`()["/v1/api/short-url/{short-url}", shortUrl]
            .then()
            .statusCode(404)
            .extract().response()
        assertEquals("The requested url cannot be found!", resolvedUrl.body().asString())
    }
}