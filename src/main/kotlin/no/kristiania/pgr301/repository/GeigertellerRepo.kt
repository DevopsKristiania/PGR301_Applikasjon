package no.kristiania.pgr301.repository

import no.kristiania.pgr301.entity.GeigertellerEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime
import javax.persistence.EntityManager

@Repository
interface GeigertellerRepo : CrudRepository<GeigertellerEntity,Long> {
    fun findByDeviceId(deviceId: String): Iterable<GeigertellerEntity>
}

@Transactional
interface GeigertellerRepoCustom {

}

@Repository
@Transactional
class GeigertellerRepoImpl(val em : EntityManager) : GeigertellerRepoCustom {


}