package model.ia;
import model.Player;
import model.plateau.actions.PutDomino;

import java.lang.*;

public class Expectiminimax {

    private Player player;

    public Expectiminimax(Player player){
        this.player=player;
    }

    public float test(Node node,int depth){
        float a;
        if(node.getChild().isEmpty() || depth == 0){
            return node.getHeuristic();
        }
        if(node.getPlayer()!=this.player){
            a=1000000000;
            for(Node child: node.getChild()) {
                a = Math.min(a, test(child, depth-1));
            }
        }else if(node.getPlayer()==this.player){
            a=-1000000000;
            for(Node child: node.getChild()) {
                a = Math.max(a, test(child, depth-1));
            }
        }else{
            a = 0;
            for(Node child: node.getChild()) {
                a += proba(child)*test(child,depth-1);
            }
        }
        return a;
    }

    public float proba(Node node) {
        /*
        switch (((PutDomino) node.getState().getGame().getCurrentPlayer().getLast()).getDomino().getExtremiteDroite().getPaysage().getName()) {
            case "wheat":
                return (float) node.getState().getGame().getDeck().getRemainingCase().get("wheat") / node.getState().getGame().getDeck().getSize();
            case "forest":
                return (float) node.getState().getGame().getDeck().getRemainingCase().get("forest") / node.getState().getGame().getDeck().getSize();
            case "water":
                return (float) node.getState().getGame().getDeck().getRemainingCase().get("water") / node.getState().getGame().getDeck().getSize();
            case "grass":
                return (float) node.getState().getGame().getDeck().getRemainingCase().get("grass") / node.getState().getGame().getDeck().getSize();
            case "swamp":
                return (float) node.getState().getGame().getDeck().getRemainingCase().get("swamp") / node.getState().getGame().getDeck().getSize();
            case "mine":
                return (float) node.getState().getGame().getDeck().getRemainingCase().get("mine") / node.getState().getGame().getDeck().getSize();
            default:
                return (float) 0.0;
        }
        */
        return 0.0F;
    }
}

/*function expectiminimax(node, depth)
    if node is a terminal node or depth = 0
        return the heuristic value of node
    if the adversary is to play at node
        // Return value of minimum-valued child node
        let α := +∞
        foreach child of node
            α := min(α, expectiminimax(child, depth-1))
    else if we are to play at node
        // Return value of maximum-valued child node
        let α := -∞
        foreach child of node
            α := max(α, expectiminimax(child, depth-1))
    else if random event at node
        // Return weighted average of all child nodes' values
        let α := 0
        foreach child of node
            α := α + (Probability[child] × expectiminimax(child, depth-1))
    return α

    tree p1 :               p1
                            /\
                       pick 1 pick 2
                         |      |
                  p2 pick 2   p2 pick 1
                        \       /
                         nature
                      ||||||||||||| nb de cartes restante dans le deck

    Square : 96
    -> champs : 26/96 ->27%
    -> forêt : 22/96 -> 23%
    -> eau : 18/96 -> 19%
    -> herbe : 14/96 -> 15%
    -> marais : 10/96 -> 10%
    -> mine : 6/96 -> 6%

 */