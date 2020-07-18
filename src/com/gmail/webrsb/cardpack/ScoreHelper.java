package com.gmail.webrsb.cardpack;

import com.gmail.webrsb.Player;
import com.gmail.webrsb.card.Card;
import com.gmail.webrsb.card.Suit;
import com.gmail.webrsb.cardpack.child.Paired;

import java.util.ArrayList;
import java.util.Iterator;

public class ScoreHelper {
    private Paired paired;

    public ScoreHelper(Paired paired) {
        this.paired = paired;
    }

    public Paired getPaired() {
        return paired;
    }

    public void setPaired(Paired paired) {
        this.paired = paired;
    }

    /**
     * 分數計算 黑桃A +30
     * A +20
     * 紅色 9, 10, J, Q, K +10
     * 紅色 2-8 +本身數字
     * @param card
     * @return
     */
    public int getScore(Card card) {
        int size = card.getRank().getSize();
        Suit suit = card.getSuit();

        if (suit.equals(Suit.SPADE) && size == 1) {
            return 30;
        }
        else if (suit.equals(Suit.DIAMOND) || suit.equals(Suit.HEART)) {
            if (size == 1) {
                return 20;
            }
            else if (size >= 9) {
                return 10;
            }
            else {
                return size;
            }
        }
        else {
            return 0;
        }
    }

    public int getTotalScore() {
        int total = 0;
        ArrayList<Card> all = new ArrayList();
        all.addAll(paired.getOriginPairedCards());
        all.addAll(paired.getTargetPairedCards());

        for(Iterator iterator = all.iterator(); iterator.hasNext();) {
            Card card = (Card) iterator.next();

            total += getScore(card);
        }

        return total;
    }
}
