package no.kristiania.pgr301.controller

import DtoConverter
import no.kristiania.pgr301.dto.GeigertellerDto
import no.kristiania.pgr301.repository.GeigertellerRepo
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

// Dont forget to change github user when comitting to github

@RestController
class GeigertellerController(val repo: GeigertellerRepo) {

    @GetMapping( path = ["/"])
    fun home() : String {
        return "Velkommen til DominiGeiger." +
        "Treff endpunktene spesifisert i Readme.md for å se innholdet"
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE], path = ["/devices"])
    fun getAllDevices(): ResponseEntity<List<GeigertellerDto>> {
        return ResponseEntity.status(200).body(
                DtoConverter.transform(repo.findAll())
        )
    }

}
