/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic.GameObjects;

import com.Util_Classes.Dict;

import com.woc.world_of_castles.scr.GameLogic.Hero.Merch;
import com.woc.world_of_castles.scr.GameLogic.Hero.TypeofMerch;
import com.woc.world_of_castles.scr.GameLogic.TypeOfSkill;
import com.woc.world_of_castles.scr.GameLogic.TypeOfWarrior;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class IHaveCoordAndWarriorsAndMerch extends IHaveCoordAndWarriors
{
    protected ArrayList<Merch> rucksack = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger(IHaveCoordAndWarriorsAndMerch.class.getName());

    public IHaveCoordAndWarriorsAndMerch(Dict<String> objectData, Dict<TypeofMerch> TypeofMerch, Dict<TypeOfWarrior> TypeOfWarrior)
    {
        super(objectData, TypeOfWarrior);
        logger.log(Level.DEBUG, "(из IHaveCoordAndWarriorsAndMerch) Запуск конструктора");
        
        for (Dict<String> each_merch : objectData.getValues_Dict_Arrays("RUCKSACK"))
        {
            if (!each_merch.isEmpty()) this.rucksack.add(new Merch(each_merch, TypeofMerch));
        }
        
        logger.log(Level.DEBUG, "(из IHaveMerch) Значение rucksack = {}", this.rucksack);
    }   
}
