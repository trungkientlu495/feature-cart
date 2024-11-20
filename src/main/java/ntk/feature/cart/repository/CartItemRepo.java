package ntk.feature.cart.repository;

import ntk.feature.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    @Query("SELECT u FROM CartItem u WHERE u.product.Id = :id")
    CartItem findCartItemById(@Param("id") Long id);
}
