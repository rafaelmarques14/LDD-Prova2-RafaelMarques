package jaxb.cities_jaxb;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cities")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cities {
    @XmlElement(name = "city")
    private List<City> cityList;

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return cityList.stream()
                .map(City::toString)
                .reduce((a, b) -> a + "\n" + b)
                .orElse("No cities available");
    }
}
