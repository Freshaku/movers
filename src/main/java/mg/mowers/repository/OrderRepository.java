// src/main/java/mg/mowers/repository/OrderRepository.java

package mg.mowers.repository;

import mg.mowers.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
