package godrok;

import java.nio.file.Path;
import java.util.Scanner;

public class GodrokMain {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Csatorna csatorna = new Csatorna();

//            1. Olvassa be és tárolja el a melyseg.txt fájl tartalmát! Írja ki a képernyőre, hogy az
//            adatforrás hány adatot tartalmaz!
        csatorna.loadFromFile(Path.of("src", "main", "resources", "melyseg.txt"));

        System.out.println("1. feladat");
        System.out.printf("A fájl adatainak száma: %d", csatorna.melysegek().size()).println(System.lineSeparator());


//            2. Olvasson be egy távolságértéket, majd írja a képernyőre, hogy milyen mélyen van a gödör
//            alja azon a helyen! Ezt a távolságértéket használja majd a 6. feladat megoldása során is!

        System.out.println("2. feladat");
        System.out.print("Adjon meg egy távolságértéket! ");
        int tavolsag = scanner.nextInt();
        System.out.printf("Ezen a helyen a felszín %d méter mélyen van.", csatorna.getMelysegByTavolsag(tavolsag)).println(System.lineSeparator());


//        3. Határozza meg, hogy a felszín hány százaléka maradt érintetlen és jelenítse meg 2 tizedes
//        pontossággal!

        System.out.println("3. feladat");
        System.out.printf("Az érintetlen terület aránya %,.2f%%.", csatorna.getErintetlenFelszinArany() * 100).println(System.lineSeparator());


//        4. Írja ki a godrok.txt fájlba a gödrök leírását, azaz azokat a számsorokat, amelyek egy-egy
//        gödör méterenkénti mélységét adják meg! Minden gödör leírása külön sorba kerüljön! Az
//        állomány pontosan a gödrök számával egyező számú sort tartalmazzon!

        System.out.println("4. feladat".concat(System.lineSeparator()));
        csatorna.writeGodrokToFile(Path.of("src", "main", "resources", "godrok.txt"));


//        5. Határozza meg a gödrök számát és írja a képernyőre!

        System.out.println("5. feladat");
        System.out.printf("A gödrök száma: %d", csatorna.godrok().size()).println(System.lineSeparator());


//        6. Ha a 2. feladatban beolvasott helyen nincs gödör, akkor „Az adott helyen nincs gödör.”
//        üzenetet jelenítse meg, ha ott gödör található, akkor határozza meg, hogy
//        a) mi a gödör kezdő és végpontja! A meghatározott értékeket írja a képernyőre!
//                (Ha nem tudja meghatározni, használja a további részfeladatoknál a 7 és 22
//        értéket, mint a kezdő és a végpont helyét)
//        b) a legmélyebb pontja felé mindkét irányból folyamatosan mélyül-e! Azaz a gödör
//        az egyik szélétől monoton mélyül egy pontig, és onnantól monoton emelkedik a
//        másik széléig. Az eredménytől függően írja ki a képernyőre a „Nem mélyül
//        folyamatosan.” vagy a „Folyamatosan mélyül.” mondatot!
//                c) mekkora a legnagyobb mélysége! A meghatározott értéket írja a képernyőre!
//                d) mekkora a térfogata, ha szélessége minden helyen 10 méternyi! A meghatározott
//        értéket írja a képernyőre!
//                e) a félkész csatorna esőben jelentős mennyiségű vizet fogad be. Egy gödör annyi
//        vizet képes befogadni anélkül, hogy egy nagyobb szélvihar hatására se öntsön
//        ki, amennyi esetén a víz felszíne legalább 1 méter mélyen van a külső felszínhez
//        képest. Írja a képernyőre ezt a vízmennyiséget!

        System.out.println("6. feladat");
        Godor godor = csatorna.getGodorByTavolsag(tavolsag);
        if (godor == null) {
            System.out.println("Az adott helyen nincs gödör.");
        } else {
            System.out.println("a)");
            System.out.printf("A gödör kezdete: %d méter, a gödör vége: %d méter.", godor.kezdet(), godor.vege()).println();
            System.out.println("b)");
            System.out.println(godor.isMelyul() ? "Folyamatosan mélyül." : "Nem mélyül folyamatosan.");
            System.out.println("c)");
            System.out.printf("A legnagyobb mélysége %d méter.", godor.getMaxmelyseg()).println();
            System.out.println("d)");
            System.out.printf("A térfogata %d m^3.", godor.getTerfogat()).println();
            System.out.println("e)");
            System.out.printf("A vízmennyiség %d m^3.", godor.getVizmennyiseg());
        }
    }
}
