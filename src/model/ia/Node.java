package model.ia;

import java.util.HashSet;

public class Node {

    private int heuristic;
    private Player player;
    private State state;
    private HashSet<Node> child;

    // private int depth;

    public Node(Player player, State state,int heuristic){
        this.player = player;
        this.state = state;
        this.heuristic = heuristic;
        this.child = new HashSet<>();
        calculHeuristic();
    }

    /*
    public int getDepth(){
        return depth;
    }
     */

    public State getState() {
        return state;
    }

    public int getHeuristic(){
        return this.heuristic;
    }

    public HashSet<Node> getChild() {
        return child;
    }

    public void setHeuristic(int heuristic){
        this.heuristic = heuristic;
    }

    public Player getPlayer() {
        return player;
    }

    public int calculHeuristic(){
        return 0;
    }
}