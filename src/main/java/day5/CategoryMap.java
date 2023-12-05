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
public class CategoryMap {

    private Category source;
    private Category destination;
    private final List<CategoryMap.Entry> entries = new ArrayList<>();

    @Getter
    @Setter
    public static class Entry {

        private long destinationRangeStart;
        private long sourceRangeStart;
        private long rangeLength;
    }
}
