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
public class Form35 implements FormInterface 
{
    /*
    from scr.FormLogic.Forms.FormInterface import IForm
from colorama import init, Fore, Back, Style
import os
from scr.WaitAnswer import WaitAnswer
import scr.GlobalVar as GV
from scr.Log import Log
from scr.EventLogic.Command import *
from scr.EventLogic.EventKeeper import *
import json
import glob

init(autoreset=True)

class Form35(IForm):
  EK : EventKeeper
  reqsymbols : List[str] = ['ANY_KEY']
  missiondata : Dict[str,str] = {}

  def Show(self) -> None:
    this.FindRightFile()
    os.system('clear')
    System.out.println(Back.RED + "World Of Castles")

    if this.missiondata['DEMOSCENE']['HAVE'] == "YES":
      for eache in this.missiondata['DEMOSCENE']['DATA']:
        logger.log(Level.DEBUG, "Вывод заставки")
        System.out.println(eache['SCREEN'])
        logger.log(Level.DEBUG, "(из Form35) Нужно ввести значение по требованиям this.reqsymbols: {}', this.reqsymbols)
        this.answer = WaitAnswer(this.reqsymbols)
        os.system('clear')
        System.out.println(Back.RED + "World Of Castles")

    logger.log(Level.DEBUG, "Form35 выведена")

  def CreateEvent(self) -> None:
    this.EK = GlobalVariable.EventsKeeperFactory.returnEventKeeper()
    logger.log(Level.DEBUG, "(из Form35) Был возвращен: this.EK: значение {}', this.EK)

    this.EK.PutNewEventInArray(Command("Form35","ANY_KEY","NULL","NULL"))
    logger.log(Level.DEBUG, "(из Form35) Была выполнена this.EK.PutNewEventInArray() и добавлена команда Command('Form35','ANY_KEY','NULL','NULL')")

  def FindRightFile(self):
    if GlobalVariable.ChosenCampanyName != "":
      files = glob.glob(GlobalVariable.ChosenCampanyName)
      for each in files:
        opening = open(each)
        logger.log(Level.DEBUG, "Проверка json файлов кампаний. Файл с именем: {}", opening.name)
        data = json.load(opening)
        logger.log(Level.DEBUG, "Был импортирован из json файл с заголовком: {}", data['NAME'])

      for everymission in data['MISSIONS']:
        if everymission['NAME'] == GlobalVariable.ChosenMissionName:
          logger.log(Level.DEBUG, "Миссия {} совпадает с выбором в прошлых формах", each['NAME'])
          this.missiondata = everymission
    else:
      files = glob.glob(GlobalVariable.ChosenMissionName)
      for each in files:
          opening = open(each)
          logger.log(Level.DEBUG, "Проверка json файлов кампаний. Файл с именем: {}", opening.name)
          this.missiondata = json.load(opening)
          logger.log(Level.DEBUG, "Был импортирован из json файл с заголовком: {}", this.missiondata['NAME'])
    */
    String answer;
    EventKeeper EK;
    ArrayList<String> reqsymbols = new ArrayList(Arrays.asList("A"));
    
    private static final Logger logger = LogManager.getLogger(Form40.class.getName());
    
    @Override
    public void Show()
    {
//        GlobalVariable.myOS.clearmyscreen();
//
//        System.out.println(BackColor.RED + "World Of Castles" +BackColor.RESET);
//        System.out.println(BackColor.RED + "Choose your language:" + BackColor.RESET);
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
