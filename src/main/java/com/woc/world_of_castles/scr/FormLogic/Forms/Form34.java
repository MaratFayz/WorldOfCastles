/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.FormLogic.Forms;


import com.woc.world_of_castles.scr.EventLogic.Command;
import com.woc.world_of_castles.scr.EventLogic.EventKeeper;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form34_Show.Form34_ALREADY_CHOSEN;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form34_Show.Form34_CHOOSE;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form34_Show.IShowingForm34;
import com.woc.world_of_castles.scr.GameLogic.GameStateKeeper;
import com.woc.world_of_castles.scr.GlobalVariable;
import com.Util_Classes.Dict;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class Form34 implements FormInterface 
{
    IShowingForm34 ShowerForm34;
    Dict<String> loaded_users_data;

    String answer;
    EventKeeper EK;
    
    private static final Logger logger = LogManager.getLogger(Form34.class.getName());
    
    @Override
    public void Show()
    {
        GlobalVariable.GameStateKeeperFactory.createGSK(GlobalVariable.MissionLoader.returnloadedfile());
        GameStateKeeper GSK = GlobalVariable.GameStateKeeperFactory.returnGameStateKeeper();

        if (GSK.return_isGameCanBeChosen())
        {
            this.ShowerForm34 = new Form34_CHOOSE();
        }
        else
        {
            this.ShowerForm34 = new Form34_ALREADY_CHOSEN();
        }

        this.answer = this.ShowerForm34.getanswer();
        this.loaded_users_data = this.ShowerForm34.getdata();

        logger.log(Level.DEBUG, "Form34 выведена");        
    }

    @Override
    public void CreateEvent()
    {
        this.EK = GlobalVariable.EventsKeeperFactory.returnEventKeeper();
        logger.log(Level.DEBUG, "(из Form34) Был возвращен: this.EK: значение {}", this.EK);

        switch(this.answer)
        {
            case "A":
                Command createdEvent = new Command("Form34","START_MISSION","NULL","NULL");
                logger.log(Level.DEBUG, "(из Form34) Была создана команда Command('Form34','START_MISSION','NULL','NULL')");
                
                createdEvent.addData(this.loaded_users_data);
                
                logger.log(Level.DEBUG, "(из Form34 Была добавлена дополнительная информация в команду this.loaded_users_data = {}", this.loaded_users_data);

                this.EK.PutNewEventInArray(createdEvent);
                logger.log(Level.DEBUG, "(из Form34) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form34','START_MISSION','NULL','NULL')");
                break;
            case "B":
                this.EK.PutNewEventInArray(new Command("Form34","BACK","NULL","NULL"));
                logger.log(Level.DEBUG, "(из Form34) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form34','BACK','NULL','NULL')");     
                break;
        }
    }    
}
