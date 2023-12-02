package challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Miles
 * @since 02.12.2023
 */
public abstract class Challenge {

    protected final List<String> lines = new ArrayList<>();
    private final int day;

    public Challenge(int day) {
        this.day = day;

        this.readFile();
        this.additionalSetup();
        this.firstPart();
        this.secondPart();
    }

    // Nothing by default
    public void additionalSetup() {

    }

    public abstract void firstPart();

    public abstract void secondPart();

    public void readFile() {
        final String fileName = "input_day" + day + ".txt";

        try (FileReader fileReader = new FileReader("input_day" + day + ".txt");
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Could not read file " + fileName + ": " + e.getMessage());
        }
    }
}
