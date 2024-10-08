package jaxb.cities_jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class City {
    @XmlAttribute(name = "id")
    private int id;

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "country_id")
    private int countryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "City [id=" + id + ", name=" + name + ", countryId=" + countryId + "]";
    }
}
