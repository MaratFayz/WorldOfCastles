/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic.MapPoles;

import com.Util_Classes.BackColor;
import com.Util_Classes.Dict;

import com.Util_Classes.TextColor;
import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class Pole 
{
    private String X, Y, TYPEOFPOLEHANDLER, PRIORITET, SYMBOL, SYMBOL_COLOR, COLOR_BACK = new String();

    private static final Logger logger = LogManager.getLogger(Pole.class.getName());     
    
    public Pole(Dict<String> celldata)
    {
        this.X = celldata.getValue("X");
        logger.log(Level.DEBUG, "(из Pole) Был создано поле [X]: значение {}", this.X);

        this.Y = celldata.getValue("Y");
        logger.log(Level.DEBUG, "(из Pole) Был создано поле [Y]: значение {}", this.Y);

        this.TYPEOFPOLEHANDLER = celldata.getValue("TYPEOFPOLEHANDLER");
        logger.log(Level.DEBUG, "(из Pole) Был создано поле [TYPEOFPOLEHANDLER]: значение {}", this.TYPEOFPOLEHANDLER);

        this.PRIORITET = celldata.getValue("PRIORITET");

        this.SYMBOL_COLOR = this.returnColorFore(celldata.getValue("SYMBOL_COLOR"));
        logger.log(Level.DEBUG, "(из Pole) Был создано поле [SYMBOL_COLOR]: значение = {}", this.SYMBOL_COLOR);

        this.COLOR_BACK = this.returnColorBack(celldata.getValue("COLOR_BACK"));
        logger.log(Level.DEBUG, "(из Pole) Был создано поле [COLOR_BACK]: значение = {}", this.COLOR_BACK);

        this.SYMBOL = celldata.getValue("SYMBOL");
        logger.log(Level.DEBUG, "(из Pole) Был создано поле [SYMBOL]: значение = {}", this.SYMBOL);

        logger.log(Level.DEBUG, "(из Pole) Был создан объект Pole: значение {}", this.returnprintPole());
    }
    
    public void printPole()
    {
        System.out.print(this.SYMBOL_COLOR + this.COLOR_BACK + this.SYMBOL + BackColor.RESET + TextColor.RESET);
    }

    public String returnprintPole()
    {
        return this.SYMBOL_COLOR + this.COLOR_BACK + this.SYMBOL;
    }

    public String returnTypeOfPole()
    {
        return this.TYPEOFPOLEHANDLER;
    }
    
    private String returnColorFore(String color)
    {
        String answer = new String();
        switch(color)
        {
            case "BLACK":
                answer = TextColor.BLACK;
                break;
            case "RED":
                answer = TextColor.RED;
                break;                
            case "GREEN":
                answer = TextColor.GREEN;
                break;                
            case "YELLOW":
                answer = TextColor.YELLOW;
                break;                
            case "BLUE":
                answer = TextColor.BLUE;
                break;                
            case "PURPLE":
                answer = TextColor.PURPLE;
                break;                
            case "CYAN":
                answer = TextColor.CYAN;
                break;                
            case "WHITE":
                answer = TextColor.WHITE;
                break;                
        }
        
        return answer;
    }

    private String returnColorBack(String color)
    {
        String answer = new String();
        switch(color)
        {
            case "BLACK":
                answer = BackColor.BLACK;
                break;
            case "RED":
                answer = BackColor.RED;
                break;                
            case "GREEN":
                answer = BackColor.GREEN;
                break;                
            case "YELLOW":
                answer = BackColor.YELLOW;
                break;                
            case "BLUE":
                answer = BackColor.BLUE;
                break;                
            case "PURPLE":
                answer = BackColor.PURPLE;
                break;                
            case "CYAN":
                answer = BackColor.CYAN;
                break;                
            case "WHITE":
                answer = BackColor.WHITE;
                break;                
        }
        
        return answer;
    }
    
    public String getX()
    {
        return this.X;
    }
    
    public String getY()
    {
        return this.Y;
    }

    @Override
    public String toString()
    {
        return "X = " + this.X +
                "\nY = " + this.Y +
                "\nTYPEOFPOLEHANDLER = " + this.TYPEOFPOLEHANDLER +
                "\nPRIORITET = " + this.PRIORITET +
                "\nSYMBOL = " + this.SYMBOL_COLOR +
                "\nCOLOR_BACK = " + this.COLOR_BACK;
    }

    public String returnSymbol()
    {
        return this.SYMBOL;
    }
}
