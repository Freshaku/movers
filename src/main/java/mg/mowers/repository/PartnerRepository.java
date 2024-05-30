// src/main/java/mg/mowers/repository/PartnerRepository.java

package mg.mowers.repository;

import mg.mowers.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
