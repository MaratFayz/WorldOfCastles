/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic.GameObjects;

import com.Util_Classes.Dict;

import com.woc.world_of_castles.scr.GameLogic.MapPoles.Pole;
import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class ICoordinatesOnGameMap
{
    private static final Logger logger = LogManager.getLogger(ICoordinatesOnGameMap.class.getName());
    protected Pole coordinates;
        
    public ICoordinatesOnGameMap(Dict<String> objectData)
    {
        logger.log(Level.DEBUG, "(из ICoordinatesOnGameMap) Запуск конструктора");
        this.coordinates = new Pole(objectData.getValues_Link("MAP"));
    }
    
    public Pole returnCoordinates()
    {
        logger.log(Level.DEBUG, "(из ICoordinatesOnGameMap) Возврат координат = {}", this.coordinates.returnprintPole());
        return this.coordinates;
    }
}
