package tw.com.softleader.training.policy.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tw.com.softleader.training.policy.entity.Insured;
import tw.com.softleader.training.policy.entity.Item;
import tw.com.softleader.training.policy.entity.Policy;


@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PolicyRepositoryTest {
    
    @Autowired
    private PolicyRepository policyRepository;
    
    @BeforeAll
    void initAll() {
        Policy policy0 = new Policy();
        policy0.setPolicyNo("9921ABC00001");
        policy0.setEndstNo(0);
        policy0.setApplicantIdno("A123456789");
        policy0.setApplicantLocalName("��之���");
        
        
        Insured insured0 = new Insured();
        insured0.setInsuredIndo("A176280531");
        insured0.setInsuredLocalName("��之�");
        
        
        Set<Item> items = new LinkedHashSet<Item>();
        Item item = new Item();
        item.setCode("AD");
        item.setItemLocalName("甇餅��");
        item.setAmount(1000000);
        item.setPremium(120);
        
        items.add(item);
        
        item = new Item();
        item.setCode("MR");
        item.setItemLocalName("����");
        item.setAmount(100000);
        item.setPremium(50);
        
        items.add(item);
        
        insured0.setItems(items);
        
        Insured insured1 = new Insured();
        insured1.setInsuredIndo("A141538837");
        insured1.setInsuredLocalName("���");
        
        items = new LinkedHashSet<Item>();
        item = new Item();
        item.setCode("AD");
        item.setItemLocalName("甇餅��");
        item.setAmount(1000000);
        item.setPremium(120);
        
        items.add(item);
        
        item = new Item();
        item.setCode("MR");
        item.setItemLocalName("����");
        item.setAmount(100000);
        item.setPremium(50);
        
        items.add(item);
        
        insured1.setItems(items);
        
        
        
        Set<Insured> insureds = new LinkedHashSet<Insured>();
        insureds.add(insured0);
        insureds.add(insured1);
        policy0.setInsureds(insureds);

        policyRepository.save(policy0);
        
        
        Policy policy1 = new Policy();
        policy1.setPolicyNo("9921ABC00002");
        policy1.setEndstNo(0);
        policy1.setApplicantIdno("A123456789");
        policy1.setApplicantLocalName("��予憭�");
        
        
        insured0 = new Insured();
        insured0.setInsuredIndo("A176280513");
        insured0.setInsuredLocalName("���");
        
        items = new LinkedHashSet<Item>();
        item = new Item();
        item.setCode("AD");
        item.setItemLocalName("甇餅��");
        item.setAmount(1000000);
        item.setPremium(120);
        
        items.add(item);
        
        item = new Item();
        item.setCode("MR");
        item.setItemLocalName("����");
        item.setAmount(100000);
        item.setPremium(50);
        
        items.add(item);
        
        insured0.setItems(items);
        
        insured1 = new Insured();
        insured1.setInsuredIndo("A141538873");
        insured1.setInsuredLocalName("�����");
        
        items = new LinkedHashSet<Item>();
        item = new Item();
        item.setCode("AD");
        item.setItemLocalName("甇餅��");
        item.setAmount(1000000);
        item.setPremium(120);
        
        items.add(item);
        
        item = new Item();
        item.setCode("MR");
        item.setItemLocalName("����");
        item.setAmount(100000);
        item.setPremium(50);
        
        items.add(item);
        
        insured1.setItems(items);
        
        insureds = new LinkedHashSet<Insured>();
        insureds.add(insured0);
        insureds.add(insured1);
        policy1.setInsureds(insureds);

        policyRepository.save(policy1);
        
        
    }

    
//
    @Test
    @Transactional
    void testFindByPolicyNoAndEndstNo() {
        Policy policy = policyRepository.findByPolicyNoAndEndstNo("9921ABC00001", 0);
        assertEquals("A123456789", policy.getApplicantIdno());
        
        log.info("Now we try to get insureds");
        Set<Insured> insureds = policy.getInsureds();
        for(Insured insured:insureds) {
            log.info("{}", insured);
        }
    }

//    @Test
//    @Transactional
//    void testFindByApplicantIdno() {
//        List<Policy> policies = policyRepository.findByApplicantIdno("A123456789");
//        for(Policy policy:policies) {
//            assertEquals("A123456789", policy.getApplicantIdno());
//        
//            log.info("Now we try to get insureds");
//            List<Insured> insureds = policy.getInsureds();
//            for(Insured insured:insureds) {
//                log.info("{}", insured);
//            }
//        }
//        
//        
//    }
    
//    @Test
//    @Transactional
//    void testFindByGraph() {
//        Policy policy = policyRepository.findWithGraph(1L, "policy.insureds");
//
//            assertEquals("A123456789", policy.getApplicantIdno());
//        
//            log.info("Now we try to get insureds");
//            List<Insured> insureds = policy.getInsureds();
//            for(Insured insured:insureds) {
//                log.info("{}", insured);
//            }
//        
//        
//    }
    
//
//    @Test
//    void testFindByApplicantLocalNameLike() {
//        List<Policy> policies = policyRepository.findByApplicantLocalNameLike("���%");
//        for(Policy policy:policies) {
//            assertEquals("���", policy.getApplicantLocalName().substring(0,1));
//        }
//    }

}
