package no.kristiania.pgr301.dto

import javax.validation.constraints.NotNull

data class MeasurementDto(


        @get:NotNull
        var Sievert: Int? = null,

        @get:NotNull
        var Lat: String? = null,

        @get:NotNull
        var Lng: Double?

)