package com.wurmonline.server.spells;

import com.wurmonline.server.deities.Deities;
import com.wurmonline.server.deities.Deity;
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
                    if (AddSpell) {
                        for (final Deity deity : Deities.getDeities()) {
                            deity.addSpell(ModifyGestationTime);
                        }
                        if (CS_fo) {
                            Deities.getDeity(Deities.DEITY_FO).addSpell(ModifyGestationTime);
                        }
                        if (CS_mag) {
                            Deities.getDeity(Deities.DEITY_MAGRANON).addSpell(ModifyGestationTime);
                        }
                        if (CS_vyn) {
                            Deities.getDeity(Deities.DEITY_VYNORA).addSpell(ModifyGestationTime);
                        }
                        if (CS_lib) {
                            Deities.getDeity(Deities.DEITY_LIBILA).addSpell(ModifyGestationTime);
                        }
                        if (CustomGod101) {
                            Deities.getDeity(101).addSpell(ModifyGestationTime);
                        }
                        if (CustomGod102) {
                            Deities.getDeity(102).addSpell(ModifyGestationTime);
                        }
                    }
                }
            }
        }.run();
    }
}
