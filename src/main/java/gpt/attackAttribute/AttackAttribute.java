package gpt.attackAttribute;

import gpt.Attack;
import gpt.model.Model;

public interface AttackAttribute {
    void onAttackEvent(AttackEvent event, Attack attack, Model defender);
}
