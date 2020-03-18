/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic.City;

import com.Util_Classes.Dict;

import com.woc.world_of_castles.scr.GameLogic.GameObjects.IHaveCoordAndWarriors;
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
public class City extends IHaveCoordAndWarriors
{
    //ArrayList<CityBuilding> PossibleBuildings;
    //ArrayList<CityBuilding> BuiltBuildings;
    String name;
    CityRace CityRace;

    private static final Logger logger = LogManager.getLogger(City.class.getName());    
    
    public City(Dict<String> objectData, 
                Dict<CityRace> LoadedCityRaces,
                Dict<TypeOfWarrior> LoadedWarriors)
    {
        super(objectData, LoadedWarriors);
        logger.log(Level.DEBUG, "(из City) Был создан объект: this.City");

        this.name = objectData.getValue("NAME");

        if (!objectData.getValue("RACE").equals("NULL"))
        {
            this.CityRace = LoadedCityRaces.getValue(objectData.getValue("RACE"));
        }
        
        logger.log(Level.DEBUG, "(из City) Значение name = {}", this.name);
        logger.log(Level.DEBUG, "(из City) Значение CityRace = {}", this.CityRace);
    }
    
    public String showName()
    {
        return this.name;
    }
}
