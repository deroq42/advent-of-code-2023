package day2;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miles
 * @since 02.12.2023
 */
@Getter
@Setter
public class Game {

    private int id;
    private final List<Set> sets = new ArrayList<>();
}
