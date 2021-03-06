package model;

import model.Player;
import model.pieces.domino.Domino;
import model.pieces.domino.DominoFactory;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Kingdomino {

    private HashSet<Domino> deck;
    private HashSet<Player> players;
    private HashSet<Domino> pick;
    private boolean terminate=false;

    public Kingdomino(){
        this.deck = new HashSet<>();
        this.players = new HashSet<>();
        this.pick = new HashSet<>();
        for(int i=0; i<48; i++){
            this.deck.add(DominoFactory.getDomino(i,new int []{0,0}));
        }

    }

    public void pick(){
        for(int i = 0;i<this.players.size();i++){
            Random random = new Random();
            int nb;
            nb = random.nextInt(this.deck.size());
            this.pick.add(DominoFactory.getDomino(nb,new int []{0,0}));
            this.deck.remove(DominoFactory.getDomino(nb,new int []{0,0}));
        }
    }

    public void start(){
        System.out.println("Debut du jeu");
        while(! this.terminate){
            if (this.deck.size()==0){
                this.terminate = true;
            }
            System.out.println("Tirage de la pioches");
            pick();
            System.out.println("supprimer "+ this.deck.size());
            for(Domino d : this.pick){
                System.out.println(d);
            }

            for(Player p : this.players){
                System.out.println("Veuillez choisir un domino");
                Scanner myObj = new Scanner(System.in);
                Integer idDomino = myObj.nextInt();
                System.out.println("Veuillez placez votre domino");
                p.getPlateau().afficheGrille() ;

            }
            this.pick.clear();



        }
    }

    public HashSet<Domino> getDeck() {
        return this.deck;
    }
    public void addPlayer(Player player){
        players.add(player);
    }
    public HashSet<Domino> getPick() { return this.pick; }

}