package model.ia;

import model.pieces.domino.*;
import java.util.HashSet;
import java.util.Random;

public class State {

    private HashSet<Domino> deck;

    private HashSet<Player> players;

    //Domino d = DominoFactory.getDomino( 5,new int[]{3,2});

    public State(){
        this.deck = new HashSet<>();
        this.players = new HashSet<>();
        for(int i=0; i<48; i++){
            this.deck.add(DominoFactory.getDomino(i,new int []{0,0}));
        }
    }

    public HashSet<Domino> getDeck() {
        return this.deck;
    }

    public HashSet<Domino> pick(){
        HashSet<Domino> pick = new HashSet<>();
        for(int i = 0;i<this.players.size();i++){
            Random random = new Random();
            int nb;
            nb = random.nextInt(this.deck.size());
            pick.add(DominoFactory.getDomino(nb,new int []{0,0}));
            this.deck.remove(DominoFactory.getDomino(nb,new int []{0,0}));
        }
        return pick;
    }

    public void addPlayer(Player player){
        players.add(player);
    }
}