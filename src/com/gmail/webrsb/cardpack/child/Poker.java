package com.gmail.webrsb.cardpack.child;

import com.gmail.webrsb.card.Card;
import com.gmail.webrsb.card.Suit;
import com.gmail.webrsb.cardpack.CardPack;
import com.gmail.webrsb.cardpack.DrawAble;

import java.util.ArrayList;
import java.util.Collections;

public class Poker<T extends Card> extends CardPack implements DrawAble {
    public Poker() {
        super();
    }

    public Poker(ArrayList cards) {
        super(cards);
    }

    @Override
    public T draw() {
        return (T) cards.remove(cards.size() - 1);
    }

    public void shuffling() {
        Collections.shuffle(cards);
    }

    public static Poker getInsWithStander() {
        Poker cardPack = new Poker();

        for(int i = 1; i <= 13; i++) {
            for (Suit suit : Suit.values()) {
                Card card = new Card(i, suit);

                cardPack.add(card);
            }
        }

        return cardPack;
    }
}
