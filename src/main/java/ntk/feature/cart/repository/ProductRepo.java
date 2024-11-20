package ntk.feature.cart.repository;

import ntk.feature.cart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
