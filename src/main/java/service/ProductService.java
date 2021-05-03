package service;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.sql.SQLException;

public interface ProductService {

    void writeXML() throws SQLException, ParserConfigurationException, TransformerException;

    void writeXSLTFromXML() throws TransformerException;

    void countXSLT();

    void fillTable(int quantity);

    void closeSessionFactory();

}
