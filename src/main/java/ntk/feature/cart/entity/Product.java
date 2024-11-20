package ntk.feature.cart.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private Double price;

    private Long stock;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<CartItem> cartItemList;
}
