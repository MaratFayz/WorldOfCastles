/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.FormLogic.Forms.Form34_Show;

import com.Util_Classes.BackColor;
import com.Util_Classes.Dict;

import com.woc.world_of_castles.scr.GameLogic.User;
import com.woc.world_of_castles.scr.GlobalVariable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

import static com.woc.world_of_castles.scr.WaitAnswer.WaitAnswer;

/**
 *
 * @author mfayzelgay001
 */
public class Form34_ALREADY_CHOSEN extends IShowingForm34
{
    private static final Logger logger = LogManager.getLogger(Form34_ALREADY_CHOSEN.class.getName());

    public Form34_ALREADY_CHOSEN()
    {
        super();
    }
    
    @Override
    void createUserArray()
    {
        Dict<String> loadingUser = new Dict<>();

        for (User every_user : GSK.get_ALL_users())
        {
            if (every_user.isUserStepping())
            {
                loadingUser.saveValue(new String[]{"NAME"}, every_user.getUserName());
                logger.log(Level.DEBUG, "(из формы  Form34_ALREADY_CHOSEN/Form34): Загружены данные по User из файла миссии: loadinguser['NAME'] = {}", loadingUser.getValue("NAME"));

                loadingUser.saveValue(new String[]{"COLOR"}, every_user.getUserColor());
                logger.log(Level.DEBUG, "(из формы Form34_ALREADY_CHOSEN/Form34): Загружены данные по User из файла миссии: loadinguser['COLOR'] = {}", loadingUser.getValue("COLOR"));

                loadingUser.saveValue(new String[]{"ISPLAYING"}, "В игре");

                loadingUser.saveValue(new String[]{"TEAM"}, every_user.getTeam());

                loadingUser.saveValue(new String[]{"ISPLAYER"}, every_user.isPlayer() ? "PLAYER" : "COMPUTER");

                logger.log(Level.DEBUG, "(из формы Form34_ALREADY_CHOSEN/Form34): Загружены данные по User из файла миссии: loadinguser['ISPLAYING'] = {}", loadingUser.getValue("ISPLAYING"));
                logger.log(Level.DEBUG, "(из формы Form34_ALREADY_CHOSEN/Form34): Загружены данные по User из файла миссии: loadinguser['TEAM'] = {}", loadingUser.getValue("TEAM"));
                logger.log(Level.DEBUG, "(из формы Form34_ALREADY_CHOSEN/Form34): Загружены данные по User из файла миссии: loadinguser['ISPLAYER'] = {}", loadingUser.getValue("ISPLAYER"));

                this.loaded_users_data.saveValue(new String[]{every_user.getUserName()}, loadingUser.getLinkHashMap());

                loadingUser = new Dict<>();
            }
        }
    }
    
    @Override
    void presentForm34()
    {
        GlobalVariable.myOS.clearmyscreen();
        System.out.println(BackColor.RED + GlobalVariable.myOS.translate("World Of Castles") + BackColor.RESET);
        System.out.println(GlobalVariable.myOS.translate("Миссия: ") + GSK.getMissionName());
        System.out.println("");
        System.out.println(GlobalVariable.myOS.translate("Заданные условия для миссии:"));

        for (String everyUser : this.loaded_users_data.getLinkHashMap().keySet())
        {
            System.out.println(this.loaded_users_data.getValue(everyUser, "NAME") + ": "
                          + this.loaded_users_data.getValue(everyUser, "ISPLAYING")
                          + "; цвет: " + this.loaded_users_data.getValue(everyUser, "COLOR")
                          + "; команда: " + this.loaded_users_data.getValue(everyUser, "TEAM")
                          + "; компьютер/игрок: " + this.loaded_users_data.getValue(everyUser, "ISPLAYER") + ")");
        }

        System.out.println("");

        System.out.println(GlobalVariable.myOS.translate("Миниатюра карты:"));

        GSK = GlobalVariable.GameStateKeeperFactory.returnGameStateKeeper();
        GSK.showMiniMap();

        System.out.println("");

        System.out.println(GlobalVariable.myOS.translate(">> Старт карты (A)"));
        System.out.println(GlobalVariable.myOS.translate(">> Обратно (B)"));
        this.answer = WaitAnswer(this.reqsymbols);
    }

    @Override
    void otherActionsToPrepareGame()
    {
        this.reqsymbols.addAll(Arrays.asList("A", "B"));
    }
}
