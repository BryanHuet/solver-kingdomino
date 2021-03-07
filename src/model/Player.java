package model;

import model.pieces.domino.Domino;
import model.plateau.Grille;
import model.plateau.Score;
import model.plateau.actions.IPut;
import model.plateau.actions.PutDomino;

import java.util.ArrayList;
import java.util.HashSet;

public class Player {

    private int id;
    private int score;
    private Grille plateau;

    public Player(int id){
        this.id = id;
        this.plateau = new Grille(5,5);
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

    public ArrayList<IPut> actionsPossible(ArrayList<Domino> dominos){
        ArrayList<IPut> coups = new ArrayList<>();
        for (int i = 0; i < this.getPlateau().getNbLigne(); i++) {
            for (int j = 0; j < this.getPlateau().getNbColonne(); j++) {
                for (Domino d : dominos){
                    PutDomino actionh = new PutDomino(this.getPlateau(),d,"horizontal",new int[]{i,j});
                    PutDomino actionv = new PutDomino(this.getPlateau(),d,"vertical",new int[]{i,j});
                    if(actionh.isValid()){
                        coups.add(actionh);
                    }
                    if (actionv.isValid()){
                        coups.add(actionv);
                    }
                }
            }
        }
        return coups;
    }

    public void play(IPut action){
        action.put();
    }

    public int getScore() {
        return Score.calculateScore(this.getPlateau());
    }


}
