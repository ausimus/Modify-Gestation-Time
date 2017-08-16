package org.ausimus.wurmunlimited.mods.modifygestationperiod.Config;

import java.util.Properties;

import static org.ausimus.wurmunlimited.mods.modifygestationperiod.Config.Constants.*;
import static org.ausimus.wurmunlimited.mods.modifygestationperiod.Config.Constants.ActionBreedGestation;
import static org.ausimus.wurmunlimited.mods.modifygestationperiod.Config.Constants.CoolDown;

public class AusProperties {
    /**
     * @param properties A list of properties stored in an external file named MODNAME.properties.
     **/
    public AusProperties(Properties properties) {
        GestationTime = Byte.parseByte(properties.getProperty("GestationTime", Byte.toString(GestationTime)));
        AddSpell = Boolean.parseBoolean(properties.getProperty("AddSpell", Boolean.toString(AddSpell)));

        CS_all = Boolean.parseBoolean(properties.getProperty("CS_all", Boolean.toString(CS_all)));
        CS_fo = Boolean.parseBoolean(properties.getProperty("CS_fo", Boolean.toString(CS_fo)));
        CS_vyn = Boolean.parseBoolean(properties.getProperty("CS_vyn", Boolean.toString(CS_vyn)));
        CS_mag = Boolean.parseBoolean(properties.getProperty("CS_mag", Boolean.toString(CS_mag)));
        CS_lib = Boolean.parseBoolean(properties.getProperty("CS_lib", Boolean.toString(CS_lib)));

        CustomGod102 = Boolean.parseBoolean(properties.getProperty("CustomGod102", Boolean.toString(CustomGod102)));
        CustomGod101 = Boolean.parseBoolean(properties.getProperty("CustomGod101", Boolean.toString(CustomGod101)));

        aCastingTime = Integer.parseInt(properties.getProperty("aCastingTime", Integer.toString(aCastingTime)));
        aCost = Integer.parseInt(properties.getProperty("aCost", Integer.toString(aCost)));
        aDifficulty = Integer.parseInt(properties.getProperty("aDifficulty", Integer.toString(aDifficulty)));
        aLevel = Integer.parseInt(properties.getProperty("aLevel", Integer.toString(aLevel)));
        CoolDown = Long.parseLong(properties.getProperty("CoolDown", Long.toString(CoolDown)));
        ActionBreedGestation = Boolean.parseBoolean(properties.getProperty("ActionBreedGestation", Boolean.toString(ActionBreedGestation)));
    }
}
