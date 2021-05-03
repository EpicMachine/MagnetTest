package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Properties;

public class ProductDaoImpl implements ProductDao{

    private static SessionFactory sessionFactory;
    private Properties properties = new Properties();

    public ProductDaoImpl(String url, String username, String password) {
        this.properties.setProperty("hibernate.connection.url", url);
        this.properties.setProperty("dialect", "org.hibernate.dialect.MySQL5Dialect");
        this.properties.setProperty("hibernate.connection.username", username);
        this.properties.setProperty("hibernate.connection.password", password);
        this.properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
    }

    public void createSessionFactory() {
        try {
            sessionFactory = new Configuration()
                    .addProperties(properties)
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void closeSessionFactory() {
        try {
            sessionFactory.close();
        } catch (HibernateException e) {/*ignore*/}
    }

    public void fillTable(int quantity) {

        if (getSessionFactory() == null) {
            createSessionFactory();
        }
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE TEST").executeUpdate();
            for (int i = 1; i <= quantity; i++) {
                session.createNativeQuery("INSERT INTO TEST (FIELD) VALUES (" + i + ")").executeUpdate();
            }

            session.getTransaction().commit();
        }
    }

    public List<Integer> getAllUsers() {
        try (Session session = getSessionFactory().openSession()) {
            return session.createNativeQuery("SELECT FIELD FROM TEST").list();
        }
    }
}
