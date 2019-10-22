package no.kristiania.pgr301.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "Geigerteller") // select * from geigerteller
class GeigertellerEntity(

        @Column(name = "Navn")
        @get:NotBlank(message = "Geigerteller navn kan ikke være blank")
        var name: String,

        @Column(name = "Pris")
        @get:NotNull(message = "Geigerteller navn kan ikke være null")
        var price: Int,

        @get:Id @get:GeneratedValue
        var id: Long? = null,


        var deviceId: String? = null
)