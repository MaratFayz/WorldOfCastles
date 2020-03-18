/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers;


import com.woc.world_of_castles.scr.EventLogic.Command;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public abstract class IEventHandler 
{
    Command Command;
    private static final Logger logger = LogManager.getLogger(IEventHandler.class.getName());

    IEventHandler(Command Command)
    {
        this.Command = Command;
    }
    
    abstract public void Execute();
}
