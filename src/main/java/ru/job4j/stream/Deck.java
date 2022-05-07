package ru.job4j.stream;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {

    public enum Suit {
        Diamonds, Hearts, Spades, Clubs
    }

    public enum Value {
        V_6, V_7, V_8
    }

    public static class Card {
        private Suit suit;
        private Value value;

        private Card(Suit suit, Value value) {
            this.suit = suit;
            this.value = value;
        }

        @Override
        public String toString() {
            return suit + " " + value;
        }
    }

    public static void main(String[] args) {
        Stream.of(Suit.values())
                .flatMap(sv -> Stream.of(Value.values())
                        .map(vv -> new Card(sv, vv)))
                .forEach(System.out::println);
    }
}
