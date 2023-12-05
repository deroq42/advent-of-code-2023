package day5;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miles
 * @since 05.12.2023
 */
@Getter
@Setter
public class Almanac {

    private final List<Long> seeds = new ArrayList<>();
    private final List<CategoryMap> maps = new ArrayList<>();
}
