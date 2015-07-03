package pl.touk.domain;

public final class Person {
    Address address;

    public Person(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
