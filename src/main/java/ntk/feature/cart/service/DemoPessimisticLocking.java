package ntk.feature.cart.service;

import lombok.RequiredArgsConstructor;
import ntk.feature.cart.entity.Product;
import ntk.feature.cart.exception.CustormException;
import ntk.feature.cart.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DemoPessimisticLocking {
    private final ProductRepo productRepo;
    @Transactional
    public String getStock(Long idProduct) {
//        Thread.sleep(5000);
        Product product = productRepo.getProduct(idProduct);
        if(product.getStock() == 0) {
            new CustormException("Product has no stock",404);
        }
        product.setStock(product.getStock() - 1);
        productRepo.save(product);
        return product.getStock() + "";
    }
}
