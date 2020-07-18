package com.gmail.webrsb.card;

public class Rank {
    private int size;

    public Rank(int size) {
        this.setSize(size);
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        if (isSizeAvalible(size))
            this.size = size;
        else
            throw new RuntimeException("size not avalible");
    }

    public static boolean isSizeAvalible (int size) {
        if (size >= 1 && size <= 13) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        if (this.size >= 2 && this.size <= 10) {
            return String.valueOf(this.size);
        } else if (this.size == 1) {
            return "A";
        } else if (this.size == 11) {
            return "J";
        } else if (this.size == 12) {
            return "Q";
        } else if (this.size == 13) {
            return "K";
        }

        return "";
    }
}
