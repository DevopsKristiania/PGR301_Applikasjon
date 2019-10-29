package no.kristiania.pgr301.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "DeviceId") // select * from geigerteller
class DeviceIdEntity(

        @Column(name = "deviceId")
        @get:Id
        var deviceId: String? = null
)
