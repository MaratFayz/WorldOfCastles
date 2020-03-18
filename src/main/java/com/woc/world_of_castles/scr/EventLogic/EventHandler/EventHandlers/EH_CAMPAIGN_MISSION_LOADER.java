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
public class EH_CAMPAIGN_MISSION_LOADER extends IEventHandler
{    
    private static final Logger logger = LogManager.getLogger(EH_CAMPAIGN_MISSION_LOADER.class.getName());
    
    public EH_CAMPAIGN_MISSION_LOADER(Command Command)
    {
        super(Command);
    }
    
    @Override
    public void Execute()
    {
        logger.log(Level.DEBUG, "(из EH_CAMPAIGN_MISSION_LOADER) Вызван GameStateKeeper");
        
        GlobalVariable.GameStateKeeperFactory.createGSK(GlobalVariable.CampaignLoader.nested_returnloadedfile());

        logger.log(Level.DEBUG, "Загрузка завершена. EventHandler EH_CAMPAIGN_MISSION_LOADER закончил работу");        
    }
}
