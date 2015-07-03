package pl.touk.domain;

import java.util.Optional;

public final class Address {
    City city;

    public Address(City city) {
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public Optional<City> getOptionalCity() {
        return Optional.ofNullable(city);
    }
}
