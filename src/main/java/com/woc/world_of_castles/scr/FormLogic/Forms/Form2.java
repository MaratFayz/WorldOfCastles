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
public class Form2 implements FormInterface 
{
    String answer;
    EventKeeper EK;
    ArrayList<String> reqsymbols = new ArrayList(Arrays.asList("A", "B"));
    
    private static final Logger logger = LogManager.getLogger(Form2.class.getName());
    
    @Override
    public void Show()
    {
        GlobalVariable.myOS.clearmyscreen();

        System.out.println(BackColor.RED + "World Of Castles" +BackColor.RESET);
        System.out.println(GlobalVariable.myOS.translate("Выбор кампании:"));

        for (String each : GlobalVariable.CampaignLoader.returnloadedlistNotFinished())
        {
            System.out.println(each);
        }

        System.out.println("");
        System.out.println(GlobalVariable.myOS.translate(">> Завершенные кампании (A)"));
        System.out.println(GlobalVariable.myOS.translate(">> Обратно (B)"));
        logger.log(Level.DEBUG, "Form2 выведена");     
    }

    @Override
    public void CreateEvent()
    {
        logger.log(Level.DEBUG, "(из Form2) Нужно ввести значение по требованиям GlobalVariable.CampaignLoader.returnreqsymbolsnotfinished() =  {}", GlobalVariable.CampaignLoader.returnreqsymbolsnotfinished());

        this.reqsymbols.addAll(GlobalVariable.CampaignLoader.returnreqsymbolsnotfinished());
        logger.log(Level.DEBUG, "(из Form2) Всего требований this.reqsymbols = {}", this.reqsymbols);

        this.answer = WaitAnswer(this.reqsymbols);

        this.EK = GlobalVariable.EventsKeeperFactory.returnEventKeeper();
        logger.log(Level.DEBUG, "(из Form2) Был возвращен: this.EK: значение {}", this.EK);

        switch(this.answer)
        {
            case "A":
                this.EK.PutNewEventInArray(new Command("Form2","FINISHED_CAMPANIES","NULL","NULL"));
                logger.log(Level.DEBUG, "(из Form2) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form2','FINISHED_COMPANIES','NULL','NULL')");
                break;
            case "B":
                this.EK.PutNewEventInArray(new Command("Form2","BACK","NULL","NULL"));
                logger.log(Level.DEBUG, "(из Form2) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form2','BACK','NULL','NULL')");
                break;
            default:
                Command createdEvent = new Command("Form2","CHOSEN_CAMPANY","NULL","NULL");
                logger.log(Level.DEBUG, "(из Form2) Была создана команда Command('Form2','CHOSEN_CAMPANY','NULL','NULL')");

                createdEvent.addData(new Dict<>(new String[]{"ChosenCampaignNumber"}, this.answer));
                logger.log(Level.DEBUG, "(из Form2 Была добавлена дополнительная информация в команду {ChosenCampaignNumber : this.answer} = {}", this.answer);

                this.EK.PutNewEventInArray(createdEvent);
                logger.log(Level.DEBUG, "(из Form2) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form2','CHOSEN_CAMPANY','NULL','NULL')");
        }
    }    
}
