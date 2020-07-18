package com.gmail.webrsb;

import com.gmail.webrsb.card.Card;
import com.gmail.webrsb.cardpack.CardPack;
import com.gmail.webrsb.cardpack.PairCache;
import com.gmail.webrsb.cardpack.ScoreHelper;
import com.gmail.webrsb.cardpack.child.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Game {
    final int handCardNum = 6;
    final int oceanCardNum = 6;
    private int turnPlayer = 0;
    private Player[] players;
    private Poker poker;
    private Pool pool;
    private Ocean ocean;

    public void init () {
        turnPlayer = 0;
        players = new Player[]{new Player(), new Player(), new Player(), new Player()};
        poker = Poker.getInsWithStander();
        pool = new Pool();
        ocean = new Ocean();
    }

    public void start () {
        this.init();
        // 洗牌
        poker.shuffling();

        // 玩家發牌
        for (Player player : players) {
            Hand hand = player.getHand();
            for (int i = 0; i < handCardNum; i++) {
                hand.add(poker.draw());
            }
        }

        // 發海底牌
        for (int i = 0; i < oceanCardNum; i++) {
            ocean.add(poker.draw());
        }

        // 剩餘牌當作牌庫
        pool.getCards().addAll(poker.getCards());

        while (true) {
            System.out.println("目前換玩家" + (turnPlayer + 1) + " 進行配對");
            startPair();

            // 最後一名玩家無牌可抽時結束遊戲
            if (pool.getSize() == 0 && turnPlayer == players.length - 1) {
                boolean flag = false;

                for (Player player: players) {
                    if (player.getHand().getSize() > 0) {
                        flag = true;
                        break;
                    }
                }

                if (!flag)
                    break;
            }

            turnPlayer ++;

            if (turnPlayer >= 4)
                turnPlayer = 0;
        }

        gameOver();
    }

    public void startPair() {
        Player player = players[turnPlayer];
        System.out.println("擁有的手牌清單：");
        showAll(player.getHand());
        System.out.println("海底牌清單：");
        showAll(ocean);

        if (!checkPairableForAll(player.getHand(), ocean)) {
            PairCache selCard = null;
            System.out.println("無牌可配對，選擇一張手牌丟海底");

            while(selCard == null) {
                selCard = userInput(player.getHand());
            }

            System.out.println("您選擇的手牌是： " + selCard.getCard().toString());

            ocean.putCard(selCard);

            System.out.println("擁有的手牌清單：");
            showAll(player.getHand());
            System.out.println("海底牌清單：");
            showAll(ocean);
        } else {
            boolean pairResult = false;

            // 開始配對
            while (!pairResult) {
                PairCache selHandCard, selOceanCard;

                System.out.println("請輸入手牌編號");

                selHandCard = userInput(player.getHand());

                System.out.println("您選擇的手牌是： " + selHandCard.getCard().toString());
                System.out.println("請輸入海底牌編號");

                selOceanCard = userInput(ocean);

                System.out.println("您選擇的海底牌是： " + selOceanCard.getCard().toString());

                pairResult = player.getPaired().pair(selHandCard, selOceanCard);

                if (!pairResult) {
                    System.out.println("配對失敗，請重新選擇");
                } else {
                    System.out.println("配對成功！");
                }
            }
        }

        Pool tempPool = new Pool();

        // 第二階段抽牌流程
        while (true)
        {
            if (pool.getSize() <= 0) {
                System.out.println("無牌可抽");

                return;
            }

            PairCache selOceanCard;
            boolean pairResult = false;
            tempPool.add(pool.draw());
            System.out.println("您抽了一張牌：");
            showAll(tempPool);
            System.out.println("目前海底牌清單：");
            showAll(ocean);

            if (!checkPairableForAll(tempPool, ocean)) {
                System.out.println("無牌可配對，將此牌丟至海底");
                ocean.putCard(new PairCache(0, tempPool));
                System.out.println("海底牌清單：");
                showAll(ocean);

                break;
            }

            while (pairResult == false) {
                System.out.println("請選擇一張海底牌進行配對：");
                selOceanCard = userInput(ocean);

                System.out.println("您選擇的海底牌是： " + selOceanCard.getCard().toString());

                pairResult = player.getPaired().pair(new PairCache(0, tempPool), selOceanCard);

                if (!pairResult) {
                    System.out.println("配對失敗，請重新選擇");
                } else {
                    System.out.println("配對成功！");
                }
            }
        }

        return;
    }

    public void gameOver () {
        int i = 1;
        System.out.println("遊戲結束，成績總計：");

        for (Player player: players) {
            System.out.println("玩家" + String.valueOf(i) + " 分數為：");
            System.out.println((new ScoreHelper(player.getPaired()).getTotalScore()));
            i++;
        }
    }

    public static PairCache userInput(CardPack cardPack) {
        Scanner scanner = new Scanner(System.in);
        Card selCard = null;
        int selIndex = -1;

        if (cardPack.getSize() == 0)
            return null;

        while (selCard == null) {
            selIndex = Integer.valueOf(scanner.nextLine());

            try {
                selCard = cardPack.getByIndex(selIndex);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("無此編號，請重新輸入");
            }
        }

        return new PairCache(selIndex, cardPack);
    }

    public static void showAll(CardPack cardPack) {
        ArrayList cards = cardPack.getCards();

        int i = 0;
        for (Iterator iterator = cards.iterator(); iterator.hasNext(); i++) {
            Card card = (Card) iterator.next();

            System.out.println(i + ": " + card.toString());
        }
    }

    public static boolean checkPairableForAll(CardPack cardPack, Ocean ocean) {
        for (Iterator i = cardPack.getCards().iterator(); i.hasNext();) {
            Card cardPlayer = (Card) i.next();

            for (Iterator j = ocean.getCards().iterator(); j.hasNext();) {
                Card cardOcean = (Card) j.next();

                if (Paired.isPairable(cardPlayer, cardOcean)) {
                    return true;
                }
            }
        }

        return false;
    }
}
