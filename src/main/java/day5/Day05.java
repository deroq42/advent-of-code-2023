package day5;

import challenge.Challenge;

import java.util.Arrays;

/**
 * @author Miles
 * @since 05.12.2023
 */
public class Day05 extends Challenge {

    private Almanac almanac;

    public Day05() {
        super(5);
    }

    @Override
    public void additionalSetup() {
        this.almanac = this.parse();
    }

    @Override
    public void firstPart() {
        long min = Long.MAX_VALUE;

        for (Long seed : almanac.getSeeds()) {
            long current = seed;

            for (CategoryMap map : almanac.getMaps()) {
                for (CategoryMap.Entry entry : map.getEntries()) {
                    if (entry.getSourceRangeStart() <= current
                            && current < entry.getSourceRangeStart() + entry.getRangeLength()) {
                        long diff = current - entry.getSourceRangeStart();
                        current = entry.getDestinationRangeStart() + diff;
                        break;
                    }
                }
            }

            min = Math.min(min, current);
        }

        System.out.println("Part 1: " + min);
    }

    @Override
    public void secondPart() {

    }

    private Almanac parse() {
        Almanac almanac = new Almanac();
        CategoryMap currentMap = null;

        for (int i = 0; i < lines.size() + 1; i++) {
            if (i == lines.size()) {
                almanac.getMaps().add(currentMap);
                break;
            }

            String line = lines.get(i);
            if (line.isBlank()) {
                if (currentMap != null) {
                    almanac.getMaps().add(currentMap);
                }
                continue;
            }

            if (i == 0) {
                line = line.substring(7);
                String[] seeds = line.split(" ");
                Arrays.stream(seeds)
                        .map(Long::parseLong)
                        .forEach(seed -> almanac.getSeeds().add(seed));
            } else {
                if (line.endsWith("map:")) {
                    line = line.substring(0, line.indexOf(' '));
                    String[] map = line.split("-to-");

                    currentMap = new CategoryMap();
                    currentMap.setSource(Category.valueOf(map[0].toUpperCase()));
                    currentMap.setDestination(Category.valueOf(map[1].toUpperCase()));
                    continue;
                }

                CategoryMap.Entry entry = new CategoryMap.Entry();
                String[] entryParts = line.split(" ");
                for (int j = 0; j < entryParts.length; j++) {
                    final String number = entryParts[j];

                    switch (j) {
                        case 0 -> entry.setDestinationRangeStart(Long.parseLong(number));
                        case 1 -> entry.setSourceRangeStart(Long.parseLong(number));
                        case 2 -> entry.setRangeLength(Long.parseLong(number));
                    }
                }

                currentMap.getEntries().add(entry);
            }
        }

        return almanac;
    }

    public static void main(String[] args) {
        new Day05();
    }
}
