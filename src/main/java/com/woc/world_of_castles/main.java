/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles;
import com.woc.world_of_castles.scr.EventLogic.EventKeeper;
import com.woc.world_of_castles.scr.GlobalVariable;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
/**
 *
 * @author mfayzelgay001
 */
public class main
{
    private static final Logger logger = LogManager.getLogger(main.class.getName());
            
    public static void main (String[] args)
    {
        logger.log(Level.DEBUG, "Program started");
        
        logger.log(Level.DEBUG, "Loop starts: Variable Game = {}", GlobalVariable.Game);
        while (GlobalVariable.Game != false)
        {
            EventKeeper EventKeeper = GlobalVariable.EventsKeeperFactory.returnEventKeeper();
            logger.log(Level.DEBUG, "Returned: EventKeeper: value = {}", EventKeeper);
            EventKeeper.HandleEvent();
            logger.log(Level.DEBUG, "Was executed function = EventKeeper.HandleEvent()");
            logger.log(Level.DEBUG, "Loop ends: Variable Game = {}", GlobalVariable.Game);
        }
    }
}
