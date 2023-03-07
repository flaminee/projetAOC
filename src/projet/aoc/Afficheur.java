package projet.aoc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Afficheur {
    String name;

    Afficheur(String name){
        this.name = name;
    };
    Integer update(Canal canal) throws ExecutionException, InterruptedException {
        Future<Integer> future = canal.getValue(this);
        while(!future.isDone()) {
            Thread.sleep(1000);
        }
        System.out.println(this.name + " : " + future.get() + " " );
        return future.get();
    }

}
