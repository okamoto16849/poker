package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PokerRuleTest {

    PokerRule pokerRule = new PokerRule();

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
}
