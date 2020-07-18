package com.gmail.webrsb;

import com.gmail.webrsb.card.Card;
import com.gmail.webrsb.card.Suit;
import com.gmail.webrsb.cardpack.CardPack;
import com.gmail.webrsb.cardpack.PairCache;
import com.gmail.webrsb.cardpack.ScoreHelper;
import com.gmail.webrsb.cardpack.child.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Game().start();
    }

}