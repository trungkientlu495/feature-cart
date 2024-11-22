package ntk.feature.cart.service;

import lombok.AllArgsConstructor;
import ntk.feature.cart.entity.Cart;
import ntk.feature.cart.entity.User;
import ntk.feature.cart.exception.CustormException;
import ntk.feature.cart.payload.Mapping.UserMapper;
import ntk.feature.cart.payload.MessageResponseHandler;
import ntk.feature.cart.payload.dto.request.LoginRequest;
import ntk.feature.cart.payload.dto.request.RegisterRequest;
import ntk.feature.cart.repository.CartRepo;
import ntk.feature.cart.repository.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServices {
    private final UserRepo userRepo;
    private final CartRepo cartRepo;
    private final AuthenticationManager authenticationManager;
    public MessageResponseHandler registerAccount(RegisterRequest registerRequest) {
        try{
            User user = UserMapper.INSTANCE.toUser(registerRequest);
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userRepo.save(user);
            cartRepo.save(new Cart(user));
            return new MessageResponseHandler("Register Success",200);
        }catch (Exception e) {
            return new MessageResponseHandler("Register Failed",400);
        }
    }

    public MessageResponseHandler loginAccount(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
            return new MessageResponseHandler("Login Success",200);
        }catch (Exception e) {
            return new MessageResponseHandler("Login Failed",400);
        }
    }

    public User getAuth() {
        Object userDetail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = null;
        if(userDetail instanceof org.springframework.security.core.userdetails.User) {
            userEmail = ((org.springframework.security.core.userdetails.User)userDetail).getUsername();
        }
        if(userEmail == null) throw new CustormException("Email not found",404);
        User user = userRepo.findByEmail(userEmail);
        if(user==null) throw new CustormException("User not found",404);
        return user;
    }
}
