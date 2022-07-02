import Util.FactoryConfigurations;
import dao.Custom.impl.OrderDetailDAOImpl;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;

public class test {
    public static void main(String[] args) {
        try {
            System.out.println(new OrderDetailDAOImpl().getTotalProfit());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
