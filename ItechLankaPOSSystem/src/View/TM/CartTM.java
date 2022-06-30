package View.TM;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartTM {
    private String itemCode;
    private int qtyOnHand;
    private int qty;
    private BigDecimal unitPrice;
    private BigDecimal total;
    private JFXButton btn;
}
