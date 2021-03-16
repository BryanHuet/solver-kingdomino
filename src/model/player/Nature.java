package model.player;

import model.Kingdomino;
import model.pieces.domino.Domino;
import model.plateau.Grille;
import model.plateau.actions.IPut;
import model.plateau.actions.PutDomino;

import java.util.ArrayList;

public class Nature implements Player{

    private int id;
    private Kingdomino game;

    public Nature(int id, Kingdomino game) {
        this.id=id;
        this.game=game;
    }

    @Override
    public void play() {
        this.game.pick();
    }

    @Override
    public Grille getPlateau() {
        return null;
    }

    @Override
    public ArrayList<IPut> actionsPossible(ArrayList<Domino> dominos) {
        return null;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }
}
