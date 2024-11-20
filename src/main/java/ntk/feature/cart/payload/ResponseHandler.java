package ntk.feature.cart.payload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static ResponseEntity<Object> generateResponse(HttpStatus status, String message, Object data) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", message);
        response.put("status", status.value());
        response.put("data", data);
        return new ResponseEntity(response, status);
    }

}
