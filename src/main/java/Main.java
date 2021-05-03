import dao.ProductDao;
import dao.ProductDaoImpl;
import models.Product;
import service.ProductService;
import service.ProductServiceImpl;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws TransformerException, SQLException, ParserConfigurationException {

        Product product = new Product("jdbc:mysql://localhost:3306/magnet" +
                "?useUnicode=true&serverTimezone=UTC&useSSL=false", "root", "root", 1000000);

        ProductService test = new ProductServiceImpl(product.getDbUrl(), product.getUsername(), product.getPassword());

        test.fillTable(product.getQuantity());
        test.writeXML();
        test.writeXSLTFromXML();
        test.countXSLT();

        test.closeSessionFactory();
    }
}
