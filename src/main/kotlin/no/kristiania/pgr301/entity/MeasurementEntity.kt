package no.kristiania.pgr301.entity


import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "Measurement") // select * from measurment
class MeasurementEntity(

       // Almost all who receive a dose of 10 sieverts will die within weeks.
        @Column(name = "Sievert")
        @get:NotNull
        var Sievert: Int,

        // Example chernobyl Latitude: 51.3870 N
        @Column(name = "Lat")
        @get:NotNull
        var Lat : String,

        // Liquefied Natural Gas: 0.220
        @Column(name = "Lng")
        @get:NotNull
        var Lng: Double,

        @get:Id @get:GeneratedValue
        var id: Long? = null

)

