import no.kristiania.pgr301.dto.GeigertellerDto
import no.kristiania.pgr301.db.GeigertellerEntity

class DtoConverter {

    companion object {

        fun transform(myGeigertellerEntity: GeigertellerEntity): GeigertellerDto {
            return GeigertellerDto(
                    name = myGeigertellerEntity.name,
                    id = myGeigertellerEntity.id?.toString(),
                    price = myGeigertellerEntity.price
            )
        }


        fun transform(champions: Iterable<GeigertellerEntity>): List<GeigertellerDto> {
            return champions.map { transform(it) }
        }
    }
}