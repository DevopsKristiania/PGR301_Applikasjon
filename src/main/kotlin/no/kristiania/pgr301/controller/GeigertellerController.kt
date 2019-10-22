package no.kristiania.pgr301.controller

import no.kristiania.pgr301.dto.GeigerDtoConverter
import no.kristiania.pgr301.dto.GeigertellerDto
import no.kristiania.pgr301.dto.MeasurementDto
import no.kristiania.pgr301.entity.GeigertellerEntity
import no.kristiania.pgr301.repository.GeigertellerRepo
import no.kristiania.pgr301.repository.MeasurementRepo
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
        INSERT INTO geigerteller VALUES (4, 'XYZ-1279', 9000);
        You can now see the new device at http://localhost:8080/devices
     */
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createGeigerteller(@RequestBody dto: GeigertellerDto) : ResponseEntity<Long> {
        val newGeigertellerEntity = GeigertellerEntity(dto.name!!,dto.price!!)
        repo.save(newGeigertellerEntity)

        //return ResponseEntity.created(URI.create("/devices" + newGeigertellerEntity.id)).build()
        return ResponseEntity.created(URI.create("/devices" + newGeigertellerEntity.id)).build()
    }


    //2.REST Endepunkt for ny måling av stråling og lokasjon (lat, lng, sievert). POST /devices/{deviceId}/measurements


    /*  REST Endepunkt for å hente en eller flere målinger for en device. GET til /devices/{id}/measurements
        bruk measurmentsdto for å returnere den ved id - DONE for now
        Starter på 4 da det er en annen tabell :(
        http://localhost:8080/devices/4/measurements
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

    // REST Endepunkt for å liste alle målere. GET til /devices - DONE
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