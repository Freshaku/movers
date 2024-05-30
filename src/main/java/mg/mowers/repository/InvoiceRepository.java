// src/main/java/mg/mowers/repository/InvoiceRepository.java

package mg.mowers.repository;

import mg.mowers.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
