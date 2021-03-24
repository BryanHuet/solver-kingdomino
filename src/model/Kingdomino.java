package model;

import model.pieces.cases.Castle;
import model.pieces.domino.Domino;
import model.plateau.Deck;
import model.plateau.actions.IPut;
import model.plateau.actions.PutCastle;
import model.player.Player;

import java.util.*;

public class Kingdomino {

    private Deck deck;
    private ArrayList<Player> players;
    private ArrayList<Domino> pick;
    private boolean terminate=false;
    private Player currentPlayer;

    public Kingdomino(){
        this.deck = new Deck(48);
        this.players = new ArrayList<>();
        this.pick = new ArrayList<>();

    }

/*
---------------------------------------------------------------------------------------------------------------------
                                    GETTER/SETTER
---------------------------------------------------------------------------------------------------------------------
*/

    public ArrayList<Player> getPlayers() { return players; }

    public Player getCurrentPlayer() { return currentPlayer; }

    public Deck getDeck() {
        return this.deck;
    }

    public void setPick(ArrayList<Domino> pick) {
        this.pick = pick;
    }

    public ArrayList<Domino> getPick() {
        return pick;
    }

/*
---------------------------------------------------------------------------------------------------------------------
                                    METHODES
---------------------------------------------------------------------------------------------------------------------
*/


    public void nextPlayer(){
        int idNextPlayer = this.currentPlayer.getId()+1;
        if(idNextPlayer>=this.players.size()){
            idNextPlayer=0;
        }
        this.currentPlayer=this.players.get(idNextPlayer);
    }

    public void move(IPut action){
        action.put();
    }

    public void addPlayer(Player player){
        player.setId(this.players.size());
        players.add(player);
        this.currentPlayer=player;
    }

// Boucle de jeu
    public void start(){
        System.out.println("Debut du jeu");
        int n=1;
        while(! this.terminate){
            System.out.println("============= TOUR "+n+" ==================");
            if (this.deck.getDominos().size()==0){
                this.terminate = true;
            }
            System.out.println("Tirage de la pioches");
            try{
                this.pick = this.deck.pick(this.players.size());
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
            System.out.println(this.pick);
            int i=1;
            for(Player p : this.players){
                System.out.println();
                System.out.println(i+"-------- TOUR du joueur : "+p+" --------");
                this.currentPlayer=p;
                if(! p.play()){
                    return;
                }
                this.pick.remove(p.getLastAction().getDomino());
                p.getPlateau().afficheGrille();
                System.out.println("Le score du joueur est :"+ p.getScore());
                System.out.println();
                i=i+1;
            }
            n=n+1;
        }
    }




}