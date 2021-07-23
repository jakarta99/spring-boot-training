package tw.com.softleader.training.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.softleader.training.integration.entity.BackupPolicy;

public interface BackupPolicyRepository  extends JpaRepository<BackupPolicy, Long> {
    
    
}
