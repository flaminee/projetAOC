package projet.aoc;


import projet.aoc.strategy.AlgoDiffusion;



import java.util.*;

public class Capteur {

    Integer value;
    List<Canal> canalList;
    AlgoDiffusion algoDiffusion;
    boolean lock;

    Capteur(List<Canal> canalList, AlgoDiffusion algoDiffusion) {
        this.canalList = canalList;
        this.value = 0;
        this.algoDiffusion = algoDiffusion;
        this.lock = false;
    }

    int getValue() {
        return value;
    }

    void tick() throws InterruptedException {
        this.value++;
        if (!lock) {
            algoDiffusion.execute(this);
        }
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public List<Canal> getCanalList() {
        return canalList;
    }

}
