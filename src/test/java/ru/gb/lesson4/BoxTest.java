package ru.gb.lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class BoxTest {
    Box box;

    @Nested
    class WhenBoxIsEmpty{
        @BeforeEach
        void createBox() {
            box = new Box();
        }

        @Test
        void exceptionWhenTryToDeleteBall() {
            Assertions.assertThrows(BoxIsEmptyException.class, () -> box.deleteBall());
            assertThatExceptionOfType(BoxIsEmptyException.class).isThrownBy(() -> box.deleteBall());
        }

        @Nested
        class WhenOneBall{
            @BeforeEach
            void addBall() {
                box.addBall();
            }
            @Test
            void deleteBallTest() throws BoxIsEmptyException{
                box.deleteBall();
                assertThat(box.getBallsCount()).isEqualTo(0);

            }
        }
    }
}
