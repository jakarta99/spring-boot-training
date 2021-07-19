package tw.com.softleader.training.policy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import tw.com.softleader.training.policy.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long>, JpaSpecificationExecutor<Policy>  {
    
    @EntityGraph(value = "policy.insureds")
    Policy findByPolicyNoAndEndstNo(String policyNo, int endstNo);
    

    //@EntityGraph(value = "policy.insureds", type = EntityGraphType.LOAD)
    //@EntityGraph(value = "policy.insureds", attributePaths = { "insureds","insureds.items" })
    List<Policy> findByApplicantIdno(String applicantIdno);
    
    List<Policy> findByApplicantLocalNameLike(String name);
    
    
    List<Policy> findByPolicyNo(String policyNo);
    List<Policy> findByPolicyNoAndApplicantLocalNameLike(String policyNo, String applicantLocalName);

}
