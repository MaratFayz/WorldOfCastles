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
public class Form7 implements FormInterface  
{
    String answer;
    EventKeeper EK;
    ArrayList<String> reqsymbols = new ArrayList(Arrays.asList("A"));
    
    private static final Logger logger = LogManager.getLogger(Form7.class.getName());
    
    @Override
    public void Show()
    {
        GlobalVariable.myOS.clearmyscreen();

        System.out.println(BackColor.RED + "World Of Castles" + BackColor.RESET);
        System.out.println(GlobalVariable.myOS.translate("Выбор из завершённых кампаний:"));

        for (String each : GlobalVariable.CampaignLoader.returnloadedlistFinished())
        {
            System.out.println(each);
        }

        System.out.println("");
        System.out.println(GlobalVariable.myOS.translate(">> Обратно (A)"));

        logger.log(Level.DEBUG, "Form7 выведена");  
    }

    @Override
    public void CreateEvent()
    {
        logger.log(Level.DEBUG, "(из Form7) Нужно ввести значение по требованиям GlobalVariable.CampaignLoader.returnreqsymbolsfinished(): {}", GlobalVariable.CampaignLoader.returnreqsymbolsfinished());

        this.reqsymbols.addAll(GlobalVariable.CampaignLoader.returnreqsymbolsfinished());
        logger.log(Level.DEBUG, "(из Form7) Всего требований this.reqsymbols = {}", this.reqsymbols);

        this.answer = WaitAnswer(this.reqsymbols);

        this.EK = GlobalVariable.EventsKeeperFactory.returnEventKeeper();
        logger.log(Level.DEBUG, "(из Form7) Был возвращен: this.EK: значение {}", this.EK);

        if (this.answer.equals("A"))
        {
            this.EK.PutNewEventInArray(new Command("Form7","BACK","NULL","NULL"));
            logger.log(Level.DEBUG, "(из Form7) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form7','BACK','NULL','NULL')");
        }
        else
        {
            Command createdEvent = new Command("Form7","CHOSEN_CAMPANY","NULL","NULL");
            logger.log(Level.DEBUG, "(из Form7) Была создана команда Command('Form7','CHOSEN_CAMPANY','NULL','NULL')");

            createdEvent.addData(new Dict(new String[]{"ChosenCampaignNumber"}, this.answer));
            logger.log(Level.DEBUG, "(из Form7 Была добавлена дополнительная информация в команду {ChosenCampaignNumber : this.answer} = {}", this.answer);

            this.EK.PutNewEventInArray(createdEvent);
            logger.log(Level.DEBUG, "(из Form7) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form7','CHOSEN_CAMPANY','NULL','NULL')");
        }        
    }    
}
