package ntk.feature.cart.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ntk.feature.cart.entity.Cart;
import ntk.feature.cart.entity.CartItem;
import ntk.feature.cart.entity.Product;
import ntk.feature.cart.entity.User;
import ntk.feature.cart.exception.CustormException;
import ntk.feature.cart.payload.Mapping.CartResponseMapper;
import ntk.feature.cart.payload.dto.request.ProductRequest;
import ntk.feature.cart.payload.dto.response.CartResponse;
import ntk.feature.cart.payload.paging.PageDTO;
import ntk.feature.cart.repository.CartItemRepo;
import ntk.feature.cart.repository.CartRepo;
import ntk.feature.cart.repository.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CartItemServices {
    private final CartItemRepo cartItemRepo;
    private final ProductRepo productRepo;
    private final AuthServices authServices;
    private final CartRepo cartRepo;

    @Transactional
    public void InsertProductCart(ProductRequest productRequest) {
        User user = authServices.getAuth();
        Cart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        Product product = productRepo.findById(productRequest.getIdProduct()).orElseThrow(
                () -> new CustormException("Product not found",404)
        );
        if(cartItemRepo.findCartItemById(productRequest.getIdProduct())==null&&product.getStock()>=0) {
            CartItem cartItem = new CartItem(product, productRequest.getQuantity());
            cartItem.setCart(cart);
            cartItems.add(cartItem);
            if(productRequest.getQuantity()>product.getStock()) {
                throw new CustormException("Không đủ hàng",205);
            }

        }else{
            CartItem cartItem = cartItemRepo.findCartItemById(productRequest.getIdProduct());
            if(productRequest.getQuantity()+cartItem.getQuantity()>product.getStock()&&product.getStock()>=0) {
                throw new CustormException("Không đủ hàng",205);
            }
            cartItem.setQuantity(productRequest.getQuantity()+cartItem.getQuantity());

        }
        productRepo.save(product);
        cartRepo.save(cart);
    }


    @Transactional
    public void deleteProduct(ProductRequest productRequest){
        User user = authServices.getAuth();
        Cart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        Product product = productRepo.findById(productRequest.getIdProduct()).orElseThrow(
                () -> new CustormException("Product not found",404)
        );
        List<CartItem> cartItemList = cartItems.stream()
                .filter(cartItem -> cartItem.getProduct().getId()
                        .equals(product.getId())).collect(Collectors.toList());
       cartItems.removeAll(cartItemList);
        cartRepo.save(cart);
    }

    public Page<CartResponse> getCartItems(Long idCart, PageDTO pageDTO) {
        Pageable pageable = PageRequest.of(pageDTO.getCurrentPage(), pageDTO.getPageSize());
       Cart cart = cartRepo.findById(idCart).orElseThrow(() -> new CustormException("Cart not found",404));
       List<CartItem> cartItems = cart.getCartItems();
       List<CartResponse> cartResponses = cartItems.stream().map(CartResponseMapper.INSTANCE::toCartResponse).collect(Collectors.toList());
       log.info("TEST "+cartResponses.get(0).getNameProduct());
        return new PageImpl<>(cartResponses, pageable, cartResponses.size());
    }
}
