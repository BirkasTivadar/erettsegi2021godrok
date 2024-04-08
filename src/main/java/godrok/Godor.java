package godrok;

import java.util.ArrayList;
import java.util.List;

public record Godor(int kezdet, int vege, List<Integer> melysegek) {

    public List<Integer> melysegek() {
        return new ArrayList<>(melysegek);
    }

    public String melysegekToString() {
        StringBuilder stringBuilder = new StringBuilder();
        melysegek
                .forEach(n -> stringBuilder.append(n).append(" "));
        return stringBuilder.toString();
    }

    public int getMaxmelyseg() {
        return melysegek.stream()
                .max(Integer::compare)
                .orElse(0);
    }

    public int getTerfogat() {
        return melysegek.stream()
                .mapToInt(Integer::intValue)
                .sum() * 10;
    }

    public int getVizmennyiseg() {
        return getTerfogat() - (vege - kezdet + 1) * 10;
    }

    public boolean isMelyul() {
        int index = melysegek.indexOf(getMaxmelyseg());
        for (int i = 1; i < index; i++) {
            if (melysegek.get(i) < melysegek.get(i - 1)) return false;
        }
        for (int i = index + 1; i < melysegek.size(); i++) {
            if (melysegek.get(i) > melysegek.get(i - 1)) return false;
        }
        return true;
    }
}
