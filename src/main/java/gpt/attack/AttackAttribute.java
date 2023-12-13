package gpt.attack;

import gpt.model.Model;

public interface AttackAttribute {
    void onAttackEvent(AttackEvent event, Attack attack, Model defender);
}
