package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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