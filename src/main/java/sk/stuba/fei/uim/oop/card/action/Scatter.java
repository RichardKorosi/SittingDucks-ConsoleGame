package sk.stuba.fei.uim.oop.card.action;

import sk.stuba.fei.uim.oop.card.tile.PondCard;
import sk.stuba.fei.uim.oop.card.tile.PondTile;

import java.util.ArrayList;
import java.util.Collections;

public class Scatter extends ActionCard {
    @Override
    public void play(ArrayList<PondTile> pondArea, ArrayList<ActionCard> actionCardsDeck, ArrayList<ActionCard> hand, ArrayList<PondCard> pondDeck) {
        ArrayList<Boolean> aimedTiles = new ArrayList<>();
        for (PondTile pondTile : pondArea) {
            aimedTiles.add(pondTile.isAimed());
        }
        Collections.shuffle(pondArea);
        for(int i =0; i < pondArea.size(); i++){
            pondArea.get(i).setAimed(aimedTiles.get(i));
        }
    }

    public Scatter() {
        super("Scatter");
    }

    @Override
    public String toString() {
        return this.name;
    }
}
