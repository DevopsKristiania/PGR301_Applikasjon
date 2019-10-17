package no.kristiania.pgr301.repository

import no.kristiania.pgr301.entity.GeigertellerEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Repository
interface GeigertellerRepo : CrudRepository<GeigertellerEntity,Long> {
}

@Transactional
interface GeigertellerRepoCustom {

}
@Repository
@Transactional
class GeigertellerImpl(val em : EntityManager) : GeigertellerRepoCustom