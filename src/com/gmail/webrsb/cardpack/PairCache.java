package com.gmail.webrsb.cardpack;

import com.gmail.webrsb.card.Card;

public class PairCache {
    private int index;
    private CardPack cardPack;

    public PairCache(int index, CardPack cardPack) {
        this.index = index;
        this.cardPack = cardPack;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public CardPack getCardPack() {
        return cardPack;
    }

    public Card getCard() {
        return cardPack.getByIndex(this.index);
    }

    public Card removeCard() {
        return cardPack.removeByIndex(this.index);
    }

    public void setCardPack(CardPack cardPack) {
        this.cardPack = cardPack;
    }
}
