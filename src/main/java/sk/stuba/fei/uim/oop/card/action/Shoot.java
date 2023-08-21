package sk.stuba.fei.uim.oop.card.action;

import sk.stuba.fei.uim.oop.card.tile.PondCard;
import sk.stuba.fei.uim.oop.card.tile.PondTile;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Shoot extends ActionCard {
    @Override
    public void play(ArrayList<PondTile> pondArea, ArrayList<ActionCard> actionCardsDeck, ArrayList<ActionCard> hand, ArrayList<PondCard> pondDeck) {
        int placeToShoot = activateCardOnTile(pondArea, true);
        shoot(pondArea, pondDeck, placeToShoot);
    }

    public Shoot() {
        super("Shoot");
    }

    public Shoot(String wildBill){
        super(wildBill);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean canPlayCard(int checkPondStatus) {
        return checkPondStatus != 2;
    }

    @Override
    public void checkCardValue(Player player) {
        int handBalance = player.getHandBalance() - 1;
        player.setHandBalance(handBalance);
    }

    protected void shoot(ArrayList<PondTile> pondArea, ArrayList<PondCard> pondDeck, int placeToShoot){
        pondArea.get(placeToShoot).getCardOnTile().getShot(pondArea, pondDeck, placeToShoot);
    }
}
