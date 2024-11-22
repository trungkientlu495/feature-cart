package ntk.feature.cart.payload.paging;

import lombok.Data;

@Data
public class PageDTO {
    private int pageSize;
    private int currentPage;
}
