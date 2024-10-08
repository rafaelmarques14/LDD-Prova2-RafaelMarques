package jaxb.addresses_jaxb;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "addresses")
@XmlAccessorType(XmlAccessType.FIELD)
public class Addresses {
    @XmlElement(name = "address")
    private List<Address> addressList;

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    @Override
    public String toString() {
        return addressList.stream()
                .map(Address::toString)
                .reduce((a, b) -> a + "\n" + b)
                .orElse("No addresses found.");
    }
}
