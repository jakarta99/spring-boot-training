package tw.com.softleader.training.policy.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ITEM")
@Getter
@Setter
public class Item {
    
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    
    @Column(name = "INSURED_ID")
    private Long insuredId;
    
    @Column(name = "CODE")
    private String code;
    
    @Column(name = "ITEM_LOCAL_NAME")
    private String itemLocalName;
    
    @Column(name = "AMOUNT")
    private Integer amount;
    
    @Column(name = "PREMIUM")
    private Integer premium;
    

    
}
