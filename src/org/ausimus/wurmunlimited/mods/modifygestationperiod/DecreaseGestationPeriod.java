package org.ausimus.wurmunlimited.mods.modifygestationperiod;

import javassist.*;
import javassist.bytecode.Descriptor;
import org.ausimus.wurmunlimited.mods.modifygestationperiod.Config.Constants;
import org.gotti.wurmunlimited.modloader.classhooks.HookException;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;


class DecreaseGestationPeriod{
    DecreaseGestationPeriod() {
        try {
            CtClass ctClass = HookManager.getInstance().getClassPool().get("com.wurmonline.server.creatures.Offspring");
            CtMethod method = ctClass.getMethod("create", Descriptor.ofMethod(CtPrimitiveType.voidType, null));
            method.setBody("{\n" +
                    "        this.deliveryDays = " + Constants.GestationTime + ";\n" +
                    "        if(this.deliveryDays < 1) {\n" +
                    "            this.deliveryDays = 1;\n" +
                    "        }\n" +
                    "\n" +
                    "        java.sql.Connection dbcon = null;\n" +
                    "        java.sql.PreparedStatement ps = null;\n" +
                    "\n" +
                    "        try {\n" +
                    "            dbcon = com.wurmonline.server.DbConnector.getCreatureDbCon();\n" +
                    "            ps = dbcon.prepareStatement(\"INSERT INTO OFFSPRING (MOTHERID,FATHERID,TRAITS,DELIVERYDAYS) VALUES (?,?,?,?)\");\n" +
                    "            ps.setLong(1, this.mother);\n" +
                    "            ps.setLong(2, this.father);\n" +
                    "            ps.setLong(3, this.traits);\n" +
                    "            ps.setByte(4, this.deliveryDays);\n" +
                    "            ps.executeUpdate();\n" +
                    "        } catch (java.sql.SQLException var7) {\n" +
                    "            logger.log(java.util.logging.Level.WARNING, \"Failed to create offspring for \" + this.mother, var7);\n" +
                    "        } finally {\n" +
                    "            com.wurmonline.server.utils.DbUtilities.closeDatabaseObjects(ps, (java.sql.ResultSet)null);\n" +
                    "            com.wurmonline.server.DbConnector.returnConnection(dbcon);\n" +
                    "        }\n" +
                    "\n" +
                    "    }");
        } catch (CannotCompileException | NotFoundException ex) {
            throw new HookException(ex);
        }
    }
}