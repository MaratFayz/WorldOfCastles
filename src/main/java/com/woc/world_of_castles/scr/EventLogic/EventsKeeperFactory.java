/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.EventLogic;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class EventsKeeperFactory 
{
    private EventKeeper EventKeeper;
    private static final Logger logger = LogManager.getLogger(EventsKeeperFactory.class.getName());

    public EventsKeeperFactory()
    {
        this.EventKeeper = new EventKeeper();
        logger.log(Level.DEBUG, "(From EventsKeeperFactory) Was Initialized: EventKeeper: value = {}", this.EventKeeper);
    }
    
    public EventKeeper returnEventKeeper()
    {
        logger.log(Level.DEBUG, "(From EventsKeeperFactory) Was returned  EventKeeper: value = {}", this.EventKeeper);
        return this.EventKeeper;
    }
}
