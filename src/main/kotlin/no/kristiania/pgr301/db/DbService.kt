package no.kristiania.pgr301.db

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Service
@Transactional
class DbService {
    @Autowired
    private val em: EntityManager? = null
}