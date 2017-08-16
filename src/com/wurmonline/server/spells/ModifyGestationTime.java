package com.wurmonline.server.spells;

import com.wurmonline.server.LoginHandler;
import com.wurmonline.server.behaviours.ActionEntry;
import com.wurmonline.server.creatures.Creature;
import com.wurmonline.server.creatures.Offspring;
import com.wurmonline.server.skills.Skill;
import org.ausimus.wurmunlimited.mods.modifygestationperiod.Config.Constants;
import org.gotti.wurmunlimited.modsupport.actions.ModActions;

class ModifyGestationTime extends ReligiousSpell {
    ModifyGestationTime() {
        super(
                "Decrease Gestation",
                ModActions.getNextActionId(),
                Constants.aCastingTime,
                Constants.aCost,
                Constants.aDifficulty,
                Constants.aLevel,
                Constants.CoolDown
        );
        this.targetCreature = true;
        this.description = "Lowers Creature Gestation Time";
        ActionEntry actionEntry = ActionEntry.createEntry((short) number, name, "Casting",
                new int[]{2, 36, 48});
        ModActions.registerAction(actionEntry);
    }

    /**
     * @param castSkill Skill required.
     * @param performer The Creature casting the spell.
     * @param target    The spell target.
     * @return Obvious.
     **/
    boolean precondition(Skill castSkill, Creature performer, Creature target) {
        if (!target.isPregnant()) {
            performer.getCommunicator().sendNormalServerMessage("Creature not pregnant.");
            return false;
        }
        if (target.isNotFemale()) {
            performer.getCommunicator().sendNormalServerMessage("Not Female.");
            return false;
        }
        return true;
    }

    /**
     * @param castSkill Skill required.
     * @param performer The Creature casting the spell.
     * @param target    The spell target.
     **/
    void doEffect(Skill castSkill, double power, Creature performer, Creature target) {
        Offspring offspring = target.getOffspring();
        int left = offspring.getDaysLeft();
        if (target.isPregnant()) {
            target.checkPregnancy(true);
            performer.getCommunicator().sendNormalServerMessage(LoginHandler.raiseFirstLetter(target.getHeSheItString()) + " will deliver in about " + left + (left != 1 ? " days." : " day."));
        }
    }
}