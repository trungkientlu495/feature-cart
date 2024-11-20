package ntk.feature.cart.payload.request;

import lombok.Data;

@Data
public class InsertProduct {
    private Long idProduct;
    private Long quantity;
}
