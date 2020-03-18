/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic;

import com.Util_Classes.Dict;

import com.woc.world_of_castles.scr.GameLogic.City.City;
import com.woc.world_of_castles.scr.GameLogic.Hero.Hero;
import com.woc.world_of_castles.scr.GameLogic.MapPoles.PoleBuilding;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class User 
{
    ArrayList<Hero> heroes = new ArrayList<>();
    ArrayList<City> cities = new ArrayList<>();
    ArrayList<PoleBuilding> buildings_on_poles = new ArrayList<>();
    //steppingunits : List[SteppingUnit] = []

    String name, color, team;
    UserAction useractions;
    //maplayer3 : Dict[str, Pole]
    //buildingsonpole : List[PoleBuilding] = []
    boolean isstepping, canwinlose;
            
    private static final Logger logger = LogManager.getLogger(User.class.getName()); 

    public User(Dict<String> objectData, Dict<Hero> LoadedHeroes, 
                Dict<City> LoadedCities, Dict<PoleBuilding> LoadedPoleBuildings)
    {
        logger.log(Level.DEBUG, "(из User) Был создан объект: this.User");

        this.name = objectData.getValue("NAME");
        logger.log(Level.DEBUG, "(из User) Значение name = {}", this.name);

        if (objectData.getValue("ISPLAYER").equals("PLAYER"))
        {
            this.useractions = new PlayerAction();
        }
        else if (objectData.getValue("ISPLAYER").equals("COMPUTER"))
        {
            this.useractions = new ComputerAction();
        }

        logger.log(Level.DEBUG, "----(из User) data_WO_addData['ISPLAYER']  = {}", objectData.getValue("ISPLAYER"));

        if (objectData.getValue("ISSTEPPING").equals("YES")) this.isstepping = true;
        else this.isstepping = false;
        
        logger.log(Level.DEBUG, "----(из User) isstepping  = {}", this.isstepping);

        this.color = objectData.getValue("COLOR");
        logger.log(Level.DEBUG, "----(из User) color  = {}", this.color);
       
        if (objectData.getValue("CANWINLOSE").equals("YES")) this.canwinlose = true;
        else this.canwinlose = false;
        logger.log(Level.DEBUG, "----(из User) canwinlose  = {}", this.canwinlose);

        if (!LoadedHeroes.isEmpty())
        {
            for (Dict<String> eachHero : objectData.getValues_Dict_Arrays("HEROES"))
            {
                 if (!eachHero.isEmpty()) this.heroes.add(LoadedHeroes.getValue(eachHero.getValue()));
            }
        }
        
        logger.log(Level.DEBUG, "----(из User) this.heroes  = {}", this.heroes);

        for (Dict<String> eachCity : objectData.getValues_Dict_Arrays("CITIES"))
        {
            logger.log(Level.DEBUG, "----(из User) eachCity  = {}", eachCity);

            if(!eachCity.isEmpty()) this.cities.add(LoadedCities.getValue(eachCity.getValue()));
        }

        logger.log(Level.DEBUG, "----(из User) this.cities  = {}", this.cities);

        logger.log(Level.DEBUG, "----(из User) objectData.getValues_Dict_Arrays(\"BUILDINGSonPOLES\").isEmpty() = {}", objectData.getValues_Dict_Arrays("BUILDINGSonPOLES").isEmpty());
        logger.log(Level.DEBUG, "----(из User) objectData.getValues_Dict_Arrays(\"BUILDINGSonPOLES\") = {}", objectData.getValues_Dict_Arrays("BUILDINGSonPOLES"));

        for (Dict<String> eachPoleBuilding : objectData.getValues_Dict_Arrays("BUILDINGSonPOLES"))
        {
            logger.log(Level.DEBUG, "----(из User) eachPoleBuilding  = {}", eachPoleBuilding);

            if(!eachPoleBuilding.isEmpty()) this.buildings_on_poles.add(LoadedPoleBuildings.getValue(eachPoleBuilding.getValue()));
        }

        logger.log(Level.DEBUG, "----(из User) this.buildings_on_poles  = {}", this.buildings_on_poles);

        this.team = objectData.getValue("TEAM");
        logger.log(Level.DEBUG, "----(из User) team  = {}", this.team);
        //Потом сделать
//#        for eachy in data_WO_addData['MAP']:
//#            if eachy == 'MAP_LAYER_FLOOR':
    }
    
    public String showName()
    {
        return this.name;
    }
    public String getTeam()
    {
        return this.team;
    }
    //  def registerNewSteppingUnit(self, NewSteppingUnit : SteppingUnit) -> None:
    //    pass

    public ArrayList<Hero> returnAllHeroes()
    {
        logger.log(Level.DEBUG, "----(из User) Возвращены герои = {}", this.heroes);
        return this.heroes;
    }

    public ArrayList<City> returnAllCities()
    {
        logger.log(Level.DEBUG, "----(из User) Возвращены города = {}", this.cities);
        return this.cities;
    }
    
    public ArrayList<PoleBuilding> returnAllPoleBuildings()
    {
        logger.log(Level.DEBUG, "----(из User) Возвращены здания на поле = {}", this.buildings_on_poles);
        return this.buildings_on_poles;
    }
    
    public boolean isUserStepping()
    {
        return this.isstepping;
    }
    
    public String getUserName()
    {
        return this.name;
    }
    
    public String getUserColor()
    {
        return this.color;
    }

    public boolean isPlayer() {return useractions.isPlayer();}

    @Override
    public String toString()
    {
        return "User{" +
                "heroes=" + heroes +
                ", cities=" + cities +
                ", buildings_on_poles=" + buildings_on_poles +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", team='" + team + '\'' +
                ", useractions=" + useractions +
                ", isstepping=" + isstepping +
                ", canwinlose=" + canwinlose +
                '}';
    }
}
