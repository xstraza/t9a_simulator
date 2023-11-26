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
        return 10;
    }

    public int getHealthPoints() {
        //todo
        return defensiveProfile.hp();
    }

    public int getResilience() {
        return defensiveProfile.resilience();
    }

    public int getArmor() {
        return defensiveProfile.armour();
    }

    public int getOffensiveSkill() {
        return offensiveProfile.offensiveSkill();
    }

    public int getDefensiveSkill() {
        return defensiveProfile.defensiveSkill();
    }

    public int getStrength() {
        return offensiveProfile.strength();
    }

    public int getAttacks() {
        return offensiveProfile.attacks();
    }

    public int getArmorPenetration() {
        return offensiveProfile.armourPenetration();
    }
}

