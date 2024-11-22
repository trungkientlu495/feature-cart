package ntk.feature.cart.payload.Mapping;

import ntk.feature.cart.entity.CartItem;
import ntk.feature.cart.payload.dto.response.CartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartResponseMapper {
    CartResponseMapper INSTANCE = Mappers.getMapper(CartResponseMapper.class);
    @Mapping(target = "nameProduct", source = "product.name")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "product.price")
    CartResponse toCartResponse(CartItem cartItem);
}
