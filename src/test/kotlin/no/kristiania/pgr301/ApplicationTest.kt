package no.kristiania.pgr301

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.RestAssured.port
import io.restassured.http.ContentType
import no.kristiania.pgr301.db.Dbinit
import no.kristiania.pgr301.repository.GeigertellerRepo
import org.hamcrest.CoreMatchers
import org.junit.Assert
//import org.junit.Test
/*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith

 */

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.validation.constraints.AssertTrue
import io.restassured.matcher.RestAssuredMatchers.*
import org.hamcrest.Matchers.*
import org.junit.BeforeClass
import org.junit.Test
import java.awt.print.Book

//@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [(Application::class)],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {


   // @BeforeEach
    //@AfterEach
    fun initRestAssured() {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = 8080
    }

    @LocalServerPort protected var port = 8080

    @Autowired
    protected lateinit var repo: GeigertellerRepo

    @Autowired
    protected lateinit var myDbinit: Dbinit

    // NOTE start Application.kt før du kjører disse testene lokalt. Ikke noe problem med Travis, da den starter applikasjonen
    @Test
    fun testSizeOfDb() {
        given().get("/devices").then()
                .statusCode(200)
                .body("size()", CoreMatchers.equalTo(3))
    }


    // Default test when creating a Spring application. Test if Spring loads from right configuration
    @Test
    fun contextLoads() {
    }



}
