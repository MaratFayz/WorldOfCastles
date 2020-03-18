/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic;

import com.Util_Classes.Dict;;

import java.util.ArrayList;
import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class TypeOfWarrior 
{
    String name;
    int health;
    ArrayList<TypeOfSkill> skills = new ArrayList<>();
    
    private static final Logger logger = LogManager.getLogger(TypeOfWarrior.class.getName());      
    
    TypeOfWarrior (Dict<String> data_WO_addData, Dict<TypeOfSkill> Types_Of_Skills)
    {
        this.name = data_WO_addData.getValue("NAME");
        this.health = Integer.parseInt(data_WO_addData.getValue("CHARACTERISTICS","HEALTH"));

        logger.log(Level.DEBUG, "(из TypeOfWarrior) data_WO_addData.getValues_Dict_Arrays(Arrays.asList(\"SKILLS\")) = {}", data_WO_addData.getValues_Dict_Arrays("SKILLS"));
        logger.log(Level.DEBUG, "(из TypeOfWarrior) data_WO_addData = {}", data_WO_addData);

        for (Dict<String> eachy : data_WO_addData.getValues_Dict_Arrays("SKILLS"))
        {
            logger.log(Level.DEBUG, "(из TypeOfWarrior) SKILL = {}", eachy.getValue());
            if(eachy.getValue() != null)
            {
                logger.log(Level.DEBUG, "(из TypeOfWarrior) Types_Of_Skills.getValue(eachy.getValue()) = {}", Types_Of_Skills.getValue(eachy.getValue()));
                this.skills.add(Types_Of_Skills.getValue(eachy.getValue()));
            }
        }

        logger.log(Level.DEBUG, "(из TypeOfWarrior) Был создан объект: this.TypeOfWarrior");
        logger.log(Level.DEBUG, "(из TypeOfWarrior) Значение name = {}", this.name);
        logger.log(Level.DEBUG, "(из TypeOfWarrior) Значение health = {}", this.health);
        logger.log(Level.DEBUG, "(из TypeOfWarrior) Значение skills = {}", this.skills);

    }

    @Override
    public String toString()
    {
        return "TypeOfWarrior: \n name = " + name + "\n health = " + health + "\n skills = " + skills;
    }

}
