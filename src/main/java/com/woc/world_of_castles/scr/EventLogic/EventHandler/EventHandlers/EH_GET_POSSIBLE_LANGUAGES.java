/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers;

import com.woc.world_of_castles.scr.EventLogic.Command;
import com.woc.world_of_castles.scr.GlobalVariable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class EH_GET_POSSIBLE_LANGUAGES extends IEventHandler
{
    private static final Logger logger = LogManager.getLogger(IEventHandler.class.getName());
    
    public EH_GET_POSSIBLE_LANGUAGES(Command Command)
    {
        super(Command);
    }
    
    @Override
    public void Execute()
    {
        logger.log(Level.DEBUG, "(из EH_GET_POSSIBLE_LANGUAGES) Была запущена функция GlobalVariable.TranslationsLoader.loadfilelist()");
        GlobalVariable.TranslationsLoader.loadfilelist(false);
    }
}
