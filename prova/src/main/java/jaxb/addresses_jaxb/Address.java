package jaxb.addresses_jaxb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlAttribute
    private int id;

    @XmlAttribute(name = "a1")
    private String addressLine1;

    @XmlAttribute(name = "a2")
    private String addressLine2;

    @XmlAttribute
    private String district;

    @XmlAttribute(name = "city_id")
    private int cityId;

    @XmlAttribute(name = "postal_code")
    private String postalCode;

    @XmlAttribute
    private String phone;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Address [id=" + id + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 +
               ", district=" + district + ", cityId=" + cityId + ", postalCode=" + postalCode + ", phone=" + phone + "]";
    }
}
