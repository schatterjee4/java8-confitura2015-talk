package pl.touk;

import pl.touk.domain.Address;
import pl.touk.domain.City;
import pl.touk.domain.Person;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class WorkshopOptional {

    public static String fooJava7(Map<String, Person> people) {
        final Person kowalski = people.get("Kowalski");

        if (kowalski != null) {
            final Address address = kowalski.getAddress();

            if (address != null) {
                final City city = address.getCity();

                if (city != null) {
                    final String cityName = city.getCityName();

                    if (!cityName.isEmpty()) {
                        return cityName;
                    }
                }
            }
        }
        return "UNKNOWN";
    }

    public static String fooJava8(Map<String, Person> people) {
        return Optional.ofNullable(people.get("Kowalski"))
                .map(Person::getAddress)
                .map(Address::getCity)
                .map(City::getCityName)
                .filter(name -> !name.isEmpty())
                .orElse("UNKNOWN");
    }

    public static String fooJava8FlatMap(Map<String, Person> people) {
        return Optional.ofNullable(people.get("Kowalski"))
                .map(Person::getAddress)
                .flatMap(Address::getOptionalCity) // because we do not want Optional<Optional<City>>
                .map(City::getCityName)
                .filter(name -> !name.isEmpty())
                .orElse("UNKNOWN");
    }

    public static String fooJava75(Map<String, Person> people) {
        final Optional<Person> kowalski = Optional.ofNullable(people.get("Kowalski"));

        if (kowalski.isPresent()) {
            final Optional<City> city = kowalski.get().getAddress().getOptionalCity();
            if (city.isPresent()) {
                return city.get().getCityName();
            }
        }

        return "UNKNOWN";
    }

    public static Date parseDateFrom(String date) throws ParseException {
        return Optional.ofNullable(date)
                .map(Instant::parse) // 1st step: String to Instant
                .map(Date::from) // 2nd step: Instant to Date
                .orElseThrow(() -> new ParseException("Could not parse given date", 0));
    }
}
