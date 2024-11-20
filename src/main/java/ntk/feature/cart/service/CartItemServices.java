package ntk.feature.cart.service;

import lombok.AllArgsConstructor;
import ntk.feature.cart.entity.Cart;
import ntk.feature.cart.entity.CartItem;
import ntk.feature.cart.entity.Product;
import ntk.feature.cart.entity.User;
import ntk.feature.cart.exception.CustormException;
import ntk.feature.cart.payload.request.InsertProduct;
import ntk.feature.cart.repository.CartItemRepo;
import ntk.feature.cart.repository.CartRepo;
import ntk.feature.cart.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartItemServices {
    private final CartItemRepo cartItemRepo;
    private final ProductRepo productRepo;
    private final AuthServices authServices;
    private final CartRepo cartRepo;

    public void InsertProductCart(InsertProduct insertProduct) {
        User user = authServices.getAuth();
        Cart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        Product product = productRepo.findById(insertProduct.getIdProduct()).orElseThrow(
                () -> new CustormException("Product not found",404)
        );
        if(cartItemRepo.findCartItemById(insertProduct.getIdProduct())==null) {
            CartItem cartItem = new CartItem(product, insertProduct.getQuantity());
            cartItem.setCart(cart);
            cartItems.add(cartItem);
        }else{
            CartItem cartItem = cartItemRepo.findCartItemById(insertProduct.getIdProduct());
            if(insertProduct.getQuantity()+cartItem.getQuantity()>product.getStock()) {
                throw new CustormException("Không đủ hàng",205);
            }
            cartItem.setQuantity(insertProduct.getQuantity()+cartItem.getQuantity());
        }
        cartRepo.save(cart);
    }
}
