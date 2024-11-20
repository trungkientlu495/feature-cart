package ntk.feature.cart.exception;

import lombok.Data;

public class CustormException extends RuntimeException {
    private int status;

    public CustormException(String message, int status) {
        super(message); // Gọi constructor của RuntimeException để lưu message
        this.status = status;
    }

    public int getStatus() {
        return status;
    }


}
