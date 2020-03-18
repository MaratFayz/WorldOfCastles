/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.FormLogic.Forms;

import com.Util_Classes.BackColor;
import com.woc.world_of_castles.scr.EventLogic.Command;
import com.woc.world_of_castles.scr.EventLogic.EventKeeper;
import com.woc.world_of_castles.scr.GlobalVariable;
import com.Util_Classes.Dict;

import static com.woc.world_of_castles.scr.WaitAnswer.WaitAnswer;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class Form8 implements FormInterface 
{
    String answer;
    EventKeeper EK;
    ArrayList<String> reqsymbols = new ArrayList(Arrays.asList("A"));
    
    private static final Logger logger = LogManager.getLogger(Form8.class.getName());
    
    @Override
    public void Show()
    {
        GlobalVariable.myOS.clearmyscreen();

        System.out.println(BackColor.RED + "World Of Castles" + BackColor.RESET);
        
        for (String each : GlobalVariable.CampaignLoader.nested_returnloadedlistFinished())
        {
            System.out.println(each);
        }

        System.out.println(GlobalVariable.myOS.translate(">> Обратно (A)"));
        logger.log(Level.DEBUG, "Form8 выведена");
    }    
    
    @Override
    public void CreateEvent()
    {
        logger.log(Level.DEBUG, "(из Form8) Нужно ввести значение по требованиям GlobalVariable.CampaignLoader.nested_returnreqsymbolsfinished(): {}", GlobalVariable.CampaignLoader.nested_returnreqsymbolsfinished());

        this.reqsymbols.addAll(GlobalVariable.CampaignLoader.nested_returnreqsymbolsfinished());
        logger.log(Level.DEBUG, "(из Form8) Всего требований this.reqsymbols = {}", this.reqsymbols);

        this.answer = WaitAnswer(this.reqsymbols);

        this.EK = GlobalVariable.EventsKeeperFactory.returnEventKeeper();
        logger.log(Level.DEBUG, "(из Form8) Был возвращен: this.EK: значение {}", this.EK);

        switch(this.answer)
        {
            case "A":
                this.EK.PutNewEventInArray(new Command("Form8","BACK","NULL","NULL"));
                logger.log(Level.DEBUG, "(из Form8) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form8','BACK','NULL','NULL')");
                break;
            default:
                Command createdEvent = new Command("Form8","CHOSEN_MISSION","NULL","NULL");
                logger.log(Level.DEBUG, "(из Form8) Была создана команда Command('Form8','CHOSEN_MISSION','NULL','NULL')");

                Dict<String> adding_data = new Dict<>();

                adding_data.saveValue(new String[]{"ChosenMissionNumber"}, this.answer);
                createdEvent.addData(adding_data);
                logger.log(Level.DEBUG, "(из Form8) Была добавлена дополнительная информация в команду {Form8 : this.answer} = {}", this.answer);

                this.EK.PutNewEventInArray(createdEvent);
                logger.log(Level.DEBUG, "(из Form8) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form8','CHOSEN_MISSION','NULL','NULL')");
        }
    }        
    
/*



  def CreateEvent(self) -> None:

    */    

    



}
