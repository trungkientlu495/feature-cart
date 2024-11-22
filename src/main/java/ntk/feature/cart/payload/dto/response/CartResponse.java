package ntk.feature.cart.payload.dto.response;

import lombok.Data;

@Data
public class CartResponse {
    private String nameProduct;
    private int quantity;
    private Double price;
}
