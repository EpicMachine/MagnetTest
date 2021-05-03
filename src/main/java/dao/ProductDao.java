package dao;

import org.hibernate.SessionFactory;

import java.util.List;

public interface ProductDao {

    void createSessionFactory();

    SessionFactory getSessionFactory();

    void closeSessionFactory();

    void fillTable(int quantity);

    List<Integer> getAllUsers();

}
