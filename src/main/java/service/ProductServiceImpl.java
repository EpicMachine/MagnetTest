package service;

import dao.ProductDao;
import dao.ProductDaoImpl;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.util.Iterator;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductDao productDao;

    public ProductServiceImpl(String url, String username, String password) {
        productDao = new ProductDaoImpl(url, username, password);
    }

    public void writeXML() throws ParserConfigurationException, TransformerException {

        List<Integer> result = productDao.getAllUsers();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element entries = doc.createElement("entries");
        doc.appendChild(entries);

        int i = 0;
        Iterator<Integer> iterator = result.iterator();
        while (iterator.hasNext()) {
            Element row = doc.createElement("entry");
            entries.appendChild(row);
            Element node = doc.createElement("field");
            Integer temp = iterator.next();
            node.appendChild(doc.createTextNode(temp.toString()));
            row.appendChild(node);
        }

        DOMSource domSource = new DOMSource(doc);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
        StreamResult file = new StreamResult(new File("..\\MagnetTest\\src\\main\\resources\\1.xml"));
        transformer.transform(domSource, file);

    }

    public void writeXSLTFromXML() throws TransformerException {
        TransformerFactory transformFactory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("..\\MagnetTest\\src\\main\\resources\\style.xsl"));
        Transformer transformerXSLT = transformFactory.newTransformer(xslt);
        Source xml = new StreamSource(new File("..\\MagnetTest\\src\\main\\resources\\1.xml"));
        transformerXSLT.transform(xml, new StreamResult(new File("..\\MagnetTest\\src\\main\\resources\\2.xml")));
    }

    @Override
    public void countXSLT() {
        File xmlFile = new File("..\\MagnetTest\\src\\main\\resources\\2.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        long sum = 0;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("entry");

            for (int i = 0; i < nodeList.getLength(); i++) {

                sum += Integer.parseInt(nodeList.item(i).getAttributes().getNamedItem("field").getNodeValue());

            }

            System.out.println("sum : " + sum);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void fillTable(int quantity) {
        productDao.fillTable(quantity);
    }

    @Override
    public void closeSessionFactory() {
        productDao.closeSessionFactory();
    }
}
