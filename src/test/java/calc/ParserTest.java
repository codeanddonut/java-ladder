package calc;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ParserTest {
    @Test
    void emptyTest() {
        assertThat(Parser.run("")).isEqualTo(Arrays.asList(0));
    }

    @Test
    void parseSingleNumberTest() {
        assertThat(Parser.run("1")).isEqualTo(Arrays.asList(1));
    }

    @Test
    void parseMultipleNumbersTest() {
        assertThat(Parser.run("1,2:3")).isEqualTo(Arrays.asList(1, 2, 3));
    }

    @Test
    void parseWithCustomDelimiterTest() {
        assertThat(Parser.run("//!\n1!2,3:4")).isEqualTo(Arrays.asList(1, 2, 3, 4));
    }
}