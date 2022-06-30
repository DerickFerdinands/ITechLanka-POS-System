package Model;

import entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDTO {
    private String id;
    private entity.Orders Orders;
    private Item item;
    private int qty;

}
