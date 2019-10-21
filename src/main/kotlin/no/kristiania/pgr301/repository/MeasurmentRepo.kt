package no.kristiania.pgr301.repository

import no.kristiania.pgr301.dto.MeasurementDto
import no.kristiania.pgr301.entity.MeasurementEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Repository
interface MeasurementRepo : CrudRepository<MeasurementEntity, Long>

@Transactional
interface  MeasurementRepoCustom

@Repository
@Transactional
class MeasurementImpl(val em: EntityManager) : MeasurementRepoCustom

