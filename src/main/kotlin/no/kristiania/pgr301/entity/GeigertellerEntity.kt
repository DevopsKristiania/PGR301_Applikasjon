package no.kristiania.pgr301.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "Geigerteller")
class GeigertellerEntity(

        @Column(name = "Navn")
        @get:NotBlank(message = "Champion Name cannot be left blank")
        var name: String,

        @Column(name = "Pris")
        @get:NotBlank(message = "Champion Name cannot be left blank")
        var price: Int,

        @get:Id @get:GeneratedValue
        var id: Long? = null
)