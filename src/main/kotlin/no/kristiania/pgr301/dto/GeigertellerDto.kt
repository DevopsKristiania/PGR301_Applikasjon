package no.kristiania.pgr301.dto

import javax.validation.constraints.NotNull

data class GeigertellerDto(

        @get:NotNull
        var name: String? = null,

        @get:NotNull
        var price: Int? = null,

        @get:NotNull
        var id: String? = null
)