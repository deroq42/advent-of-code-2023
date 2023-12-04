package day1;

import challenge.Challenge;

import java.util.Map;

/**
 * @author Miles
 * @since 01.12.23
 */
public class Day01 extends Challenge {

    public Day01() {
        super(1);
    }

    private static final Map<String, String> numberReplacements = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    @Override
    public void firstPart() {
        final int sum = (int) lines.stream()
                .mapToLong(this::parseNumber)
                .sum();

        System.out.println("Part 1: " + sum);
    }

    @Override
    public void secondPart() {
        final int sum = (int) lines.stream()
                .map(this::mapAlphabeticNumbers)
                .mapToLong(this::parseNumber)
                .sum();

        System.out.println("Part 2: " + sum);
    }

    private String mapAlphabeticNumbers(String line) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            String numberCandidate = line.substring(i);

            boolean match = false;
            for (Map.Entry<String, String> replacement : numberReplacements.entrySet()) {
                if (numberCandidate.startsWith(replacement.getKey())) {
                    result.append(replacement.getValue());
                    match = true;
                }
            }

            if (!match) {
                result.append(line.charAt(i));
            }
        }

        return result.toString();
    }

    private long parseNumber(String line) {
        char firstChar = ' ';
        char lastChar = ' ';

        for (char c : line.toCharArray()) {
            if (Character.isDigit(c)) {
                if (firstChar == ' ') {
                    firstChar = c;
                }
                lastChar = c;
            }
        }

        return Long.parseLong(String.valueOf(firstChar) + lastChar);
    }

    public static void main(String[] args) {
        new Day01();
    }
}
