package no.kristiania.pgr301.controller

import no.kristiania.pgr301.dto.GeigertellerDto
import no.kristiania.pgr301.repository.GeigertellerRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(path = ["/devices"])
@RestController
class GeigertellerController(val repo: GeigertellerRepo) {

    // Dont forget to change github user when comitting to github
    @GetMapping
    fun getAllDevices(): ResponseEntity<List<GeigertellerDto>> {
        return ResponseEntity.status(200).body(
                DtoConverter.transform(repo.findAll())
        )
    }

}