package no.kristiania.pgr301

import no.kristiania.pgr301.db.Dbinit
import no.kristiania.pgr301.repository.GeigertellerRepo
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.validation.constraints.AssertTrue
import io.restassured.RestAssured.*
import io.restassured.matcher.RestAssuredMatchers.*
import org.hamcrest.Matchers.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [(Application::class)],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
    /*
    @LocalServerPort
    protected var port = 0

    @Autowired
    protected lateinit var repo: GeigertellerRepo

    @Autowired
    protected lateinit var myDbinit: Dbinit

    @Test
    fun initTest() {
        Assert.assertNotNull(repo)
    }
       */
    // Default test when creating a Spring application. Test if Spring loads from right configuration
    @Test
    fun contextLoads() {
    }


}
