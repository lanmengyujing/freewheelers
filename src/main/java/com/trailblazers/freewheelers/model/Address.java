package com.trailblazers.freewheelers.model;

public class Address {
    private String streetOne, streetTwo, city, state, country, zip;

    public Address(String streetOne, String streetTwo, String city, String state, String country, String zip) {
        this.streetOne = streetOne;
        this.streetTwo = streetTwo;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }

    public Address() {

    }

    public Address setStreetOne(String streetOne) {
        this.streetOne = streetOne;
        return this;
    }

    public Address setStreetTwo(String streetTwo) {
        this.streetTwo = streetTwo;
        return this;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    public Address setCountry(String country) {
        this.country = country;
        return this;
    }

    public Address setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public String getStreetOne() {
        return streetOne;
    }

    public String getStreetTwo() {
        return streetTwo;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZip() {
        return zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        if (state != null ? !state.equals(address.state) : address.state != null) return false;
        if (streetOne != null ? !streetOne.equals(address.streetOne) : address.streetOne != null) return false;
        if (streetTwo != null ? !streetTwo.equals(address.streetTwo) : address.streetTwo != null) return false;
        if (zip != null ? !zip.equals(address.zip) : address.zip != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = streetOne != null ? streetOne.hashCode() : 0;
        result = 31 * result + (streetTwo != null ? streetTwo.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        return result;
    }
}
