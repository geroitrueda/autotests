package ru.gb.lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriangleTest {
    private static Logger logger = LoggerFactory.getLogger(FunctionTest.class);

    @ParameterizedTest
    @CsvSource({"a<= 0, false", "b<= 0, false", "c<= 0, false"})
    void PositiveLenght(String testWord, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, new Functions().isPalindrom(testWord));

//public class TriangleTest {
        // Triangle triangle;

        //@Nested
        //  class PositiveLenght
    }
}