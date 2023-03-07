package projet.aoc.strategy;

import projet.aoc.Afficheur;
import projet.aoc.Capteur;

public interface AlgoDiffusion {

    public void valueWritten(Capteur capteur) throws InterruptedException;

    public Integer valueRead(Afficheur afficheur) throws InterruptedException;
}
