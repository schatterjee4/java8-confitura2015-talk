package pl.touk;

import junit.framework.Assert;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkshopTest {

    @Test
    public void shouldComposeFunctions() throws Exception {
        // given
        final Integer arg = 2;
        final Function<Integer, Integer> addOne = i -> i + 1;
        final Function<Integer, Integer> timesTwo = i -> 2 * i;

        // when
        /**
         * Returns a composed function that first applies this function to
         * its input, and then applies the {@code after} function to the result.
         */
        final Integer r1 = addOne.andThen(timesTwo).apply(arg);

        /**
         * Returns a composed function that first applies the {@code before}
         * function to its input, and then applies this function to the result.
         */
        final Integer r2 = addOne.compose(timesTwo).apply(arg);

        // then
        assertThat(r1).isEqualTo(6); // (2 + 1) * 2

        assertThat(r2).isEqualTo(5); // (2 * 2) + 1
    }
}
