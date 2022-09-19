package ru.gb.lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJExamplesTest {
    @Test
    void assertJTest() {
        //Assumptions.assumeTrue(1 == 2);
        List<String> stringList = Arrays.asList("test1", "test3", "test5");

    Assertions.assertAll(
                () -> assertThat(new Functions().isPalindrom("123")).isFalse(),
                () -> assertThat(5).isGreaterThan(4).isLessThan(3),
                () -> assertThat(stringList).containsAnyOf("test6", "test1")
        );


    }
}
