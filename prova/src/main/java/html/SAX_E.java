package html;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX_E {

    static class City {
        String name;

        public City(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            Map<String, List<City>> countryCityMap = new HashMap<>();

            DefaultHandler handler = new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("city")) {
                        String cityName = attributes.getValue("name");
                        String countryId = attributes.getValue("country_id");
                        City city = new City(cityName);
                        countryCityMap.computeIfAbsent(countryId, k -> new ArrayList<>()).add(city);
                    }
                }
            };

            saxParser.parse("prova/src/main/resources/city.xml", handler);


            StringWriter stringWriter = new StringWriter();
            XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(stringWriter);


            for (Map.Entry<String, List<City>> entry : countryCityMap.entrySet()) {
                String countryId = entry.getKey();
                List<City> cities = entry.getValue();
                cities.sort((c1, c2) -> c1.name.compareTo(c2.name));

                xmlStreamWriter.writeStartElement("ul");
                xmlStreamWriter.writeAttribute("country", countryId);
                xmlStreamWriter.writeAttribute("count", String.valueOf(cities.size()));
                xmlStreamWriter.writeCharacters("\n"); 

                for (City city : cities) {
                    xmlStreamWriter.writeCharacters("    "); 
                    xmlStreamWriter.writeStartElement("li");
                    xmlStreamWriter.writeCharacters(city.name);
                    xmlStreamWriter.writeEndElement(); 
                    xmlStreamWriter.writeCharacters("\n"); 
                }

                xmlStreamWriter.writeEndElement(); 
                xmlStreamWriter.writeCharacters("\n"); 
            }

            xmlStreamWriter.flush();
            xmlStreamWriter.close();

            System.out.println(stringWriter.toString().trim()); 

        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
