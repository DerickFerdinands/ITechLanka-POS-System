package View.TM;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfitTM {
    private String DirID;
    private String DirName;
    private double profitMargin;
    private BigDecimal TotalProfit;
}
