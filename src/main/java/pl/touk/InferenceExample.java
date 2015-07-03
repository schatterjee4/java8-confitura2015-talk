package pl.touk;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class InferenceExample {

    Function<Integer, Integer> foo1 = x -> x + 1;

    Function<String, String>   foo2 = x -> x + 1;

    List<Integer> foo3 = new ArrayList<>();
}
