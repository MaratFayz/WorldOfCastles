/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic.MapPoles;

import com.Util_Classes.Dict;

import com.woc.world_of_castles.scr.GameLogic.GameObjects.IHaveCoordAndWarriors;
import com.woc.world_of_castles.scr.GameLogic.TypeOfWarrior;
import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class PoleBuilding extends IHaveCoordAndWarriors
{
    String name;
    
    private static final Logger logger = LogManager.getLogger(PoleBuilding.class.getName());        

    public PoleBuilding(Dict<String> objectData,                         
                        Dict<TypeOfWarrior> LoadedWarriors)
    {
        super(objectData, LoadedWarriors);
        logger.log(Level.DEBUG, "(из PoleBuilding) Был создан объект: this.PoleBuilding");

        this.name = objectData.getValue("NAME");
        logger.log(Level.DEBUG, "(из PoleBuilding) Значение name = {}", this.name);
    }
    
    public String showName()
    {
        return this.name;
    }

    @Override
    public String toString()
    {
        return "PoleBuilding = { name = " + name + "\n" + "warriors=" + warriors + "}";
    }
}
