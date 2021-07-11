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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "INSURED")
@Getter
@Setter
@ToString
//@NamedEntityGraph(name = "Insured.items", attributeNodes = @NamedAttributeNode("items"))
public class Insured {
    
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    
    @Column(name = "POLICY_ID")
    private Long policyId;
    
    @Column(name = "INSURED_IDNO")
    private String insuredIndo;
    
    @Column(name = "INSURED_LOCAL_NAME")
    private String insuredLocalName;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "INSURED_ID")    
    private Set<Item> items;
    
    

}
