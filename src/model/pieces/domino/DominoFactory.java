package model.pieces.domino;

import model.pieces.Case;
import model.pieces.Paysage;

import java.util.List;

public class DominoFactory {

    public static Domino getDomino(int id, int[] position) {
        List<String> listOfDomino = CsvParser.parse().get(id);
        Case case1 = new Case(new Paysage(listOfDomino.get(1),Integer.parseInt(listOfDomino.get(5))), 'a');
        Case case2 = new Case(new Paysage(listOfDomino.get(3), Integer.parseInt(listOfDomino.get(6))), 'b');
        return new Domino(case1, case2, Integer.parseInt(listOfDomino.get(0)), position);
    }
}
