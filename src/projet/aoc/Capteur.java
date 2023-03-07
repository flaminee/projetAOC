package projet.aoc;


import projet.aoc.strategy.AlgoDiffusion;



import java.util.*;

public class Capteur {


    List<Canal> canalList;
    AlgoDiffusion algoDiffusion;
    boolean lock;

    Capteur(List<Canal> canalList, AlgoDiffusion algoDiffusion) {
        this.canalList = canalList;
        this.algoDiffusion = algoDiffusion;
        this.lock = false;
    }

    public int getValue(Afficheur afficheur) throws InterruptedException {
        return algoDiffusion.valueRead(afficheur);
    }

    void tick() throws InterruptedException {
        algoDiffusion.valueWritten(this);
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }
    public boolean getLock() {
        return lock;
    }

    public List<Canal> getCanalList() {
        return canalList;
    }
}
