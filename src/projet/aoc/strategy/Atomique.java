package projet.aoc.strategy;

import projet.aoc.Afficheur;
import projet.aoc.Canal;
import projet.aoc.Capteur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Atomique implements AlgoDiffusion {

    Integer value;
    Capteur capteur;
    ArrayList<Afficheur> finished;
    List<Canal> toUpdate;

    public Atomique(){
        super();
        this.value = 0;
        this.finished = new ArrayList<>();
    }

    @Override
    public void valueWritten(Capteur capteur) throws InterruptedException {

        this.toUpdate = capteur.getCanalList();
        this.capteur = capteur;
        if (!capteur.getLock()) {

            this.value++;
            System.out.println("tick");
            capteur.setLock(true);
            ExecutorService executor = Executors.newFixedThreadPool(this.toUpdate.size());

            this.toUpdate.forEach(
                    canal -> {
                        executor.execute(() -> {
                            canal.update(capteur);
                        });
                    }
            );
            executor.shutdown();
        }
    }

    @Override
    public Integer valueRead(Afficheur afficheur) throws InterruptedException {
        this.finished.add(afficheur);
        if(finished.size() == toUpdate.size()){
            finished.clear();
            capteur.setLock(false);
        }
        return value;
    }

}
