import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JUnitTestingCalculator {
    private static long suiteStartTime;
    private long testStartTime;

    @BeforeAll
    public static void BeforeAll() {
        System.out.println("Tests beginning");
        suiteStartTime = System.nanoTime();
    }

    @AfterAll
    public static void AfterAll() {
        System.out.println("Test finished " + (System.nanoTime() - suiteStartTime));
    }

    @BeforeEach
    public void BeforeEachMethod() {
        System.out.println("Test started");
        testStartTime = System.nanoTime();
    }

    @AfterEach
    public void AfterEachMethod() {
        System.out.println("Test compiled " + (System.nanoTime() - testStartTime));
    }

    @Test
    public void sum(){
        System.out.println("testing sum");
        Calculator calc1 = new Calculator();

        final int x = 2, y = 2, expectedResult = 4;

        final int result = calc1.plus.apply(x, y);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void divide(){
        System.out.println("testing divide");
        Calculator calc2 = new Calculator();

        final int x = 2, y = 0;

        Assertions.assertThrows(ArithmeticException.class, ()-> calc2.devide.apply(x, y));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 500, -70, 0})
    public void multiply(int x) {
        System.out.println("testing multiply");
        Calculator calc3 = new Calculator();

        final int y = 0, expectedResult = 0;

        int result = calc3.multiply.apply(x, y);

        Assertions.assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @MethodSource("methodSource")
    public void minus(int x, int y, int expectedResult) {
        System.out.println("testing minus");
        Calculator calc4 = new Calculator();

        int result = calc4.minus.apply(x, y);

        Assertions.assertEquals(expectedResult, result);
    }

    public Stream<Arguments> methodSource(){
        return Stream.of(
                Arguments.of(10, 5, 5),
                Arguments.of(10, 15, -5),
                Arguments.of(10, 0, 10)
        );
    }

    @Test
    public void isPositive(){
        System.out.println("testing \"isPositive\"");
        Calculator calc5 = new Calculator();

        final int x = 10;

        Assertions.assertTrue(calc5.isPositive.test(x));
    }

}
