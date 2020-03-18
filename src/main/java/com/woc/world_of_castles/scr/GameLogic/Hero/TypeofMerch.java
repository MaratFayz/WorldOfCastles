/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic.Hero;

import com.Util_Classes.Dict;

import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class TypeofMerch 
{
    String  name;
    int health;
    
    private static final Logger logger = LogManager.getLogger(TypeofMerch.class.getName());         

    public TypeofMerch(Dict<String> data)
    {
        this.name = data.getValue("NAME");
        //this.health = Integer.parseInt(data.getValue("HEALTH"));

        logger.log(Level.DEBUG, "(из TypeofMerch) Был создан объект: this.TypeofMerch");
        logger.log(Level.DEBUG, "(из TypeofMerch) Значение name = {}", this.name);
        //logger.log(Level.DEBUG, "(из TypeofMerch) Значение health = {}", this.health);
    }
}
