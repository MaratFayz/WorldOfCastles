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
public class EH_MISSION_LOADER extends IEventHandler
{    
    private static final Logger logger = LogManager.getLogger(EH_MISSION_LOADER.class.getName());    
    
    public EH_MISSION_LOADER(Command Command)
    {
        super(Command);
    }
    
    @Override
    public void Execute()
    {
        logger.log(Level.DEBUG, "(из EH_MISSION_LOADER) Вызван GameStateKeeper");

        if (GlobalVariable.MissionLoader.returnloadedfile().getValue("CAN_CHOOSE_USERS").equals("YES"))
        {
            logger.log(Level.DEBUG, "(из EH_MISSION_LOADER) Начинаем GlobalVariable.MissionLoader.injectData(this.Command.giveData())");
            GlobalVariable.MissionLoader.injectData(this.Command.giveData());
            logger.log(Level.DEBUG, "(из EH_MISSION_LOADER) Была добавлена информация в файл миссии GlobalVariable.MissionLoader.injectData(this.Command.giveData('ChosenMissionConditions')");
        }
        else
        {
            logger.log(Level.DEBUG, "(из EH_MISSION_LOADER) НЕ ДЕЛАЕМ GlobalVariable.MissionLoader.injectData(this.Command.giveData())");
        }

        GlobalVariable.GameStateKeeperFactory.createGSK(GlobalVariable.MissionLoader.returnloadedfile());

        logger.log(Level.DEBUG, "Загрузка завершена. EventHandler EH_MISSION_LOADER закончил работу");                
    }    
}
