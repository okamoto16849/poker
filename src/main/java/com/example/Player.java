package com.example;

import java.util.ArrayList;
import java.util.List;

public class Player {

    List<PlayingCards> playerCardList = new ArrayList<>();

    //プレイヤーが5枚の手札をひく
    public List<PlayingCards> addPlayerHand(List<PlayingCards> cardList) {
        for (int i = 0; i < 5; i++) {
            playerCardList.add(cardList.get(i));
        }
        return playerCardList;
    }
}
