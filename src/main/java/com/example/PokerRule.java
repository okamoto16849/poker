package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class PokerRule {

    public enum PokerHand {
        ROYAL_STRAIGHT_FLUSH(10), //ロイヤルストレートフラッシュ
        STRAIGHT_FLUSH(9), //ストレートフラッシュ
        FOUR_OF_A_KIND(8), //フォーカード
        FULL_HOUSE(7), //フルハウス
        FLUSH(6), //フラッシュ
        STRAIGHT(5), //ストレート
        THREE_OF_A_KIND(4), //スリーカード
        TWO_PAIR(3), //ツーペア
        ONE_PAIR(2), //ワンペア
        HIGH_CARD(1); //ハイカード(役なし)

        private int pokerRoleGrade;

        PokerHand(int pokerRoleGrade) {
            this.pokerRoleGrade = pokerRoleGrade;
        }

        public int getPokerRoleGrade() {
            return pokerRoleGrade;
        }
    }

    /**
     * 対戦結果を表示する
     * @param playerHand プレイヤーの役
     * @param computerHand コンピュターの役
     */
    public void showMatchResult(PokerHand playerHand, PokerHand computerHand) {
        if (playerHand.getPokerRoleGrade() > computerHand.getPokerRoleGrade()) {
            System.out.println("プレイヤーの勝ちです");
        } else if (playerHand.getPokerRoleGrade() == computerHand.getPokerRoleGrade()) {
            System.out.println("引き分けです");
        } else {
            System.out.println("プレイヤーの負けです");
        }
    }

    //ポーカーの役を判定する
    public PokerHand judgePokerHand(List<PlayingCards> playingCardsList) {

        if (isRoyalStraightFlush(playingCardsList)) {
            return PokerHand.ROYAL_STRAIGHT_FLUSH;
        }

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

    public boolean isRoyalStraightFlush(List<PlayingCards> playingCardsList) {

        List<Integer> cardRankList = new ArrayList<>();

        for (PlayingCards playingCards : playingCardsList) {
            int cardNumber = playingCards.getCardRank();
            cardRankList.add(cardNumber);
        }

        Collections.sort(cardRankList);

        for (int i = 0; i < 1; i++) {
            if (cardRankList.get(i) == PlayingCards.CardRank.ACE.getRankNumber() &&
                    cardRankList.get(i + 1) == PlayingCards.CardRank.TEN.getRankNumber() &&
                    cardRankList.get(i + 2) == PlayingCards.CardRank.JACK.getRankNumber() &&
                    cardRankList.get(i + 3) == PlayingCards.CardRank.QUEEN.getRankNumber() &&
                    cardRankList.get(i + 4) == PlayingCards.CardRank.KING.getRankNumber()) {
                if (isFLUSH(playingCardsList)) {
                    return true;
                }
            }
        }

        return false;
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
