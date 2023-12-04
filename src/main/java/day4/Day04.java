package day4;

import challenge.Challenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Miles
 * @since 04.12.2023
 */
public class Day04 extends Challenge {

    private List<ScratchCard> cards;

    public Day04() {
        super(4);
    }

    @Override
    public void additionalSetup() {
        this.cards = lines.stream()
                .map(this::parse)
                .toList();
    }

    @Override
    public void firstPart() {
        int sum = 0;

        for (ScratchCard card : cards) {
            int matches = 0;
            int factor = 1;

            for (int winningCandidate : card.getWinningCandidates()) {
                if (card.getWinningNumbers().contains(winningCandidate)) {
                    sum += factor;
                    matches++;

                    if (matches >= 2) {
                        factor *= 2;
                    }
                }
            }
        }

        System.out.println("Part 1: " + sum);
    }

    @Override
    public void secondPart() {
        Map<Integer, Integer> instances = new HashMap<>();
        copyScratchCards(cards.get(0), instances);

        int sum = instances.values().stream()
                .reduce(Integer::sum)
                .get();

        System.out.println("Part 2: " + sum);
    }

    private ScratchCard parse(String line) {
        ScratchCard card = new ScratchCard();
        card.setId(Integer.parseInt(line.substring(5, line.indexOf(':')).trim()));

        line = line.substring(9);
        String[] parts = line.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            String[] numbers = parts[i].split(" ");

            for (int j = 0; j < numbers.length; j++) {
                String stringNumber = numbers[j].trim();
                if (stringNumber.isEmpty()) {
                    continue;
                }

                final int number = Integer.parseInt(stringNumber);

                if (i == 0) {
                    card.getWinningNumbers().add(number);
                } else {
                    card.getWinningCandidates().add(number);
                }
            }
        }

        return card;
    }

    /* ScratchCards werden rekursiv "kopiert" / "instanziiert" */
    private void copyScratchCards(ScratchCard card, Map<Integer, Integer> instances) {
        instances.putIfAbsent(card.getId(), 1);

        int matching = (int) card.getWinningCandidates().stream()
                .filter(number -> card.getWinningNumbers().contains(number))
                .count();

        final boolean isLastCard = card.getId() >= cards.size();

        // Nächste ScratchCard der Liste
        ScratchCard nextCard = null;
        if (!isLastCard) {
            nextCard = cards.get(card.getId());
        }

        // Wenn es keine übereinstimmenden Zahlen gibt, wird zur
        // nächsten Karte gesprungen, sofern es eine gibt
        if (matching == 0
                && nextCard != null) {
            copyScratchCards(nextCard, instances);
            return;
        }

        // Anzahl an Karten, welche kopiert werden sollen
        final int cardsToCopy = card.getId() + matching;
        for (int i = card.getId(); i < cardsToCopy; i++) {
            ScratchCard wonCopy = cards.get(i);

            // Anzahl an Instanzen, welche von den Kopien erstellt werden sollen
            final int instancesToCreate = instances.get(card.getId());
            for (int j = 0; j < instancesToCreate; j++) {
                // Anzahl an bereits bestehenden Instanzen der Kopie
                final int existingInstances = instances.getOrDefault(wonCopy.getId(), 1);
                // Instanzen der Kopie inkrementieren
                instances.put(wonCopy.getId(), existingInstances + 1);
            }
        }

        // Sollte es weitere Karten geben, wird zur nächsten gesprungen
        if (!isLastCard
                && nextCard != null) {
            copyScratchCards(nextCard, instances);
        }
    }

    public static void main(String[] args) {
        new Day04();
    }
}
