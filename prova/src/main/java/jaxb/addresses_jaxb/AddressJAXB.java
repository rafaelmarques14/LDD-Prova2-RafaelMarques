package jaxb.addresses_jaxb;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class AddressJAXB {

    public static void main(String[] args) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(Addresses.class);
            Unmarshaller um = ctx.createUnmarshaller();
            Addresses addresses = (Addresses) um.unmarshal(new File("prova/src/main/resources/address.xml"));
            System.out.println(addresses);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
