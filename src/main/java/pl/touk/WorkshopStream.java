package pl.touk;

import static java.util.stream.Collectors.*;

import pl.touk.domain.Address;
import pl.touk.domain.City;
import pl.touk.domain.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkshopStream {
    Collection<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);


    Collection<Person> people;

    public List<String> fooStreamJava7() {
        final ArrayList<String> result = new ArrayList<>();
        for (Person person : people) {
            final String name = person.getAddress().getCity().getCityName();
            if (!name.isEmpty()) {
                result.add(name);
            }
        }

        return result;
    }

    public List<String> fooStreamJava8() {
        return people.stream()
                .map(Person::getAddress)
                .map(Address::getCity)
                .map(City::getCityName)
                .filter(name -> !name.isEmpty())
                .collect(toList());
    }

    public String getCityNameFor(String person) {
        return people.stream()
                .filter(Predicate.isEqual("Kowalski"))
                .map(Person::getAddress)
                .map(Address::getCity)
                .map(City::getCityName)
                .findAny()
                .orElseThrow(RuntimeException::new);


    }

    public Map<Person, String> getPersonCityMap(Collection<Person> people) {
        return people.stream()
                .collect(toMap(p -> p, p -> p.getAddress().getCity().getCityName()));

    }

    public Integer getLongestCityNameLength (Collection<Person> people) {
        return people.stream()
                .map(p -> p.getAddress().getCity().getCityName())
                .reduce((a, b) -> a.length() > b.length() ? a : b)
                .map(String::length)
                .orElse(0);
    }

    public String getCitiesAsList(Collection<Person> people) {
        return people.stream()
                .map(p -> p.getAddress().getOptionalCity())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(City::getCityName)
                .distinct()
                .collect(Collectors.joining(","));
    }

    public static void main(String[] args) {
        final Stream<Integer> stream = Stream.of(1, 2, 3)
                .map(i -> {
                    System.out.println(i.toString());
                    return i;
                });
        // prints nothing :(
    }



}
