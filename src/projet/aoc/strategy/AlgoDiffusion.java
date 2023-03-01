package projet.aoc.strategy;

import projet.aoc.Capteur;

public interface AlgoDiffusion {

    public void execute(Capteur capteur) throws InterruptedException;
}
