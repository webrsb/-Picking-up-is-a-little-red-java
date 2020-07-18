package com.gmail.webrsb.cardpack.child;

import com.gmail.webrsb.card.Card;
import com.gmail.webrsb.cardpack.CardPack;
import com.gmail.webrsb.cardpack.PairCache;

import java.util.ArrayList;
import java.util.List;

public class Paired {
    private ArrayList<Card> originPairedCards;
    private ArrayList<Card> targetPairedCards;

    public ArrayList<Card> getOriginPairedCards() {
        return originPairedCards;
    }

    public void setOriginPairedCards(ArrayList<Card> originPairedCards) {
        this.originPairedCards = originPairedCards;
    }

    public ArrayList<Card> getTargetPairedCards() {
        return targetPairedCards;
    }

    public void setTargetPairedCards(ArrayList<Card> targetPairedCards) {
        this.targetPairedCards = targetPairedCards;
    }

    public Paired() {
        this.originPairedCards = new ArrayList();
        this.targetPairedCards = new ArrayList();
    }

    public Paired(ArrayList<Card> originPairedCards, ArrayList<Card> targetPairedCards) {
        this.originPairedCards = originPairedCards;
        this.targetPairedCards = targetPairedCards;
    }

    public boolean pair(PairCache cardsOrigin, PairCache cardsTarget) {
        int orignIndex = cardsOrigin.getIndex();
        int targetIndex = cardsTarget.getIndex();
        CardPack originCardPack = cardsOrigin.getCardPack();
        CardPack targetCardPack = cardsTarget.getCardPack();
        Card cardOrigin = originCardPack.getByIndex(orignIndex);
        Card cardTarget = targetCardPack.getByIndex(targetIndex);

        if (isPairable(cardOrigin, cardTarget)) {
            originPairedCards.add(originCardPack.removeByIndex(orignIndex));
            targetPairedCards.add(targetCardPack.removeByIndex(targetIndex));

            return true;
        }

        return false;
    }

    /**
     * 檢查是否符合撿紅點可進行配對的規則，1 ~ 9 若兩卡加種為 10 則可配對
     * 若為 10, J, Q, K 則需要兩張一樣的牌才可配對
     * @param orign
     * @param target
     * @return
     */
    public static boolean isPairable(Card orign, Card target) {
        int originSize = orign.getRank().getSize();
        int targetSize = target.getRank().getSize();

        if (originSize <= 9) {
            if (originSize + targetSize == 10) {
                return true;
            }
        }
        else if (originSize == targetSize) {
            return true;
        }

        return false;
    }
}
