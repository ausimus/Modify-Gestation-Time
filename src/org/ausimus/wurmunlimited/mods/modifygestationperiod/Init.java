package org.ausimus.wurmunlimited.mods.modifygestationperiod;
import com.wurmonline.server.spells.*;
import org.ausimus.wurmunlimited.mods.modifygestationperiod.Config.AusProperties;
import org.gotti.wurmunlimited.modloader.interfaces.*;
import org.gotti.wurmunlimited.modsupport.actions.ModActions;

import java.util.Properties;

import static org.ausimus.wurmunlimited.mods.modifygestationperiod.Config.Constants.*;

public class Init implements PreInitable, WurmServerMod, ServerStartedListener, Configurable {

    @Override
    public void preInit() {
        if (ActionBreedGestation) {
            new DecreaseGestationPeriod();
            ModActions.init();
        }
    }

    @Override
    public void onServerStarted() {
        new InitSpell();
    }

    /**
     * @param properties A list of properties stored in an external file named MODNAME.properties.
     **/
    @Override
    public void configure(Properties properties) {
        new AusProperties(properties);
    }
}