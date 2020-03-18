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
public class EH_GET_POSSIBLE_CAMPAIGNS extends IEventHandler
{    
    private static final Logger logger = LogManager.getLogger(EH_GET_POSSIBLE_CAMPAIGNS.class.getName());
    
    public EH_GET_POSSIBLE_CAMPAIGNS(Command Command)
    {
        super(Command);
    }
    
    @Override
    public void Execute()
    {
        logger.log(Level.DEBUG, "(из EH_GET_POSSIBLE_CAMPAIGNS) Была запущена функция GlobalVariable.CampaignLoader.loadfilelist()");
        GlobalVariable.CampaignLoader.loadfilelist(true);
    }    
}
