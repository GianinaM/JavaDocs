package com.java_8_training.problems.collectors;

import javassist.bytecode.ByteArray;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static junit.framework.Assert.assertEquals;

@Ignore
public class OccurrencesTest {

    @Test
    public void occurrencesForAWord() {
        String word = "cool";
        //TODO #C9
        Map<String, Long> occ = new HashMap<>();
//        System.out.println(word.chars().toString());
        String[] myList = word.split("");
        occ = Arrays.stream(myList).collect(groupingBy(Function.identity(), counting()));

        assertEquals(2, (long) occ.get("o"));
        assertEquals(1, (long) occ.get("c"));
        assertEquals(1, (long) occ.get("l"));
        assertEquals(3, occ.size());

    }

    @Test
    public void occurrencesForAListOfSentences() {
        List<String> sentences = Arrays.asList("Hello everyone!", "Java 8 is here!");

        //TODO #C9
        Map<String, Long> occ = new HashMap<>();
        occ = sentences.stream().map(s -> s.split("")).flatMap(Arrays::stream).collect(groupingBy(Function.identity(), counting()));

        assertEquals(2, (long) occ.get("l"));
        assertEquals(4, (long) occ.get(" "));
        assertEquals(2, (long) occ.get("!"));
        assertEquals(2, (long) occ.get("a"));
        assertEquals(6, (long) occ.get("e"));
        assertEquals(16, occ.size());
    }

}
