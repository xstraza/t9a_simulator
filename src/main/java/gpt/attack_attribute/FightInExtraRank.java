package gpt.attack_attribute;

import gpt.model.Model;

public class FightInExtraRank implements AttackAttribute {
    @Override
    public void onAttackEvent(AttackEvent event, Model attacker, Model defender) {
        if (event != AttackEvent.DETERMINE_ATTACKS) {
            return;
        }
        attacker.setFightInExtraRanks(attacker.getFightInExtraRanks() + 1);
    }
}
