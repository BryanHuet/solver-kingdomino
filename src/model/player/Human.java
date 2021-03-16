package model.player;

import model.Kingdomino;
import model.pieces.domino.Domino;
import model.plateau.Grille;
import model.plateau.Score;
import model.plateau.actions.IPut;
import model.plateau.actions.PutDomino;

import java.util.ArrayList;

public class Human implements Player{

    private int id;
    private int score;
    private Grille plateau;
    private boolean canplay = true;
    private Kingdomino game;


    public Human(Kingdomino game){
        this.game=game;
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

    public Kingdomino getGame() {
        return game;
    }

    public ArrayList<IPut> actionsPossible(ArrayList<Domino> dominos){
        ArrayList<IPut> coups = new ArrayList<>();
        for (int i = 0; i < this.getPlateau().getNbLigne(); i++) {
            for (int j = 0; j < this.getPlateau().getNbColonne(); j++) {
                for (Domino d : dominos){
                    PutDomino actionh = new PutDomino(this.getPlateau(),d,"horizontal",new int[]{i,j});
                    PutDomino actionhr = new PutDomino(this.getPlateau(),d,"horizontalReversed",new int[]{i,j});
                    PutDomino actionv = new PutDomino(this.getPlateau(),d,"vertical",new int[]{i,j});
                    PutDomino actionvr = new PutDomino(this.getPlateau(),d,"verticalReversed",new int[]{i,j});
                    if(actionh.isValid()){
                        coups.add(actionh);
                    }
                    if (actionv.isValid()){
                        coups.add(actionv);
                    }
                    if(actionhr.isValid()){
                        coups.add(actionhr);
                    }
                    if (actionvr.isValid()){
                        coups.add(actionvr);
                    }
                }
            }
        }
        if(coups.size()==0){
            this.canplay=false;
        }
        return coups;
    }
    public boolean getCanPlay(){
        return this.canplay;
    }

    public void play(){

    }

    public void playb(IPut action){
        action.put();
    }
    public IPut playAi(){return null;}

    public int getScore() {
        Score score = new Score(plateau);
        return score.calculateScore();
    }
}
