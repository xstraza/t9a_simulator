package gpt.attack.attribute.general;

import gpt.attack.Attack;
import gpt.attack.AttackAttribute;
import gpt.attack.AttackEvent;
import gpt.model.Model;

public class Harnessed implements AttackAttribute {

    @Override
    public void onAttackEvent(AttackEvent event, Attack attack, Model defender) {
        if (event != AttackEvent.DETERMINE_ATTACKS) {
            return;
        }
        attack.setRank(attack.getRank() == 1 ? 1 : 100);
    }
}
