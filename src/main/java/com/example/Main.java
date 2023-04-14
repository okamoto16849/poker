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

        System.out.println(deck);
        //山札に加えたトランプをシャッフルする
        Collections.shuffle(deck);
        System.out.println("-------------");
        System.out.println("シャッフル後の山札");
        System.out.println(deck);

        System.out.println("-------------");
        System.out.println("プレイヤーの手札");
        Player player = new Player();

        List<PlayingCards> playingCardsList = player.addPlayerHand(deck);
        //TODO:確認後に削除
        System.out.println(playingCardsList);
        //山札からプレイヤーが引いた手札を削除
        deck.removeAll(playingCardsList);
        //TODO:確認後に削除
        System.out.println(deck);

        Computer computer = new Computer();
        List<PlayingCards> computerCardList = computer.addComputerHand(deck);
        System.out.println(deck);
        //TODO:確認後に削除
        System.out.println(computerCardList);
        //山札からコンピュターが引いた手札を削除
        deck.removeAll(computerCardList);
        //TODO:確認後に削除
        System.out.println(deck);

        PokerRule pokerRule = new PokerRule();
        PokerRule.PokerHand playerHand = pokerRule.judgePokerHand(playingCardsList);
        System.out.println(playerHand);
    }
}