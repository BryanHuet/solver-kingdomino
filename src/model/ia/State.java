package model.ia;

import model.Kingdomino;
import model.player.Player;
import model.pieces.domino.Domino;
import model.plateau.Deck;
import model.plateau.Grille;

import java.util.HashMap;

public class State {

    private Kingdomino game;
    private HashMap<Player, Grille> savesGrid;
    private Deck deck;
    private Player actualPayer;


    public State(Kingdomino game, Player player){
        this.game=game;
        this.savesGrid =new HashMap<>();
        this.deck = new Deck(game.getDeck().getSize(),true);
        this.actualPayer=player;
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

    public void nextPlayer(){
        int idNextPlayer = this.actualPayer.getId()+1;
        while(this.getGame().getPlayers().get(idNextPlayer)==this.actualPayer){

            idNextPlayer=idNextPlayer+1;
            if(idNextPlayer>=this.getGame().getPlayers().size()){
                idNextPlayer=0;
            }
        }
        this.actualPayer=this.getGame().getPlayers().get(idNextPlayer);
    }
    @Override
    public boolean equals(Object object){
        if (! (object instanceof State)){
            return false;
        }
        return this.getActualPayer() == ((State) object).getActualPayer() && this.getSavesGrid() == ((State) object).getSavesGrid();
    }


}