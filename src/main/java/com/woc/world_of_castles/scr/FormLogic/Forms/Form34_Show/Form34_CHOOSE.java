/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.FormLogic.Forms.Form34_Show;

import com.Util_Classes.BackColor;
import com.Util_Classes.Dict;
import com.woc.world_of_castles.scr.GameLogic.City.City;
import com.woc.world_of_castles.scr.GameLogic.User;
import com.woc.world_of_castles.scr.GlobalVariable;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.woc.world_of_castles.scr.WaitAnswer.WaitAnswer;

/**
 *
 * @author mfayzelgay001
 */
public class Form34_CHOOSE extends IShowingForm34
{
    Dict<String> loaded_users_int;
    Dict<String> loaded_colors;
    Dict<String> loaded_races = new Dict<>();
    HashMap<String, HashMap<String, String>> loadedCities;
    Dict<String> loadedTeams;
    final int countOfNumberInAnswer = 7;

    private static final Logger logger = LogManager.getLogger(Form34_CHOOSE.class.getName());

    public Form34_CHOOSE()
    {
        super();
    }

    @Override
    void otherActionsToPrepareGame()
    {
        this.reqsymbols.addAll(Arrays.asList("ANY"));
        loadedCities = new HashMap<>();
        loadedTeams = new Dict<>();

        this.loaded_races = this.createIntArray(this.GSK.return_ALL_races());
        this.loaded_colors = GlobalVariable.possible_colors;

        logger.log(Level.DEBUG, "(из Form34/otherActionsToPrepareGame) this.loadedCities = {}", this.loadedCities);

//        this.loadedCities.addAll(
//                                GSK.get_ALL_users().stream()
//                                            .map(User::returnAllCities)
//                                            .collect(ArrayList<City>::new, (al, el) -> al.addAll(el), (s1, s2) -> s1.addAll(s2))
//                                            .stream()
//                                            .filter(city -> city != null)
//                                            .map(City::returnCoordinates)
//                                            .map(Pole::returnSymbol)
//                                            .collect(Collectors.toList()));

        this.loadedCities =
                GSK.get_ALL_users().stream()
                        .map(User::returnAllCities)
                        .collect(ArrayList<City>::new, (al, el) -> al.addAll(el), (s1, s2) -> s1.addAll(s2))
                        .stream()
                        .filter(city -> city != null)
                        .map(City::returnCoordinates)
                        .collect(HashMap<String, HashMap<String, String>>::new,
                                (hm, cityCoord) -> {
                                    HashMap<String, String> n = new HashMap<>();
                                    n.put("X", cityCoord.getX());
                                    n.put("Y", cityCoord.getY());
                                    hm.put(cityCoord.returnSymbol(), n);
                                },
                                (hm1, hm2) -> hm1.putAll(hm2));



        logger.log(Level.DEBUG, "(из Form34/otherActionsToPrepareGame) this.loadedCities = {}", this.loadedCities);

        this.loadedTeams = this.createIntArray(GSK.returnRelationsBetweenUsers().getALLKeysInLink().stream()
                                                                                .filter(team -> !team.contains("NPC"))
                                                                                .collect(Dict<String>::new,
                                                                                (dict, el) -> {
                                                                                                dict.saveValue(new String[]{el}, "");
                                                                                                },
                                                                                (dict1, dict2) -> dict1.saveValue(dict2.getLinkHashMap())));

        logger.log(Level.DEBUG, "(из Form34/otherActionsToPrepareGame) this.loadedTeams = {}", this.loadedTeams);
    }

    @Override
    void createUserArray() {
        int i = 1;
        Dict<String> loadingUser = new Dict<>();
        this.loaded_users_int = new Dict<>();

        for (User every_user : GSK.get_ALL_users())
        {
            logger.log(Level.DEBUG, "(из формы Form34): every_user = {}", every_user);

            if (every_user.isUserStepping())
            {
                loadingUser.saveValue(new String[]{"NAME"}, every_user.getUserName());
                logger.log(Level.DEBUG, "(из формы Form34): Загружены данные по User из файла миссии: loadinguser['NAME'] = {}", loadingUser.getValue("NAME"));

                loadingUser.saveValue(new String[]{"X"}, "N/A");
                logger.log(Level.DEBUG, "(из формы Form34): Загружены данные по User из файла миссии: loadinguser['X'] = {}", loadingUser.getValue("X"));

                loadingUser.saveValue(new String[]{"Y"}, "N/A");
                logger.log(Level.DEBUG, "(из формы Form34): Загружены данные по User из файла миссии: loadinguser['Y'] = {}", loadingUser.getValue("Y"));

                loadingUser.saveValue(new String[]{"NUMBER"}, "N/A"); // номер места для города
                logger.log(Level.DEBUG, "(из формы Form34): Загружены данные по User из файла миссии: loadinguser['NUMBER'] = {}", loadingUser.getValue("NUMBER"));

                loadingUser.saveValue(new String[]{"COLOR"}, "N/A");
                logger.log(Level.DEBUG, "(из формы Form34): Загружены данные по User из файла миссии: loadinguser['COLOR'] = {}", loadingUser.getValue("COLOR"));

                loadingUser.saveValue(new String[]{"ISPLAYING"}, "В игре");
                logger.log(Level.DEBUG, "(из формы Form34): Загружены данные по User из файла миссии: loadinguser['ISPLAYING'] = {}", loadingUser.getValue("ISPLAYING"));

                loadingUser.saveValue(new String[]{"TEAM"}, "N/A");
                logger.log(Level.DEBUG, "(из формы Form34): Загружены данные по User из файла миссии: loadinguser['TEAM'] = {}", loadingUser.getValue("TEAM"));

                loadingUser.saveValue(new String[]{"RACE"}, "N/A");
                logger.log(Level.DEBUG, "(из формы Form34): Загружены данные по User из файла миссии: loadinguser['COMMAND'] = {}", loadingUser.getValue("RACE"));

                loadingUser.saveValue(new String[]{"ISPLAYER"}, "N/A");
                logger.log(Level.DEBUG, "(из формы Form34): Загружены данные по User из файла миссии: loadinguser['ISPLAYER'] = {}", loadingUser.getValue("ISPLAYER"));

                this.loaded_users_data.saveValue(new String[]{every_user.getUserName()}, loadingUser.getLinkHashMap());

                loadingUser = new Dict<String>(); //очистка

                logger.log(Level.DEBUG, "(из формы Form34): Integer.toString(i) = {}", Integer.toString(i));
                logger.log(Level.DEBUG, "(из формы Form34): new String[]{Integer.toString(i)} = {}", Arrays.toString(new String[]{Integer.toString(i)}));
                logger.log(Level.DEBUG, "(из формы Form34): every_user.getUserName() = {}", every_user.getUserName());
                logger.log(Level.DEBUG, "(из формы Form34): loaded_users_int = {}", loaded_users_int);

                this.loaded_users_int.saveValue(new String[]{Integer.toString(i)}, every_user.getUserName());

                i = i + 1;
            }
        }
    }

    @Override
    void presentForm34()
    {
        do
        {
            GlobalVariable.myOS.clearmyscreen();
            System.out.println(BackColor.RED + GlobalVariable.myOS.translate("World Of Castles") + BackColor.RESET);
            System.out.println(GlobalVariable.myOS.translate("Миссия: ") + GSK.getMissionName());
            System.out.println("");
            System.out.println(GlobalVariable.myOS.translate("Выбранные условия для миссии:"));

            for (String everyUser : this.loaded_users_data.getLinkHashMap().keySet())
            {
                System.out.println(this.loaded_users_data.getValue(everyUser, "NAME") + ": "
                                    + this.loaded_users_data.getValue(everyUser, "ISPLAYING")
                                    + " (Место города: " + this.loaded_users_data.getValue(everyUser, "NUMBER")
                                    + "; Раса: " + this.loaded_users_data.getValue(everyUser, "RACE")
                                    + "; цвет: " + this.loaded_users_data.getValue(everyUser, "COLOR")
                                    + "; команда: " + this.loaded_users_data.getValue(everyUser, "TEAM")
                                    + "; компьютер/игрок: " + this.loaded_users_data.getValue(everyUser, "ISPLAYER") + ")");
            }

            System.out.println("");

            System.out.println(GlobalVariable.myOS.translate("Миниатюра карты:"));

            GSK = GlobalVariable.GameStateKeeperFactory.returnGameStateKeeper();
            GSK.showMiniMap();

            System.out.println("");
            System.out.println(GlobalVariable.myOS.translate("Цвета:"));
            System.out.println("");

            for (String colorNumber : this.loaded_colors.getALLKeysInLink())
            {
                System.out.println(colorNumber + " : " + this.loaded_colors.getValue(colorNumber));
            }

            System.out.println("");

            System.out.println(GlobalVariable.myOS.translate("Расы:"));
            System.out.println("");

            for (String key : this.loaded_races.getLinkHashMap().keySet()) System.out.println(key + " : " + this.loaded_races.getValue(key));

            System.out.println("");

            System.out.println(GlobalVariable.myOS.translate("Команды:"));
            System.out.println("");

            for (String key : this.loadedTeams.getLinkHashMap().keySet()) System.out.println(key + " : " + this.loadedTeams.getValue(key));

            System.out.println("");

            System.out.println(GlobalVariable.myOS.translate(">> Выбор условий: N (Номер пользователя) - Y (В игре или нет 1/0) - M (Номер места города) - R (Номер расы) - C (Цвет пользователя) - K (Номер команды) - I (Игрок - 1/Компьютер - 0):"));
            System.out.println(GlobalVariable.myOS.translate(">> Пример ввода условий: 1-1-1-1-1-1-1"));
            System.out.println(GlobalVariable.myOS.translate(">> Старт карты (A)"));
            System.out.println(GlobalVariable.myOS.translate(">> Обратно (B)"));

            this.answer = WaitAnswer(this.reqsymbols);
            this.load_answer_into_UserArray(this.answer);
        }
        while (!(this.checkChoice() && this.answer.equals("A")) && !this.answer.equals("B"));
    }

    private <T> Dict<String> createIntArray(Dict<T> dict)
    {
        int i = 1;
        Dict<String> result = new Dict<>();

        LinkedHashSet<String> setOFKeysInDict = dict.getLinkHashMap().keySet().stream().sorted((e1, e2) -> {
            if(e1.length() == e2.length()) return e1.compareTo(e2);
            else
            {
                if(e1.length() > e2.length()) return 1;
                else return -1;
            }
        }).collect(LinkedHashSet::new, LinkedHashSet::add, LinkedHashSet::addAll);

        for (String key : setOFKeysInDict)
        {
            logger.log(Level.DEBUG, "(из Form34_CHOOSE) key (ключ) = {}", key);
            result.saveValue(new String[]{Integer.toString(i)}, key);
            i = i + 1;
        }

        return result;
    }

    private boolean checkChoice()
    {
        boolean isGood = true;

        //------------Контроль 1----------------------------НАЧАЛО
        //цвет юзера встречается один раз только у одного юзера
        List<String> colorsInUsers = this.loaded_users_data
                .getALLKeysInLink().stream()
                .map(userName -> this.loaded_users_data.getValue(userName, "COLOR"))
                .filter(userColor -> userColor.length() > 0 && userColor != null)
                .collect(Collectors.toList());

        if(colorsInUsers.stream().filter(userColor -> colorsInUsers.stream().filter(e -> e.equals(userColor)).count() > 1).count() > 0) isGood = false;
        //------------Контроль 1----------------------------КОНЕЦ


//        добавить check выбора игрока перед игрой в сражения
//        проверка на место города (не должно быть совпадающих)
//        количество юзеров не должно быть больше городов
//        играющих юзеров должно быть не меньше 2 разных команд
//




//        действительно ли пользователь ввёл те номера городов, которые могут быть?

        return isGood;
    }

    private void load_answer_into_UserArray (String answer)
    {
        logger.log(Level.DEBUG, "(из Form34/load_answer_into_UserArray) answer = {}", answer);
        logger.log(Level.DEBUG, "(из Form34/load_answer_into_UserArray) Проверка на наличие только чисел и дефисов");

        //ПРОВЕРКА: замена все символы кроме чисел на пустоту
        String checkAnswer = answer.replaceAll("[^0-9]+?", "");
        logger.log(Level.DEBUG, "(из Form34/load_answer_into_UserArray) checkAnswer = {}", checkAnswer);
        //проверить, что остались именно числа
        logger.log(Level.DEBUG, "(из Form34/load_answer_into_UserArray) checkAnswer.split(\"[0-9]+\") = {}", checkAnswer.split("[0-9]+"));

        if(checkAnswer.split("[0-9]+").length == 0)
        {
            logger.log(Level.DEBUG, "(из Form34/load_answer_into_UserArray) Условие checkAnswer.split(\"[0-9]+\").length == 0 сработало");

            String[] decomposed_answer = answer.split("[^0-9]+");
            logger.log(Level.DEBUG, "(из Form34/load_answer_into_UserArray) decomposed_answer = {}", Arrays.toString(decomposed_answer));

            //количество чисел должно быть равно заданному
            if (decomposed_answer.length == this.countOfNumberInAnswer)
            {
                logger.log(Level.DEBUG, "(из Form34) decomposed_answer.length == this.countOfNumberInAnswer = {}", decomposed_answer.length == this.countOfNumberInAnswer);

                String userNumber = decomposed_answer[0];
                String isInGame = decomposed_answer[1];
                String cityNumber = decomposed_answer[2];
                String raceNumber = decomposed_answer[3];
                String colorNumber = decomposed_answer[4];
                String teamNumber = decomposed_answer[5];
                String isPlayer = decomposed_answer[6];

                if(this.loaded_users_int.getALLKeysInLink().stream().anyMatch(keyInDict -> keyInDict.equals(userNumber)))
                {
                    //города надо взять
                    if(this.loadedCities.keySet().contains(cityNumber))
                    {
                        this.loaded_users_data.saveValue(new String[]{this.loaded_users_int.getValue(userNumber), "NUMBER"}, cityNumber);
                        this.loaded_users_data.saveValue(new String[]{this.loaded_users_int.getValue(userNumber), "X"}, this.loadedCities.get(cityNumber).get("X"));
                        this.loaded_users_data.saveValue(new String[]{this.loaded_users_int.getValue(userNumber), "Y"}, this.loadedCities.get(cityNumber).get("Y"));
                    }

                    if (isInGame.equals("1"))
                        this.loaded_users_data.saveValue(new String[]{this.loaded_users_int.getValue(userNumber), "ISPLAYING"}, "В игре");
                    else if (isInGame.equals("0"))
                        this.loaded_users_data.saveValue(new String[]{this.loaded_users_int.getValue(userNumber), "ISPLAYING"}, "Не играет");

                    if (this.loaded_races.getALLKeysInLink().stream().anyMatch(keyInDict -> keyInDict.equals(raceNumber)))
                        this.loaded_users_data.saveValue(new String[]{this.loaded_users_int.getValue(userNumber), "RACE"}, this.loaded_races.getValue(raceNumber));

                    if (this.loaded_colors.getALLKeysInLink().stream().anyMatch(keyInDict -> keyInDict.equals(colorNumber)))
                        this.loaded_users_data.saveValue(new String[]{this.loaded_users_int.getValue(userNumber), "COLOR"}, this.loaded_colors.getValue(colorNumber));

                    if(this.loadedTeams.getALLKeysInLink().stream().anyMatch(keyInDict -> keyInDict.equals(teamNumber)))
                        this.loaded_users_data.saveValue(new String[]{this.loaded_users_int.getValue(userNumber), "TEAM"}, this.loadedTeams.getValue(teamNumber));

                    if (isPlayer.equals("1"))
                        this.loaded_users_data.saveValue(new String[]{this.loaded_users_int.getValue(userNumber), "ISPLAYER"}, "Игрок");
                    else if (isPlayer.equals("0"))
                        this.loaded_users_data.saveValue(new String[]{this.loaded_users_int.getValue(userNumber), "ISPLAYER"}, "Компьютер");
                }
                else
                    logger.log(Level.DEBUG, "(из Form34) Условие this.loaded_users_int.getALLKeysInLink().stream().anyMatch(keyInDict -> keyInDict.equals(userNumber)) не выполнено");

                logger.log(Level.DEBUG, "(из Form34) В массив loaded_users_data была записана информация. Теперь массив стал таким = {}", this.loaded_users_data);
            }
            else
            {
                logger.log(Level.DEBUG, "(из Form34) decomposed_answer.length != this.countOfNumberInAnswer = {}", decomposed_answer.length == this.countOfNumberInAnswer);
            }
        }
        else
            logger.log(Level.DEBUG, "(из Form34/load_answer_into_UserArray) Условие checkAnswer.split(\"[0-9]+\").length == 0 не сработало");
    }
}
