package day3;

import challenge.Challenge;

/**
 * @author Miles
 * @since 03.12.2023
 */
public class Day03 extends Challenge {


    public Day03() {
        super(3);
    }

    @Override
    public void firstPart() {
        int sum = 0;

        // Durch die einzelnen Lines iterieren
        for (int i = 0; i < lines.size(); i++) {
            final String line = lines.get(i);
            StringBuilder numberBuilder = new StringBuilder();

            // Durch den String iterieren
            for (int j = 0; j < line.length(); j++) {
                final char c = line.charAt(j);
                // Ziffer an den String hängen
                if (Character.isDigit(c)) {
                    numberBuilder.append(c);

                    // Wenn wir noch nicht beim letzten Char angekommen sind, können
                    // ja noch weitere Zahlen folgen
                    if (j != line.length() - 1) {
                        continue;
                    }
                }

                if (numberBuilder.isEmpty()) {
                    continue;
                }

                // Zahl vollständig, auf Gültigkeit überprüfen
                final String stringNumber = numberBuilder.toString();
                final int number = Integer.parseInt(stringNumber);

                // Wenn das Symbol rechts neben der Zahl gültig ist
                if (this.isValidSymbol(c)) {
                    sum += number;
                    numberBuilder.setLength(0);
                    continue;
                }

                int numberIndex = j - stringNumber.length();
                // Wenn sich die Zahl nicht an erster Stelle im String befindet
                if (numberIndex - 1 > 0) {
                    // Wenn das Symbol links neben der Zahl gültig ist
                    if (this.isValidSymbol(line.charAt(numberIndex - 1))
                            || this.isValidSymbol(line.charAt(numberIndex))) {
                        sum += number;
                        numberBuilder.setLength(0);
                        continue;
                    }
                }

                // Wenn wir uns nicht in der ersten Line befinden und es
                // deshalb eine vorherige Line gibt
                if (i != 0) {
                    if (this.checkVertical(i, numberIndex, stringNumber.length() + 2, true)) {
                        sum += Integer.parseInt(stringNumber);
                        numberBuilder.setLength(0);
                        continue;
                    }
                }

                // Wenn wir uns nicht in der letzten Line befinden und es
                // deshalb eine nächste Line gibt
                if (i != lines.size() - 1) {
                    if (this.checkVertical(i, numberIndex, stringNumber.length() + 2, false)) {
                        sum += Integer.parseInt(stringNumber);
                        numberBuilder.setLength(0);
                        continue;
                    }
                }
                numberBuilder.setLength(0);
            }
        }


        System.out.println("Part 1: " + sum);
    }

    @Override
    public void secondPart() {

    }

    private boolean checkVertical(int i, int index, int interval, boolean previous) {
        final int lineIndex = previous
                ? i - 1
                : i + 1;

        final String lineToCheck = lines.get(lineIndex);
        final int temp = index > 0
                ? index - 1
                : 0;
        final int condition = temp + interval;

        // Durch die Chars über der Zahl iterieren, um auf vertikale
        // und diagonale Nachbarn zu prüfen
        for (int k = temp; k < condition; k++) {
            if (this.isValidSymbol(lineToCheck.charAt(k))) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidSymbol(char c) {
        return !Character.isLetterOrDigit(c)
                && c != '.';
    }

    public static void main(String[] args) {
        new Day03();
    }
}
