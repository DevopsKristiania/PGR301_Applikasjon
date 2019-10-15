package no.kristiania.pgr301.repository

import no.kristiania.pgr301.entity.GeigertellerEntity
import org.springframework.data.repository.CrudRepository

interface GeigertellerRepo : CrudRepository<GeigertellerEntity,Long> {
}