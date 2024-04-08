package godrok;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public record Csatorna(List<Integer> melysegek, List<Godor> godrok) {

    public Csatorna() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public List<Integer> melysegek() {
        return new ArrayList<>(melysegek);
    }

    public List<Godor> godrok() {
        return new ArrayList<>(godrok);
    }

    public void loadFromFile(Path path) {
        String line;
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            while ((line = bufferedReader.readLine()) != null) {
                int melyseg = Integer.parseInt(line);
                melysegek.add(melyseg);
            }
        } catch (IOException ioException) {
            throw new IllegalStateException("Can not read file", ioException);
        }
    }

    public int getMelysegByTavolsag(int tavolsag) {
        return melysegek.get(tavolsag - 1);
    }

    public double getErintetlenFelszinArany() {
        int csatornahossz = melysegek().size();
        long erintetlen = melysegek.stream()
                .filter(number -> number == 0)
                .count();
        return (double) erintetlen / csatornahossz;
    }

    private void loadGodrok() {
        List<Integer> godorMelysegek = new ArrayList<>();
        int j = 1;
        for (int i = 1; i < melysegek.size() - 1; i += j, j = 1) {
            if (melysegek.get(i) > 0) {
                godorMelysegek.add(melysegek.get(i));
                while (melysegek.get(i + j) > 0) {
                    godorMelysegek.add(melysegek.get(i + j));
                    j++;
                }
                godrok.add(new Godor(i + 1, i + j, godorMelysegek));
                godorMelysegek = new ArrayList<>();
            }
        }
    }

    public void writeGodrokToFile(Path path) {
        loadGodrok();
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            for (Godor godor : godrok) {
                bufferedWriter.write(godor.melysegekToString().concat(System.lineSeparator()));
            }
        } catch (IOException ioException) {
            throw new IllegalStateException("Can not write file", ioException);
        }
    }

    public Godor getGodorByTavolsag(int tavolsag) {
        return godrok.stream()
                .filter(godor -> godor.kezdet() <= tavolsag && godor.vege() >= tavolsag)
                .findAny()
                .orElse(null);
    }
}
