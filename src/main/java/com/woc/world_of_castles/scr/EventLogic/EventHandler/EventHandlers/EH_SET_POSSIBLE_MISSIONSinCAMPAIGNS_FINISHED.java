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
public class EH_SET_POSSIBLE_MISSIONSinCAMPAIGNS_FINISHED extends IEventHandler
{    
    private static final Logger logger = LogManager.getLogger(EH_SET_POSSIBLE_MISSIONSinCAMPAIGNS_FINISHED.class.getName());
    
    public EH_SET_POSSIBLE_MISSIONSinCAMPAIGNS_FINISHED(Command Command)
    {
        super(Command);
    }
    
    @Override
    public void Execute()
    {
        //Загрузка выбранного файла миссии из кампании        
        logger.log(Level.DEBUG, "(из EH_SET_POSSIBLE_MISSIONSinCAMPAIGNS_FINISHED) Была запущена функция GlobalVariable.CampaignLoader.nested_loadfullfilenotfinished(this.Command.giveInfo(ChosenMissionNumber))");

        GlobalVariable.CampaignLoader.nested_loadfullfilefinished(this.Command.giveData().getValue("ChosenMissionNumber"));
    }    
}
