package com.gmail.webrsb.cardpack;

import com.gmail.webrsb.card.Card;

import java.util.ArrayList;

public abstract class CardPack<T extends Card> {
    protected ArrayList<T> cards;

    public CardPack() {
        cards = new ArrayList<T>();
    }

    public CardPack(ArrayList cards) {
        this.cards = cards;
    }

    public boolean add(T card) {
        return this.cards.add(card);
    }

    public T getByIndex(int index) {
        return this.cards.get(index);
    }

    public T removeByIndex(int index) {
        return this.cards.remove(index);
    }

    public ArrayList getCards() {
        return cards;
    }

    public void setCards(ArrayList cards) {
        this.cards = cards;
    }

    public int getSize () {
        return cards.size();
    }
}
