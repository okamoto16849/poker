package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PokerRuleTest {

    PokerRule pokerRule = new PokerRule();

    @Test
    @DisplayName("フルハウスになるカードをフルハウスと判定できる")
    void full_house_true() {
        assertFalse(pokerRule.isFullHouse(List.of(
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
        assertFalse(pokerRule.isFullHouse(List.of(
                new PlayingCards("❤︎", 3),
                new PlayingCards("♦︎", 4),
                new PlayingCards("♦︎", 5),
                new PlayingCards("♦︎", 6),
                new PlayingCards("♦︎", 8)
        )));
    }
}
