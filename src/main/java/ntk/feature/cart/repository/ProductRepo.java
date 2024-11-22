package ntk.feature.cart.repository;

import jakarta.persistence.LockModeType;
import ntk.feature.cart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("Select u From Product u Where u.Id = :idProduct")
    Product getProduct(@Param("idProduct") Long idProduct);
}
