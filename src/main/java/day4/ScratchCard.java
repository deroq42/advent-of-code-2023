package day4;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miles
 * @since 04.12.2023
 */
@Getter
@Setter
public class ScratchCard {

    private int id;
    private List<Integer> winningNumbers = new ArrayList<>();
    private List<Integer> winningCandidates = new ArrayList<>();
}
