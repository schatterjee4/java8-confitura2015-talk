package pl.touk;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import pl.touk.domain.Address;
import pl.touk.domain.City;
import pl.touk.domain.Person;

import static org.assertj.core.api.Assertions.*;

public class WorkshopOptionalTest {

    @Test
    public void shouldRetrieveKowalskiCityName() throws Exception {
        // given
        final ImmutableMap<String, Person> people = ImmutableMap.of("Kowalski", new Person(new Address(new City("Warszawa"))));

        // when
        final String r1 = WorkshopOptional.fooJava7(people);
        final String r2 = WorkshopOptional.fooJava8(people);
        final String r3 = WorkshopOptional.fooJava8FlatMap(people);

        // then
        assertThat(r1).isEqualTo(r2).isEqualTo(r3).isEqualTo("Warszawa");
    }

    @Test
    public void shouldRetrieveUnknownCityName() throws Exception {
        // given
        final ImmutableMap<String, Person> people = ImmutableMap.of();

        // when
        final String r1 = WorkshopOptional.fooJava7(people);
        final String r2 = WorkshopOptional.fooJava8(people);
        final String r3 = WorkshopOptional.fooJava8FlatMap(people);

        // then
        assertThat(r1).isEqualTo(r2).isEqualTo(r3).isEqualTo("UNKNOWN");
    }
}