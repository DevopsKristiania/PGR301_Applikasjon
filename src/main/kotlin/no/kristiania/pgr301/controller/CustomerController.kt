package no.kristiania.pgr301.controller

import no.kristiania.pgr301.repository.GeigertellerRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(path = ["/devices"])
@RestController
class CustomerController(val repo: GeigertellerRepo) {

    // Dont forget to change github user when comitting to github
    @GetMapping
    fun home(): String {
        return "spring works"
    }

}