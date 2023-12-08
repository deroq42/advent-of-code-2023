package day6;

import challenge.Challenge;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

/**
 * @author Miles
 * @since 08.12.2023
 */
public class Day06 extends Challenge {

    private static final Pattern numberPattern = Pattern.compile("(?:(?<![a-zA-Z0-9])-)?\\d+");

    public Day06() {
        super(6);
    }

    @Override
    public void firstPart() {
        final long ways = this.getWaysToBeatRecord(1);
        System.out.println("Part 1: " + ways);
    }

    @Override
    public void secondPart() {
        final long ways = this.getWaysToBeatRecord(2);
        System.out.println("Part 2: " + ways);
    }

    private long getWaysToBeatRecord(int part) {
        long[] times = parseLine(lines.get(0), part);
        long[] distances = parseLine(lines.get(1), part);

        int ways = 1;

        for (int i = 0; i < times.length; i++) {
            long time = times[i];
            long distance = distances[i];

            ways *= LongStream.range(1, time)
                    .filter(v -> v * (time - v) > distance)
                    .count();
        }

        return ways;
    }

    private long[] parseLine(String line, int part) {
        return parseLongs(part == 1
                ? line
                : line.replace(" ", ""));
    }

    private long[] parseLongs(String line) {
        return numberPattern.matcher(line)
                .results()
                .map(MatchResult::group)
                .mapToLong(Long::parseLong)
                .toArray();
    }

    public static void main(String[] args) {
        new Day06();
    }
}