package com.diku.ku;

public enum Teams {
    ONE("Hold 1"),
    TWO("Hold 2"),
    THREE("Hold 3"),
    FOUR("Hold 4"),
    FIVE("Hold 5"),
    SIX("Hold 6"),
    SEVEN("Hold 7"),
    EIGHT("Hold 8"),
    NINE("Hold 9"),
    TEN("Hold 10"),
    ELEVEN("Hold 11"),
    TWELVE("Hold 12"),
    THIRTEEN("Hold 13"),
    FOURTEEN("Hold 14"),
    FIFTEEN("Hold 15"),
    SIXTEEN("Hold 16"),
    SEVENTEEN("Hold 17"),
    EIGHTEEN("Hold 18");

    String name;
    Teams(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

}
