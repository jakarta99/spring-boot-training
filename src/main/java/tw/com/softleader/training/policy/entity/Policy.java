package tw.com.softleader.training.policy.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "POLICY")
@Getter
@Setter
@ToString
@NamedEntityGraph(name = "policy.insureds", 
   attributeNodes = @NamedAttributeNode( value= "insureds", subgraph = "insureds.items"), 
   subgraphs= @NamedSubgraph(name = "insureds.items", 
   attributeNodes = @NamedAttributeNode( value= "items")))
        

public class Policy {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    
    @Column(name = "POLICY_NO")
    private String policyNo;
    
    @Column(name = "ENDST_NO")
    private int endstNo;
    
    @Column(name = "APPLICANT_LOCAL_NAME")
    private String applicantLocalName;
    
    @Column(name = "APPLICANT_IDNO")
    private String applicantIdno;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "POLICY_ID")    
    private Set<Insured> insureds;

    
    
    
    
}
