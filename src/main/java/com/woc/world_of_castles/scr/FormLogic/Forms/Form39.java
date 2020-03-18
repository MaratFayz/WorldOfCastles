/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.FormLogic.Forms;

import com.Util_Classes.BackColor;
import com.woc.world_of_castles.scr.EventLogic.EventKeeper;
import com.woc.world_of_castles.scr.GlobalVariable;
import static com.woc.world_of_castles.scr.WaitAnswer.WaitAnswer;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.woc.world_of_castles.scr.EventLogic.Command;
import com.Util_Classes.Dict;


/**
 *
 * @author mfayzelgay001
 */
public class Form39 implements FormInterface
{
    String answer;
    EventKeeper EK;
    ArrayList<String> reqsymbols = new ArrayList(Arrays.asList("A"));
    
    private static final Logger logger = LogManager.getLogger(Form39.class.getName());

    @Override
    public void Show()
    {
        GlobalVariable.myOS.clearmyscreen();

        System.out.println(BackColor.RED + "World Of Castles" + BackColor.RESET);
        System.out.println(BackColor.RED + "Choose your language:" + BackColor.RESET);

        for (String each : GlobalVariable.TranslationsLoader.returnloadedlistFull())
        {
            System.out.println(each);
        }

        System.out.println("");
        System.out.println("Exit (A)");

        logger.log(Level.DEBUG, "Form39 выведена");
    }

    @Override
    public void CreateEvent()
    {
        logger.log(Level.DEBUG, "(из Form39) Нужно ввести значение по требованиям GlobalVariable.TranslationsLoader.returnreqsymbolsfull() = {}", GlobalVariable.TranslationsLoader.returnreqsymbolsfull());

        for (String each : GlobalVariable.TranslationsLoader.returnreqsymbolsfull())
        {
            this.reqsymbols.add(each);
        }

        logger.log(Level.DEBUG, "(из Form39) Всего требований this.reqsymbols = {}", this.reqsymbols);

        this.answer = WaitAnswer(this.reqsymbols);

        this.EK = GlobalVariable.EventsKeeperFactory.returnEventKeeper();
        logger.log(Level.DEBUG, "(из Form39) Был возвращен: this.EK: значение {}", this.EK);

        switch(this.answer)
        {
            case "A":           
                this.EK.PutNewEventInArray(new Command("Form39","QUITGAME","NULL","NULL"));
                logger.log(Level.DEBUG, "(из Form39) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form39','QUITGAME','NULL','NULL')");
                break;            
            default:            
                Command createdEvent = new Command("Form39","LANGUAGE_CHOSEN","NULL","NULL");
                logger.log(Level.DEBUG, "(из Form39) Была создана команда Command('Form39','LANGUAGE_CHOSEN','NULL','NULL')");
                
                Dict<String> SavingCommand = new Dict<>();

                SavingCommand.saveValue(new String[]{"ChosenLanguageNumber"}, this.answer);
                createdEvent.addData(SavingCommand);
                logger.log(Level.DEBUG, "(из Form39) Была добавлена дополнительная информация в команду {ChosenLanguageNumber : this.answer} = {}", this.answer);

                this.EK.PutNewEventInArray(createdEvent);
                logger.log(Level.DEBUG, "(из Form39) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form39','LANGUAGE_CHOSEN','NULL','NULL')");
        }
    }   
}
