package sk.stuba.fei.uim.oop.card.action;

import sk.stuba.fei.uim.oop.card.tile.PondCard;
import sk.stuba.fei.uim.oop.card.tile.PondTile;
import java.util.ArrayList;

public class Turboduck extends ActionCard {
    @Override
    public void play(ArrayList<PondTile> pondArea, ArrayList<ActionCard> actionCardsDeck, ArrayList<ActionCard> hand, ArrayList<PondCard> pondDeck) {
        int duckToSpeedUp;
        do{
            duckToSpeedUp = chooseTile();
        }while (!pondArea.get(duckToSpeedUp).getCardOnTile().turboTheCard(pondArea, duckToSpeedUp));
    }

    public Turboduck() {
        super("Turboduck");
    }

    @Override
    public String toString() {
        return this.name;
    }
}
