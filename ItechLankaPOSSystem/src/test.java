import Util.FactoryConfigurations;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class test {
    public static void main(String[] args) {
        Session session = FactoryConfigurations.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        transaction.commit();
        session.close();
    }
}
