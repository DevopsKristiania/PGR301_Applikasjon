package no.kristiania.pgr301

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.RestAssured.port
import io.restassured.http.ContentType
import no.kristiania.pgr301.db.Dbinit
import no.kristiania.pgr301.repository.GeigertellerRepo
import org.hamcrest.CoreMatchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.validation.constraints.AssertTrue
import io.restassured.matcher.RestAssuredMatchers.*
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(Application::class)],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {

    @LocalServerPort var port = 0

    @Before
    fun initRestAssured() {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = port
    }

    @Autowired
    protected lateinit var repo: GeigertellerRepo

    @Autowired
    protected lateinit var myDbinit: Dbinit

    // ** REST Assured tester for å teste Rest API **

    // Test the size of data our database
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
