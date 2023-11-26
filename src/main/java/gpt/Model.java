package gpt;


import java.util.List;

public class Model {
    OffensiveProfile offensiveProfile;
    DefensiveProfile defensiveProfile;
    List<Armor> armor;
    Weapon weapon;

    public Model(OffensiveProfile offensiveProfile, DefensiveProfile defensiveProfile, List<Armor> armor, Weapon weapon) {
        this.offensiveProfile = offensiveProfile;
        this.defensiveProfile = defensiveProfile;
        this.armor = armor;
        this.weapon = weapon;
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

