package model.ia;

import model.Kingdomino;
import model.Player;
import model.pieces.domino.Domino;
import model.plateau.Deck;
import model.plateau.Grille;

import java.util.HashMap;

public class State {

    private Kingdomino game;
    private HashMap<Player, Grille> savesGrid;
    private Deck deck;
    private Player actualPayer =null;


    public State(Kingdomino game){
        this.game=game;
        this.savesGrid =new HashMap<>();
        this.deck = new Deck(game.getDeck().getSize(),true);
        for (Player p: game.getPlayers()) {
            Grille grille = new Grille(p.getPlateau().getNbLigne(), p.getPlateau().getNbColonne());
            for (int i = 0; i < p.getPlateau().getNbLigne(); i++) {
                for (int j = 0; j < p.getPlateau().getNbColonne(); j++) {
                    grille.getGrille()[i][j] = p.getPlateau().getCase(new int[]{i, j});
                }
            }
            this.savesGrid.put(p,grille);
        }

        for (Domino d: game.getDeck().getDominos()) {
            this.deck.addDomino(d);
        }


    }

    public Kingdomino getGame() { return game; }

    public HashMap<Player, Grille> getSavesGrid() {
        return savesGrid;
    }

    public Deck getDeck() {
        return deck;
    }

    public Player getActualPayer() {
        return actualPayer;
    }

    public void setActualPayer(Player actualPayer) {
        this.actualPayer = actualPayer;
    }

    public static void main(String[] args){

        Kingdomino game = new Kingdomino();
        game.addPlayer(new Player(3));
        game.addPlayer(new Player(3));
        game.addPlayer(new Player(3));
        State a = new State(game);
        game.pick();
        game.pick();
        State b = new State(game);
        System.out.println("deck state a "+a.deck.getNbDominos());
        System.out.println("deck Game "+game.getDeck().getNbDominos());
        System.out.println("deck state b "+b.deck.getNbDominos());



    }


}