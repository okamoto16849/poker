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
    public PokerHand judgePokerHand(boolean isFourOfAKind, boolean isFullHouse,
                                    boolean isThreeOfAKind, boolean isTwoPair,
                                    boolean isOnePair) {
        if (isFourOfAKind) {
            return PokerHand.FOUR_OF_A_KIND;
        }

        if (isFullHouse) {
            return PokerHand.FULL_HOUSE;
        }

        if (isThreeOfAKind) {
            return PokerHand.THREE_OF_A_KIND;
        }

        if (isTwoPair) {
            return PokerHand.TWO_PAIR;
        }

        if (isOnePair) {
            return PokerHand.ONE_PAIR;
        }

        return PokerHand.HIGH_CARD;
    }

    //フォーカードかどうか判定する
    public boolean isFourOfAKind(List<PlayingCards> playingCardsList) {
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

    //フルハウスかどうか判定する
    public boolean isFullHouse(List<PlayingCards> playingCardsList) {
        //キーには数札、値には数字の出現回数を格納
        Map<Integer, Integer> cardRankCountMap = getCardRankCountMap(playingCardsList);

        //3の数字の出現回数
        int threePairNumber = 0;
        //countMapの値を全て取得して、値(数字の出現回数)が3であればthreePairNumberを1増やす
        for (int countValue : cardRankCountMap.values()) {
            if (countValue == 3) {
                threePairNumber = threePairNumber + 1;
            //値(数字の出現回数)が2かつ3の数字の出現回数が1であればtrueを返す
            } else if (countValue == 2 && threePairNumber == 1) {
                return true;
            }
        }

        return false;
    }

    //スリーカードかどうか判定する
    public boolean isThreeOfAKind(List<PlayingCards> playingCardsList) {
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
    public boolean isTwoPair(List<PlayingCards> playingCardsList) {
        //キーには数札、値には数字の出現回数を格納
        Map<Integer, Integer> cardRankCountMap = getCardRankCountMap(playingCardsList);

        //2の数字の出現回数
        int twoPairNumber = 0;
        //countMapの値を全て取得して、値(数字の出現回数)が2であればtwoPairNumberに1を足す
        for (int countValue : cardRankCountMap.values()) {
            if (countValue == 2) {
                twoPairNumber = twoPairNumber + 1;
                //2の数字の出現回数が2つあればtrueを返す
                if (twoPairNumber == 2) {
                    return true;

                }
            }
        }

        return false;
    }

    //ワンペアかどうか判定する
    public boolean isOnePair(List<PlayingCards> playingCardsList) {
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
