package projet.aoc.strategy;

import projet.aoc.Canal;
import projet.aoc.Capteur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;


public class Atomique implements AlgoDiffusion {

    List<Thread> runnableList;
    HashMap<Canal, Boolean> canalBoolMap;

    public Atomique(){
        super();
        this.runnableList = new ArrayList<>();
        this.canalBoolMap = new HashMap<>();
    }

    @Override
    public void execute(Capteur capteur) throws InterruptedException {

        capteur.setLock(true);

        capteur.getCanalList().forEach(
                canal -> {
                    canalBoolMap.put(canal, false);
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Future future = canal.update(capteur);

                            while(!future.isDone()) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            canalBoolMap.replace(canal, true);
                        }
                    });
                    runnableList.add(thread);
                }
        );

        runnableList.forEach(Thread::start);

        while(!unlock(canalBoolMap.values())){
            Thread.sleep(1000);
        }
        runnableList.clear();
        System.out.println("unlock");
        capteur.setLock(false);

    }

    boolean unlock(Collection<Boolean> booleans){
        for(boolean b : booleans) if(!b) return false;
        return true;
    }
}
