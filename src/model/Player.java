package model;

import model.plateau.Grille;
import model.plateau.actions.IPut;

import java.util.HashSet;

public class Player {

    private int id;
    private int score;
    private Grille plateau;

    public Player(int id){
        this.id = id;
        this.plateau = new Grille(5,5);
        this.score = 0;
    }

    public int getId() {
        return this.id;
    }

    public Grille getPlateau() {
        return plateau;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlateau(Grille plateau) {
        this.plateau = plateau;
    }

    public void play(IPut action){
        action.put();
    }

}
