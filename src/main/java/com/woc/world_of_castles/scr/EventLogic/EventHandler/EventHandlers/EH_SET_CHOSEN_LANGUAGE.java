/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers;

import com.woc.world_of_castles.scr.EventLogic.Command;
import com.woc.world_of_castles.scr.GlobalVariable;

import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class EH_SET_CHOSEN_LANGUAGE extends IEventHandler
{    
    private static final Logger logger = LogManager.getLogger(EH_SET_CHOSEN_LANGUAGE.class.getName());   
    
    public EH_SET_CHOSEN_LANGUAGE(Command Command)
    {
        super(Command);
    }
    
    @Override
    public void Execute()
    {      
        logger.log(Level.DEBUG, "(из EH_SET_CHOSEN_LANGUAGE) Была запущена функция GlobalVariable.TranslationsLoader.loadfullfilefull(this.Command.giveInfo(ChosenLanguageNumber))");

        GlobalVariable.TranslationsLoader.loadfullfilefull(this.Command.giveData().getValue("ChosenLanguageNumber"));

        logger.log(Level.DEBUG, "(из EH_SET_CHOSEN_LANGUAGE) Была запущена функция GlobalVariable.TranslationsLoader.returnloadedfile()");
        GlobalVariable.myOS.getoutingtext(GlobalVariable.TranslationsLoader.returnloadedfile());
    }    
}
