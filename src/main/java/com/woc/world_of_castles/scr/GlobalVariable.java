/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr;
import com.woc.world_of_castles.scr.EventLogic.EventsKeeperFactory;
import com.woc.world_of_castles.scr.FileLoaders.CampaignLoader;
import com.woc.world_of_castles.scr.FileLoaders.MissionLoader;
import com.woc.world_of_castles.scr.FileLoaders.SavingsFileLoader;
import com.woc.world_of_castles.scr.FileLoaders.TranslationsLoader;
import com.woc.world_of_castles.scr.GameLogic.GameStateKeeperFactory;
import com.Util_Classes.Dict;

import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class GlobalVariable
{
    private static final Logger logger = LogManager.getLogger(GlobalVariable.class.getName());
    public static boolean Game = true;
    public static EventsKeeperFactory EventsKeeperFactory = new EventsKeeperFactory();
    public static GameStateKeeperFactory GameStateKeeperFactory = new GameStateKeeperFactory();
    public static myOS myOS = new myOS();
    public static CampaignLoader CampaignLoader = new CampaignLoader("GameContent/Campaigns/", "CAMPAIGN","json");
    public static MissionLoader MissionLoader = new MissionLoader("GameContent/Missions/", "MISSION","json");
    public static SavingsFileLoader SavingsFileLoader = new SavingsFileLoader("GameContent/Saves/", "SAVING","json");
    public static TranslationsLoader TranslationsLoader = new TranslationsLoader("GameContent/Translations/", "TRANSLATION","json"); 
    
    public static Dict<String> possible_colors = createcolors();    
    
    private static Dict<String> createcolors() 
    {
        Dict<String> possible_colors = new Dict<>();

        possible_colors.saveValue(new String[]{"1"}, "BLACK");
        possible_colors.saveValue(new String[]{"2"}, "RED");
        possible_colors.saveValue(new String[]{"3"}, "GREEN");
        possible_colors.saveValue(new String[]{"4"}, "YELLOW");
        possible_colors.saveValue(new String[]{"5"}, "BLUE");
        possible_colors.saveValue(new String[]{"6"}, "MAGENTA");
        possible_colors.saveValue(new String[]{"7"}, "CYAN");
        possible_colors.saveValue(new String[]{"8"}, "WHITE");

        return possible_colors;
    }
}
