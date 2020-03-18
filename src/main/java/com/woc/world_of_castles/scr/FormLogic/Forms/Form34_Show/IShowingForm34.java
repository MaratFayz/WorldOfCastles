/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.FormLogic.Forms.Form34_Show;


import com.woc.world_of_castles.scr.GameLogic.GameStateKeeper;
import com.woc.world_of_castles.scr.GlobalVariable;
import com.Util_Classes.Dict;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public abstract class IShowingForm34 
{    
    Dict<String> loaded_users_data = new Dict<>();
    String answer = new String();
    GameStateKeeper GSK;
    ArrayList<String> reqsymbols = new ArrayList<>();
    
    private static final Logger logger = LogManager.getLogger(IShowingForm34.class.getName());

    IShowingForm34()
    {
        this.GSK = GlobalVariable.GameStateKeeperFactory.returnGameStateKeeper();
        this.createUserArray();
        this.otherActionsToPrepareGame();
        this.presentForm34();
    }

    abstract void createUserArray();
    abstract void presentForm34();
    abstract void otherActionsToPrepareGame();

    public Dict<String> getdata()
    {
        return this.loaded_users_data;
    }
    
    public String getanswer()
    {
        return this.answer;
    }   
}
