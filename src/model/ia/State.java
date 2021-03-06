package model.ia;

import model.Kingdomino;
import model.plateau.actions.IPut;

import java.util.ArrayList;
import java.util.HashSet;

public class State {

    private Kingdomino game;

    private HashSet<IPut> coupPossibles;

    public State(Kingdomino game){
        this.game=game;
        this.coupPossibles=new HashSet<>();
    }
    public Kingdomino getGame() { return game; }




}