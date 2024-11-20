package ntk.feature.cart.controller.cart;


import lombok.AllArgsConstructor;
import ntk.feature.cart.payload.MessageResponseHandler;
import ntk.feature.cart.payload.request.InsertProduct;
import ntk.feature.cart.service.CartItemServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartController {
    private final CartItemServices cartItemServices;
    @GetMapping("/getAll")
    public String getAll() {
        return null;
    }

    @PostMapping("/insert/product")
    public MessageResponseHandler insertProduct(@RequestBody InsertProduct insertProduct) {
        cartItemServices.InsertProductCart(insertProduct);
        return new MessageResponseHandler("Insert product success",200);
    }



    @DeleteMapping("/delete/product")
    public String deleteProduct() {
        return null;
    }
}
