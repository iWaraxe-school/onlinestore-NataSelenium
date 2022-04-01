package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLDomParser {

    public static Map<String,String> parseXMLFile()
    {
        Map<String,String > xmlMap = new LinkedHashMap<>();
        File file = new File("config.xml");
        Document doc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            doc = factory.newDocumentBuilder().parse(file);
        } catch (SAXException e) {
            System.out.println("SAXException: " + e);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (ParserConfigurationException e) {
            System.out.println("ParseConfigurationException: " + e);
        }
        Node rootNode = doc.getFirstChild();

        NodeList childNodes = rootNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++)
        {
            if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                xmlMap.put(childNodes.item(i).getNodeName(),childNodes.item(i).getTextContent());
            }
        }
        return xmlMap;
    }
}
