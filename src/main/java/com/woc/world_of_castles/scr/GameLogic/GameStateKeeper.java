/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic;

import com.Util_Classes.Dict;

import com.woc.world_of_castles.scr.GameLogic.City.City;
import com.woc.world_of_castles.scr.GameLogic.City.CityBuilding;
import com.woc.world_of_castles.scr.GameLogic.City.CityRace;
import com.woc.world_of_castles.scr.GameLogic.GameMap.GameMap;
import com.woc.world_of_castles.scr.GameLogic.Hero.Hero;
import com.woc.world_of_castles.scr.GameLogic.Hero.HeroClass;
import com.woc.world_of_castles.scr.GameLogic.Hero.HeroRace;
import com.woc.world_of_castles.scr.GameLogic.Hero.TypeofMerch;
import com.woc.world_of_castles.scr.GameLogic.MapPoles.PoleBuilding;
import com.woc.world_of_castles.scr.GameLogic.TypeOfSkill;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class GameStateKeeper 
{
    boolean isGameCanBeChosen = false;

    Dict<String> RelationsBetweenUsers = new Dict<>();
    GameMap GameMap;
    ArrayList<User> AllUsers = new ArrayList<>();
    Dict<TypeOfSkill> LoadedSkills = new Dict<>();
    Dict<TypeOfWarrior> LoadedWarriors = new Dict<>(); 
    Dict<TypeofMerch> LoadedTypesOfMerch = new Dict<>();
    Dict<HeroRace> LoadedHeroRaces = new Dict<>();
    Dict<HeroClass> LoadedHeroClasses = new Dict<>();
    Dict<CityBuilding> LoadedCityBuildings = new Dict<>();
    Dict<CityRace> LoadedCityRaces = new Dict<>(); 
    Dict<Hero> LoadedHeroes = new Dict<>(); 
    Dict<City> LoadedCities = new Dict<>(); 
    Dict<PoleBuilding> LoadedPoleBuildings = new Dict<>();
    String MissionName = new String();
        
    private static final Logger logger = LogManager.getLogger(GameStateKeeper.class.getName());  

    ArrayList<User> SteppedUsers = new ArrayList<>(); //герои, которые уже сходили за день
    User SteppingUser;
    Hero SteppingHero;
    ArrayList<Hero> SteppedHeroes;
    Dict<Integer> BalanceOfStepsInDay;

    Dict<String> WinCondition = new Dict<>();
//
//  def startnewgame(self) -> None:
//    pass
//
//  def registerNewUser(self, User : User) -> None:
//    pass
//
//  def getAllHeroes(self) -> None:
//    pass//

//    public String showNameSteppingHero()
//    {
//      return this.SteppingHero.showName();
//    }
//
//  def showNameSteppingUser(self) -> str:
//    return this.SteppingUser.showName()
//
//  def startANewDay(self) -> None:
//    this.SteppedUsers = []
//    BalanceOfStepsInDay
//
//  def showBalStepsOfHero(self) -> int:
//    return this.BalanceOfStepsInDay[this.SteppingHero]
//
//  def startANewWeek(self) -> None:
//    pass

    public GameStateKeeper(Dict<String> LoadedFile)
    {
        this.MissionName = LoadedFile.getValue("NAME");

        //определение игры - может ли игрок выбирать условия или нет (для отображения в Form34)
        if (LoadedFile.getValue("CAN_CHOOSE_USERS").equals("YES")) {this.isGameCanBeChosen = true;}
        else {this.isGameCanBeChosen = false;}

        //загрузка умений
        for (Dict<String> each : LoadedFile.getValues_Dict_Arrays("SKILLS"))
        {
            LoadedSkills.saveValue(new String[]{each.getValue("NAME")}, new TypeOfSkill(each));
            logger.log(Level.DEBUG, "(из GameStateKeeper) Была добавлено значение в переменную LoadedSkills значение {}", LoadedSkills);
        }

        //загрузка воинов
        for (Dict<String> each : LoadedFile.getValues_Dict_Arrays("WARRIORS"))
        {
            LoadedWarriors.saveValue(new String[]{each.getValue("NAME")}, new TypeOfWarrior(each, this.LoadedSkills));
            logger.log(Level.DEBUG, "(из GameStateKeeper) Была добавлено значение в переменную LoadingWarrior значение {}", LoadedWarriors);
        }

        //загрузка вещей
        for (Dict<String> each : LoadedFile.getValues_Dict_Arrays("MERCH"))
        {
            LoadedTypesOfMerch.saveValue(new String[]{each.getValue("NAME")}, new TypeofMerch(each));
            logger.log(Level.DEBUG, "(из GameStateKeeper) Была добавлено значение в переменную LoadingTypeOfMerch значение {}", LoadedTypesOfMerch);
        }

        //загрузка отношений
        this.RelationsBetweenUsers = LoadedFile.getValues_Link("TEAM_RELATIONS");
        logger.log(Level.DEBUG, "(из GameStateKeeper) Была добавлено значение в переменную this.RelationsBetweenUsers значение {}", this.RelationsBetweenUsers);

        //загрузка рас героев
        for (Dict<String> each : LoadedFile.getValues_Dict_Arrays("HERO_RACES"))
        {
            LoadedHeroRaces.saveValue(new String[]{each.getValue("NAME")}, new HeroRace(each, this.LoadedSkills));
            logger.log(Level.DEBUG, "(из GameStateKeeper) Была добавлено значение в переменную LoadingHeroRace значение {}", LoadedHeroRaces);
        }

        //загрузка классов героев
        for (Dict<String> each : LoadedFile.getValues_Dict_Arrays("HERO_CLASSES"))
        {
            LoadedHeroClasses.saveValue(new String[]{each.getValue("NAME")}, new HeroClass(each, this.LoadedSkills));
            logger.log(Level.DEBUG, "(из GameStateKeeper) Была добавлено значение в переменную LoadingHeroClass значение {}", LoadedHeroClasses);
        }
        
        //загрузка зданий города
        for (Dict<String> each : LoadedFile.getValues_Dict_Arrays("CITY_BUILDINGS"))
        {
            LoadedCityBuildings.saveValue(new String[]{each.getValue("NAME")}, new CityBuilding(each));
            logger.log(Level.DEBUG, "(из GameStateKeeper) Была добавлено значение в переменную LoadingCityBuilding значение {}", LoadedCityBuildings);
        }
        
        //загрузка рас города
        for (Dict<String> each : LoadedFile.getValues_Dict_Arrays("CITY_RACES"))
        {
            LoadedCityRaces.saveValue(new String[]{each.getValue("NAME")}, new CityRace(each, this.LoadedCityBuildings));
            logger.log(Level.DEBUG, "(из GameStateKeeper) Была добавлено значение в переменную LoadingCityRace значение {}", LoadedCityRaces);
        }
        
        //загрузка героев
        if (!LoadedFile.getValues_Dict_Arrays("HEROES", "HEROES").isEmpty())
        {
            for (Dict<String> each : LoadedFile.getValues_Dict_Arrays("HEROES", "HEROES"))
            {
                LoadedHeroes.saveValue(new String[]{each.getValue("NAME")}, new Hero(each, this.LoadedTypesOfMerch, this.LoadedHeroRaces, this.LoadedHeroClasses, this.LoadedWarriors));
                logger.log(Level.DEBUG, "(из GameStateKeeper) Была добавлено значение в переменную LoadedHeroes значение {}", LoadedHeroes);
            }
        }
        else
        {
            logger.log(Level.DEBUG, "(из GameStateKeeper) Отсутствуют значения HEROES для создания");
        }

        //загрузка городов
        if (!LoadedFile.getValues_Dict_Arrays("CITIES", "CITIES").isEmpty())
        {        
            for (Dict<String> each : LoadedFile.getValues_Dict_Arrays("CITIES", "CITIES"))
            {
                LoadedCities.saveValue(new String[]{each.getValue("NAME")}, new City(each, this.LoadedCityRaces, this.LoadedWarriors));
                logger.log(Level.DEBUG, "(из GameStateKeeper) Была добавлено значение в переменную LoadingCity значение {}", LoadedCities);
            }
        }

        //загрузка зданий на карте
        for (Dict<String> each : LoadedFile.getValues_Dict_Arrays("BUILDINGS_ON_POLES"))
        {
            LoadedPoleBuildings.saveValue(new String[]{each.getValue("NAME")}, new PoleBuilding(each, this.LoadedWarriors));
            logger.log(Level.DEBUG, "(из GameStateKeeper) Была добавлено значение в переменную LoadingPoleBuilding значение {}", LoadedPoleBuildings);
        }

        //загрузка юзеров на карте
        for (Dict<String> each : LoadedFile.getValues_Dict_Arrays("USERS"))
        {
            this.AllUsers.add(new User(each, this.LoadedHeroes, this.LoadedCities, this.LoadedPoleBuildings));
        }
        
        //загрузка карты
        this.GameMap = new GameMap(LoadedFile.getValues_Link("MAP"));

        logger.log(Level.DEBUG, "(из GameStateKeeper) Добавляем значения координат героев");
        for (User each_user : this.AllUsers)
        {
            if (!each_user.returnAllHeroes().isEmpty())
            {
                logger.log(Level.DEBUG, "(из GameStateKeeper) От юзера {} получены герои", each_user);
                logger.log(Level.DEBUG, "(из GameStateKeeper) Герои = {}", each_user.returnAllHeroes());
                for (Hero each_hero : each_user.returnAllHeroes()) this.GameMap.getDataHeroes(each_hero.returnCoordinates());
            }
        }

        logger.log(Level.DEBUG, "(из GameStateKeeper) Добавляем значения координат городов");

        for (User eachUser : this.AllUsers)
        {
            ArrayList<City> Cities = eachUser.returnAllCities();
            if (!Cities.isEmpty())
            {
                logger.log(Level.DEBUG, "(из GameStateKeeper) От юзера {} получены города", eachUser);
                logger.log(Level.DEBUG, "(из GameStateKeeper) Города = ", Cities);
                for (City eachCity : Cities)
                {
                    this.GameMap.getDataCities(eachCity.returnCoordinates());
                }
            }
        }

        logger.log(Level.DEBUG, "(из GameStateKeeper) Добавляем значения координат зданий на поле");
        for (User eachUser : this.AllUsers)
        {
            ArrayList<PoleBuilding> PoleBuildings = eachUser.returnAllPoleBuildings();
            if (!PoleBuildings.isEmpty())
            {
                logger.log(Level.DEBUG, "(из GameStateKeeper) От юзера {} получены здания на поле", eachUser);
                logger.log(Level.DEBUG, "(из GameStateKeeper) Здания на поле = ", PoleBuildings);
                for (PoleBuilding eachPoleBuilding: PoleBuildings)
                {
                    this.GameMap.getDataBuildings(eachPoleBuilding.returnCoordinates());
                }
            }
        }

        this.GameMap.createmapwithoutheroes();
        this.GameMap.create_map_WITH_heroes();
        this.GameMap.createminimap();
    }

    public void showMiniMap()
    {
        this.GameMap.showMiniMap();
    }

    public boolean return_isGameCanBeChosen()
    {
        return this.isGameCanBeChosen;
    }

    public Dict<CityRace> return_ALL_races()
    {
        return this.LoadedCityRaces;
    }

    public ArrayList<User> get_ALL_users()
    {
        return this.AllUsers;
    }

    public Dict<String> returnRelationsBetweenUsers() {return this.RelationsBetweenUsers;}

    public String getMissionName()
    {
        return this.MissionName;
    }
}
