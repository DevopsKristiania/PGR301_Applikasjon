package no.kristiania.pgr301.dto

import javax.validation.constraints.NotNull

data class DeviceIdDto(

        @get:NotNull
        var deviceId: String? = null
)
