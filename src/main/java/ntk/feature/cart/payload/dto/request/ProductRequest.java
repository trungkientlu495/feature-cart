package ntk.feature.cart.payload.dto.request;

import lombok.Data;

@Data
public class ProductRequest {
    private Long idProduct;
    private Long quantity;
}
