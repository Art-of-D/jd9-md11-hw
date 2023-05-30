package hibernate;

import lombok.Getter;
import objectid.Client;
import objectid.Planet;
import objectid.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtil {
    private static final HibernateUtil INSTANCE;
    @Getter
    private SessionFactory sessionFactory;
    private Logger logger = Logger.getLogger(HibernateUtil.class.getName());

    static {
        INSTANCE = new HibernateUtil();
    }
    public HibernateUtil(){
        try {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(Planet.class)
                    .addAnnotatedClass(Ticket.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            logger.log(Level.SEVERE, "Failed to initialize Hibernate SessionFactory", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }
    public void close() {
        sessionFactory.close();
    }
}
