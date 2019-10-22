package no.kristiania.pgr301.dto

import no.kristiania.pgr301.dto.GeigertellerDto
import no.kristiania.pgr301.entity.GeigertellerEntity

class GeigerDtoConverter {

    companion object {

        fun transform(myGeigertellerEntity: GeigertellerEntity): GeigertellerDto {

            return GeigertellerDto(
                    name = myGeigertellerEntity.name,
                    id = myGeigertellerEntity.id?.toString(),
                    price = myGeigertellerEntity.price
                   // deviceId = myGeigertellerEntity.deviceId?.toString()
            )
        }


        fun transform(geigerEntity: Iterable<GeigertellerEntity>): List<GeigertellerDto> {
            return geigerEntity.map { transform(it) }
        }
    }
}