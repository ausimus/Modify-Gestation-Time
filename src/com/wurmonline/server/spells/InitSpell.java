package com.wurmonline.server.spells;

import com.wurmonline.server.deities.Deities;
import com.wurmonline.server.deities.Deity;
import org.ausimus.wurmunlimited.mods.modifygestationperiod.Config.Constants;
import org.gotti.wurmunlimited.modloader.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;
import static org.ausimus.wurmunlimited.mods.modifygestationperiod.Config.Constants.*;

public class InitSpell {
    public InitSpell() {
        new Runnable() {
            @Override
            public void run() {
                if (AddSpell) {
                    final ModifyGestationTime ModifyGestationTime = new ModifyGestationTime();
                    try {
                        ReflectionUtil.callPrivateMethod(Spells.class, ReflectionUtil.getMethod(Spells.class, "addSpell"), ModifyGestationTime);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                            | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                    if (Constants.CS_all) {
                        for (final Deity deity : Deities.getDeities()) {
                            deity.addSpell(ModifyGestationTime);
                        }
                    } else {
                        if (Constants.CS_fo) {
                            Deities.getDeity(Deities.DEITY_FO).addSpell(ModifyGestationTime);
                        }
                        if (Constants.CS_mag) {
                            Deities.getDeity(Deities.DEITY_MAGRANON).addSpell(ModifyGestationTime);
                        }
                        if (Constants.CS_vyn) {
                            Deities.getDeity(Deities.DEITY_VYNORA).addSpell(ModifyGestationTime);
                        }
                        if (Constants.CS_lib) {
                            Deities.getDeity(Deities.DEITY_LIBILA).addSpell(ModifyGestationTime);
                        }
                        if (AddToCustom) {
                            /*
                            Adds to all custom gods with a id >= 100 and <= 1024 (a metric FuckTon).
                            Requires a server restart after ascension for spell to be visible.
                            */
                            for (int i = 100; i <= 1024; i++) {
                                Deities.getDeity(i).addSpell(ModifyGestationTime);
                            }
                        }
                    }
                }
            }
        }.run();
    }
}