package org.example.unit;

public class OrkFactory {


    public static Unit createOrk() {
        return UnitFactory.create(
                "ork",
                1,2,3,4,5,6,7,8,9
        );
    }
}
