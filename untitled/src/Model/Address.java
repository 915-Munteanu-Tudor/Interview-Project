package Model;

public class Address {
    private String country;
    private String city;


    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    @Override
    public String toString() {
        return country + "," + city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
