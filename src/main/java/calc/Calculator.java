package calc;

public class Calculator {
    static int sum(String input) {
        return Parser.run(input).stream().reduce(0, Integer::sum);
    }
}