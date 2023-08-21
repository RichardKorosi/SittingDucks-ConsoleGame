package sk.stuba.fei.uim.oop.card.tile;

import static sk.stuba.fei.uim.oop.color.ConsoleColor.RED;
import static sk.stuba.fei.uim.oop.color.ConsoleColor.RESET;

public class PondTile {
    private boolean aimed;
    private PondCard cardOnTile;

    public String getInfoIfAimedOn() {
        if(aimed) return RED + "X" + RESET;
        return RED + "O" + RESET;
    }

    public boolean isAimed() {
        return aimed;
    }

    public void setAimed(boolean aimed) {
        this.aimed = aimed;
    }

    public PondCard getCardOnTile() {
        return cardOnTile;
    }

    public void setCardOnTile(PondCard cardOnTile) {
        this.cardOnTile = cardOnTile;
    }

    public PondTile(PondCard cardOnTile) {
        this.cardOnTile = cardOnTile;
    }
}
