package day2;

import challenge.Challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Miles
 * @since 02.12.2023
 */
public class Day02 extends Challenge {

    private List<Game> games;

    public Day02() {
        super(2);
    }

    public static void main(String[] args) {
        new Day02();
    }

    @Override
    public void additionalSetup() {
        this.games = lines.stream()
                .map(this::parse)
                .toList();
    }

    @Override
    public void firstPart() {
        int sum = 0;

        for (Game game : games) {
            boolean isValidGame = true;

            for (Set set : game.getSets()) {
                if (set.getRed() > 12 || set.getGreen() > 13 || set.getBlue() > 14) {
                    isValidGame = false;
                    break;
                }
            }

            if (isValidGame) {
                sum += game.getId();
            }
        }

        System.out.println("Part 1: " + sum);
    }

    @Override
    public void secondPart() {
        int power = 0;

        for (Game game : games) {
            int redPower = game.getSets().stream()
                    .map(Set::getRed)
                    .reduce(Math::max)
                    .orElse(0);

            int greenPower = game.getSets().stream()
                    .map(Set::getGreen)
                    .reduce(Math::max)
                    .orElse(0);

            int bluePower = game.getSets().stream()
                    .map(Set::getBlue)
                    .reduce(Math::max)
                    .orElse(0);

            power = power + (redPower * greenPower * bluePower);
        }

        System.out.println("Part 2: " + power);
    }

    private Game parse(String line) {
        Game game = new Game();
        game.setId(Integer.parseInt(line.substring(5, line.indexOf(':'))));

        String[] sets = line.substring(line.indexOf(':') + 1)
                .trim()
                .split(";");

        for (String stringSet : sets) {
            Set set = new Set();
            game.getSets().add(set);

            String[] cubes = stringSet.split(",");

            for (String cube : cubes) {
                String[] s = cube.trim().split(" ");

                final Color color = Color.valueOf(s[1].toUpperCase());
                final int amount = Integer.parseInt(s[0]);

                switch (color) {
                    case RED -> set.setRed(amount);
                    case GREEN -> set.setGreen(amount);
                    case BLUE -> set.setBlue(amount);
                }
            }
        }

        return game;
    }
}
