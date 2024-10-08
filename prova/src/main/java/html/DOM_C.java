package html;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOM_C {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, Exception {
  
        Document emptyDocument = createEmptyDocument();
        String serializedEmptyDoc = serializeDocument(emptyDocument);
        System.out.println(serializedEmptyDoc);

        String citiesFilePath = "prova/src/main/resources/city.xml"; 
        String addressesFilePath = "prova/src/main/resources/address.xml"; 
        Map<String, Integer> cityCount = new HashMap<>();

        try {
            Document citiesDoc = parseXMLFile(citiesFilePath);
            Document addressesDoc = parseXMLFile(addressesFilePath);

            NodeList cityList = citiesDoc.getElementsByTagName("city");
            NodeList addressList = addressesDoc.getElementsByTagName("address");

            for (int i = 0; i < addressList.getLength(); i++) {
                String cityId = addressList.item(i).getAttributes().getNamedItem("city_id").getNodeValue();
                String cityName = getCityNameById(cityId, cityList);
                if (cityName != null) {
                    cityCount.put(cityName, cityCount.getOrDefault(cityName, 0) + 1);
                }
            }

            List<String> filteredCities = cityCount.entrySet().stream()
                    .filter(entry -> entry.getValue() > 1) 
                    .map(Map.Entry::getKey)
                    .sorted() 
                    .collect(Collectors.toList());

            Document htmlDocument = createHtmlDocument(filteredCities);

            StringWriter stringWriter = new StringWriter();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "html");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes"); 
            transformer.transform(new DOMSource(htmlDocument), new StreamResult(stringWriter));

            System.out.println(stringWriter.toString());

        } catch (Exception e) {
        }
    }

    private static Document parseXMLFile(String filePath) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(filePath));
    }

    private static String getCityNameById(String cityId, NodeList cityList) {
        for (int i = 0; i < cityList.getLength(); i++) {
            if (cityList.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(cityId)) {
                return cityList.item(i).getAttributes().getNamedItem("name").getNodeValue();
            }
        }
        return null; 
    }

    private static Document createHtmlDocument(List<String> cities) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element ulElement = document.createElement("ul");
        document.appendChild(ulElement);

        for (String city : cities) {
            Element liElement = document.createElement("li");
            liElement.setTextContent(city);
            ulElement.appendChild(liElement);
        }

        return document;
    }


    private static Document createEmptyDocument() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.newDocument(); 
    }


    private static String serializeDocument(Document document) throws Exception {
        StringWriter stringWriter = new StringWriter();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes"); 


        transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
        return stringWriter.toString(); 
    }
}
