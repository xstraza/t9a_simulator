package gpt.specialRules;

import gpt.model.Model;
import gpt.specialRules.attribute.general.*;
import gpt.util.TriConsumer;

import java.util.Map;

public interface SpecialRule {

    void onEvent(Event event, Attack attack, Model defender);

    SpecialRuleType getSpecialRuleType();

    static void trigger(Event event, Attack attack, Model defender) {
        attack.getSpecialRules()
                .forEach(attribute ->
                        attribute.onEvent(event, attack, defender));
        defender.getProtections()
                .forEach(protection ->
                        protection.onEvent(event, attack, defender));
    }

    Map<SpecialRuleType, SpecialRule> map = Map.of(
            SpecialRuleType.HARNESSED, new Harnessed(),
            SpecialRuleType.INANIMATE, new Inanimate(),
            SpecialRuleType.CHARGE_AGILITY_BONUS, new ChargeAgilityBonus(),
            SpecialRuleType.LIGHTNING_REFLEXES, new LightningReflexes(),
            SpecialRuleType.FIGHT_IN_EXTRA_RANKS, new FightInExtraRank()
    );

    static SpecialRule chargeAgilityBonus() {
        return map.get(SpecialRuleType.CHARGE_AGILITY_BONUS);
    }

    static SpecialRule inanimate() {
        return map.get(SpecialRuleType.INANIMATE);
    }

    static SpecialRule harnessed() {
        return map.get(SpecialRuleType.HARNESSED);
    }

    static SpecialRule lightningReflexes() {
        return map.get(SpecialRuleType.LIGHTNING_REFLEXES);
    }

    static SpecialRule fightInExtraRank() {
        return map.get(SpecialRuleType.FIGHT_IN_EXTRA_RANKS);
    }

    static SpecialRule devastatingCharge(TriConsumer<Event,Attack,Model> consumer) {
        return new DevastatingCharge(consumer);
    }
}
