package no.kristiania.pgr301.controller

import no.kristiania.pgr301.db.DbService
import no.kristiania.pgr301.dto.GeigerDtoConverter
import no.kristiania.pgr301.dto.GeigertellerDto
import no.kristiania.pgr301.dto.MeasurementDto
import no.kristiania.pgr301.entity.GeigertellerEntity
import no.kristiania.pgr301.entity.MeasurementEntity
import no.kristiania.pgr301.repository.GeigertellerRepo
import no.kristiania.pgr301.repository.MeasurementRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping


// Dont forget to change github user when comitting to github

//@RequestMapping(path = ["/devices"])
@RestController
@RequestMapping(path = ["/devices"])
class GeigertellerController(
        val repo: GeigertellerRepo,
        val measurementRepo: MeasurementRepo
) {

    //1.REST Endepunkt for ny geigerteller. Svare på POST til /devices og returnere et objekt med en unik identifikator "deviceId"
    /*
        INSERT INTO geigerteller VALUES (7,'af467', 'XYZ-1279', 9000);
        You can now see the new device at http://localhost:8080/devices
     */
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createGeigerteller(@RequestBody dto: GeigertellerDto) : ResponseEntity<Long> {
        val newGeigertellerEntity = GeigertellerEntity(dto.name!!,dto.price!!)
        repo.save(newGeigertellerEntity)

        return ResponseEntity.created(URI.create("/devices" + newGeigertellerEntity.deviceId)).build()
    }
    // GET by providing the device id IKKE RØR
    // http://localhost:8080/devices/K076
    @GetMapping(path = ["/{deviceId}"])
    fun getDeviceByDeviceId(
            @PathVariable("deviceId")
            pathId: String?)
            : ResponseEntity<List<GeigertellerDto>> {

        val id: String
        try {
            id = pathId!!.toString()
        } catch (e: Exception) {
            return ResponseEntity.status(404).build()
        }

        val entity = repo.findByDeviceId(id)

        return ResponseEntity.ok(GeigerDtoConverter.transform(entity))
    }


    //2.REST Endepunkt for ny måling av stråling og lokasjon (lat, lng, sievert). POST /devices/{deviceId}/measurements
    // INSERT INTO measurement VALUES (7,'af467', 'XYZ-1279', 9000);
    // her brukes id og ikke deviceId !!!
    // http://localhost:8080/devices/1/measurements
    @PostMapping(path = ["/{deviceId}/measurements"])
    fun createNewMeasurement(@PathVariable("deviceId") pathId: String?,
                             @RequestBody dto: MeasurementDto): ResponseEntity<List<MeasurementDto>> {
        val newMeasurementEntity = MeasurementEntity(dto.Sievert!!,dto.Lng!!,dto.Lat!!)
        measurementRepo.save(newMeasurementEntity)

        return ResponseEntity.created(URI.create("/devices" + newMeasurementEntity.id)).build()
    }

    /* 3. REST Endepunkt for å hente en eller flere målinger for en device. GET til /devices/{id}/measurements
        bruk measurmentsdto for å returnere den ved id - DONE for now
        http://localhost:8080/devices/1/measurements
     */

    @GetMapping(path = ["/{id}/measurements"])
    fun getById(
            @PathVariable("id")
            pathId: String
    ): ResponseEntity<MeasurementDto> {

        val id: Long
        try {
            id = pathId.toLong()
        } catch (e: Exception) {
            return ResponseEntity.status(400).build()
        }

        val measurementForDevice = measurementRepo.findById(id).orElse(null)
                ?: return ResponseEntity.status(404).build()

        return ResponseEntity.status(200).body(MeasurementDtoConverter.transform(measurementForDevice))
    }



    // 4. REST Endepunkt for å liste alle målere. GET til /devices - DONE
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllDevices(): ResponseEntity<List<GeigertellerDto>> {
        return ResponseEntity.status(200).body(
                GeigerDtoConverter.transform(repo.findAll())
        )
    }

    // ikke del av oppgaven
    @GetMapping(path =["/measurements"] ,produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllMeasurements(): ResponseEntity<List<MeasurementDto>> {
        return ResponseEntity.status(200).body(
                MeasurementDtoConverter.transform(measurementRepo.findAll())
        )
    }
}