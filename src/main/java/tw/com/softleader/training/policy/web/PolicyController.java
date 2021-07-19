package tw.com.softleader.training.policy.web;

import java.util.List;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import tw.com.softleader.training.policy.entity.Policy;
import tw.com.softleader.training.policy.repository.PolicyRepository;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyRepository policyRepository;

    //    @GetMapping
    //    public List<Policy> query(Policy form) {
    //        
    //        if(form.getPolicyNo() != null && form.getApplicantLocalName() != null) {
    //            return policyRepository.findByPolicyNoAndApplicantLocalNameLike(form.getPolicyNo(), form.getApplicantLocalName()+"%");
    //        } else if (form.getPolicyNo() != null && form.getApplicantLocalName() == null) {
    //            return policyRepository.findByPolicyNo(form.getPolicyNo());
    //        } else if (form.getPolicyNo() == null && form.getApplicantLocalName() != null) {
    //            return policyRepository.findByApplicantLocalNameLike(form.getApplicantLocalName()+"%");
    //        } else {
    //            return policyRepository.findAll();
    //        }
    //        
    //        
    //    }

    @GetMapping
    public List<Policy> query(@And({
            @Spec(path = "policyNo", spec = Equal.class),
            @Spec(path = "applicantLocalName", spec = Like.class)
    }) Specification<Policy> specification) {

        
        //return policyRepository.findAll(specification);
        
        Specification<Policy> newSpec = (Specification<Policy>) (root, criteriaQuery, criteriaBuilder) -> {
            Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
            Root<Policy> subQueryRoot = subQuery.from(Policy.class);
            subQuery.select(criteriaBuilder.max(subQueryRoot.get("endstNo")));
            subQuery.where(criteriaBuilder.equal(root.get("policyNo"), subQueryRoot.get("policyNo")));
            return criteriaBuilder.equal(root.get("endstNo"), subQuery);
        };
        
        ;

        return policyRepository.findAll(Specification.where(specification).and(newSpec));
        
        

    }

}
