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
public class Form36 implements FormInterface 
{
    String answer;
    EventKeeper EK;
    ArrayList<String> reqsymbols = new ArrayList(Arrays.asList("A"));
    
    private static final Logger logger = LogManager.getLogger(Form36.class.getName());
    
    @Override
    public void Show()
    {              
        if(GlobalVariable.CampaignLoader.nested_isFileLoaded())
        {
              //NONE
              logger.log(Level.DEBUG, "Form36 выведена; ошибок не найдено");
        }
        else
        {
              GlobalVariable.myOS.clearmyscreen();
              System.out.println(BackColor.RED + GlobalVariable.myOS.translate("World Of Castles") + BackColor.RESET);

              System.out.println("");
              System.out.println(BackColor.RED + GlobalVariable.myOS.translate("Файл некорректный, загрузка невозможна") + BackColor.RESET);
              System.out.println("");
              System.out.println(GlobalVariable.myOS.translate(">> Обратно (A)"));
              logger.log(Level.DEBUG, "Form36 выведена; найдена ошибка");
        }              
    }

    @Override
    public void CreateEvent()
    {                                                 
        this.EK = GlobalVariable.EventsKeeperFactory.returnEventKeeper();
        logger.log(Level.DEBUG, "(из Form36) Был возвращен: this.EK: значение {}", this.EK);

        logger.log(Level.DEBUG, "GlobalVariable.CampaignLoader.nested_isFileLoaded() = {}", GlobalVariable.CampaignLoader.nested_isFileLoaded());
        if(GlobalVariable.CampaignLoader.nested_isFileLoaded())
        {
            this.EK.PutNewEventInArray(new Command("Form36","NEXT","NULL","NULL"));
            logger.log(Level.DEBUG, "(из Form36) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form36','NEXT','NULL','NULL')");
            logger.log(Level.DEBUG, "Form36 выведена; ошибок не найдено");
        }
        else
        {
            logger.log(Level.DEBUG, "Form36 выведена; ошибка найдена");
            logger.log(Level.DEBUG, "(из Form36) Нужно ввести значение по требованиям this.reqsymbols: {}", this.reqsymbols);
            this.answer = WaitAnswer(this.reqsymbols);

            if (this.answer.equals("A"))
            {
                this.EK.PutNewEventInArray(new Command("Form36","BACK","NULL","NULL"));
                logger.log(Level.DEBUG, "(из Form36) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form36','BACK','NULL','NULL')");
            }
        }                
    }    
}
