package model;

class Bomb extends Cell {
    int x;
    int y;
    Bomb (int x, int y) {
        super();
        this.x = x;
        this.y = y;
        bomb = true;
        bombNearCounter = -1;
    }

    @Override
    public void plusBombNearCounter() { }
}
