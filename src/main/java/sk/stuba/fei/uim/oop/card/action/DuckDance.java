package sk.stuba.fei.uim.oop.card.action;

import sk.stuba.fei.uim.oop.card.tile.PondCard;
import sk.stuba.fei.uim.oop.card.tile.PondTile;

import java.util.ArrayList;
import java.util.Collections;

public class DuckDance extends ActionCard {
    @Override
    public void play(ArrayList<PondTile> pondArea, ArrayList<ActionCard> actionCardsDeck, ArrayList<ActionCard> hand, ArrayList<PondCard> pondDeck) {
        ArrayList<PondCard> allPondCards = new ArrayList<>(pondDeck);
        for (PondTile pondTile : pondArea) {
            allPondCards.add(pondTile.getCardOnTile());
        }
        Collections.shuffle(allPondCards);
        for (PondTile pondTile : pondArea) {
            pondTile.setCardOnTile(allPondCards.get(0));
            allPondCards.remove(0);
        }
        pondDeck.clear();
        pondDeck.addAll(allPondCards);
    }

    public DuckDance() {
        super("DuckDance");
    }

    @Override
    public String toString() {
        return this.name;
    }
}
