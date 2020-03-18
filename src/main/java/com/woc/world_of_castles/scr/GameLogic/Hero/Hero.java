/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic.Hero;

import com.woc.world_of_castles.scr.GameLogic.MapPoles.Pole;
import com.Util_Classes.Dict;

import com.woc.world_of_castles.scr.GameLogic.GameObjects.IHaveCoordAndWarriorsAndMerch;
import com.woc.world_of_castles.scr.GameLogic.TypeOfSkill;
import com.woc.world_of_castles.scr.GameLogic.TypeOfWarrior;
import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class Hero extends IHaveCoordAndWarriorsAndMerch
{
    String name;
    Pole coordinates;  
//    stepInaDay : int количество шагов в день
    HeroClass HeroClass;
    HeroRace HeroRace;

    private static final Logger logger = LogManager.getLogger(Hero.class.getName());
    
    enum HeroBody
    {
        Head, LeftHand, RightHand, LeftLeg, RightLeg
    }    
    
    public Hero(Dict<String> objectData, 
                Dict<TypeofMerch> TypeofMerch, 
                Dict<HeroRace> LoadedHeroRaces, 
                Dict<HeroClass> LoadedHeroClasses, 
                Dict<TypeOfWarrior> LoadedWarriors)
    {
        super(objectData, TypeofMerch, LoadedWarriors);
        logger.log(Level.DEBUG, "(из Hero) Был создан объект: this.Hero");

        this.name = objectData.getValue("NAME");
        logger.log(Level.DEBUG, "(из Hero) Значение name = {}", this.name);

        logger.log(Level.DEBUG, "(из Hero) Значение objectData.getValue(\"CLASS\") = {}", objectData.getValue("CLASS"));
        logger.log(Level.DEBUG, "(из Hero) Значение objectData.getValue(\"RACE\") = {}", objectData.getValue("RACE"));
        this.HeroClass = LoadedHeroClasses.getValue(objectData.getValue("CLASS"));
        this.HeroRace = LoadedHeroRaces.getValue(objectData.getValue("RACE"));


        logger.log(Level.DEBUG, "(из Hero) Значение HeroClass = {}", this.HeroClass);
        logger.log(Level.DEBUG, "(из Hero) Значение HeroRace = {}", this.HeroRace);
    }
    
    public String showName()
    {
        return this.name;
    }

//    def getStepsInaDay(self) -> int:
//        return this.stepInaDay
//
//    def showAllWarriors(self) -> Dict[Warrior, int]:
//        pass    
}

