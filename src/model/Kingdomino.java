package model;

import model.pieces.cases.Castle;
import model.pieces.domino.Domino;
import model.plateau.Deck;
import model.plateau.actions.IPut;
import model.plateau.actions.PutCastle;
import model.plateau.actions.PutDomino;
import model.player.Human;
import model.player.Nature;
import model.player.Player;
import model.player.Robot;

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
        this.players.add(new Nature(0,this));

    }

/*
---------------------------------------------------------------------------------------------------------------------
                                    GETTER/SETTER
---------------------------------------------------------------------------------------------------------------------
*/
    public ArrayList<Domino> getPick() { return this.pick; }

    public ArrayList<Player> getPlayers() { return players; }

    public Player getCurrentPlayer() { return currentPlayer; }

    public Deck getDeck() {
        return this.deck;
    }


/*
---------------------------------------------------------------------------------------------------------------------
                                    METHODES
---------------------------------------------------------------------------------------------------------------------
*/
    public void pick(){
        //System.out.println(this.deck.getDominos().size());
        if(this.players.size()-1<=0){
            System.out.println("aucun joueur, donc aucun domino n'est tiré");
        }
        if(this.getPick().size()!=0){
            this.pick.clear();
        }
        if(this.getDeck().getDominos().size()>=this.players.size()+1){
            for(int i = 0;i<this.players.size()-1;i++){
                Random random = new Random();
                int nb;
                nb = random.nextInt(this.deck.getDominos().size());
                this.pick.add(this.deck.getDominos().get(nb));
                this.deck.getDominos().remove(nb);
            }
        }
    }

    public void nextPlayer(){
        int idNextPlayer = this.currentPlayer.getId()+1;
        if(idNextPlayer>=this.players.size()){
            idNextPlayer=0;
        }
        this.currentPlayer=this.players.get(idNextPlayer);
    }

    public void move(IPut action){
        action.put();
        this.pick.remove(action.getDomino());
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
                if(! (p instanceof Nature)){
                this.currentPlayer=p;
                System.out.println("Veuillez placer votre chateau 22 pour x=2 et y=2");
                Scanner myObj = new Scanner(System.in);
                String position = myObj.nextLine();
                Castle castle = new Castle();
                PutCastle c = new PutCastle(p.getPlateau(), castle, new int[]{Integer.parseInt(""+position.charAt(0)), Integer.parseInt(position.charAt(1)+"")});
                c.put();
                p.getPlateau().afficheGrille(); }
        }
        int n=1;
        while(! this.terminate){
            System.out.println("============= TOUR "+n+" ==================");
            if (this.deck.getDominos().size()==0){
                this.terminate = true;
            }
            System.out.println("Tirage de la pioches");
            pick();


            for(Player p : this.players){
                this.currentPlayer=p;
                p.play();
                if(! (p instanceof Nature)){
                    System.out.println("Grille du joueur : "+p);
                    p.getPlateau().afficheGrille();
                    System.out.println("Le score du joueur est :"+ p.getScore());
                    System.out.println();
                }
            }

            this.pick.clear();

            n=n+1;
        }
    }




}