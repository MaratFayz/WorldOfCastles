/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.EventLogic;

import com.Util_Classes.Dict;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class Command 
{
    public String Form;
    public String Event;
    public String Object;
    public String Relations;
    public Dict<String> Data = new Dict<>();
    
    private static final Logger logger = LogManager.getLogger(Command.class.getName());

    public Command(String Form, String Event, String Object, String Relations)
    {
        this.Form = Form;
        this.Event = Event;
        this.Object = Object;
        this.Relations = Relations;
    }            

    @Override
    public String toString()
    {
        return "Command: Form: "+ this.Form + ", Event: " + this.Event + ", Object: " + this.Object + ", Relations: " + this.Relations;
    }
  
    public void addData(Dict<String> Data)
    {
        this.Data = Data;
    }

    public Dict<String> giveData()
    {
        return this.Data;
    }
}
