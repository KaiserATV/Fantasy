package aktionen;

import java.util.Random;

public class FeldAktionen {
    
    public void betreteGebaeude() {
        // Hier können Sie den Code einfügen, um die Gebäude-GUI zu öffnen
        System.out.println("Gebäude betreten!");
    }

    public void wahrscheinlichkeitMonsterInteraktion() {
        Random rand = new Random();
        int chance = rand.nextInt(100); // Ein Wert zwischen 0 und 99

        if(chance < 20) {  // 20% Chance auf Monsterinteraktion
            // Rufen Sie hier die Methode für die Monsterinteraktion auf
            System.out.println("Ein Monster taucht auf!");
        }
    }

    public void wahrscheinlichkeitHaendlerTreffen() {
        Random rand = new Random();
        int chance = rand.nextInt(100); // Ein Wert zwischen 0 und 99

        if(chance < 10) {  // 10% Chance auf fahrenden Händler
            // Rufen Sie hier die Methode für den fahrenden Händler auf
            System.out.println("Ein fahrender Händler wurde getroffen!");
        }
    }
}
