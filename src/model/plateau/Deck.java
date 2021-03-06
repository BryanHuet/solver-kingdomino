package model.plateau;

import model.pieces.Paysage;
import model.pieces.domino.Domino;
import model.pieces.domino.DominoFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Deck {

    private int size;
    private ArrayList<Domino> deck;
    private HashMap<String, Integer> remainingCase;

    public Deck(int size){

        this.size = Math.min(size, 48);
        this.deck = new ArrayList<>();
        this.remainingCase = new HashMap<>();
        for(int i=0; i<this.size; i++){
            Domino d = DominoFactory.getDomino(i,new int []{0,0});
            this.deck.add(d);
            this.remainingCase.put(d.getExtremiteDroite().getPaysage().getName(), 0);
            this.remainingCase.put(d.getExtremiteGauche().getPaysage().getName(), 0);
        }

    }
    //getters
    public int getSize() { return size; }
    public ArrayList<Domino> getDeck() { return deck; }
    public HashMap<String, Integer> getRemainingCase() { this.updateRemaining();return remainingCase; }

    //setters
    public void setSize(int size) { this.size = size; }
    public void setDeck(ArrayList<Domino> deck) { this.deck = deck; }


    public void updateRemaining(){
        for(Domino d : this.deck){
            this.remainingCase.put(d.getExtremiteDroite().getPaysage().getName(),this.remainingCase.get(d.getExtremiteDroite().getPaysage().getName())+1);
            this.remainingCase.put(d.getExtremiteGauche().getPaysage().getName(),this.remainingCase.get(d.getExtremiteGauche().getPaysage().getName())+1);
        }
    }

    public static void main(String[] args){
        Deck d = new Deck(48);
        for (Map.Entry<String, Integer> entry : d.getRemainingCase().entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }


}
