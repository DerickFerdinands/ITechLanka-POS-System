import Util.FactoryConfigurations;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;

public class test {
    public static void main(String[] args) {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
/*        session.save(new Item("I00-001","Test Subject 1","CCTV", BigDecimal.valueOf(100),BigDecimal.valueOf(200),10,"",""));*/
        session.save(new Item("I00-002","Test Subject 1","CCTV", BigDecimal.valueOf(100),BigDecimal.valueOf(200),10,"",""));
        transaction.commit();
        session.close();
    }
}
