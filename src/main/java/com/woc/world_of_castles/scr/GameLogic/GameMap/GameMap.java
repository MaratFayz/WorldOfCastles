/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic.GameMap;

import com.woc.world_of_castles.scr.GameLogic.MapPoles.Pole;
import com.Util_Classes.Dict;

import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class GameMap 
{
    private static final Logger logger = LogManager.getLogger(GameMap.class.getName());  

    Dict<Pole> MAP_LAYER_FLOOR = new Dict<>();
    Dict<Pole> MAP_LAYER_1_HERO = new Dict<>();
    Dict<Pole> MAP_LAYER_1_BUILDING = new Dict<>();
    Dict<Pole> MAP_LAYER_2 = new Dict<>();
    int WIDTH = 0; //#количество Y в каждом X
    int HEIGHT = 0; //#количество X в каждом Y
    Dict<Pole> MINIMAP = new Dict<>();

    Dict<Pole> MAP_LAYER_FLOOR_plus_LAYER2_plus_LAYER1BUILDING = new Dict<>();
    Dict<Pole> FULLMAP = new Dict<>(); //#объединение всех объектов

    public GameMap(Dict<String> part_MAP_FILE)
    {
        logger.log(Level.DEBUG, "(из GameMap) part_MAP_FILE = {}", part_MAP_FILE);

        //#Создание карты самого низшего уровня
        this.WIDTH = Integer.parseInt(part_MAP_FILE.getValue("WIDTH"));
        this.HEIGHT = Integer.parseInt(part_MAP_FILE.getValue("HEIGHT"));
        logger.log(Level.DEBUG, "(из GameMap) Был создан объект: this.GameMap");
        logger.log(Level.DEBUG, "(из GameMap) Значение WIDTH = {}", this.WIDTH);
        logger.log(Level.DEBUG, "(из GameMap) Значение HEIGHT = {}", this.HEIGHT);

        //#загрузка полей низшего уровня
        for (Dict<String> each : part_MAP_FILE.getValues_Dict_Arrays("MAP_LAYER_FLOOR"))
        {
            this.MAP_LAYER_FLOOR.saveValue(new String[]{each.getValue("X"), each.getValue("Y")}, new Pole(each));
            logger.log(Level.DEBUG, "(из GameMap) Была добавлено значение в переменную this.MAP_LAYER_FLOOR значение {}", this.MAP_LAYER_FLOOR);
        }
    }
    
    public void showmap(Dict<Pole> map_to_show)
    {
        int number_of_symbols_in_string = map_to_show.getLinkHashMap("1").keySet().size();

        logger.log(Level.DEBUG, "(из GameMap) map_to_show = {}", map_to_show);

        for (String each : map_to_show.getLinkHashMap().keySet()) //#X
        {
            logger.log(Level.DEBUG, "(из GameMap) Вывод карты each = {}", each);
            for (String eachy : map_to_show.getLinkHashMap(each).keySet()) //#Y
            {
                logger.log(Level.DEBUG, "(из GameMap) Вывод карты eachy = {}", eachy);
                map_to_show.getValue(each, eachy).printPole();
                if (eachy.equals(Integer.toString(number_of_symbols_in_string)))
                {
                    logger.log(Level.DEBUG, "(из GameMap) eachy == number_of_symbols_in_string = {}", number_of_symbols_in_string);
                    System.out.println("");
                }
            }
        }
    }
    
    public void createmapwithoutheroes()
    {
        this.MAP_LAYER_FLOOR_plus_LAYER2_plus_LAYER1BUILDING = this.MAP_LAYER_FLOOR.clone();
        logger.log(Level.DEBUG, "(из GameMap) Была добавлено значение в переменную this.MAP_LAYER_FLOOR_plus_LAYER2_plus_LAYER1BUILDING значение this.MAP_LAYER_FLOOR. Теперь this.MAP_LAYER_FLOOR_plus_LAYER2_plus_LAYER1BUILDING стал: {}", this.MAP_LAYER_FLOOR_plus_LAYER2_plus_LAYER1BUILDING);

        for (String each : this.MAP_LAYER_1_BUILDING.getLinkHashMap().keySet())
        {
            logger.log(Level.DEBUG, "(из GameMap) Цикл раскрытия this.MAP_LAYER_1_BUILDING. Значение each = {}", each);
            for (String eachy : this.MAP_LAYER_1_BUILDING.getLinkHashMap(each).keySet())
            {
                logger.log(Level.DEBUG, "(из GameMap) Цикл раскрытия this.MAP_LAYER_1_BUILDING. Значение eachy = {}", eachy);
                this.MAP_LAYER_FLOOR_plus_LAYER2_plus_LAYER1BUILDING.saveValue(new String[]{each, eachy}, this.MAP_LAYER_1_BUILDING.getValue(each, eachy));
                logger.log(Level.DEBUG, "(из GameMap) this.MAP_LAYER_FLOOR_plus_LAYER2_plus_LAYER1BUILDING стал = {}", this.MAP_LAYER_FLOOR_plus_LAYER2_plus_LAYER1BUILDING);
            }
        }
        
        for (String each : this.MAP_LAYER_2.getLinkHashMap().keySet())
        {
            logger.log(Level.DEBUG, "(из GameMap) Цикл раскрытия this.MAP_LAYER_2. Значение each = {}", each);
            for (String eachy : this.MAP_LAYER_2.getLinkHashMap(each).keySet())
            {
                logger.log(Level.DEBUG, "(из GameMap) Цикл раскрытия this.MAP_LAYER_2. Значение eachy = {}", eachy);
                this.MAP_LAYER_FLOOR_plus_LAYER2_plus_LAYER1BUILDING.saveValue(new String[]{each, eachy}, this.MAP_LAYER_2.getValue(each, eachy));
                logger.log(Level.DEBUG, "(из GameMap) this.MAP_LAYER_FLOOR_plus_LAYER2_plus_LAYER1BUILDING стал = {}", this.MAP_LAYER_FLOOR_plus_LAYER2_plus_LAYER1BUILDING);
            }
        }
    }
    
    public void create_map_WITH_heroes()
    {
        this.FULLMAP = this.MAP_LAYER_FLOOR_plus_LAYER2_plus_LAYER1BUILDING.clone();
        logger.log(Level.DEBUG, "(из GameMap) Была добавлено значение в переменную this.FULLMAP значение this.MAP_LAYER_FLOOR_plus_LAYER2_plus_LAYER1BUILDING. Теперь this.FULLMAP стал: {}", this.FULLMAP);

        for (String each_hero_X : this.MAP_LAYER_1_HERO.getLinkHashMap().keySet())
        {
            logger.log(Level.DEBUG, "(из GameMap) Цикл раскрытия this.MAP_LAYER_1_HERO. Значение each_hero_X = {}", each_hero_X);
            for (String each_hero_Y : this.MAP_LAYER_1_HERO.getLinkHashMap(each_hero_X).keySet())
            {
                logger.log(Level.DEBUG, "(из GameMap) Цикл раскрытия this.MAP_LAYER_1_HERO. Значение eachy = {}", each_hero_Y);
                this.FULLMAP.saveValue(new String[]{each_hero_X, each_hero_Y}, this.MAP_LAYER_1_HERO.getValue(each_hero_X, each_hero_Y));
                logger.log(Level.DEBUG, "(из GameMap) this.FULLMAP стал = {}", this.FULLMAP);
            }
        }
    }
    
    public void returnAllMap()
    {
        logger.log(Level.DEBUG, "(из GameMap) Вывод полной карты = {}", this.FULLMAP);
        this.showmap(this.FULLMAP);
    }

    public void getDataHeroes(Pole HeroCoordinates)
    {
        logger.log(Level.DEBUG, "(из GameMap/getDataHeroes) HeroCoordinates получен = {}", HeroCoordinates.returnprintPole());
        this.MAP_LAYER_1_HERO.saveValue(new String[]{HeroCoordinates.getX(), HeroCoordinates.getY()}, HeroCoordinates);
        logger.log(Level.DEBUG, "(из GameMap/getDataHeroes)  this.MAP_LAYER_1_HERO = {}",  this.MAP_LAYER_1_HERO);
    }

    public void getDataCities(Pole CityCoordinates)
    {
        logger.log(Level.DEBUG, "(из GameMap/getDataCities) CityCoordinates получен = {}", CityCoordinates.returnprintPole());
        this.MAP_LAYER_2.saveValue(new String[]{CityCoordinates.getX(), CityCoordinates.getY()}, CityCoordinates);
        logger.log(Level.DEBUG, "(из GameMap/getDataCities) this.MAP_LAYER_2 = {}", this.MAP_LAYER_2);
    }
    
    public void getDataBuildings(Pole PoleBuildingsCoordinates)
    {
        logger.log(Level.DEBUG, "(из GameMap/getDataBuildings) PoleBuildingsCoordinates получен = {}", PoleBuildingsCoordinates.returnprintPole());
        this.MAP_LAYER_1_BUILDING.saveValue(new String[]{PoleBuildingsCoordinates.getX(), PoleBuildingsCoordinates.getY()}, PoleBuildingsCoordinates);
    }
    
    public void createminimap()
    {
        this.MINIMAP = this.FULLMAP.clone();

        //MINI_CREATION START
        //MINI_CREATION END
        logger.log(Level.DEBUG, "(из Game) Сформирована миникарта this.MINIMAP = {}", this.MINIMAP);
    }
        
    public void showMiniMap()
    {
        logger.log(Level.DEBUG, "(из GameMap) Вывод миникарты = {}", this.MINIMAP);
        this.showmap(this.MINIMAP);
    }
}
