package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //PlayingCardsクラスから絵札と数や記号のついたカードを山札に加える
        List<PlayingCards> deck = new ArrayList<>();
        for (PlayingCards.PictorialPattern pattern : PlayingCards.PictorialPattern.values()) {
            for (PlayingCards.CardRank cardRank : PlayingCards.CardRank.values()) {
                deck.add(new PlayingCards(pattern.getPictorialPatternMark(), cardRank.getRankNumber()));
            }
        }

        //山札に加えたトランプをシャッフルする
        Collections.shuffle(deck);

        Player player = new Player();

        List<PlayingCards> playingCardsList = player.addPlayerHand(deck);

        System.out.println("-------------");
        System.out.println("プレイヤーの手札");
        System.out.println(playingCardsList);

        //山札からプレイヤーが引いた手札を削除
        deck.removeAll(playingCardsList);

        Scanner scanner = new Scanner(System.in);
        System.out.println("手札：" + playingCardsList);

        System.out.println("捨てるカードを選んでください（0〜4の番号で選択してください）");
        System.out.println("捨てるカードがなければ9を選んでください");
        if (scanner.nextInt() != 9) {
            for (int i = 0; i < playingCardsList.size(); i++) {
                System.out.println((i) + ":" + playingCardsList.get(i));
            }

            System.out.println("捨てるカードの位置を半角スペースで区切って入力してください。");
            String input = scanner.nextLine();
            String[] positions = input.split(" ");

            // 捨てるカードの位置をリストから削除する
            int count = 0;
            for (String position : positions) {
                int index = Integer.parseInt(position) - count;
                if (index >= 0 && index < playingCardsList.size()) {
                    playingCardsList.remove(index);
                    count++;
                }
            }
            playingCardsList = player.addPlayerHand(deck);
        }

        System.out.println(playingCardsList);

        Computer computer = new Computer();
        List<PlayingCards> computerCardList = computer.addComputerHand(deck);

        System.out.println("-------------");
        System.out.println("コンピュターの手札");
        System.out.println(computerCardList);

        //山札からコンピュターが引いた手札を削除
        deck.removeAll(computerCardList);

        PokerRule pokerRule = new PokerRule();
        PokerRule.PokerHand playerHand = pokerRule.judgePokerHand(playingCardsList);
        System.out.println(playerHand);

        PokerRule.PokerHand computerHand = pokerRule.judgePokerHand(computerCardList);
        System.out.println(computerHand);

        pokerRule.showMatchResult(playerHand, computerHand);

    }
}