package org.example.unit;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Unit {
    private final String name;
    private final OffensiveProfile offensiveProfile;
    private final DefensiveProfile defensiveProfile;
}
