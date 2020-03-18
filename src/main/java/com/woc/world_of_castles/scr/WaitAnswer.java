/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;

/**
 *
 * @author mfayzelgay001
 */
public class WaitAnswer 
{
    private static final Logger logger = LogManager.getLogger(WaitAnswer.class.getName());
    
    public static String WaitAnswer(ArrayList<String> requirements)
    {
        String answer = new String();
        logger.debug("В WaitAnswer нужно ввести значение, соответствующее требованию = {}", requirements);
        
        Scanner in = new Scanner(System.in);
        
        while (answer.equals(""))
        {
            answer = in.nextLine();
            logger.log(Level.DEBUG,"In WaitAnswer was typed value = {}", answer);
            
            if (requirements.contains(answer) == true)
            {
                logger.log(Level.DEBUG,"В WaitAnswer: значение попадает по требованиям");
            }
            else if (requirements.get(0).equals("ANY_KEY"))
            {
                answer = "ANY_KEY";
                logger.log(Level.DEBUG,"В WaitAnswer: значение попадает по требованиям (любая клавиша)");
            }
            else if (requirements.get(0).equals("ANY"))
            {
                logger.log(Level.DEBUG,"В WaitAnswer: значение попадает по требованиям (любое значение)");
            }
            else
            {
                answer = "";
                logger.log(Level.DEBUG,"В WaitAnswer: значение НЕ попадает по требованиям");
            }

        }
        
        return answer;
    }
}
