package sk.stuba.fei.uim.oop.card.action;

import sk.stuba.fei.uim.oop.card.tile.PondCard;
import sk.stuba.fei.uim.oop.card.tile.PondTile;

import java.util.ArrayList;

public class DuckMarch extends ActionCard {
    @Override
    public void play(ArrayList<PondTile> pondArea, ArrayList<ActionCard> actionCardsDeck, ArrayList<ActionCard> hand, ArrayList<PondCard> pondDeck) {
        pondDeck.add(pondArea.get(0).getCardOnTile());
        pondArea.get(1).getCardOnTile().moveTiles(pondArea,pondDeck,0);
    }

    public DuckMarch() {
        super("DuckMarch");
    }

    @Override
    public String toString() {
        return this.name;
    }
}
