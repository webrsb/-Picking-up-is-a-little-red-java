package com.gmail.webrsb.card;

public enum Suit {
    // 方塊 黑桃 紅心 梅花
    DIAMOND, SPADE, HEART, CLUB;

    @Override
    public String toString() {
        switch (this) {
            case DIAMOND:
            return "方塊";
            case SPADE:
            return "黑桃";
            case HEART:
            return "紅心";
            case CLUB:
            return "梅花";
        }

        return null;
    }
}
