package io.duryskuba.interestmatcher.TagService.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionUtils {

    public static <T> HashSet<T> of(T... items) {
       return Stream.of(items)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public static <T> HashSet<T> of(HashSet<T>... sets) {
        HashSet<T> result = new HashSet<>();
        for(HashSet<T> set : sets) {
            result.addAll(set);
        }
        return result;
    }


}