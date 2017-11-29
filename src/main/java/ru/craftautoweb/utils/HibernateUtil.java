package ru.craftautoweb.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
//import ru.craftautoweb.entities.*;

/**
 * Created by User on 19.11.2016.
 */
public class HibernateUtil  {
/*    private static final SessionFactory ourSessionFactory;
    //private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.
                    addAnnotatedClass(AgencyEntity.class).
                    addAnnotatedClass(AutoEntity.class).
                    addAnnotatedClass(CompassEntity.class).
                    addAnnotatedClass(DriverEntity.class).
                    addAnnotatedClass(RouteEntity.class).
                    addAnnotatedClass(TableEntity.class).
                    addAnnotatedClass(UserEntity.class).
                    addAnnotatedClass(RolesEntity.class).
                    addAnnotatedClass(ResultsEntity.class).
                    addAnnotatedClass(DocumentsEntity.class).
                    addAnnotatedClass(TypesAgencyEntity.class).
                    addAnnotatedClass(TypesCashEntity.class).
                    addAnnotatedClass(VDocumentsEntity.class).
                    addAnnotatedClass(VBillsEntity.class).
                    addAnnotatedClass(BillsEntity.class).
                    addAnnotatedClass(BillDriversEntity.class).
                    addAnnotatedClass(VBillDriversEntity.class).
                    addAnnotatedClass(VBillDetailsEntity.class).
                    addAnnotatedClass(VBillDriverDetailsEntity.class).
                    addAnnotatedClass(VOperatingStatementEntity.class);
            configuration.configure();

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties());
            ourSessionFactory = configuration.buildSessionFactory(builder.build());


            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception ex) {
            System.out.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }*/

    public static Session getSession() throws HibernateException {
        Session session = sessionFactory.openSession();
        return session;
    }
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }


}