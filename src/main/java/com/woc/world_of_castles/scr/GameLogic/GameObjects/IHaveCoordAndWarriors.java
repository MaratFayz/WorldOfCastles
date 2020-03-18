/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic.GameObjects;

import com.Util_Classes.Dict;
import com.woc.world_of_castles.scr.GameLogic.TypeOfWarrior;
import com.woc.world_of_castles.scr.GameLogic.Warrior;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class IHaveCoordAndWarriors extends ICoordinatesOnGameMap
{
    protected ArrayList<Warrior> warriors = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger(IHaveCoordAndWarriors.class.getName());    

    public IHaveCoordAndWarriors(Dict<String> objectData, Dict<TypeOfWarrior> TypeOfWarrior)
    {
        super(objectData);
        logger.log(Level.DEBUG, "(из IHaveCoordAndWarriors) Запуск конструктора");

        try
        {
            logger.log(Level.DEBUG, "(из IHaveCoordAndWarriors) Вызвался try: проверка, есть ли в директории objectData адрес \"WARRIORS\")");
            this.createWarriors(objectData, TypeOfWarrior);
            logger.log(Level.DEBUG, "(из IHaveCoordAndWarriors) В директории objectData БЫЛ адрес \"WARRIORS\")");            
        }
        catch(NoSuchElementException exception)
        {
            logger.log(Level.DEBUG, "(из IHaveCoordAndWarriors) В директории objectData НЕТ адреса \"WARRIORS\"), значит нужно по вероятности рассчитать, какие воины будут и только потом загрузить");
            if(exception.equals("Address to get is wrong :" + "WARRIORS"))
            {
                logger.log(Level.DEBUG, "(из IHaveCoordAndWarriors) В директории objectData НЕТ адреса \"WARRIORS\"), рассчитываем вероятность");
                //----------------если есть в здании воины по вероятностям START------------------------
                Dict<String> POSSIBILITY = new Dict<>();
                Dict<String> chosenWarriors = new Dict<>();
                int counter = 0;

                if (objectData.getLinkHashMap("MAP").containsKey("WARRIORS"))
                {
                    logger.log(Level.DEBUG, "(из IHaveCoordAndWarriors/PoleBuilding) В данных есть ключ WARRIORS, поэтому начинается расчет вероятности");
                    for (Dict<String> eachWarrior : objectData.getValues_Dict_Arrays("MAP", "WARRIORS"))
                    {
                        int counter_inside;
                        for (counter_inside=counter;
                             counter_inside<counter + Integer.parseInt(eachWarrior.getValue("POSSIBILITY"));
                             counter_inside++)
                        {
                            logger.log(Level.DEBUG, "(из IHaveCoordAndWarriors/PoleBuilding) counter_inside = {}", counter_inside);
                            POSSIBILITY.saveValue(new String[]{Integer.toString(counter_inside)}, eachWarrior);
                            logger.log(Level.DEBUG, "(из IHaveCoordAndWarriors/PoleBuilding) POSSIBILITY = {}", POSSIBILITY);
                        }
                        counter = counter_inside;
                        logger.log(Level.DEBUG, "(из IHaveCoordAndWarriors/PoleBuilding) counter = {}", counter);
                    }

                    String calc_POSSIBILITY = Integer.toString(new Random().nextInt(100));//Long.toString(Math.round(Math.random() * 100));
                    logger.log(Level.DEBUG, "(из IHaveCoordAndWarriors/PoleBuilding) Рассчитана вероятность в calc_POSSIBILITY = {}", calc_POSSIBILITY);
                    chosenWarriors = POSSIBILITY.getValues_Link(calc_POSSIBILITY);
                    logger.log(Level.DEBUG, "(из IHaveCoordAndWarriors/PoleBuilding) По вероятности выпал такой воин = {}", chosenWarriors);
                }
                //----------------если есть в здании воины по вероятностям END------------------------
                
                this.createWarriors(chosenWarriors, TypeOfWarrior);
            }
            else
            {
                logger.log(Level.DEBUG, "(из IHaveCoordAndWarriors) В директории objectData НЕТ адреса \"WARRIORS\") и нет адреса MAP->WARRIORS. СТРАННОСТЬ");
            }
        }
        
        logger.log(Level.DEBUG, "(из IHaveCoordAndWarriors) Значение warriors = {}", this.warriors);
    }    
    
    private void createWarriors(Dict<String> objectData, Dict<TypeOfWarrior> TypeOfWarrior)
    {
        for (Dict<String> eachWarrior : objectData.getValues_Dict_Arrays("WARRIORS"))
        {
            if (!eachWarrior.isEmpty()) this.warriors.add(new Warrior(eachWarrior, TypeOfWarrior));
        }
    }
}
