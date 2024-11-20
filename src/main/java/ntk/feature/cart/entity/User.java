package ntk.feature.cart.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ntk.feature.cart.config.custorm_anonation.config.StrongPassword;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank(message = "User not found")
    private String username;

    @StrongPassword
    private String password;

    @Email
    private String email;

    @OneToOne(mappedBy = "user")
    private Cart cart;
}
