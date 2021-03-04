import model.ia.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("PTDR T KI ?");
        System.out.println(":keske:");

        //IA

        State state = new State(94);

        Player none = new Player(0);
        Player p1 = new Player(1);
        Player p2 = new Player(2);

        Node n1 = new Node(p1,state,0);
        Node n2_1 = new Node(p1,state,5);
        Node n2_2 = new Node(p1,state,3);

        Node n3_1 = new Node(p2,state,3);
        Node n3_2 = new Node(p2,state,5);
        n1.getChild().add(n2_1);
        n1.getChild().add(n2_2);

        n2_1.getChild().add(n3_1);
        n2_2.getChild().add(n3_2);

        /*
                    n1        = depth 2
                    /\
                 n2_1 n2_2    = depth 1
                   |  |
                 n3_1 n3_2    = depth 0
        */

        Expectiminimax expectiminimax = new Expectiminimax(p1);
        System.out.println("test 1: "+expectiminimax.test(n1, 2));
    }
}