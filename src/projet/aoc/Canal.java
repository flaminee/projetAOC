package projet.aoc;

import projet.aoc.Afficheur;

import java.util.Random;
import java.util.concurrent.*;

public class Canal {

    private ScheduledExecutorService scheduler;
    private Afficheur afficheur;
    private Capteur capteur;

    private Random random;

    Canal(Afficheur afficheur){
        super();
        this.afficheur = afficheur;
        this.scheduler = Executors.newScheduledThreadPool(2);
        this.random = new Random();
    }

    public Future update(Capteur capteur){
        this.capteur = capteur;
        return scheduler.schedule(()->{
            try {
                afficheur.update(this);
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, random.nextInt(1, 2000), TimeUnit.MILLISECONDS);
    }


    public Future<Integer> getValue() {
        return scheduler.schedule(
                ()->{return capteur.getValue();},
                random.nextInt(1,2000),
                TimeUnit.MILLISECONDS
        );
    }

}
