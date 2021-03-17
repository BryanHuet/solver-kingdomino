package model.player;

import model.Kingdomino;
import model.pieces.domino.Domino;
import model.plateau.Grille;
import model.plateau.Score;
import model.plateau.actions.IPut;
import model.plateau.actions.PutDomino;

import java.util.ArrayList;
import java.util.Scanner;

public class Human implements Player{

    private int id;
    private int score;
    private Grille plateau;
    private Kingdomino game;


    public Human(Kingdomino game){
        this.game=game;
        this.plateau = new Grille(5,5);
    }

/*
---------------------------------------------------------------------------------------------------------------------
                                        GETTER/SETTER
---------------------------------------------------------------------------------------------------------------------
*/
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

    public int getScore() {
        Score score = new Score(plateau);
        return score.calculateScore();
    }

/*
---------------------------------------------------------------------------------------------------------------------
                                        METHODES
---------------------------------------------------------------------------------------------------------------------
*/

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
        return coups;
    }

    public void play(){
        int j=0;
        for(Domino d : this.getGame().getPick()){
            System.out.print(j+" "+d+"|");
            j=j+1;
        }
        System.out.println("\nVeuillez choisir un domino");
        Scanner myObj = new Scanner(System.in);
        int idDomino = myObj.nextInt();
        Domino chosen = this.getGame().getPick().get(idDomino);
        ArrayList<Domino> justTest = new ArrayList<>();
        justTest.add(chosen);
        this.getGame().getPick().remove(this.getGame().getPick().get(idDomino));
        System.out.println("Veuillez choisir une action");
        int i=0;
        for (IPut action: this.actionsPossible(justTest)){
            System.out.print(i+" "+action+" | ");
            if (i%2==0){
                System.out.println();
            }
            i=i+1;
        }
        System.out.println();
        Scanner myObj2 = new Scanner(System.in);
        int position = myObj2.nextInt();
        this.actionsPossible(justTest).get(position).put();

    }

    @Override
    public String toString(){
        return "Humain "+this.id;
    }
}
