package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PokerRuleTest {

    PokerRule pokerRule = new PokerRule();


    @Test
    @DisplayName("ポーカーの役を判定する")
    void judgePokerHand() {
        PokerRule.PokerHand pokerHandActual = pokerRule.judgePokerHand(List.of(
                new PlayingCards("♦︎", 1),
                new PlayingCards("♦︎", 10),
                new PlayingCards("♦︎", 11),
                new PlayingCards("♦︎", 12),
                new PlayingCards("♦︎", 13)
        ));

        assertEquals(PokerRule.PokerHand.ROYAL_STRAIGHT_FLUSH, pokerHandActual);
    }

    @Test
    @DisplayName("ロイヤルストレートフラッシュになるカードをロイヤルストレートフラッシュになると判定できる")
    void royal_straight_flush_true() {
        assertTrue(pokerRule.isRoyalStraightFlush(List.of(
                new PlayingCards("♦︎", 1),
                new PlayingCards("♦︎", 10),
                new PlayingCards("♦︎", 11),
                new PlayingCards("♦︎", 12),
                new PlayingCards("♦︎", 13)
        )));
    }

    @Test
    @DisplayName("ロイヤルストレートフラッシュになるカードをロイヤルストレートフラッシュではないと判定できる")
    void royal_straight_flush_false() {
        assertFalse(pokerRule.isRoyalStraightFlush(List.of(
                new PlayingCards("♦︎", 2),
                new PlayingCards("♦︎", 10),
                new PlayingCards("♦︎", 11),
                new PlayingCards("♦︎", 12),
                new PlayingCards("♦︎", 13)
        )));
    }

    @Test
    @DisplayName("ストレートフラッシュになるカードをストレートフラッシュになると判定できる")
    void straight_flush_true() {
        assertTrue(pokerRule.isStraightFlush(List.of(
                new PlayingCards("♦︎", 6),
                new PlayingCards("♦︎", 7),
                new PlayingCards("♦︎", 8),
                new PlayingCards("♦︎", 9),
                new PlayingCards("♦︎", 10)
        )));
    }

    @Test
    @DisplayName("ストレートフラッシュになるカードをストレートフラッシュにならないと判定できる")
    void straight_flush_false() {
        assertFalse(pokerRule.isStraightFlush(List.of(
                new PlayingCards("♦︎", 6),
                new PlayingCards("♦︎", 7),
                new PlayingCards("♦︎", 8),
                new PlayingCards("♦︎", 9),
                new PlayingCards("♦︎", 11)
        )));
    }


    @Test
    @DisplayName("フォーカードになるカードをフォーカードと判定できる")
    void four_of_kind_true() {
        assertTrue(pokerRule.isFourOfAKind(List.of(
                new PlayingCards("❤︎", 3),
                new PlayingCards("♦︎", 3),
                new PlayingCards("♠︎", 3),
                new PlayingCards("♠︎", 3),
                new PlayingCards("♦︎", 1)
        )));
    }

    @Test
    @DisplayName("フォーカードになるカードをフォーカードにならないと判定できる")
    void four_of_kind_false() {
        assertFalse(pokerRule.isFourOfAKind(List.of(
                new PlayingCards("❤︎", 3),
                new PlayingCards("♦︎", 3),
                new PlayingCards("♠︎", 3),
                new PlayingCards("♠︎", 4),
                new PlayingCards("♦︎", 1)
        )));
    }

    @Test
    @DisplayName("フルハウスになるカードをフルハウスと判定できる")
    void full_house_true() {
        assertTrue(pokerRule.isFullHouse(List.of(
                new PlayingCards("❤︎", 3),
                new PlayingCards("♦︎", 3),
                new PlayingCards("♦︎", 3),
                new PlayingCards("♦︎", 6),
                new PlayingCards("♦︎", 6)
        )));
    }

    @Test
    @DisplayName("フルハウスになるカードをフルハウスではないと判定できる")
    void full_house_false() {
        assertFalse(pokerRule.isFullHouse(List.of(
                new PlayingCards("❤︎", 3),
                new PlayingCards("♦︎", 6),
                new PlayingCards("♦︎", 3),
                new PlayingCards("♦︎", 3),
                new PlayingCards("♦︎", 5)
        )));
    }

    @Test
    @DisplayName("フラッシュになるカードをフラッシュと判定できる")
    void flush_true() {
        assertTrue(pokerRule.isFLUSH(List.of(
                new PlayingCards("❤︎", 3),
                new PlayingCards("❤︎", 4),
                new PlayingCards("❤︎", 5),
                new PlayingCards("❤︎", 6),
                new PlayingCards("❤︎", 7)
        )));
    }

    @Test
    @DisplayName("フラッシュになるカードをフラッシュにならないと判定できる")
    void flush_false() {
        assertFalse(pokerRule.isFLUSH(List.of(
                new PlayingCards("❤︎", 3),
                new PlayingCards("❤︎", 4),
                new PlayingCards("❤︎", 5),
                new PlayingCards("❤︎", 6),
                new PlayingCards("♦", 7)
        )));
    }

    @Test
    @DisplayName("ストレートになるカードをストレートと判定できる")
    void straight_true() {
        assertTrue(pokerRule.isStraight(List.of(
                new PlayingCards("❤︎", 3),
                new PlayingCards("♦︎", 4),
                new PlayingCards("♦︎", 5),
                new PlayingCards("♦︎", 6),
                new PlayingCards("♦︎", 7)
        )));
    }

    @Test
    @DisplayName("ストレートになるカードをストレートではないと判定できる")
    void straight_false() {
        assertFalse(pokerRule.isStraight(List.of(
                new PlayingCards("❤︎", 3),
                new PlayingCards("♦︎", 4),
                new PlayingCards("♦︎", 5),
                new PlayingCards("♦︎", 6),
                new PlayingCards("♦︎", 8)
        )));
    }

    @Test
    @DisplayName("スリーカードになるカードをスリーカードと判定できる")
    void three_of_kind_true() {
        assertTrue(pokerRule.isThreeOfAKind(List.of(
                new PlayingCards("❤︎", 4),
                new PlayingCards("♠︎", 4),
                new PlayingCards("♠︎", 4),
                new PlayingCards("♦︎", 6),
                new PlayingCards("♦︎", 7)
        )));
    }

    @Test
    @DisplayName("スリーカードになるカードをスリーカードにならないと判定できる")
    void three_of_kind_false() {
        assertFalse(pokerRule.isThreeOfAKind(List.of(
                new PlayingCards("❤︎", 4),
                new PlayingCards("♠︎", 4),
                new PlayingCards("♠︎", 5),
                new PlayingCards("♦︎", 6),
                new PlayingCards("♦︎", 7)
        )));
    }

    @Test
    @DisplayName("ツーペアになるカードをツーペアと判定できる")
    void two_pair_true() {
        assertTrue(pokerRule.isTwoPair(List.of(
                new PlayingCards("❤︎", 4),
                new PlayingCards("♠︎", 4),
                new PlayingCards("♠︎", 5),
                new PlayingCards("♦︎", 5),
                new PlayingCards("♦︎", 7)
        )));
    }

    @Test
    @DisplayName("ツーペアになるカードをツーペアにならないと判定できる")
    void two_pair_false() {
        assertFalse(pokerRule.isTwoPair(List.of(
                new PlayingCards("❤︎", 4),
                new PlayingCards("♠︎", 4),
                new PlayingCards("♠︎", 5),
                new PlayingCards("♦︎", 6),
                new PlayingCards("♦︎", 7)
        )));
    }

    @Test
    @DisplayName("ワンペアになるカードをワンペアと判定できる")
    void one_pair_true() {
        assertTrue(pokerRule.isOnePair(List.of(
                new PlayingCards("❤︎", 7),
                new PlayingCards("♠︎", 4),
                new PlayingCards("♠︎", 5),
                new PlayingCards("♦︎", 2),
                new PlayingCards("♦︎", 7)
        )));
    }

    @Test
    @DisplayName("ワンペアになるカードをワンペアでないと判定できる")
    void one_pair_false() {
        assertFalse(pokerRule.isOnePair(List.of(
                new PlayingCards("❤︎", 7),
                new PlayingCards("♠︎", 4),
                new PlayingCards("♠︎", 5),
                new PlayingCards("♦︎", 2),
                new PlayingCards("♦︎", 8)
        )));
    }

    @Test
    @DisplayName("トランプの数札の枚数を数える")
    void getCardRankCountMap() {

        Map<Integer, Integer> cardRankMapExpected = new HashMap<>();

        cardRankMapExpected.put(2, 1);
        cardRankMapExpected.put(4, 1);
        cardRankMapExpected.put(5, 1);
        cardRankMapExpected.put(7, 2);

        Map<Integer, Integer> cardRankMapActual = pokerRule.getCardRankCountMap(List.of(
                new PlayingCards("❤︎", 7),
                new PlayingCards("♠︎", 4),
                new PlayingCards("♠︎", 5),
                new PlayingCards("♦︎", 2),
                new PlayingCards("♦︎", 7)
        ));
        assertEquals(cardRankMapExpected, cardRankMapActual);
    }

    @Test
    @DisplayName("トランプの絵札の枚数を数える")
    void getPictorialPatternCountMap() {

        Map<String, Integer> cardPictorialPatternMap = new HashMap<>();

        cardPictorialPatternMap.put("❤︎", 5);

        Map<String, Integer> cardPictorialPatternMapActual = pokerRule.getPictorialPatternCountMap(List.of(
                new PlayingCards("❤︎", 7),
                new PlayingCards("❤︎", 5),
                new PlayingCards("❤︎", 4),
                new PlayingCards("❤︎", 9),
                new PlayingCards("❤︎", 8)
        ));
        assertEquals(cardPictorialPatternMap, cardPictorialPatternMapActual);
    }
}


