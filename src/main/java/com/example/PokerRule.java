package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

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

        if (isStraightFlush(playingCardsList)) {
            return PokerHand.STRAIGHT_FLUSH;
        }

        if (isFourOfAKind(playingCardsList)) {
            return PokerHand.FOUR_OF_A_KIND;
        }

        if (isFullHouse(playingCardsList)) {
            return PokerHand.FULL_HOUSE;
        }

        if (isFLUSH(playingCardsList)) {
            return PokerHand.FLUSH;
        }

        if (isStraight(playingCardsList)) {
            return PokerHand.STRAIGHT;
        }

        if (isThreeOfAKind(playingCardsList)) {
            return PokerHand.THREE_OF_A_KIND;
        }

        if (isTwoPair(playingCardsList)) {
            return PokerHand.TWO_PAIR;
        }

        if (isOnePair(playingCardsList)) {
            return PokerHand.ONE_PAIR;
        }

        return PokerHand.HIGH_CARD;
    }

    //ストレートフラッシュかどうか判定する
    public boolean isStraightFlush(List<PlayingCards> playingCardsList) {

        return isStraight(playingCardsList) && isFLUSH(playingCardsList);
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

        //Map.Entryのリストを作成
        List<Map.Entry<Integer, Integer>> listEntries = new ArrayList<>(cardRankCountMap.entrySet());

        //Mapの値を比較して降順にソートする
        listEntries.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        //3の数字の出現回数
        int threePairNumber = 0;
        //countMapの値を全て取得して、値(数字の出現回数)が3であればthreePairNumberを1増やす
        for (Map.Entry<Integer, Integer> listEntry : listEntries) {
            if (listEntry.getValue() == 3) {
                threePairNumber++;
                //値(数字の出現回数)が2かつ3の数字の出現回数が1であればtrueを返す
            } else if (listEntry.getValue() == 2 && threePairNumber == 1) {
                return true;
            }
        }
        return false;
    }

    //フラッシュかどうか判定する
    public boolean isFLUSH(List<PlayingCards> playingCardsList) {

        //キーには絵札、値には絵札の出現回数を格納
        Map<String, Integer> countPictorialPatternMap = getPictorialPatternCountMap(playingCardsList);

        for (int countValue : countPictorialPatternMap.values()) {
            if (countValue == 5) {
                return true;
            }
        }

        return false;
    }

    //ストレートかどうか判定する
    public boolean isStraight(List<PlayingCards> playingCardsList) {

        List<Integer> cardRankList = new ArrayList<>();

        for (PlayingCards playingCards : playingCardsList) {
            int cardNumber = playingCards.getCardRank();
            cardRankList.add(cardNumber);
        }

        Collections.sort(cardRankList);

        for (int i = 0; i < cardRankList.size() - 1; i++) {
            if (cardRankList.get(i) != cardRankList.get(i + 1) - 1) {
                return false;
            }
        }

        return true;
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
                twoPairNumber++;
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

        for (PlayingCards playingCards : playingCardsList) {
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

    //トランプの絵札の枚数を数える
    public Map<String, Integer> getPictorialPatternCountMap(List<PlayingCards> playingCardsList) {

        //キーには絵札、値には絵札の出現回数を格納
        Map<String, Integer> cardPictorialPatternMap = new HashMap<>();

        for (PlayingCards playingCards : playingCardsList) {
            String pictorialPattern = playingCards.getPictorialPattern();

            //countMapに同じ絵札が含まれていたら、その絵札の出現回数を1回増やす
            if (cardPictorialPatternMap.containsKey(pictorialPattern)) {
                cardPictorialPatternMap.put(pictorialPattern,
                        cardPictorialPatternMap.get(pictorialPattern) + 1);
                //同じ絵札がなければ、出現回数を1回にする
            } else {
                cardPictorialPatternMap.put(pictorialPattern, 1);
            }
        }

        return cardPictorialPatternMap;
    }
}
