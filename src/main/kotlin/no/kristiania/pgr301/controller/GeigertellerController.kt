package no.kristiania.pgr301.controller

import DtoConverter
import no.kristiania.pgr301.dto.GeigertellerDto
import no.kristiania.pgr301.repository.GeigertellerRepo
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

// Dont forget to change github user when comitting to github

@RequestMapping(path = ["/devices"])
@RestController
class GeigertellerController(
        val repo: GeigertellerRepo) {

    // REST Endepunkt for å liste alle målere. GET til /devices
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllDevices(): ResponseEntity<List<GeigertellerDto>> {
        return ResponseEntity.status(200).body(
                DtoConverter.transform(repo.findAll())
        )
    }

   // @GetMapping(path = ["/{id}"])


    /*
    // REST Endepunkt for å hente en eller flere målinger for en device. GET til /devices/{id}/measurements
    @GetMapping(path = ["/{id}/measurements"])
    fun getMeasurementById(@PathVariable("id") pathId: String):ResponseEntity<GeigertellerDto> {
        val id: Long
    }
    */



}
