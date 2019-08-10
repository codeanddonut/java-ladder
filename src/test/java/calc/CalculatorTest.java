package calc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CalculatorTest {
    @Test
    void nullTest() {
        assertThat(Calculator.sum(null)).isEqualTo(0);
    }

    @Test
    void emptyTest() {
        assertThat(Calculator.sum("")).isEqualTo(0);
    }

    @Test
    void blankTest() {
        assertThat(Calculator.sum("  ")).isEqualTo(0);
    }

    @Test
    void singleNumberTest() {
        assertThat(Calculator.sum("5")).isEqualTo(5);
    }

    @Test
    void splitCommaTest() {
        assertThat(Calculator.sum("2,7")).isEqualTo(9);
    }

    @Test
    void splitCommaColonTest() {
        assertThat(Calculator.sum("1,3:6")).isEqualTo(10);
    }

    @Test
    void splitCustomDelimiterTest() {
        assertThat(Calculator.sum("//;\n4;7,8")).isEqualTo(19);
    }

    @Test
    void negativeNumberTest() {
        assertThatThrownBy(() -> Calculator.sum("-3,2,6"));
    }

    @Test
    void wrongInputTest() {
        assertThatThrownBy(() -> Calculator.sum(" a"));
    }
}