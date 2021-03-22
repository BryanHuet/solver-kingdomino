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
        for(Player p : this.players){
                this.currentPlayer=p;
                System.out.println("Veuillez placer votre chateau 22 pour x=2 et y=2");
                Scanner myObj = new Scanner(System.in);
                String position = myObj.nextLine();
                Castle castle = new Castle();
                PutCastle c = new PutCastle(p.getPlateau(), castle, new int[]{Integer.parseInt(""+position.charAt(0)), Integer.parseInt(position.charAt(1)+"")});
                c.put();
                p.getPlateau().afficheGrille();
        }
        int n=1;
        while(! this.terminate){
            System.out.println("============= TOUR "+n+" ==================");
            if (this.deck.getDominos().size()==0){
                this.terminate = true;
            }
            System.out.println("Tirage de la pioches");
           // this.pick = this.deck.pick(this.players.size());


            for(Player p : this.players){
                this.currentPlayer=p;

                if(! p.play()){
                    return;
                }
                    System.out.println("Grille du joueur : "+p);
                    p.getPlateau().afficheGrille();
                    System.out.println("Le score du joueur est :"+ p.getScore());
                    System.out.println();
            }

            n=n+1;
        }
    }




}