package ntk.feature.cart.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MessageResponseHandler {
    private String message;
    private Integer status;

    public MessageResponseHandler(String message, Integer status) {
        this.message = message;
        this.status = status;
    }
}
