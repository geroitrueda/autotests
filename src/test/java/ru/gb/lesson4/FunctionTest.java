package ru.gb.lesson4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Arrays;
import java.util.List;

public class FunctionTest {
    private static Logger logger = LoggerFactory.getLogger(FunctionTest.class);

    @BeforeAll
    static void beforeAll() {
        System.out.println("Выполнится 1 раз пред всеми тестами класса");
        logger.info("INFOLOG");
        WebDriverManager.safaridriver().setup();
    }
    //Trace, debug, info, warn, error
    @BeforeEach
    void beforeEach() {
        System.out.println("Выполнится перед запуском каждого теста");
    }

    @Test
    @DisplayName("Проверка метода isPalindrome со словом-палиндромом")
    void isPalindromeTest() {
        boolean result = new Functions().isPalindrom("123321");
        Assertions.assertEquals(true, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123321", "1234321"})
    @DisplayName("Проверка метода isPalindrome со словом-палиндромом с нечетным количеством символов")
    void isPalindromeTest2(String testWord) {
       boolean result = new Functions().isPalindrom(testWord);
        Assertions.assertEquals(true, result);
    }
    @ParameterizedTest
    @CsvSource({"123, false", "1234321, true"})
    void isPalindrome(String testWord, boolean expectedResult) {
        Assertions.assertEquals(expectedResult, new Functions().isPalindrom(testWord));
    }
    @ParameterizedTest
    @MethodSource("catAndAgeDataProvider")
    void catAndAgeTest(Cat cat, Integer age) {
        Assertions.assertEquals(cat.getAge(), age);

    }
    private static List<Arguments> catAndAgeDataProvider() {
        return Arrays.asList(
                Arguments.of(new Cat("Test1", 10), 10),
                Arguments.of(new Cat("Test2", 11), 12)
        );
    }

    @AfterEach
    void  afterEach() {
        System.out.println("Метод выполнится после каждого теста");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Метод выполнится 1 раз после выполнения всех тестов в классе");
    }
}
