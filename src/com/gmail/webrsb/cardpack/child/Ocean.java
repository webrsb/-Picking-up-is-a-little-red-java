package com.gmail.webrsb.cardpack.child;

import com.gmail.webrsb.cardpack.CardPack;
import com.gmail.webrsb.cardpack.PairCache;

import java.util.ArrayList;

public class Ocean extends CardPack {
    public Ocean() {
        super();
    }

    public Ocean(ArrayList cards) {
        super(cards);
    }

    public void putCard(PairCache pairCache) {
        this.add(pairCache.removeCard());
    }
}
