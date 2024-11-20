package ntk.feature.cart.controller.auth;

import lombok.AllArgsConstructor;
import ntk.feature.cart.payload.ResponseHandler;
import ntk.feature.cart.payload.request.LoginRequest;
import ntk.feature.cart.payload.request.RegisterRequest;
import ntk.feature.cart.service.AuthServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthServices authServices;
    @PostMapping("/account/register")
    public ResponseEntity<Object> registerAccount(@RequestBody RegisterRequest registerRequest){
        return ResponseHandler.generateResponse(HttpStatus.OK,ResponseHandler.SUCCESS,authServices.registerAccount(registerRequest));
    }

    @PostMapping("/account/login")
    public ResponseEntity<Object> loginAccount(@RequestBody LoginRequest loginRequest){
        return ResponseHandler.generateResponse(HttpStatus.OK,ResponseHandler.SUCCESS,authServices.loginAccount(loginRequest));
    }
}
