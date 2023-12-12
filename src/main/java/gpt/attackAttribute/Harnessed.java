package gpt.attackAttribute;

import gpt.Attack;
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
