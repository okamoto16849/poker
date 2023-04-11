package com.example;

public enum PokerRole {

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

    PokerRole(int pokerRole) {
        this.pokerRole = pokerRole;
    }

    public int getPokerRole() {
        return pokerRole;
    }
}
