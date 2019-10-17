package no.kristiania.pgr301

import io.restassured.RestAssured
import io.restassured.http.ContentType
import no.kristiania.pgr301.db.Dbinit
import no.kristiania.pgr301.repository.GeigertellerRepo
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [(Application::class)],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @LocalServerPort
    protected var port = 0

    @Autowired
    protected lateinit var repo: GeigertellerRepo

    @Autowired
    protected lateinit var myDbinit: Dbinit

    @BeforeEach
    @AfterEach
    fun clean() {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = port
        RestAssured.basePath = "/devices"
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()

        myDbinit.initGeigertellerAtStartup()
    }

    @Test
    fun testGetAll() {

        RestAssured.given().accept(ContentType.JSON)
                .get()
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.equalTo(3))
    }

    // Default test when creating a Spring application. Test if Spring loads from right configuration
    @Test
    fun contextLoads() {
    }


}
