package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerRule {

    public enum PokerHand {
        ROYAL_STRAIGHT_FLUSH(1), //ロイヤルストレートフラッシュ
        STRAIGHT_FLUSH(2), //ストレートフラッシュ
        FOUR_OF_A_KIND(3), //フォーカード
        FULL_HOUSE(4), //フルハウス
        FLUSH(5), //フラッシュ
        STRAIGHT(6), //ストレート
        THREE_OF_A_KIND(7), //スリーカード
        TWO_PAIR(8), //ツーペア
        ONE_PAIR(9), //ワンペア
        HIGH_CARD(10); //ハイカード(役なし)

        private int pokerRole;

        private PokerHand(int pokerRole) {
            this.pokerRole = pokerRole;
        }

        public int getPokerRole() {
            return pokerRole;
        }

    }

    //ポーカーの役を判定する
    public PokerHand judgePokerHand(List<PlayingCards> playingCardsList) {
        if (isFourOfAKind(playingCardsList)) {
            return PokerHand.FOUR_OF_A_KIND;
        }

        return PokerHand.HIGH_CARD;
    }

    //フォーカードかどうか判定する
    public Boolean isFourOfAKind(List<PlayingCards> playingCardsList) {
        //キーには数札、値には数字の出現回数を格納
        Map<Integer, Integer> cardRankCountMap = getCardRankCountMap(playingCardsList);

        //countMapの値を全て取得して、値(数字の出現回数)が4であればtrueを返す
        for (int countValue : cardRankCountMap.values()) {
            if (countValue == 4) {
                return true;
            }
        }

        return false;
    }

    //スリーカードかどうか判定する
    public Boolean isThreeOfAKind(List<PlayingCards> playingCardsList) {
        //キーには数札、値には数字の出現回数を格納
        Map<Integer, Integer> cardRankCountMap = getCardRankCountMap(playingCardsList);

        //countMapの値を全て取得して、値(数字の出現回数)が3であればtrueを返す
        for (int countValue : cardRankCountMap.values()) {
            if (countValue == 3) {
                return true;
            }
        }

        return false;
    }

    //ツーペアかどうか判定する
    public Boolean isTwoPair(List<PlayingCards> playingCardsList) {
        //キーには数札、値には数字の出現回数を格納
        Map<Integer, Integer> cardRankCountMap = getCardRankCountMap(playingCardsList);

        //countMapの値を全て取得して、値(数字の出現回数)が2かつ1であればtrueを返す
        for (int countValue : cardRankCountMap.values()) {
            if (countValue == 2 && countValue == 1) {
                return true;
            }
        }

        return false;
    }

    //ワンペアかどうか判定する
    public Boolean isOnePair(List<PlayingCards> playingCardsList) {
        //キーには数札、値には数字の出現回数を格納
        Map<Integer, Integer> cardRankCountMap = getCardRankCountMap(playingCardsList);

        //countMapの値を全て取得して、値(数字の出現回数)が2であればtrueを返す
        for (int countValue : cardRankCountMap.values()) {
            if (countValue == 2) {
                return true;
            }
        }

        return false;
    }

    //トランプの数札の枚数を数える
    public Map<Integer, Integer> getCardRankCountMap(List<PlayingCards> playingCardsList) {

        //キーには数札、値には数字の出現回数を格納
        Map<Integer, Integer> cardRankCountMap = new HashMap<>();

        for (PlayingCards cardRank : playingCardsList) {
            PlayingCards playingCards = new PlayingCards(cardRank.getPictorialPattern(), cardRank.getCardRank());
            int cardNumber = playingCards.getCardRank();

            //countMapに同じ数札が含まれていたら、その数札の出現回数を1回増やす
            if (cardRankCountMap.containsKey(cardNumber)) {
                cardRankCountMap.put(cardNumber, cardRankCountMap.get(cardNumber) + 1);
                //同じ数札がなければ、出現回数を1回にする
            } else {
                cardRankCountMap.put(cardNumber, 1);
            }
        }

        return cardRankCountMap;
    }
}
