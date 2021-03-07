package model.ia;

import model.Kingdomino;
import model.plateau.actions.IPut;

import java.util.ArrayList;
import java.util.HashSet;

public class State {

    private Kingdomino game;

    public State(Kingdomino game){
        this.game=game;
    }
    public Kingdomino getGame() { return game; }




}