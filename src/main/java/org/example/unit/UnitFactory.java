package org.example.unit;

public class UnitFactory {

    public static Unit create(String name, int a, int os, int s, int ap, int agi, int hp, int ds, int res, int arm) {
        OffensiveProfile offensiveProfile = new OffensiveProfile(
                a,
                os,
                s,
                ap,
                agi
        );
        DefensiveProfile defensiveProfile = new DefensiveProfile(
                hp,
                ds,
                res,
                arm
        );
        return new UnitBuilder()
                .withName(name)
                .withOffensiveProfile(offensiveProfile)
                .withDefensiveProfile(defensiveProfile)
                .build();
    }

    private static class UnitBuilder {
        private OffensiveProfile offensiveProfile;
        private DefensiveProfile defensiveProfile;
        private String name;

        public UnitBuilder() {
        }

        public Unit build() {
            return new Unit(
                    this.name, this.offensiveProfile, this.defensiveProfile);
        }

        public UnitBuilder withOffensiveProfile(OffensiveProfile offensiveProfile) {
            this.offensiveProfile = offensiveProfile;
            return this;
        }

        public UnitBuilder withDefensiveProfile(DefensiveProfile defensiveProfile) {
            this.defensiveProfile = defensiveProfile;
            return this;
        }

        public UnitBuilder withName(String name) {
            this.name = name;
            return this;
        }
    }
}
