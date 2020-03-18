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
public class Form6 implements FormInterface  
{
    String answer;
    EventKeeper EK;
    ArrayList<String> reqsymbols = new ArrayList(Arrays.asList("A", "B"));
    
    private static final Logger logger = LogManager.getLogger(Form6.class.getName());
  
    @Override
    public void Show()
    {
        GlobalVariable.myOS.clearmyscreen();

        System.out.println(BackColor.RED + GlobalVariable.myOS.translate("World Of Castles") + BackColor.RESET);
        
        for (String each : GlobalVariable.CampaignLoader.nested_returnloadedlistNotFinished())
        {
            System.out.println(each);
        }        

        System.out.println(">> Завершенные миссии (A)");
        System.out.println(">> Обратно (B)");
        logger.log(Level.DEBUG, "Form6 выведена");    
    }

    @Override
    public void CreateEvent()
    {
        logger.log(Level.DEBUG, "(из Form6) Нужно ввести значение по требованиям GlobalVariable.CampaignLoader.nested_returnreqsymbolsnotfinished(): {}", GlobalVariable.CampaignLoader.nested_returnreqsymbolsnotfinished());

        this.reqsymbols.addAll(GlobalVariable.CampaignLoader.nested_returnreqsymbolsnotfinished());
        logger.log(Level.DEBUG, "(из Form6) Всего требований this.reqsymbols = {}", this.reqsymbols);

        this.answer = WaitAnswer(this.reqsymbols);

        this.EK = GlobalVariable.EventsKeeperFactory.returnEventKeeper();
        logger.log(Level.DEBUG, "(из Form6) Был возвращен: this.EK: значение {}", this.EK);

        if (this.answer.equals("A"))
        {
          this.EK.PutNewEventInArray(new Command("Form6","FINISHED_MISSIONS","NULL","NULL"));
          logger.log(Level.DEBUG, "(из Form6) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form6','FINISHED_MISSIONS','NULL','NULL')");
        }
        else if (this.answer.equals("B"))
        {
          this.EK.PutNewEventInArray(new Command("Form6","BACK","NULL","NULL"));
          logger.log(Level.DEBUG, "(из Form6) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form6','BACK','NULL','NULL')");
        }
        else
        {
            Command createdEvent = new Command("Form6","CHOSEN_MISSION","NULL","NULL");
            logger.log(Level.DEBUG, "(из Form6) Была создана команда Command('Form6','CHOSEN_MISSION','NULL','NULL')");

            createdEvent.addData(new Dict(new String[]{"ChosenMissionNumber"}, this.answer));
            logger.log(Level.DEBUG, "(из Form6) Была добавлена дополнительная информация в команду {Form6 : this.answer} = {}", this.answer);

            this.EK.PutNewEventInArray(createdEvent);
            logger.log(Level.DEBUG, "(из Form6) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form6','CHOSEN_MISSION','NULL','NULL')");
        }
    
    }    
}
