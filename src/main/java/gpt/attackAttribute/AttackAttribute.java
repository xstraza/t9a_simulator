package gpt.attackAttribute;

import gpt.model.Model;

public interface AttackAttribute {
    void onAttackEvent(AttackEvent event, Model attacker, Model defender);
}
