package no.kristiania.pgr301.dto

import no.kristiania.pgr301.entity.MeasurementEntity
import javax.persistence.CascadeType
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.validation.constraints.NotNull

data class GeigertellerDto(

        @get:NotNull
        var name: String? = null,

        @get:NotNull
        var price: String? = null,


        @get:NotNull
        var id: String? = null,


        @get:NotNull
        var deviceId: String? = null


)