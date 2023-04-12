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
        //キーには数字、値には数字の出現回数を格納
        Map<Integer, Integer> countMap = new HashMap<>();

        for (PlayingCards cardRank : playingCardsList) {
            PlayingCards playingCards = new PlayingCards(cardRank.getPictorialPattern(), cardRank.getCardRank());
            int cardNumber = playingCards.getCardRank();

            //countMapに同じ数字が含まれていたら、その数字の出現回数を1回増やす
            if (countMap.containsKey(cardNumber)) {
                countMap.put(cardNumber, countMap.get(cardNumber) + 1);
            //同じ数字がなければ、出現回数を1回にする
            } else {
                countMap.put(cardNumber, 1);
            }
        }

        //countMapの値を全て取得して、値(数字の出現回数)が4であればtrueを返す
        for (int countValue : countMap.values()) {
            if (countValue == 4) {
                return true;
            }
        }

        return false;
    }
}
