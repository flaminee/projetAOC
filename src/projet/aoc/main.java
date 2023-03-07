package projet.aoc;

import projet.aoc.strategy.Atomique;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class main {

    public static void main(String[] args) throws InterruptedException {

        ArrayList<Canal> canalList = new ArrayList<>();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(20);

        Afficheur afficheur1 = new Afficheur("Afficheur 1");
        Afficheur afficheur2 = new Afficheur("Afficheur 2");
        Afficheur afficheur3 = new Afficheur("Afficheur 3");

        canalList.add(new Canal(afficheur1));
        canalList.add(new Canal(afficheur2));
        canalList.add(new Canal(afficheur3));

        Atomique atomique = new Atomique();

        Capteur capteur = new Capteur(canalList, atomique);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                capteur.tick();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 0,1000, TimeUnit.MILLISECONDS);

    }
}
