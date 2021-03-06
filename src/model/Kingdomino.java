package model;

import model.Player;
import model.pieces.Castle;
import model.pieces.domino.Domino;
import model.pieces.domino.DominoFactory;
import model.plateau.actions.PutCastle;
import model.plateau.actions.PutDomino;

import java.util.*;

public class Kingdomino {

    private ArrayList<Domino> deck;
    private HashSet<Player> players;
    private ArrayList<Domino> pick;
    private boolean terminate=false;

    public Kingdomino(){
        this.deck = new ArrayList<>();
        this.players = new HashSet<>();
        this.pick = new ArrayList<>();
        for(int i=0; i<48; i++){
            this.deck.add(DominoFactory.getDomino(i,new int []{0,0}));
        }

    }

    public void pick(){
        for(int i = 0;i<this.players.size();i++){
            Random random = new Random();
            int nb;
            nb = random.nextInt(this.deck.size());
            this.pick.add(this.deck.get(nb));
            this.deck.remove(nb);
        }
    }

    public void start(){
        System.out.println("Debut du jeu");
        for(Player p : this.players){
            System.out.println("Veuillez placer votre chateau 22 pour x=2 et y=2");
            Scanner myObj = new Scanner(System.in);
            String position = myObj.nextLine();
            Castle castle = new Castle(new int[]{Integer.parseInt(""+position.charAt(0)), Integer.parseInt(position.charAt(1)+"")});
            PutCastle c = new PutCastle(p.getPlateau(), castle);
            c.put();
            p.getPlateau().afficheGrille();
        }
        int n=1;
        while(! this.terminate){
            System.out.println("============= TOUR "+n+" ==================");
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
                System.out.println("Veuillez choisir un domino 0 ou 1");
                Scanner myObj = new Scanner(System.in);
                int idDomino = myObj.nextInt();
                Domino chosen = this.pick.get(idDomino);
                System.out.println("Veuillez placez votre domino 22h pour x=2 et y=2 et orientation = h ou v");
                Scanner myObj2 = new Scanner(System.in);
                String position = myObj2.nextLine();
                chosen.setPosition(new int[]{Integer.parseInt(""+position.charAt(0)), Integer.parseInt(position.charAt(1)+"")});

                PutDomino pu = new PutDomino(p.getPlateau(),chosen,position.charAt(2)=='h'? "horizontal":"vertical");
                pu.put();

                p.getPlateau().afficheGrille();

            }
            this.pick.clear();


            n=n+1;
        }
    }

    public ArrayList<Domino> getDeck() {
        return this.deck;
    }
    public void addPlayer(Player player){
        players.add(player);
    }
    public ArrayList<Domino> getPick() { return this.pick; }

    public static void main(String[] args){

        Kingdomino game = new Kingdomino();
        Player one = new Player(1);
        Player two = new Player(2);
        game.addPlayer(one);
        game.addPlayer(two);
        game.start();
    }

}