package no.kristiania.pgr301.db

import no.kristiania.pgr301.entity.GeigertellerEntity
import no.kristiania.pgr301.entity.MeasurementEntity
import no.kristiania.pgr301.repository.GeigertellerRepo
import no.kristiania.pgr301.repository.MeasurementRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class Dbinit(
        @Autowired val myGeigertellerRepo: GeigertellerRepo,
        @Autowired val myMeasurementRepo: MeasurementRepo

) {

    @PostConstruct
    fun initGeigertellerAtStartup() {
        myGeigertellerRepo.run {
            save(GeigertellerEntity(name = "X-1000", price = "7000",deviceId = "K076"))
            save(GeigertellerEntity(name = "V-9000", price = "5000",deviceId = "B5f39"))
            save(GeigertellerEntity(name = "V-4300", price = "2000",deviceId = "A5839"))
        }
    }

    @PostConstruct
    fun initMeasurementAtStartup() {
        myMeasurementRepo.run {
            save(MeasurementEntity(Sievert = "1000", Lng = "0.810", Lat = "21.3870 N"))
            save(MeasurementEntity(Sievert = "60", Lng = "0.920", Lat = "31.3870 N"))
            save(MeasurementEntity(Sievert = "30", Lng = "0.220", Lat = "51.3870 N"))
        }
    }

}