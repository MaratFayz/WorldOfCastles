/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic.Hero;

import com.woc.world_of_castles.scr.GameLogic.TypeOfSkill;
import com.Util_Classes.Dict;

import java.util.ArrayList;
import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class HeroClass 
{
    String name;
    ArrayList<TypeOfSkill> skills = new ArrayList<>();
    
    private static final Logger logger = LogManager.getLogger(HeroRace.class.getName());  
   
    public HeroClass(Dict<String> data_WO_addData, Dict<TypeOfSkill> Types_Of_Skills)
    {
        this.name = data_WO_addData.getValue("NAME");

        for (Dict<String> eachy : data_WO_addData.getValues_Dict_Arrays("SKILLS"))
            this.skills.add(Types_Of_Skills.getValue(eachy.getValue()));
        
        logger.log(Level.DEBUG, "(из HeroClass) Был создан объект: this.HeroClass");
        logger.log(Level.DEBUG, "(из HeroClass) Значение name = {}", this.name);
        logger.log(Level.DEBUG, "(из HeroClass) Значение skills = {}", this.skills);      
    }    
}
