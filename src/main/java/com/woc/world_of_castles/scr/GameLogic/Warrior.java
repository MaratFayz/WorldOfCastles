/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic;

import com.Util_Classes.Dict;

import java.util.Arrays;
import java.util.function.Consumer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class Warrior 
{
    TypeOfWarrior typeofwarrior;
    int number = 0;
    
    private static final Logger logger = LogManager.getLogger(Warrior.class.getName());  
    
    public Warrior(Dict<String> objectData, Dict<TypeOfWarrior> TypeOfWarrior)
    {          
        this.typeofwarrior = TypeOfWarrior.getValue(objectData.getValue("NAME"));
        this.number = Integer.parseInt(objectData.getValue("NUMBER"));
        logger.log(Level.DEBUG, "(из Warrior) Был создан объект: this.Warrior");
        logger.log(Level.DEBUG, "(из Warrior) Значение TypeOfWarrior.name = {}", this.typeofwarrior);
        logger.log(Level.DEBUG, "(из Warrior) Значение Warrior.number = {}", this.number);    
    } 
}
