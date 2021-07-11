package tw.com.softleader.training.policy.repository;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tw.com.softleader.training.policy.entity.Policy;

@Repository
public class PolicyRepositoryImpl implements BaseRepository<Policy, Long> {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Policy findWithGraph(Long id, String graphName) {
        EntityGraph entityGraph = entityManager.getEntityGraph(graphName);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        Policy policy = entityManager.find(Policy.class, id, properties);
        return policy;
    }

}
