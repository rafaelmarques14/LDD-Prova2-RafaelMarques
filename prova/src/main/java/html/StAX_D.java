package html;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StAX_D {
    public static void main(String[] args) {
        String filePath = "prova/src/main/resources/address.xml"; 
        Map<String, Integer> districtCount = new HashMap<>(); 

        try {

            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            File file = new File(filePath);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(new java.io.FileReader(file));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals("address")) {

                        Attribute districtAttribute = startElement.getAttributeByName(new javax.xml.namespace.QName("district"));
                        if (districtAttribute != null) {
                            String district = districtAttribute.getValue();
                            districtCount.put(district, districtCount.getOrDefault(district, 0) + 1); 
                        }
                    }
                }
            }

            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            StringWriter stringWriter = new StringWriter();
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(stringWriter);

            writer.writeStartElement("table");
            writer.writeCharacters("\n");

            writer.writeStartElement("thead");
            writer.writeCharacters("\n");
            writer.writeStartElement("tr");
            writer.writeCharacters("\n");
            writer.writeStartElement("th");
            writer.writeCharacters("Distrito");
            writer.writeEndElement(); // th
            writer.writeCharacters("\n");
            writer.writeStartElement("th");
            writer.writeCharacters("Quantidade");
            writer.writeEndElement(); // th
            writer.writeCharacters("\n");
            writer.writeEndElement(); // tr
            writer.writeCharacters("\n");
            writer.writeEndElement(); // thead

            writer.writeStartElement("tbody");
            writer.writeCharacters("\n");

            for (Map.Entry<String, Integer> entry : districtCount.entrySet()) {
                writer.writeStartElement("tr");
                writer.writeCharacters("\n");

                writer.writeStartElement("td");
                writer.writeCharacters(entry.getKey());
                writer.writeEndElement(); 
                writer.writeCharacters("\n");

                writer.writeStartElement("td");
                writer.writeCharacters(String.valueOf(entry.getValue()));
                writer.writeEndElement(); 
                writer.writeCharacters("\n");

                writer.writeEndElement(); // tr
                writer.writeCharacters("\n");
            }

            writer.writeEndElement(); 
            writer.writeCharacters("\n");
            writer.writeEndElement(); 

            writer.flush();
            writer.close();

            System.out.println(stringWriter.toString());

        } catch (IOException | XMLStreamException e) {
        }
    }
}
