package model;

import model.pieces.Castle;
import model.pieces.domino.Domino;
import model.plateau.Deck;
import model.plateau.Score;
import model.plateau.actions.IPut;
import model.plateau.actions.PutCastle;

import java.util.*;

public class Kingdomino {

    private Deck deck;
    private HashSet<Player> players;
    private ArrayList<Domino> pick;
    private boolean terminate=false;
    private Player currentPlayer;

    public Kingdomino(){
        this.deck = new Deck(48);
        this.players = new HashSet<>();
        this.pick = new ArrayList<>();


    }

    public void pick(){
        if(this.players.size()<=0){
            System.out.println("aucun joueur, donc aucun domino n'est tiré");
        }
        if(this.getPick().size()!=0){
            this.pick.clear();
        }
        for(int i = 0;i<this.players.size()+1;i++){
            Random random = new Random();
            int nb;
            nb = random.nextInt(this.deck.getDominos().size());
            this.pick.add(this.deck.getDominos().get(nb));
            this.deck.getDominos().remove(nb);
        }
    }

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
            pick();


            for(Player p : this.players){
                this.currentPlayer=p;
                if (! (this.currentPlayer instanceof Robot)){
                    int j=0;
                    for(Domino d : this.pick){
                        System.out.print(j+" "+d+"|");
                        j=j+1;
                    }
                    System.out.println("\nVeuillez choisir un domino");
                    Scanner myObj = new Scanner(System.in);
                    int idDomino = myObj.nextInt();
                    Domino chosen = this.pick.get(idDomino);
                    ArrayList<Domino> justTest = new ArrayList<>();
                    justTest.add(chosen);
                    this.pick.remove(this.pick.get(idDomino));
                    System.out.println("Veuillez choisir une action");
                    int i=0;
                    for (IPut action: p.actionsPossible(justTest)){
                        System.out.print(i+" "+action+" | ");
                        if (i%2==0){
                            System.out.println();
                        }
                        i=i+1;
                    }
                    System.out.println();
                    Scanner myObj2 = new Scanner(System.in);
                    int position = myObj2.nextInt();
                    p.play(p.actionsPossible(justTest).get(position));
                    p.getPlateau().afficheGrille();
                    System.out.println("Votre score est de :"+ p.getScore());
                }
                p.playAi();

            }
            this.pick.clear();


            n=n+1;
        }
    }

    public Deck getDeck() {
        return this.deck;
    }
    public void addPlayer(Player player){
        players.add(player);
    }
    public ArrayList<Domino> getPick() { return this.pick; }
    public HashSet<Player> getPlayers() { return players; }
    public Player getCurrentPlayer() { return currentPlayer; }

    public static void main(String[] args){

        Kingdomino game = new Kingdomino();
        Player one = new Player(1);
        Player two = new Robot(2,game,3);
        game.addPlayer(one);
        game.addPlayer(two);
        game.start();
    }

}