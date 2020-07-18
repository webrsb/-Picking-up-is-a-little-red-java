package com.gmail.webrsb;

import com.gmail.webrsb.cardpack.child.Hand;
import com.gmail.webrsb.cardpack.child.Paired;

public class Player {
    private Hand hand;
    private Paired paired;

    public Player() {
        this.hand = new Hand();
        this.paired = new Paired();
    }

    public Player(Hand hand, Paired paired) {
        this.hand = hand;
        this.paired = paired;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Paired getPaired() {
        return paired;
    }

    public void setPaired(Paired paired) {
        this.paired = paired;
    }
}
