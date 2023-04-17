package com.example;

public class PlayingCards {

    public enum PictorialPattern {
        //クラブ
        CLUBS("♣️"),
        //ダイヤ
        DIAMONDS("♦︎"),
        //ハート
        HEARTS("❤︎"),
        //スペード
        SPADES("♠️");

        private String pictorialPatternMark;

        private PictorialPattern(String pictorialPatternMark) {
            this.pictorialPatternMark = pictorialPatternMark;
        }

        public String getPictorialPatternMark() {
            return pictorialPatternMark;
        }
    }

    public enum CardRank {
        ACE(1), //数札の1
        TWO(2), //数札の2
        THREE(3), //数札の3
        FOUR(4), //数札の4
        FIVE(5), //数札の5
        SIX(6), //数札の6
        SEVEN(7), //数札の7
        EIGHT(8), //数札の8
        NINE(9), //数札の9
        TEN(10), //数札の10
        JACK(11), //絵札の11
        QUEEN(12), //絵札の12
        KING(13); //絵札の13
        private int rankNumber;

        private CardRank(int rankNumber) {
            this.rankNumber = rankNumber;
        }

        public int getRankNumber() {
            return this.rankNumber;
        }

    }

    private String pictorialPattern;

    private int cardRank;

    private PictorialPattern pattern;

    private CardRank rank;

    public PlayingCards(String pictorialPattern, int cardRank) {
        this.pictorialPattern = pictorialPattern;
        this.cardRank = cardRank;
    }

    public PlayingCards(PictorialPattern pattern, CardRank rank) {
        this.pattern = pattern;
        this.rank = rank;
    }

    public String getPictorialPattern() {
        return pictorialPattern;
    }

    public int getCardRank() {
        return cardRank;
    }

    @Override
    public String toString() {
        return "PlayingCards{" +
                "pictorialPattern='" + pictorialPattern + '\'' +
                ", cardRank=" + cardRank +
                '}';
    }
}
