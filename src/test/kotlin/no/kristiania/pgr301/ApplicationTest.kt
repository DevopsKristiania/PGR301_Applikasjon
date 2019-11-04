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
import no.kristiania.pgr301.dto.GeigertellerDto
import no.kristiania.pgr301.dto.MeasurementDto
import no.kristiania.pgr301.entity.GeigertellerEntity
import no.kristiania.pgr301.entity.MeasurementEntity
import no.kristiania.pgr301.repository.MeasurementRepo
import org.hamcrest.Matchers.*
import org.junit.Assert
import org.junit.Before
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
        RestAssured.basePath = "/devices"

        repo.run {
            deleteAll()
            save(GeigertellerEntity(name = "X-1000", price = "7000",deviceId = "K076"))
            save(GeigertellerEntity(name = "V-9000", price = "5000",deviceId = "B5f39"))
            save(GeigertellerEntity(name = "V-4300", price = "2000",deviceId = "A5839"))
        }
        measurementRepo.run {
            deleteAll()
            save(MeasurementEntity(Sievert = "1000", Lng = "0.810", Lat = "21.3870 N"))
            save(MeasurementEntity(Sievert = "60", Lng = "0.920", Lat = "31.3870 N"))
            save(MeasurementEntity(Sievert = "30", Lng = "0.220", Lat = "51.3870 N"))
        }
    }

    @Autowired
    protected lateinit var repo: GeigertellerRepo

    @Autowired
    protected lateinit var measurementRepo: MeasurementRepo

    @Autowired
    protected lateinit var myDbinit: Dbinit

    // ** REST Assured tests for testing REST API**


    // Test GET all geigerteller
    @Test
    fun testGetAllGeigerteller() {
        given().get().then()
                .statusCode(200)
                .body("size()", CoreMatchers.equalTo(3))
    }

    // Test creating a new geigerteller with POST
   @Test
   fun testCreateNewGeigerteller() {
        val id = "4"
        val name = "L-9090"
        val price = "1000"
        val deviceId = "AF467"
        val createDto = GeigertellerDto(name, price, id, deviceId)

        given().get().then().statusCode(200).body("size()", CoreMatchers.equalTo(3))


        given().contentType(ContentType.JSON)
                .body(createDto)
                .post()
                .then()
                .statusCode(201) // 201 = Created
                .extract().asString()
        // Should now be 4 entries in DB after creation
        given().get().then().statusCode(200).body("size()", CoreMatchers.equalTo(4))
    }


    // Test GET measurments for devices
    @Test
    fun testGetById(){
        val measurements = given().accept(ContentType.JSON)
                .get("/measurements")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList("", MeasurementDto::class.java)

        for (i in measurements) {
            given().accept(ContentType.JSON)
                    .get("/${i.id}/measurements")
                    .then()
                    .statusCode(200)
        }
    }


    // Default test when creating a Spring application. Test if Spring loads from right configuration
    @Test
    fun contextLoads() {
    }

}
