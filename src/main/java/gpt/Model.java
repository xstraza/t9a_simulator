package gpt;


public class Model {
    OffensiveProfile offensiveProfile;
    DefensiveProfile defensiveProfile;

    public Model(int attacks, int offensiveSkill, int strength, int armorPenetration, int agility, int healthPoints, int defensiveSkill, int resilience, int armor) {
        this.offensiveProfile = new OffensiveProfile(attacks, offensiveSkill, strength, armorPenetration, agility);
        this.defensiveProfile = new DefensiveProfile(healthPoints, defensiveSkill, resilience, armor);
    }

    public int getSpecialSave() {
        //todo;
        return -1;
    }

    public int getHealthPoints() {
        //todo
        return defensiveProfile.hp();
    }
}

