/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.EventLogic.EventHandler;

import com.woc.world_of_castles.scr.EventLogic.Command;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.EHForm1QUITGAME;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.EH_CAMPAIGN_MISSION_LOADER;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.EH_DELETE_BACK_FILELOADERS;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.EH_GET_POSSIBLE_CAMPAIGNS;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.EH_MISSION_LOADER;
import com.Util_Classes.Dict;
import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.EH_GET_POSSIBLE_LANGUAGES;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.EH_GET_POSSIBLE_MISSIONS;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.EH_GET_POSSIBLE_MISSIONSinCAMPAIGNS_FINISHED;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.EH_GET_POSSIBLE_MISSIONSinCAMPAIGNS_NOTFINISHED;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.EH_GET_POSSIBLE_SAVINGS;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.EH_SET_CHOSEN_LANGUAGE;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.EH_SET_POSSIBLE_MISSIONS;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.EH_SET_POSSIBLE_MISSIONSinCAMPAIGNS_FINISHED;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.EH_SET_POSSIBLE_MISSIONSinCAMPAIGNS_NOTFINISHED;
import com.woc.world_of_castles.scr.EventLogic.EventHandler.EventHandlers.IEventHandler;


/**
 *
 * @author mfayzelgay001
 */
public class EventHandlerFactory 
{
    Dict<String> FormsMapping = new Dict<> ();
    IEventHandler ReturnedEventHandler;
    
    private static final Logger logger = LogManager.getLogger(EventHandlerFactory.class.getName());

    public EventHandlerFactory(Dict<String> FormsMapping)
    {
        this.FormsMapping = FormsMapping;
    }
    
    public void ReturnEventHandler(Command Command)
    {
        logger.log(Level.DEBUG, "(из EventsHandlerFactory) Была вызвана функция ReturnEventHandler");

        String value = this.FormsMapping.getValue(Command.Form, Command.Event, Command.Object, Command.Relations, "EventHandler");
        logger.log(Level.DEBUG, "(из EventsHandlerFactory) Обработчик событий = {}", value);

        switch(value)
        {
            case "NULL":
                break;
            case "EHForm1QUITGAME":
                ReturnedEventHandler = new EHForm1QUITGAME(Command);
                ReturnedEventHandler.Execute();
                break;
            case "EH_MISSION_LOADER":
                ReturnedEventHandler = new EH_MISSION_LOADER(Command);
                ReturnedEventHandler.Execute();
                break;
            case "EH_CAMPAIGN_MISSION_LOADER":
                ReturnedEventHandler = new EH_CAMPAIGN_MISSION_LOADER(Command);
                ReturnedEventHandler.Execute();
                break;
            case "EH_GET_POSSIBLE_LANGUAGES":
                ReturnedEventHandler = new EH_GET_POSSIBLE_LANGUAGES(Command);
                ReturnedEventHandler.Execute();
                break;
            case "EH_SET_CHOSEN_LANGUAGE":
                ReturnedEventHandler = new EH_SET_CHOSEN_LANGUAGE(Command);
                ReturnedEventHandler.Execute();
                break;
            case "EH_GET_POSSIBLE_CAMPAIGNS":
                ReturnedEventHandler = new EH_GET_POSSIBLE_CAMPAIGNS(Command);
                ReturnedEventHandler.Execute();
                break;
            case "EH_GET_POSSIBLE_MISSIONS":
                ReturnedEventHandler = new EH_GET_POSSIBLE_MISSIONS(Command);
                ReturnedEventHandler.Execute();
                break;
            case "EH_GET_POSSIBLE_SAVINGS":
                ReturnedEventHandler = new EH_GET_POSSIBLE_SAVINGS(Command);
                ReturnedEventHandler.Execute();
                break;
            case "EH_GET_POSSIBLE_MISSIONSinCAMPAIGNS_NOTFINISHED":
                ReturnedEventHandler = new EH_GET_POSSIBLE_MISSIONSinCAMPAIGNS_NOTFINISHED(Command);
                ReturnedEventHandler.Execute();
                break;
            case "EH_GET_POSSIBLE_MISSIONSinCAMPAIGNS_FINISHED":
                ReturnedEventHandler = new EH_GET_POSSIBLE_MISSIONSinCAMPAIGNS_FINISHED(Command);
                ReturnedEventHandler.Execute();
                break;
            case "EH_SET_POSSIBLE_MISSIONSinCAMPAIGNS_NOTFINISHED":
                ReturnedEventHandler = new EH_SET_POSSIBLE_MISSIONSinCAMPAIGNS_NOTFINISHED(Command);
                ReturnedEventHandler.Execute();
                break;
            case "EH_SET_POSSIBLE_MISSIONSinCAMPAIGNS_FINISHED":
                ReturnedEventHandler = new EH_SET_POSSIBLE_MISSIONSinCAMPAIGNS_FINISHED(Command);
                ReturnedEventHandler.Execute();
                break;
            case "EH_SET_POSSIBLE_MISSIONS":
                ReturnedEventHandler = new EH_SET_POSSIBLE_MISSIONS(Command);
                ReturnedEventHandler.Execute();
                break;
            case "EH_DELETE_BACK_FILELOADERS":
                ReturnedEventHandler = new EH_DELETE_BACK_FILELOADERS(Command);
                ReturnedEventHandler.Execute();
                break;
        }
    }
}
