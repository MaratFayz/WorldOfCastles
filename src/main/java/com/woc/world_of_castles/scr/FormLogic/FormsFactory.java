/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.FormLogic;

import com.woc.world_of_castles.scr.EventLogic.Command;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form1;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form2;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form3;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form34;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form35;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form36;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form37;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form38;
import com.Util_Classes.Dict;
import com.woc.world_of_castles.scr.FormLogic.Forms.FormInterface;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form39;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form4;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form40;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form41;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form5;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form6;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form7;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form8;
import com.woc.world_of_castles.scr.FormLogic.Forms.Form9;

import java.util.ArrayList;
import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class FormsFactory 
{
    FormInterface ReturnedForm;
    Dict<String> Form_Mapping;
    String value = new String();
    
    private static final Logger logger = LogManager.getLogger(FormsFactory.class.getName());

    public FormsFactory(Dict<String> Form_Mapping)
    {
        this.Form_Mapping = Form_Mapping;
    }

    public void returnForm(Command Command)
    {
        logger.log(Level.DEBUG, "In FormsFactory: for mapping has come command = {}", Command);

        this.value = this.Form_Mapping.getValue(Command.Form, Command.Event, Command.Object, Command.Relations, "Form");
        logger.log(Level.DEBUG, "In FormsFactory: Form was mapped = {}", value);

        switch(this.value)
        {
            case "NULL":
                break;
            case "Form1":
                ReturnedForm = new Form1();
                ReturnedForm.Show();
                ReturnedForm.CreateEvent();
                break;
            case "Form2":
                ReturnedForm = new Form2();
                ReturnedForm.Show();
                ReturnedForm.CreateEvent();
                break;
            case "Form3":
                ReturnedForm = new Form3();
                ReturnedForm.Show();
                ReturnedForm.CreateEvent();
                break;
            case "Form4":
                ReturnedForm = new Form4();
                ReturnedForm.Show();
                ReturnedForm.CreateEvent();
                break;
            case "Form5":
                ReturnedForm = new Form5();
                ReturnedForm.Show();
                ReturnedForm.CreateEvent();
                break;
            case "Form6":
                ReturnedForm = new Form6();
                ReturnedForm.Show();
                ReturnedForm.CreateEvent();
                break;
            case "Form7":
                ReturnedForm = new Form7();
                ReturnedForm.Show();
                ReturnedForm.CreateEvent();
                break;
            case "Form8":
                ReturnedForm = new Form8();
                ReturnedForm.Show();
                ReturnedForm.CreateEvent();
                break;
            case "Form9":
                ReturnedForm = new Form9();
                ReturnedForm.Show();
                ReturnedForm.CreateEvent();
                break;
            case "Form34":
                ReturnedForm = new Form34();
                ReturnedForm.Show();
                ReturnedForm.CreateEvent();
                break;
            case "Form35":
                ReturnedForm = new Form35();
                ReturnedForm.Show();
                ReturnedForm.CreateEvent();
                break;
            case "Form36":
                this.ReturnedForm = new Form36();
                this.ReturnedForm.Show();
                this.ReturnedForm.CreateEvent();
                break;
            case "Form37":
                this.ReturnedForm = new Form37();
                this.ReturnedForm.Show();
                this.ReturnedForm.CreateEvent();
                break;
            case "Form38":
                this.ReturnedForm = new Form38();
                this.ReturnedForm.Show();
                this.ReturnedForm.CreateEvent();
                break;
            case "Form39":
                this.ReturnedForm = new Form39();
                this.ReturnedForm.Show();
                this.ReturnedForm.CreateEvent();
                break;
            case "Form40":
                ReturnedForm = new Form40();
                ReturnedForm.Show();
                ReturnedForm.CreateEvent();
                break;
            case "Form41":
                ReturnedForm = new Form41();
                ReturnedForm.Show();
                ReturnedForm.CreateEvent();
                break;
        }
        this.value = "";
    }    

}
