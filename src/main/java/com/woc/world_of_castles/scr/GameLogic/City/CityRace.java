/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic.City;

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
public class CityRace 
{
    String name;    
    ArrayList<CityBuilding> CityBuildings = new ArrayList<>();
    
    private static final Logger logger = LogManager.getLogger(CityRace.class.getName());  

    public CityRace(Dict<String> data_WO_addData, Dict<CityBuilding> CityBuildings)
    {
        this.name = data_WO_addData.getValue("NAME");
            
        for (Dict<String> eachy : data_WO_addData.getValues_Dict_Arrays("BUILDINGS"))
            this.CityBuildings.add(CityBuildings.getValue(eachy.getValue()));

        logger.log(Level.DEBUG, "(из CityRace) Был создан объект: this.CityRace");
        logger.log(Level.DEBUG, "(из CityRace) Значение name = {}", this.name);
        logger.log(Level.DEBUG, "(из CityRace) Значение CityBuildings = {}", this.CityBuildings);
    }
}
