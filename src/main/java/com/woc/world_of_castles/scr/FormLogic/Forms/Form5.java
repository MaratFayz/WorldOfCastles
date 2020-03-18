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
public class Form5 implements FormInterface  
{
    String answer;
    EventKeeper EK;
    ArrayList<String> reqsymbols = new ArrayList(Arrays.asList("A"));
    
    private static final Logger logger = LogManager.getLogger(Form5.class.getName());
    
    @Override
    public void Show()
    {
        GlobalVariable.myOS.clearmyscreen();
        System.out.println(BackColor.RED + GlobalVariable.myOS.translate("World Of Castles") + BackColor.RESET);
        System.out.println(BackColor.RED + GlobalVariable.myOS.translate("Настройки:") + BackColor.RESET);
        System.out.println(GlobalVariable.myOS.translate(">> Пусто"));
        System.out.println(GlobalVariable.myOS.translate(">> Пусто"));
        System.out.println(GlobalVariable.myOS.translate(">> Пусто"));
        System.out.println(GlobalVariable.myOS.translate(">> Пусто"));
        System.out.println(GlobalVariable.myOS.translate(">> Обратно (A)"));
        logger.log(Level.DEBUG, "Form5 выведена");  
    }

    @Override
    public void CreateEvent()
    {        
        logger.log(Level.DEBUG, "(из Form5) Нужно ввести значение по требованиям this.reqsymbols: {}", this.reqsymbols);
        this.answer = WaitAnswer(this.reqsymbols);

        this.EK = GlobalVariable.EventsKeeperFactory.returnEventKeeper();
        logger.log(Level.DEBUG, "(из Form5) Был возвращен: this.EK: значение {}", this.EK);

        if (this.answer.equals("A"))
        {
            this.EK.PutNewEventInArray(new Command("Form5","BACK","NULL","NULL"));
            logger.log(Level.DEBUG, "(из Form5) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form5','BACK','NULL','NULL')");                      
        }
    }    
}
