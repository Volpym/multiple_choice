package com.example.multiplechoice;

class PointSystem {
    int currentPoints = 0;


    public void increasePoints() {
        this.currentPoints++;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }
}

