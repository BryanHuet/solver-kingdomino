package model.ia;

import model.Player;
import model.plateau.Score;

import java.util.HashSet;

public class Node {

    private Player player;
    private State state;
    private HashSet<Node> child;
    private int heuristic;


    public Node(Player player, State state){
        this.player = player;
        this.state = state;
        this.child = new HashSet<>();
        this.heuristic = player.getScore();
    }


    public State getState() {
        return state;
    }


    public HashSet<Node> getChild() {
        return child;
    }

    public Player getPlayer() {
        return player;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void addChild(Node child){
        this.child.add(child);
    }
}