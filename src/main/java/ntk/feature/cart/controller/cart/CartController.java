package ntk.feature.cart.controller.cart;


import lombok.AllArgsConstructor;
import ntk.feature.cart.payload.MessageResponseHandler;
import ntk.feature.cart.payload.dto.request.ProductRequest;
import ntk.feature.cart.payload.dto.response.CartResponse;
import ntk.feature.cart.payload.paging.PageDTO;
import ntk.feature.cart.service.CartItemServices;
import ntk.feature.cart.service.DemoPessimisticLocking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartController {
    private final CartItemServices cartItemServices;
    private final DemoPessimisticLocking pessimisticLocking;
    @GetMapping("/getAll")
    public String getAll() {
        return null;
    }

    @PostMapping("/insert/product")
    public MessageResponseHandler insertProduct(@RequestBody ProductRequest productRequest) {
        cartItemServices.InsertProductCart(productRequest);
        return new MessageResponseHandler("Insert product success",200);
    }



    @DeleteMapping("/delete/product")
    public String deleteProduct(@RequestBody ProductRequest productRequest) {
        cartItemServices.deleteProduct(productRequest);
        return null;
    }

    @GetMapping("/{idCart}/product")
    public List<CartResponse> getAllProducts(@PathVariable Long idCart,@RequestBody PageDTO pageDTO) {
        return cartItemServices.getCartItems(idCart,pageDTO).getContent();
    }

    @GetMapping("/test/pessimistic")
    public String testPessimistic(@RequestParam Long idProduct){
        return pessimisticLocking.getStock(idProduct);
    }
}
