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
public class Merch 
{
    TypeofMerch TypeofMerch;
    int number = 0;

    private static final Logger logger = LogManager.getLogger(Merch.class.getName());    
    
    public Merch(Dict<String> data_WO_addData, Dict<TypeofMerch> TypeofMerch)
    {
        this.TypeofMerch = TypeofMerch.getValue(data_WO_addData.getValue("NAME"));
        this.number = Integer.parseInt(data_WO_addData.getValue("NUMBER"));
        logger.log(Level.DEBUG, "(из Merch) Был создан объект: this.Merch");
        logger.log(Level.DEBUG, "(из Merch) Значение name = {}", this.TypeofMerch);
        logger.log(Level.DEBUG, "(из Merch) Значение number = {}", this.number);
    }
}
