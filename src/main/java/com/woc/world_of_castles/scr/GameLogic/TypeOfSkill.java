/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic;

import com.Util_Classes.Dict;;

import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class TypeOfSkill
{
    String name;
    String description;
    String typeofhandler;
            
    private static final Logger logger = LogManager.getLogger(TypeOfSkill.class.getName());      
    
    TypeOfSkill(Dict<String> data)
    {
        this.name = data.getValue("NAME");
        this.description = data.getValue("DESCRIPTION");
        this.typeofhandler = data.getValue("TYPE_OF_HANDLER");
        logger.log(Level.DEBUG, "(из TypeOfSkill) Был создан объект: this.TypeOfSkill");
        logger.log(Level.DEBUG, "(из TypeOfSkill) Значение name = {}", this.name);
        logger.log(Level.DEBUG, "(из TypeOfSkill) Значение discription = {}", this.description);
        logger.log(Level.DEBUG, "(из TypeOfSkill) Значение typeofhandler = {}", this.typeofhandler);
    }

    @Override
    public String toString()
    {
        return "TypeOfSkill: \n name = " + name + "\n description = " + description + "\n typeofhandler = " + typeofhandler;
    }
}
