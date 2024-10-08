package jaxb.cities_jaxb;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class CityJAXB {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Cities.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Cities cities = (Cities) unmarshaller.unmarshal(new File("prova/src/main/resources/city.xml"));
            System.out.println(cities);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
