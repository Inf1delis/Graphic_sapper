package model;

public class Cell {
    private boolean visibility;
    protected boolean bomb;
    protected int bombNearCounter;


    Cell () {
        bombNearCounter = 0;
        visibility = false;
        bomb = false;
    }


    public boolean isBomb() {
        return bomb;
    }

    public boolean visibility() {
        return visibility;
    }

    public void setVisible() {
        this.visibility = true;
    }

    public int getBombNearCounter() {
        return bombNearCounter;
    }

    public void plusBombNearCounter() {
        bombNearCounter++;
    }
}
