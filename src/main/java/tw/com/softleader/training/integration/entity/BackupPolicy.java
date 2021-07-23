package tw.com.softleader.training.integration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "BACKUP_POLICY")
@Getter
@Setter
@ToString
public class BackupPolicy {
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
}
