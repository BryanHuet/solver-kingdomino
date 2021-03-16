package model.ia;

import model.Kingdomino;
import model.plateau.actions.IPut;
import model.player.Player;

import java.util.ArrayList;


public class Random implements Strategy {

    private Kingdomino game;

    public Random(Kingdomino game){
        this.game = game;
    }


    @Override
    public IPut resolution(ArrayList<IPut> actions) {
        return actions.get((int) (Math.random() * actions.size()));
    }
}
