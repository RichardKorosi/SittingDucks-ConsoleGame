package sk.stuba.fei.uim.oop.card.action;
import sk.stuba.fei.uim.oop.card.tile.PondCard;
import sk.stuba.fei.uim.oop.card.tile.PondTile;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Aim extends ActionCard {
    @Override
    public void play(ArrayList<PondTile> pondArea, ArrayList<ActionCard> actionCardsDeck, ArrayList<ActionCard> hand, ArrayList<PondCard> pondDeck) {
        int placeToAim = activateCardOnTile(pondArea, false);
        pondArea.get(placeToAim).setAimed(true);
    }

    public Aim() {
        super("Aim");
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean canPlayCard(int checkPondStatus) {
        return checkPondStatus != 1;
    }

    @Override
    public void checkCardValue(Player player) {
        int handBalance = player.getHandBalance() + 1;
        player.setHandBalance(handBalance);
    }
}
