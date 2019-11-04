package no.kristiania.pgr301.dto

import no.kristiania.pgr301.entity.GeigertellerEntity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.validation.constraints.NotNull

data class MeasurementDto(

        @get:NotNull
        var id: String? = null,

        @get:NotNull
        var Sievert: String? = null,

        @get:NotNull
        var Lat: String? = null,

        @get:NotNull
        var Lng: String? = null



)