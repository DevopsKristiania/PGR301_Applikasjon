import no.kristiania.pgr301.dto.MeasurementDto
import no.kristiania.pgr301.entity.MeasurementEntity

class MeasurementDtoConverter {

    companion object{

        fun transform(myMeasurementEntity: MeasurementEntity): MeasurementDto {
            return MeasurementDto(
                    id = myMeasurementEntity.id?.toString(),
                    Sievert = myMeasurementEntity.Sievert,
                    Lat = myMeasurementEntity.Lat,
                    Lng = myMeasurementEntity.Lng

            )
        }


        fun transform(measurementEntity: Iterable<MeasurementEntity>): List<MeasurementDto> {
            return measurementEntity.map { transform(it) }
        }
    }
}