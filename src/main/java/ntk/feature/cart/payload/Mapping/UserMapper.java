package ntk.feature.cart.payload.Mapping;

import ntk.feature.cart.entity.User;
import ntk.feature.cart.payload.dto.request.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "email", source = "email")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    User toUser(RegisterRequest registerRequest);
}
