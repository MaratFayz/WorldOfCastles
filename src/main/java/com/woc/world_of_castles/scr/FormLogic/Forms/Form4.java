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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mfayzelgay001
 */
public class Form4 implements FormInterface 
{    
    String answer;
    EventKeeper EK;
    ArrayList<String> reqsymbols = new ArrayList(Arrays.asList("A"));
    
    private static final Logger logger = LogManager.getLogger(Form4.class.getName());
    
    @Override
    public void Show()
    {
        GlobalVariable.myOS.clearmyscreen();

        System.out.println(BackColor.RED + GlobalVariable.myOS.translate("World Of Castles") + BackColor.RESET);
        System.out.println(GlobalVariable.myOS.translate("Выбор загрузки:"));

        for (String each : GlobalVariable.SavingsFileLoader.returnloadedlistFull())
        {
            System.out.println(each);
        }

        System.out.println("");
        System.out.println(GlobalVariable.myOS.translate(">> Обратно (A)"));

        logger.log(Level.DEBUG, "Form4 выведена");        
    }

    @Override
    public void CreateEvent()
    {
        logger.log(Level.DEBUG, "(из Form4) Нужно ввести значение по требованиям GlobalVariable.SavingsFileLoader.returnreqsymbolsfull() = {}", GlobalVariable.SavingsFileLoader.returnreqsymbolsfull());

        this.reqsymbols.addAll(GlobalVariable.SavingsFileLoader.returnreqsymbolsfull());
        logger.log(Level.DEBUG, "(из Form4) Всего требований this.reqsymbols = {}", this.reqsymbols);

        this.answer = WaitAnswer(this.reqsymbols);

        this.EK = GlobalVariable.EventsKeeperFactory.returnEventKeeper();
        logger.log(Level.DEBUG, "(из Form4) Был возвращен: this.EK: значение {}", this.EK);              
              
        switch(this.answer)
        {
            case "A":
                this.EK.PutNewEventInArray(new Command("Form4","BACK","NULL","NULL"));
                logger.log(Level.DEBUG, "(из Form4) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form4','BACK','NULL','NULL')");
                break;
            default:
                Command createdEvent = new Command("Form4","CHOSEN_LOAD","NULL","NULL");
                logger.log(Level.DEBUG, "(из Form4) Была создана команда Command('Form3','CHOSEN_LOAD','NULL','NULL')");

                createdEvent.addData(new Dict(new String[]{"ChosenSavingsNumber"}, this.answer));
                logger.log(Level.DEBUG, "(из Form4 Была добавлена дополнительная информация в команду {ChosenSavingsNumber : this.answer} = {}", this.answer);

                this.EK.PutNewEventInArray(createdEvent);
                logger.log(Level.DEBUG, "(из Form4) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form4','CHOSEN_LOAD','NULL','NULL')");
        }                      
    }    
}
