package com.example;

import java.util.ArrayList;
import java.util.List;

public class Computer {
    List<PlayingCards> computerCardList = new ArrayList<>();

    //コンピュターが5枚の手札をひく
    public List<PlayingCards> addComputerHand(List<PlayingCards> cardList) {
        for (int i = 0; i < 5; i++) {
            computerCardList.add(cardList.get(i));
        }
        return computerCardList;
    }
}
