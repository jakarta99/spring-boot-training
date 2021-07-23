package tw.com.softleader.training.policy.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import tw.com.softleader.training.integration.entity.BackupPolicy;
import tw.com.softleader.training.integration.repository.BackupPolicyRepository;
import tw.com.softleader.training.policy.entity.Policy;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransPolicyFromPrimaryToIntegrationTest {

    
    @Autowired
    private PolicyRepository policyRepository;
    
    @Autowired
    private BackupPolicyRepository backupPolicyRepository;
    
    @BeforeAll
    void initAll() {
        Policy policy;
        
        for(int i = 1; i<=88; i++) {
            
            policy = new Policy();
            String policyNo = "00000"+i;
            policyNo = "9921ABC"+policyNo.substring(policyNo.length()-5);
            
            policy.setPolicyNo(policyNo);
            policy.setEndstNo(0);
            policy.setApplicantIdno("A123456789");
            policy.setApplicantLocalName("王先生");
        
            policyRepository.save(policy);
        }
        
        
    }
    
    @Test
    public void trans() {
        List<Policy> policies = policyRepository.findAll();
        
        
        List<BackupPolicy> backupPosPolicies = new ArrayList<>();
        BackupPolicy backupPolicy ;
        for(Policy policy:policies) {
            backupPolicy = new BackupPolicy();
            BeanUtils.copyProperties(policy, backupPolicy, "id");
            backupPosPolicies.add(backupPolicy);
        }
        
        backupPolicyRepository.saveAll(backupPosPolicies);
    }
    
}
