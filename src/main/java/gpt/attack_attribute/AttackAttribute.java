package gpt.attack_attribute;

import gpt.model.Model;

public interface AttackAttribute {
    void onAttackEvent(AttackEvent event, Model attacker, Model defender);
}
