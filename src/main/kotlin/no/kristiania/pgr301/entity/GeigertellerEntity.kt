package no.kristiania.pgr301.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "customer")
class GeigertellerEntity(
        @Id
        val id: Long = -1,

        @Column(name = "firstname")
        val firstName: String = "",

        @Column(name = "lastname")
        val lastName: String = ""
)