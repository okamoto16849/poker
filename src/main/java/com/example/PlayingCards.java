package com.example;

public class PlayingCards {

    public enum PictorialPattern {
        CLUBS("♠️"),
        DIAMONDS("♦︎"),
        HEARTS("❤︎"),
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
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13),
        ACE(1);
        private int rankNumber;

        private CardRank(int rankNumber) {
            this.rankNumber = rankNumber;
        }

        public int getRankNumber() {
            return this.rankNumber;
        }

    }

    private final String pictorialPattern;

    private final int cardRank;

    public PlayingCards(String pictorialPattern, int cardRank) {
        this.pictorialPattern = pictorialPattern;
        this.cardRank = cardRank;
    }

    @Override
    public String toString() {
        return "PlayingCards{" +
                "pictorialPattern='" + pictorialPattern + '\'' +
                ", cardRank=" + cardRank +
                '}';
    }
}
