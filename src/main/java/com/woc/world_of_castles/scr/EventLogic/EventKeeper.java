/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.EventLogic;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlerFactory;
import com.woc.world_of_castles.scr.FormLogic.FormsFactory;
import static com.woc.world_of_castles.scr.FormsMapping.Form_Mapping;
import java.util.ArrayDeque;

/**
 *
 * @author mfayzelgay001
 */
public class EventKeeper 
{
    ArrayDeque<Command> EventsArray;
    EventHandlerFactory EventsHandlerFactory;
    FormsFactory FormsFactory;
    Command HandlingEvent;

    private static final Logger logger = LogManager.getLogger(EventKeeper.class.getName());
    
    public EventKeeper()
    {
        this.EventsArray = new ArrayDeque<>();
        this.EventsArray.addLast(new Command("Form0","STARTGAME","NULL","NULL"));
        logger.log(Level.DEBUG, "(from EventKeeper) First was added in the end: Command('Form0','STARTGAME','NULL','NULL')");
        this.EventsHandlerFactory = new EventHandlerFactory(Form_Mapping);
        this.FormsFactory = new FormsFactory(Form_Mapping);
    }

    public void HandleEvent()
    {
        if (!this.EventsArray.isEmpty())
        {
            this.HandlingEvent = this.EventsArray.pollFirst();
            this.EventsHandlerFactory.ReturnEventHandler(this.HandlingEvent);
            this.FormsFactory.returnForm(this.HandlingEvent);
        }
    }
                
    public void PutNewEventInArray(Command Event)
    {
        this.EventsArray.add(Event);
        logger.log(Level.DEBUG, "(из EventKeeper) Была добавлена команда через PutNewEventInArray() {}", Event);
    }
}