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
public class CityBuilding 
{    
    String name;
    String description;  
    
    private static final Logger logger = LogManager.getLogger(CityBuilding.class.getName());  
   
    public CityBuilding(Dict<String> data_WO_addData)
    {
        this.name = data_WO_addData.getValue("NAME");
        this.description = data_WO_addData.getValue("DESCRIPTION");
        
        logger.log(Level.DEBUG, "(из CityBuildings) Был создан объект: this.CityBuildings");
        logger.log(Level.DEBUG, "(из CityBuildings) Значение name = {}", this.name);
        logger.log(Level.DEBUG, "(из CityBuildings) Значение skills = {}", this.description);      
    }  
}
