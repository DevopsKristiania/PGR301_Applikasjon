package no.kristiania.pgr301.controller

import MeasurementDtoConverter
import no.kristiania.pgr301.dto.GeigerDtoConverter
import no.kristiania.pgr301.dto.GeigertellerDto
import no.kristiania.pgr301.dto.MeasurementDto
import no.kristiania.pgr301.entity.GeigertellerEntity
import no.kristiania.pgr301.entity.MeasurementEntity
import no.kristiania.pgr301.repository.GeigertellerRepo
import no.kristiania.pgr301.repository.MeasurementRepo
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

//@RequestMapping(path = ["/devices"])
@RestController
class GeigertellerController(
        val repo: GeigertellerRepo,
        val measurementRepo: MeasurementRepo
) {

    @GetMapping(path = ["/"])
    fun home(): String {
        return "Welcome to DominiGeiger. Please hit the endpoints specified in the code, starting with /devices"
    }

    /**
     * 1. REST Endepunkt for ny geigerteller. Svare på POST til /devices og returnere et objekt med en unik identifikator "deviceId"
     * Example usage H2: INSERT INTO geigerteller VALUES (4,'AF467', 'XYZ-1279', 9000);
     * You can now see the new device at http://localhost:8080/devices
     */
    @PostMapping(path = ["/devices"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createGeigerteller(@RequestBody dto: GeigertellerDto): ResponseEntity<Long> {
        val newGeigertellerEntity = GeigertellerEntity(dto.name!!, dto.price!!)
        repo.save(newGeigertellerEntity)

        return ResponseEntity.created(URI.create("/devices" + newGeigertellerEntity.deviceId)).build()
    }


    // GET by providing the device id ikke del av oppgaven
    // http://localhost:8080/devices/K076
    @GetMapping(path = ["/devices/{deviceId}"])
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

    /**
     * Note: Her brukes id og ikke deviceId for å lage ny måling... :(
     * 2. REST Endepunkt for ny måling av stråling og lokasjon (lat, lng, sievert). POST /devices/{deviceId}/measurements
     * Example usage H2: INSERT INTO measurement VALUES (7,'10.00 N', '0.35', 500)
     * http://localhost:8080/devices/7/measurements
     */
    @PostMapping(path = ["/devices/{deviceId}/measurements"])
    fun createNewMeasurement(@PathVariable("deviceId") pathId: String?,
                             @RequestBody dto: MeasurementDto): ResponseEntity<List<MeasurementDto>> {
        val newMeasurementEntity = MeasurementEntity(dto.Sievert!!, dto.Lng!!, dto.Lat!!)
        measurementRepo.save(newMeasurementEntity)

        return ResponseEntity.created(URI.create("/devices" + newMeasurementEntity.id)).build()
    }


    /**
     * 3. REST Endepunkt for å hente en eller flere målinger for en device. GET til /devices/{id}/measurements
     * http://localhost:8080/devices/1/measurements
     */
    @GetMapping(path = ["/devices/{id}/measurements"])
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
    @GetMapping(path = ["/devices"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllDevices(): ResponseEntity<List<GeigertellerDto>> {
        return ResponseEntity.status(200).body(
                GeigerDtoConverter.transform(repo.findAll())
        )
    }

    // ikke del av oppgaven
    @GetMapping(path = ["/measurements"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllMeasurements(): ResponseEntity<List<MeasurementDto>> {
        return ResponseEntity.status(200).body(
                MeasurementDtoConverter.transform(measurementRepo.findAll())
        )
    }
}