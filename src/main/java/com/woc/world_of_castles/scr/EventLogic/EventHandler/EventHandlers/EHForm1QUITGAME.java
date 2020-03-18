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
public class EHForm1QUITGAME extends IEventHandler
{    
    private static final Logger logger = LogManager.getLogger(EHForm1QUITGAME.class.getName());
    
    public EHForm1QUITGAME(Command Command)
    {
        super(Command);
    }
    
    @Override
    public void Execute()
    {
        logger.log(Level.DEBUG, "(из EHForm1QUITGAME) Обнуляем GlobalVariable.Game");
        GlobalVariable.Game = false;
    }
}
