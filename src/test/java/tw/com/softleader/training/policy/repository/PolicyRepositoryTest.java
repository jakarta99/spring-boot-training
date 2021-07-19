package tw.com.softleader.training.policy.repository;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tw.com.softleader.training.policy.entity.Policy;


@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PolicyRepositoryTest {
    
    @Autowired
    private PolicyRepository policyRepository;
    
    
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
    @Transactional
    public void testFindInPage() {
        Pageable pageRequest = PageRequest.of(0, 10, Sort.by("policyNo").descending());
        
        Page<Policy> page = policyRepository.findAll(pageRequest);
        
        log.debug("{}", page);
        
        List<Policy> policies = page.getContent();
        
        for(Policy policy:policies) {
            log.debug("{}", policy);
        }
        
    }
    
    
//    @BeforeAll
//    void initAll() {
//        Policy policy0 = new Policy();
//        policy0.setPolicyNo("9921ABC00001");
//        policy0.setEndstNo(0);
//        policy0.setApplicantIdno("A123456789");
//        policy0.setApplicantLocalName("王先生");
//        
//        
//        Insured insured0 = new Insured();
//        insured0.setInsuredIndo("A176280531");
//        insured0.setInsuredLocalName("王哥哥");
//        
//        
//        Set<Item> items = new LinkedHashSet<Item>();
//        Item item = new Item();
//        item.setCode("AD");
//        item.setItemLocalName("死殘");
//        item.setAmount(1000000);
//        item.setPremium(120);
//        
//        items.add(item);
//        
//        item = new Item();
//        item.setCode("MR");
//        item.setItemLocalName("意外醫療");
//        item.setAmount(100000);
//        item.setPremium(50);
//        
//        items.add(item);
//        
//        insured0.setItems(items);
//        
//        Insured insured1 = new Insured();
//        insured1.setInsuredIndo("A176280577");
//        insured1.setInsuredLocalName("王弟弟");
//        
//        items = new LinkedHashSet<Item>();
//        item = new Item();
//        item.setCode("AD");
//        item.setItemLocalName("死殘");
//        item.setAmount(1000000);
//        item.setPremium(120);
//        
//        items.add(item);
//        
//        item = new Item();
//        item.setCode("MR");
//        item.setItemLocalName("意外醫療");
//        item.setAmount(100000);
//        item.setPremium(50);
//        
//        items.add(item);
//        
//        insured1.setItems(items);
//        
//        
//        
//        Set<Insured> insureds = new LinkedHashSet<Insured>();
//        insureds.add(insured0);
//        insureds.add(insured1);
//        policy0.setInsureds(insureds);
//
//        policyRepository.save(policy0);
//        
//        
//        Policy policy1 = new Policy();
//        policy1.setPolicyNo("9921ABC00002");
//        policy1.setEndstNo(0);
//        policy1.setApplicantIdno("A111222333");
//        policy1.setApplicantLocalName("王叔叔");
//        
//        
//        insured0 = new Insured();
//        insured0.setInsuredIndo("A111222333");
//        insured0.setInsuredLocalName("王叔叔");
//        
//        items = new LinkedHashSet<Item>();
//        item = new Item();
//        item.setCode("AD");
//        item.setItemLocalName("死殘");
//        item.setAmount(1000000);
//        item.setPremium(120);
//        
//        items.add(item);
//        
//        item = new Item();
//        item.setCode("MR");
//        item.setItemLocalName("意外醫療");
//        item.setAmount(100000);
//        item.setPremium(50);
//        
//        items.add(item);
//        
//        insured0.setItems(items);
//        
//        insured1 = new Insured();
//        insured1.setInsuredIndo("A222333555");
//        insured1.setInsuredLocalName("王姐姐");
//        
//        items = new LinkedHashSet<Item>();
//        item = new Item();
//        item.setCode("AD");
//        item.setItemLocalName("死殘");
//        item.setAmount(1000000);
//        item.setPremium(120);
//        
//        items.add(item);
//        
//        item = new Item();
//        item.setCode("MR");
//        item.setItemLocalName("意外醫療");
//        item.setAmount(100000);
//        item.setPremium(50);
//        
//        items.add(item);
//        
//        insured1.setItems(items);
//        
//        insureds = new LinkedHashSet<Insured>();
//        insureds.add(insured0);
//        insureds.add(insured1);
//        policy1.setInsureds(insureds);
//
//        policyRepository.save(policy1);
//        
//        
//    }

    
//
//    @Test
//    @Transactional
//    void testFindByPolicyNoAndEndstNo() {
//        Policy policy = policyRepository.findByPolicyNoAndEndstNo("9921ABC00001", 0);
//        assertEquals("A123456789", policy.getApplicantIdno());
//        
//        log.info("Now we try to get insureds");
//        Set<Insured> insureds = policy.getInsureds();
//        for(Insured insured:insureds) {
//            log.info("{}", insured);
//        }
//    }

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
//        List<Policy> policies = policyRepository.findByApplicantLocalNameLike("王%");
//        for(Policy policy:policies) {
//            assertEquals("王", policy.getApplicantLocalName().substring(0,1));
//        }
//    }

}
