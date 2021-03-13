package model.plateau;

import model.pieces.Paysage;
import model.pieces.domino.Domino;
import model.pieces.domino.DominoFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

///Etat interne incohérent possible
public class Deck {

    private int size;
    private ArrayList<Domino> deck;
    private HashMap<String, Integer> remainingCase;

    public Deck(int size){

        this.size = Math.min(size, 48);
        this.deck = new ArrayList<>();
        this.remainingCase = new HashMap<>();
        for(int i=0; i<this.size; i++){
            Domino d = DominoFactory.getDomino(i);
            this.deck.add(d);
            this.remainingCase.put(d.getExtremiteDroite().getPaysage().getName(), 0);
            this.remainingCase.put(d.getExtremiteGauche().getPaysage().getName(), 0);
        }

    }
    public Deck(int size, boolean clear){
        this(size);
        if(clear){
            this.deck.clear();
        }
    }
    //getters
    public int getSize() { return size; }
    public int getNbDominos(){ return this.deck.size(); }
    public ArrayList<Domino> getDominos() { return this.deck; }
    public HashMap<String, Integer> getRemainingCase() { this.updateRemaining();return remainingCase; }

//updateRemaining ici;
    public void addDomino(Domino d){
        this.deck.add(d);

    }
//updateRemaining ici;
    public void removeDomino(Domino d){
        this.deck.remove(d);
    }

    public void updateRemaining(){
        for(Domino d : this.deck){
            this.remainingCase.put(d.getExtremiteDroite().getPaysage().getName(),this.remainingCase.get(d.getExtremiteDroite().getPaysage().getName())+1);
            this.remainingCase.put(d.getExtremiteGauche().getPaysage().getName(),this.remainingCase.get(d.getExtremiteGauche().getPaysage().getName())+1);
        }
    }

}
