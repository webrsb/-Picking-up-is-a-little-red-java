package com.gmail.webrsb.cardpack.child;

import com.gmail.webrsb.card.Card;
import com.gmail.webrsb.cardpack.CardPack;
import com.gmail.webrsb.cardpack.DrawAble;

import java.util.ArrayList;

public class Pool<T extends Card> extends CardPack implements DrawAble {
    public Pool() {
        super();
    }

    public Pool(ArrayList cards) {
        super(cards);
    }

    @Override
    public T draw() {
        return (T) cards.remove(cards.size() - 1);
    }
}
