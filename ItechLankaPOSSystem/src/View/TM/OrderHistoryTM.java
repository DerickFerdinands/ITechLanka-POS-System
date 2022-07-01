package View.TM;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderHistoryTM {
    private String OrderId;
    private String CustomerName;
    private String address;
    private BigDecimal total;
    private BigDecimal advancedAmount;
    private BigDecimal balance;
    private String status;
    private JFXButton updateBtn;
    private JFXButton billBtn;
}
