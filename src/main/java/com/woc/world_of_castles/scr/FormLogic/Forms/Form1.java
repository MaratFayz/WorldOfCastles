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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import static com.woc.world_of_castles.scr.WaitAnswer.WaitAnswer;
import java.util.ArrayList;

/**
 *
 * @author mfayzelgay001
 */
public class Form1 implements FormInterface 
{
    String answer;
    EventKeeper EK;
    
    private static final Logger logger = LogManager.getLogger(Form1.class.getName());

    @Override
    public void Show()
    {
        GlobalVariable.myOS.clearmyscreen();
        System.out.println(BackColor.RED + GlobalVariable.myOS.translate("World Of Castles") + BackColor.RESET);
        System.out.println(BackColor.RED + GlobalVariable.myOS.translate("Меню:") + BackColor.RESET);
        System.out.println(GlobalVariable.myOS.translate(">> Проходить кампанию (1)"));
        System.out.println(GlobalVariable.myOS.translate(">> Играть в сражения (2)"));
        System.out.println(GlobalVariable.myOS.translate(">> Загрузить игру (3)"));
        System.out.println(GlobalVariable.myOS.translate(">> Настройки (4)"));
        System.out.println(GlobalVariable.myOS.translate(">> Выход (5)"));
        logger.log(Level.DEBUG, "Form1 showed");
    }
    
    @Override
    public void CreateEvent()
    {
        logger.log(Level.DEBUG, "(из Form1) Нужно ввести значение по требованиям [1,2,3,4,5]");
        this.answer = WaitAnswer(new ArrayList(Arrays.asList("1","2","3","4","5")));

        this.EK = GlobalVariable.EventsKeeperFactory.returnEventKeeper();
        logger.log(Level.DEBUG, "(из Form1) Был возвращен: this.EK: значение {}", this.EK);

        switch(this.answer)
        {
            case "1":
                this.EK.PutNewEventInArray(new Command("Form1","PLAYCAMPANY","NULL","NULL"));
                logger.log(Level.DEBUG, "(из Form1) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form1','PLAYCAMPANY','NULL','NULL')");
                break;
            case "2":
                this.EK.PutNewEventInArray(new Command("Form1","BATTLE","NULL","NULL"));
                logger.log(Level.DEBUG, "(из Form1) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form1','BATTLE','NULL','NULL')");
                break;
            case "3":
                this.EK.PutNewEventInArray(new Command("Form1","LOADGAME","NULL","NULL"));
                logger.log(Level.DEBUG, "(из Form1) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form1','LOADGAME','NULL','NULL')");
                break;
            case "4":
                this.EK.PutNewEventInArray(new Command("Form1","OPTIONS","NULL","NULL"));
                logger.log(Level.DEBUG, "(из Form1) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form1','OPTIONS','NULL','NULL')");
                break;
            case "5":
                this.EK.PutNewEventInArray(new Command("Form1","QUITGAME","NULL","NULL"));
                logger.log(Level.DEBUG, "(из Form1) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form1','QUITGAME','NULL','NULL')");
                break;
        }
    }
}
