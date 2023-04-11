package com.example;

import java.util.ArrayList;
import java.util.List;

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

    public PokerHand getPokerRole(List<PlayingCards.PictorialPattern> playingCardsList) {
        if (isFourOfAKind(playingCardsList)) {
            return PokerHand.FOUR_OF_A_KIND;
        }

        return PokerHand.HIGH_CARD;
    }

    public Boolean isFourOfAKind(List<PlayingCards.PictorialPattern> playingCardsList) {

        List<PlayingCards.PictorialPattern> playingCards = new ArrayList<>();

        for (PlayingCards.PictorialPattern pattern : playingCardsList) {

            playingCards.remove(pattern.getPictorialPatternMark());

        }

        if (playingCardsList.get(1).equals(playingCardsList.get(2))) {
            return true;
        }

        return false;
    }

}
