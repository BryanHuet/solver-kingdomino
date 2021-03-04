package model.pieces.domino;

import model.pieces.Case;

import java.util.List;

public class DominoFactory {

    private List<List<String>> listOfDominos;

    public static Domino getDomino(int id) {
        List<String> listOfDomino = CsvParser.parse().get(id);
        Case case1 = new Case(listOfDomino.get(1), Integer.parseInt(listOfDomino.get(5)), 'a');
        Case case2 = new Case(listOfDomino.get(3), Integer.parseInt(listOfDomino.get(6)), 'b');
        return new Domino(case1, case2, Integer.parseInt(listOfDomino.get(0)));
    }
}
