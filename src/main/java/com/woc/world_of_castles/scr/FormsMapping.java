/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr;

import com.Util_Classes.Dict;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class FormsMapping 
{
    public static Dict<String> Form_Mapping = new Dict<>();
    
    private static final Logger logger = LogManager.getLogger(FormsMapping.class.getName());
        
    static
    {      
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form0", "STARTGAME", "NULL", "NULL", "Form")), "Form39");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form0", "STARTGAME", "NULL", "NULL", "EventHandler")), "EH_GET_POSSIBLE_LANGUAGES");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form1", "PLAYCAMPANY", "NULL", "NULL", "Form")), "Form2");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form1", "PLAYCAMPANY", "NULL", "NULL", "EventHandler")), "EH_GET_POSSIBLE_CAMPAIGNS");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form1", "BATTLE", "NULL", "NULL", "Form")), "Form3");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form1", "BATTLE", "NULL", "NULL", "EventHandler")), "EH_GET_POSSIBLE_MISSIONS");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form1", "LOADGAME", "NULL", "NULL", "Form")), "Form4");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form1", "LOADGAME", "NULL", "NULL", "EventHandler")), "EH_GET_POSSIBLE_SAVINGS");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form1", "OPTIONS", "NULL", "NULL", "Form")), "Form5");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form1", "OPTIONS", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form1", "QUITGAME", "NULL", "NULL", "Form")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form1", "QUITGAME", "NULL", "NULL", "EventHandler")), "EHForm1QUITGAME");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form2", "BACK", "NULL", "NULL", "Form")), "Form1");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form2", "BACK", "NULL", "NULL", "EventHandler")), "EH_DELETE_BACK_FILELOADERS");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form2", "FINISHED_CAMPANIES", "NULL", "NULL", "Form")), "Form7");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form2", "FINISHED_CAMPANIES", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form2", "CHOSEN_CAMPANY", "NULL", "NULL", "Form")), "Form40");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form2", "CHOSEN_CAMPANY", "NULL", "NULL", "EventHandler")), "EH_GET_POSSIBLE_MISSIONSinCAMPAIGNS_NOTFINISHED");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form3", "BACK", "NULL", "NULL", "Form")), "Form1");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form3", "BACK", "NULL", "NULL", "EventHandler")), "EH_DELETE_BACK_FILELOADERS");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form3", "CHOSEN_MISSION", "NULL", "NULL", "Form")), "Form38");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form3", "CHOSEN_MISSION", "NULL", "NULL", "EventHandler")), "EH_SET_POSSIBLE_MISSIONS");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form4", "BACK", "NULL", "NULL", "Form")), "Form1");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form4", "BACK", "NULL", "NULL", "EventHandler")), "EH_DELETE_BACK_FILELOADERS");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form4", "CHOSEN_LOAD", "NULL", "NULL", "Form")), "Form9");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form4", "CHOSEN_LOAD", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form5", "BACK", "NULL", "NULL", "Form")), "Form1");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form5", "BACK", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form6", "FINISHED_MISSIONS", "NULL", "NULL", "Form")), "Form8");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form6", "FINISHED_MISSIONS", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form6", "BACK", "NULL", "NULL", "Form")), "Form2");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form6", "BACK", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form6", "CHOSEN_MISSION", "NULL", "NULL", "Form")), "Form36");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form6", "CHOSEN_MISSION", "NULL", "NULL", "EventHandler")), "EH_SET_POSSIBLE_MISSIONSinCAMPAIGNS_NOTFINISHED");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form7", "BACK", "NULL", "NULL", "Form")), "Form2");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form7", "BACK", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form7", "CHOSEN_CAMPANY", "NULL", "NULL", "Form")), "Form42");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form7", "CHOSEN_CAMPANY", "NULL", "NULL", "EventHandler")), "EH_GET_POSSIBLE_MISSIONSinCAMPAIGNS_FINISHED");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form8", "BACK", "NULL", "NULL", "Form")), "Form6");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form8", "BACK", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form8", "CHOSEN_MISSION", "NULL", "NULL", "Form")), "Form37");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form8", "CHOSEN_MISSION", "NULL", "NULL", "EventHandler")), "EH_SET_POSSIBLE_MISSIONSinCAMPAIGNS_FINISHED");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form34", "START_MISSION", "NULL", "NULL", "Form")), "Form35");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form34", "START_MISSION", "NULL", "NULL", "EventHandler")), "EH_MISSION_LOADER");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form34", "BACK", "NULL", "NULL", "Form")), "Form3");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form34", "BACK", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form35", "ANY_KEY", "NULL", "NULL", "Form")), "Form9");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form35", "ANY_KEY", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form36", "NEXT", "NULL", "NULL", "Form")), "Form35");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form36", "NEXT", "NULL", "NULL", "EventHandler")), "EH_CAMPAIGN_MISSION_LOADER");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form36", "BACK", "NULL", "NULL", "Form")), "Form6");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form36", "BACK", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form37", "NEXT", "NULL", "NULL", "Form")), "Form35");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form37", "NEXT", "NULL", "NULL", "EventHandler")), "EH_CAMPAIGN_MISSION_LOADER");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form37", "BACK", "NULL", "NULL", "Form")), "Form8");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form37", "BACK", "NULL", "NULL", "EventHandler")), "NULL");

        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form38", "NEXT", "NULL", "NULL", "Form")), "Form34");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form38", "NEXT", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form38", "BACK", "NULL", "NULL", "Form")), "Form3");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form38", "BACK", "NULL", "NULL", "EventHandler")), "NULL");

        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form39", "LANGUAGE_CHOSEN", "NULL", "NULL", "Form")), "Form41");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form39", "LANGUAGE_CHOSEN", "NULL", "NULL", "EventHandler")), "EH_SET_CHOSEN_LANGUAGE");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form39", "QUITGAME", "NULL", "NULL", "Form")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form39", "QUITGAME", "NULL", "NULL", "EventHandler")), "EHForm1QUITGAME");

        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form40", "NEXT", "NULL", "NULL", "Form")), "Form6");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form40", "NEXT", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form40", "BACK", "NULL", "NULL", "Form")), "Form2");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form40", "BACK", "NULL", "NULL", "EventHandler")), "NULL");

        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form41", "NEXT", "NULL", "NULL", "Form")), "Form1");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form41", "NEXT", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form41", "BACK", "NULL", "NULL", "Form")), "Form39");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form41", "BACK", "NULL", "NULL", "EventHandler")), "NULL");

        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form42", "NEXT", "NULL", "NULL", "Form")), "Form6");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form42", "NEXT", "NULL", "NULL", "EventHandler")), "NULL");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form42", "BACK", "NULL", "NULL", "Form")), "Form7");
        Form_Mapping.saveValue(new LinkedList<String> (Arrays.asList("Form42", "BACK", "NULL", "NULL", "EventHandler")), "NULL");

        logger.log(Level.DEBUG, "(from FormsMapping) Form_Mapping = {}", Form_Mapping);
    }
}
    
