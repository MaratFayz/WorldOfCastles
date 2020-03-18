/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.FormLogic.Forms;

import com.woc.world_of_castles.scr.EventLogic.EventKeeper;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class Form9 implements FormInterface  
{
    String answer;
    EventKeeper EK;
    ArrayList<String> reqsymbols = new ArrayList(Arrays.asList("A"));
    
    private static final Logger logger = LogManager.getLogger(Form9.class.getName());    
/*

class Form9():
  zope.interface.implementer(IForm)
  answer : str = ""
  reqsymbols : List[str] = ['A']
  counterOfMissions : int = 0
  mapping_KeyMission : Dict[str,str] = {}
  EK : EventKeeper
  GSK : GameStateKeeper

  def Show(self) -> None:
    os.system('clear')
    System.out.println(Back.RED + "World Of Castles")
    System.out.println(Back.RED + " ")
    this.GSK = GlobalVariable.GameStateKeeperFactory.returnGameStateKeeper()
    System.out.println("Ходит игрок " + this.GSK.showNameSteppingUser() + " героем " + this.GSK.showNameSteppingHero())
    System.out.println("Осталось шагов за день (ход): " + this.GSK.showBalStepsOfHero())

    '''files = glob.glob(GlobalVariable.ChosenCampanyName)
    for each in files:
      opening = open(each)
      logger.log(Level.DEBUG, "Проверка json файлов кампаний. Файл с именем: {}", opening.name)
      data = json.load(opening)
      logger.log(Level.DEBUG, "Был импортирован из json файл с заголовком: {}", data['NAME'])
      for each in data ['MISSIONS']:
        if each['FINISHED'] == "YES":
          logger.log(Level.DEBUG, "Миссия {} в кампании пройдена", each['NAME'])
          this.counterOfMissions = this.counterOfMissions + 1
          logger.log(Level.DEBUG, "Счетчик миссий пройденных (this.counterOfMissions) стал {}", this.counterOfMissions)
          this.reqsymbols.append(str(this.counterOfMissions))
          logger.log(Level.DEBUG, "Были добавлены новые требования на ввод. Требования (this.reqsymbols) стали: {}", this.reqsymbols)
          this.mapping_KeyMission.update({str(this.counterOfMissions) : each['NAME']})
          logger.log(Level.DEBUG, "Были добавлены новый мэппинг на ввод. Теперь он стал таким (this.mapping_KeyMission): {}", this.mapping_KeyMission)
          System.out.println(">> Миссия: "+ each['NAME'] +" ("+str(this.counterOfMissions)+")")
        else:
          logger.log(Level.DEBUG, "Миссия {} не пройдена и выводиться не будет в этой форме", each['NAME'])

    System.out.println(">> Обратно (A)")'''

    logger.log(Level.DEBUG, "Form9 выведена")
  
  def CreateEvent(self) -> None:
    logger.log(Level.DEBUG, "(из Form9) Нужно ввести значение по требованиям this.reqsymbols: {}', this.reqsymbols)
    this.answer = WaitAnswer(this.reqsymbols)
    
    this.EK = GlobalVariable.EventsKeeperFactory.returnEventKeeper()
    logger.log(Level.DEBUG, "(из Form9) Был возвращен: this.EK: значение {}', this.EK)
'''
    if this.answer == 'A':      
      this.EK.PutNewEventInArray(Command("Form9","BACK","NULL","NULL"))	
      logger.log(Level.DEBUG, "(из Form9) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form9','BACK','NULL','NULL')")
    else:
      this.EK.PutNewEventInArray(Command("Form9","CHOSEN_MISSION","NULL","NULL"))	
      logger.log(Level.DEBUG, "(из Form9) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form9','CHOSEN_MISSION','NULL','NULL')")
      GlobalVariable.ChosenMissionName = this.mapping_KeyMission[str(this.answer)]
      logger.log(Level.DEBUG, "(из Form9) в переменную GlobalVariable.ChosenMissionName было добавлено значение {}", GlobalVariable.ChosenMissionName)
'''
    */    

    
    @Override
    public void Show()
    {
//        GlobalVariable.myOS.clearmyscreen();
//
//        System.out.println(BackColor.RED + "World Of Castles" +BackColor.RESET);
//        System.out.println(BackColor.RED + "Choose your language:");
//
//        for (String each : GlobalVariable.TranslationsLoader.returnloadedlistFull())
//        {
//            System.out.println(each);
//        }
//
//        System.out.println("");
//        System.out.println("Exit (A)");
//
//        logger.log(Level.DEBUG, "Form39 выведена");
    }

    @Override
    public void CreateEvent()
    {
    }    
}
