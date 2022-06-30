package Util;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfigurations {
    private static FactoryConfigurations factoryConfigurations;
    private final SessionFactory sessionFactory;

    private FactoryConfigurations() {
      Configuration config = new Configuration()
                .addAnnotatedClass(Director.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Orders.class)
                .addAnnotatedClass(Supplier.class)
                .addAnnotatedClass(Purchase.class)
                .addAnnotatedClass(PurchaseDetail.class)
                .addAnnotatedClass(OrderDetail.class)
              .addAnnotatedClass(Customer.class);

        sessionFactory = config.buildSessionFactory();
    }

    public static FactoryConfigurations getInstance() {
        return factoryConfigurations == null ? factoryConfigurations = new FactoryConfigurations() : factoryConfigurations;
    }

    public Session getSession() {
        return sessionFactory.openSession();

    }


}
