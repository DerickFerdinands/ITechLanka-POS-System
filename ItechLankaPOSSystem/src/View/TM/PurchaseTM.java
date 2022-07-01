package View.TM;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseTM {
    private String ItemCode;
    private String ItemName;
    private int qty;
    private BigDecimal unitPrice;
    private BigDecimal cost;
    private JFXButton btn;
}
