/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr;

import com.Util_Classes.Dict;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class myOS 
{
    Dict<String> outingtext = new Dict<>(); //Dictionary for outputing the local language
    
    private static final Logger logger = LogManager.getLogger(myOS.class.getName());

    public myOS()
    {
    }

    public void getoutingtext(Dict<String> outingtext)
    {
        this.outingtext = outingtext.clone();
    }

    public void clearmyscreen()
    {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public String translate(String text)
    {
        try
        {
            return this.outingtext.getValue("CONTENT", text);
        }
        catch (NoSuchElementException exception)
        {
            logger.log(Level.DEBUG, "(Из myOS) translate => в this.outingtext отсутствует значение = {}", text);
            return text;
        }
    }
}

