/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.FileLoaders;

import com.Util_Classes.Dict;


import java.util.*;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class MissionLoader extends IStandAloneFilesLoader
{
    private Dict<String> Transfomed_FullLoadedFile = new Dict<>();
    
    private static final Logger logger = LogManager.getLogger(MissionLoader.class.getName());
    
    public MissionLoader(String Path, String TypeOfLoadingFile, String FileExtension)
    {
        super(Path, TypeOfLoadingFile, FileExtension);
    }
    
    public MissionLoader(ArrayList<Dict<String>> FileWithNestedFiles, String TypeOfLoadingFile)
    {
        super(FileWithNestedFiles, TypeOfLoadingFile);
    }
    
    //-----------------------------------------------------------------------------------
    //Переопределим функцию returnloadedfile для загрузки файлов, что автоматически возвращался трансформированный файл
    @Override
    public Dict<String> returnloadedfile()
    {
        logger.log(Level.DEBUG, "Файл this.Transfomed_FullLoadedFile был возвращен. Значения {}", this.Transfomed_FullLoadedFile);
        return this.Transfomed_FullLoadedFile;
    }

    //-----------------------------------------------------------------------------------
    //Переопределим функции для загрузки файлов, что автоматически делалась трансформация
    @Override
    public void loadfullfilefull(String counteroffiles)
    {
        logger.log(Level.DEBUG, "Запущена функция loadfullfilefull в MissionLoader. this.Transfomed_FullLoadedFile = {}", this.Transfomed_FullLoadedFile);
        logger.log(Level.DEBUG, "counteroffiles в MissionLoader = {}", counteroffiles);
        super.loadfullfilefull(counteroffiles);
        logger.log(Level.DEBUG, "Завершилось выполнение super().loadfullfilefull(counteroffiles) в MissionLoader. this.FullLoadedFile = {}", this.FullLoadedFile);
        //если файл загружен, то его надо модифицировать
        if (this.isFileLoaded())
        {
            this.transform_File_Into_eatable_Format();
        }
    }

    @Override
    public void loadfullfilefinished(String counteroffiles)
    {
        logger.log(Level.DEBUG, "Запущена функция loadfullfilefinished в MissionLoader. this.Transfomed_FullLoadedFile = {}", this.Transfomed_FullLoadedFile);
        logger.log(Level.DEBUG, "counteroffiles в MissionLoader = {}", counteroffiles);
        super.loadfullfilefinished(counteroffiles);
        logger.log(Level.DEBUG, "Завершилось выполнение super().loadfullfilefinished(counteroffiles) в MissionLoader. this.FullLoadedFile = {}", this.FullLoadedFile);
        //если файл загружен, то его надо модифицировать
        if (this.isFileLoaded())
        {
            this.transform_File_Into_eatable_Format();
        }
    }
    
    @Override
    public void loadfullfilenotfinished(String counteroffiles)
    {
        logger.log(Level.DEBUG, "Запущена функция loadfullfilenotfinished в MissionLoader. this.Transfomed_FullLoadedFile = {}", this.Transfomed_FullLoadedFile);
        logger.log(Level.DEBUG, "counteroffiles в MissionLoader = {}", counteroffiles);
        super.loadfullfilenotfinished(counteroffiles);
        logger.log(Level.DEBUG, "Завершилось выполнение super().loadfullfilenotfinished(counteroffiles) в MissionLoader. this.FullLoadedFile = {}", this.FullLoadedFile);
        //если файл загружен, то его надо модифицировать
        if (this.isFileLoaded())
        {
            this.transform_File_Into_eatable_Format();
        }
    }
    
    //-----------------------------------------------------------------------------------

    private void transform_File_Into_eatable_Format()
    {
        this.Transfomed_FullLoadedFile = super.returnloadedfile().clone();
        logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) (должно быть пусто) File = ", this.FullLoadedFile.toString());
        logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) this.Transfomed_FullLoadedFile = ", this.Transfomed_FullLoadedFile.getLinkHashMap());

        //--------------------------------------------------------------------------------------
        //0. Получим данные из MAP - DESIGN, ADD_DATA
        //--------------------------------------------------------------------------------------

        //1. получим данные из блока MAP: ADD_DATA, DESIGN
        Dict<String> ADD_DATA = this.Transfomed_FullLoadedFile.getValues_Link("MAP", "ADD_DATA");
        logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) Получено значение из this.Transfomed_FullLoadedFile['MAP']['ADD_DATA'] ADD_DATA = {}", ADD_DATA);

        Dict<String> DESIGN = this.Transfomed_FullLoadedFile.getValues_Link("MAP", "DESIGN");
        logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) Получено значение из this.Transfomed_FullLoadedFile['MAP']['DESIGN'] DESIGN = {}", DESIGN);

        //2. получим цвета пользователя и присвоим его к его объектам (герою, городу, зданиям)
        Dict<String> objects_colors = new Dict<>();

        for (Dict<String> each_user : this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("USERS"))
        {
            logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) each_user = {}", each_user);
            String user_color = each_user.getValue("COLOR");
            for (Dict<String> each_hero : each_user.getValues_Dict_Arrays("HEROES"))
            {
                String name_hero = each_hero.getValue();
                objects_colors.saveValue(new String[]{name_hero}, user_color);
            }
            for (Dict<String> each_city : each_user.getValues_Dict_Arrays("CITIES"))
            {
                String name_city = each_city.getValue();
                objects_colors.saveValue(new String[]{name_city}, user_color);
            }
            for (Dict<String> each_build : each_user.getValues_Dict_Arrays("BUILDINGSonPOLES"))
            {
                String name_build = each_build.getValue();
                objects_colors.saveValue(new String[]{name_build}, user_color);
            }
        }

        logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) Получено значение цветов героя из this.Transfomed_FullLoadedFile['USERS'] = {}", objects_colors);

        //--------------------------------------------------------------------------------------
        //2. В CITIES переместить данные MAP и вытащить данные по дизайну и т.д. (Если надо из юзеров)
        // также удалить блок MAP в CITIES
        //--------------------------------------------------------------------------------------
        Dict<String> CITY_MAP_DATA = this.Transfomed_FullLoadedFile.getValues_Link("CITIES", "MAP").clone();
        this.Transfomed_FullLoadedFile.deleteValue("CITIES", "MAP");

        //возьмем из CITY_MAP_DATA данные ADD_DATA (описание городов для карты);
        //получим из ADD_DATA что означает эта инфа
        //через ключи вставим в CITY_MAP_DATA
        CITY_MAP_DATA.saveValue(ADD_DATA.getLinkHashMap(CITY_MAP_DATA.getValue("ADD_DATA"))); //замена на ADD_DATA
        CITY_MAP_DATA.deleteValue("ADD_DATA"); //удалить ADD_DATA
        CITY_MAP_DATA.saveValue(DESIGN.getLinkHashMap(CITY_MAP_DATA.getValue("DESIGN")));
        CITY_MAP_DATA.deleteValue("DESIGN"); //удалить DESIGN

        for (Dict<String> each_city : this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("CITIES", "CITIES"))
        {
            Dict<String> _CITY_MAP_DATA = CITY_MAP_DATA.clone();

            if (_CITY_MAP_DATA.getValue("SYMBOL_COLOR").equals("USER_COLORS"))
            {
                _CITY_MAP_DATA.saveValue(new String[]{"SYMBOL_COLOR"}, objects_colors.getValue(each_city.getValue("NAME")));
            }

            if (_CITY_MAP_DATA.getValue("COLOR_BACK").equals("USER_COLORS"))
            {
                _CITY_MAP_DATA.saveValue(new String[]{"COLOR_BACK"}, objects_colors.getValue(each_city.getValue("NAME")));
            }

            each_city.saveValue(new String[]{"MAP"}, _CITY_MAP_DATA.getLinkHashMap());
            logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) Была добавлено значение _CITY_MAP_DATA в переменную each['MAP']. each['MAP'] стал = {}", each_city.getValue("MAP"));
        }

        logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) После перемещения инфы карты в города this.Transfomed_FullLoadedFile стало = {}", this.Transfomed_FullLoadedFile);

        //--------------------------------------------------------------------------------------
        //3. В CITIES переместить данные городов, которые для выбора мест
        //--------------------------------------------------------------------------------------
        for (Dict<String> each_cityplace : this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("MAP", "CITY_PLACES"))
        {
            if(!each_cityplace.isEmpty())
            {
                each_cityplace.saveValue(new String[]{"MAP"}, ADD_DATA.getLinkHashMap(each_cityplace.getValue("MAP", "ADD_DATA"))); //замена на ADD_DATA
                each_cityplace.deleteValue("MAP", "ADD_DATA"); //удалить ADD_DATA
                each_cityplace.saveValue(new String[]{"MAP"}, DESIGN.getLinkHashMap(each_cityplace.getValue("MAP", "DESIGN"))); //замена на DESIGN
                each_cityplace.deleteValue("MAP", "DESIGN"); //удалить DESIGN

                logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) Были добавлены значения в города. Город стал = {}", each_cityplace);
            }
            else
            {
                logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) Нет городов для выбора!");
            }
        }
        //--------------------------------------------------------------------------------------
        //4. Вставить значения мест городов в города
        //--------------------------------------------------------------------------------------
        for (Dict<String> each_cityplace : this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("MAP", "CITY_PLACES"))
        {
            if(!each_cityplace.isEmpty())
            {
                this.Transfomed_FullLoadedFile.saveValue(new String[]{"CITIES", "CITIES"}, each_cityplace);
                //-----------------надо вставить город первому юзеру ------------------------
                this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("USERS").get(0).getValues_Dict_Arrays("CITIES").add(new Dict<>(each_cityplace.getValue("NAME")));
                //-----------------надо вставить город первому юзеру------------------------
            }
            else
            {
                logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) Нет городов для выбора!");
            }
        }

        logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) После добавления мест города в города. this.Transfomed_FullLoadedFile стало = {}", this.Transfomed_FullLoadedFile);

        //--------------------------------------------------------------------------------------
        //4.1. Удалить города для выбора
        //--------------------------------------------------------------------------------------
        this.Transfomed_FullLoadedFile.deleteValue("MAP", "CITY_PLACES");
        logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) После удаления мест городов из MAP. this.Transfomed_FullLoadedFile стало = {}", this.Transfomed_FullLoadedFile);

        //--------------------------------------------------------------------------------------
        //5. Аналогично HEROES
        //--------------------------------------------------------------------------------------
        Dict<String> HERO_MAP_DATA = this.Transfomed_FullLoadedFile.getValues_Link("HEROES", "MAP").clone();
        this.Transfomed_FullLoadedFile.deleteValue("HEROES", "MAP");

        //возьмем из HERO_MAP_DATA данные ADD_DATA (описание героев для карты);
        //получим из ADD_DATA что означает эта инфа
        //через ключи вставим в HERO_MAP_DATA
        HERO_MAP_DATA.saveValue(new LinkedList<String>(), ADD_DATA.getLinkHashMap(HERO_MAP_DATA.getValue("ADD_DATA")));
        HERO_MAP_DATA.deleteValue("ADD_DATA");
        HERO_MAP_DATA.saveValue(new LinkedList<String>(), DESIGN.getLinkHashMap(HERO_MAP_DATA.getValue("DESIGN")));
        HERO_MAP_DATA.deleteValue("DESIGN");

        for (Dict<String> each_hero : this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("HEROES", "HEROES"))
        {
            Dict<String> _HERO_MAP_DATA = HERO_MAP_DATA.clone();

            if (_HERO_MAP_DATA.getValue("SYMBOL_COLOR").equals("USER_COLORS"))
            {
                _HERO_MAP_DATA.saveValue(new String[]{"SYMBOL_COLOR"}, objects_colors.getValue(each_hero.getValue("NAME")));
            }

            if (_HERO_MAP_DATA.getValue("COLOR_BACK").equals("USER_COLORS"))
            {
                _HERO_MAP_DATA.saveValue(new String[]{"COLOR_BACK"}, objects_colors.getValue(each_hero.getValue("NAME")));
            }

            each_hero.saveValue(new String[]{"MAP"}, _HERO_MAP_DATA.getLinkHashMap());
            logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) Была добавлено значение _HERO_MAP_DATA в переменную each['MAP']. each['MAP'] стал = {}", each_hero.getValue("MAP"));
        }

        //--------------------------------------------------------------------------------------
        //6. Аналогично MAP_FLOOR
        //--------------------------------------------------------------------------------------
        for (Dict<String> each_pole : this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("MAP", "MAP_LAYER_FLOOR"))
        {
            each_pole.saveValue(new LinkedList<String>(), ADD_DATA.getLinkHashMap(each_pole.getValue("ADD_DATA")));
            each_pole.deleteValue("ADD_DATA");
            each_pole.saveValue(new LinkedList<String>(), DESIGN.getLinkHashMap(each_pole.getValue("DESIGN")));
            each_pole.deleteValue("DESIGN");

            logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) Были добавлены значения во FLOOR. Поле стало = {}", each_pole);
        }

        //--------------------------------------------------------------------------------------
        //7. Здания на поле
        //--------------------------------------------------------------------------------------
        for (Dict<String> each_pole_building : this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("BUILDINGS_ON_POLES"))
        {
            each_pole_building.saveValue(new String[]{"MAP"},ADD_DATA.getLinkHashMap(each_pole_building.getValue("MAP", "ADD_DATA"))); //замена на ADD_DATA
            each_pole_building.deleteValue("MAP", "ADD_DATA"); //удалить ADD_DATA

            each_pole_building.saveValue(new String[]{"MAP","SYMBOL"}, DESIGN.getValue(each_pole_building.getValue("MAP", "DESIGN"), "SYMBOL"));

            if (DESIGN.getValue(each_pole_building.getValue("MAP", "DESIGN"), "SYMBOL_COLOR").equals("USER_COLORS"))
            {
                each_pole_building.saveValue(new String[]{"MAP","SYMBOL_COLOR"}, objects_colors.getValue(each_pole_building.getValue("NAME")));
            }
            else
            {
                each_pole_building.saveValue(new String[]{"MAP","SYMBOL_COLOR"}, DESIGN.getValue(each_pole_building.getValue("MAP", "DESIGN"), "SYMBOL_COLOR"));
            }

            if (DESIGN.getValue(each_pole_building.getValue("MAP", "DESIGN"), "COLOR_BACK").equals("USER_COLORS"))
            {
                each_pole_building.saveValue(new String[]{"MAP","COLOR_BACK"}, objects_colors.getValue(each_pole_building.getValue("NAME")));
            }
            else
            {
                each_pole_building.saveValue(new String[]{"MAP","COLOR_BACK"}, DESIGN.getValue(each_pole_building.getValue("MAP", "DESIGN"), "COLOR_BACK"));
            }

            each_pole_building.deleteValue("MAP", "DESIGN"); //удалить DESIGN

            logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) Были добавлены значения во BUILDINGS. Здание стало (each_pole_building['MAP']) = {}", each_pole_building.getValue("MAP"));
        }

        logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) this.Transfomed_FullLoadedFile стал = {}", this.Transfomed_FullLoadedFile);

        //--------------------------------------------------------------------------------------
        //6. Удалить значения CITY, HEROES, которые с N/A, так как они не определены, значит отображаться не будут (по полям X, Y, Цвет юзера)
        //--------------------------------------------------------------------------------------

        //---------------------------CITIES----------------------------------
        logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) //6.1 this.Transfomed_FullLoadedFile['CITIES']['CITIES'] = {}", this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("CITIES", "CITIES"));

        ArrayList<Integer> indexes_of_cities_to_delete = new ArrayList<>();

        for (Dict<String> each_city : this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("CITIES", "CITIES"))
        {
            logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) //6.1 Анализ города со значениями координат N/A = {}", each_city);
            if (each_city.getValue("MAP", "X").equals("N/A") && each_city.getValue("MAP", "Y").equals("N/A"))
            {
                indexes_of_cities_to_delete.add(this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("CITIES", "CITIES").indexOf(each_city));
                logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) //6.1 Для удаления в indexes_of_cities_to_delete был добавлен город со значениями координат N/A = {}", indexes_of_cities_to_delete);
                logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) //6.1 Город со значениями координат N/A = {}", each_city);

                //-----------------надо удалить город у юзера ------------------------
                for (Dict<String> eachUser : this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("USERS"))
                {
                    logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) // Проверка USER = {}", eachUser);

                    for (Dict<String> eachCityInUser : eachUser.getValues_Dict_Arrays("CITIES"))
                    {
                        if(eachCityInUser.getStatus() != Dict.DICT_PARTS.EMPTY)
                        {
                            if (eachCityInUser.getValue().equals(each_city.getValue("NAME")))
                            {
                                logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) // Найдено равенство городов each_city.getValue(\"NAME\") = {}", each_city.getValue("NAME"));
                                logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) // Найдено равенство городов eachCityInUser.getValue() = {}", eachCityInUser.getValue());

                                String cityName = each_city.getValue("NAME");
                                eachUser.getValues_Dict_Arrays("CITIES").removeIf(usercity -> usercity.getValue().equals(cityName));

                                logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) // После удаления города eachUser.getValues_Dict_Arrays(\"CITIES\") стало = {}", eachUser.getValues_Dict_Arrays("CITIES"));
                                break;
                            }
                        }
                    }
                }
                //-----------------надо удалить город у юзера ------------------------
            }
        }

        for (int index=indexes_of_cities_to_delete.size()- 1; index>=0; index--)
        {
            logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) //6.1 Был удален город со значениями координат N/A = {}", this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("CITIES", "CITIES").get(index));
            this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("CITIES", "CITIES").remove(index);
        }
        logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) //6.1 this.Transfomed_FullLoadedFile после удаления города стал = {}", this.Transfomed_FullLoadedFile);

        //---------------------------HEROES----------------------------------
        logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) //6.2 this.Transfomed_FullLoadedFile['HEROES']['HEROES'] = {}", this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("HEROES", "HEROES"));

        ArrayList<Integer> indexes_of_heroes_to_delete = new ArrayList<>();

        for (Dict<String> each_hero : this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("HEROES", "HEROES"))
        {
            logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) //6.2 Анализ героя со значениями координат N/A = {}", each_hero);
            if (each_hero.getValue("MAP", "X").equals("N/A") && each_hero.getValue("MAP", "Y").equals("N/A"))
            {
                indexes_of_heroes_to_delete.add(this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("HEROES", "HEROES").indexOf(each_hero));
                logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) //6.2 Для удаления в indexes_of_heroes_to_delete был добавлен герой со значениями координат N/A = {}", indexes_of_heroes_to_delete);
                logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) //6.2 Герой со значениями координат N/A = {}", each_hero);

                //-----------------надо удалить героя у юзера ------------------------
                for (Dict<String> eachUser : this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("USERS"))
                {
                    for (Dict<String> eachHeroInUser : eachUser.getValues_Dict_Arrays("HEROES"))
                    {
                        if(eachHeroInUser.getStatus() != Dict.DICT_PARTS.EMPTY)
                        {
                            if (eachHeroInUser.getValue().equals(each_hero.getValue(new LinkedList<String>(Arrays.asList("NAME")))))
                            {
                                eachUser.getValues_Dict_Arrays("HEROES").remove(each_hero);
                                //-----------------надо удалить героя у юзера ------------------------
                            }
                        }
                    }
                }
            }
        }

        for (int index=indexes_of_heroes_to_delete.size()- 1; index>=0; index--)
        {
            logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) //6.2 Был удален герой со значениями координат N/A = {}", this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("HEROES", "HEROES").get(index));
            this.Transfomed_FullLoadedFile.getValues_Dict_Arrays("HEROES", "HEROES").remove(index);
        }
        logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) //6.2 this.Transfomed_FullLoadedFile после удаления героя стал = {}", this.Transfomed_FullLoadedFile);

        //--------------------------------------------------------------------------------------
        logger.log(Level.DEBUG, "(из MissionLoader/transform_File_Into_eatable_Format) Файл был трансформирован. Стало = {}", this.Transfomed_FullLoadedFile);
    }
    
    @Override
    public void injectData(Dict<String> data)
    {
        //1. Код номера города должен определять координату героев, если они есть
        //2. Удалить возможные пункты для стройки городов
        //3. Вставить сделанные выборы в карту (в герои, города, отношения)
        //4.
    }

    @Override
    public boolean checkfile_low(Dict<String> data)
    {
        boolean result = true;

        try
        {
        //--------------------------------------------------------------------------------------
        //1 Контроль полей в файле согласно иерархии
        //--------------------------------------------------------------------------------------
            logger.log(Level.DEBUG, "(из MissionLoader/checkfile_low) data = {}", data);     
            //--------------------------------------------------------------------------------------
            //1.1 Контроль полей 1-го уровня в файле согласно иерархии 1-го уровня
            //--------------------------------------------------------------------------------------
            if (!this.isContainReqKeys(data, Arrays.asList("ID", "NAME", "ISFINISHED", 
                    "TYPE_OF_FILE", "DEMOSCENE", "CAN_CHOOSE_USERS", "USERS", "HEROES", 
                    "CITIES", "TEAM_RELATIONS", "WIN_CONDITION", "MAP", "WARRIORS", "MERCH",
                    "SKILLS", "HERO_CLASSES", "HERO_RACES", "BUILDINGS_ON_POLES", "CITY_BUILDINGS", "CITY_RACES"))) result = false;
            
            logger.log(Level.DEBUG, "(из MissionLoader/checkfile_low) data после this.isContainReqKeys = {}", data);     
            //--------------------------------------------------------------------------------------
            //1.2.1 Контроль соответствующих полей 2-го уровня в блоках: DEMOSCENE
            //--------------------------------------------------------------------------------------             
            logger.log(Level.DEBUG, "(из MissionLoader/checkfile_low) data.getValues_Link(Arrays.asList(\"DEMOSCENE\") = {}", data.getValues_Link("DEMOSCENE"));
            if (!this.isContainReqKeys(data.getValues_Link("DEMOSCENE"), Arrays.asList("HAVE","DATA")))  result = false;
            
            //--------------------------------------------------------------------------------------
            //1.2.1.1 Контроль соответствующих полей 3-го уровня в блоках: DEMOSCENE.DATA
            //--------------------------------------------------------------------------------------    
            for (Dict<String> each_element : data.getValues_Dict_Arrays("DEMOSCENE", "DATA"))
            {
                if (!this.isContainReqKeys(each_element, Arrays.asList("WIDTH","TEXT")))  result = false;   
            }

            //--------------------------------------------------------------------------------------
            //1.2.1.1.1 Контроль соответствующих полей 4-го уровня в блоках: DEMOSCENE.DATA.TEXT
            //--------------------------------------------------------------------------------------    
            for (Dict<String> each_element : data.getValues_Dict_Arrays("DEMOSCENE", "DATA"))
            {
                for (Dict<String> each_element_in_text : each_element.getValues_Dict_Arrays("TEXT"))
                {
                    if (!this.isContainReqKeys(each_element_in_text, Arrays.asList("TEXT","COLOR_TEXT", "COLOR_BACK")))  result = false;
                }                  
            }            

            //--------------------------------------------------------------------------------------
            //1.2.2 Контроль соответствующих полей 2-го уровня в блоках: USER
            //--------------------------------------------------------------------------------------
            //--------------------------------------------------------------------------------------
            //1.2.3 Контроль соответствующих полей 2-го уровня в блоках: HEROES
            //--------------------------------------------------------------------------------------
            //--------------------------------------------------------------------------------------
            //1.2.4 Контроль соответствующих полей 2-го уровня в блоках: CITIES
            //--------------------------------------------------------------------------------------
            //--------------------------------------------------------------------------------------
            //1.2.5 Контроль соответствующих полей 2-го уровня в блоках: TEAM_RELATIONS
            //--------------------------------------------------------------------------------------
            //--------------------------------------------------------------------------------------
            //1.2.6 Контроль соответствующих полей 2-го уровня в блоках: WIN_CONDITION
            //--------------------------------------------------------------------------------------            
            //--------------------------------------------------------------------------------------
            //1.2.7 Контроль соответствующих полей 2-го уровня в блоках: MAP
            //--------------------------------------------------------------------------------------
            //-------------------CITY_PLACES удалить проверку для карт с уже предопределённым выбором--------------
            if (!this.isContainReqKeys(data.getValues_Link("MAP"), Arrays.asList("WIDTH",
                    "HEIGHT", "DESIGN", "ADD_DATA", "MAP_LAYER_FLOOR", "CITY_PLACES", "TEAMS")))  result = false;
            //--------------------------------------------------------------------------------------
            //1.2.8 Контроль соответствующих полей 2-го уровня в блоках: WARRIORS
            //--------------------------------------------------------------------------------------    
            //--------------------------------------------------------------------------------------
            //1.2.9 Контроль соответствующих полей 2-го уровня в блоках: SKILLS
            //--------------------------------------------------------------------------------------    
            //--------------------------------------------------------------------------------------
            //1.2.10 Контроль соответствующих полей 2-го уровня в блоках: HERO_CLASSES
            //--------------------------------------------------------------------------------------    
            //--------------------------------------------------------------------------------------
            //1.2.11 Контроль соответствующих полей 2-го уровня в блоках: HERO_RACES
            //--------------------------------------------------------------------------------------    
            //--------------------------------------------------------------------------------------
            //1.2.12 Контроль соответствующих полей 2-го уровня в блоках: BUILDINGS_ON_POLES
            //--------------------------------------------------------------------------------------   
            //--------------------------------------------------------------------------------------
            //1.2.13 Контроль соответствующих полей 2-го уровня в блоках: CITY_BUILDINGS
            //--------------------------------------------------------------------------------------    
            //--------------------------------------------------------------------------------------
            //1.2.14 Контроль соответствующих полей 2-го уровня в блоках: CITY_RACES
            //--------------------------------------------------------------------------------------    

        //--------------------------------------------------------------------------------------
        //2 Логические контроли между блоками файла
        //--------------------------------------------------------------------------------------

        //--------------------------------------------------------------------------------------
        //2.1 При наличии крыжика "CAN_CHOOSE_USERS" при "YES" должны быть города (с определенными полями) в MAP-CITY_PLACES); при "NO" это же поле должно быть пустым.
        //--------------------------------------------------------------------------------------

//        if data["CAN_CHOOSE_USERS"] == "YES":
//            pass
//        elif data["CAN_CHOOSE_USERS"] == "NO":
//            pass
//            //        for each in data['MAP']['MAP_LAYER_FLOOR']:
////            if each['X'] == counter_of_x and each['Y'] == counter_of_y:
////                logger.log(Level.DEBUG, "Проверка пройдена, ячейка имеет требуемые координаты = ['X'] = ", each['X'])
////                logger.log(Level.DEBUG, "Проверка пройдена, ячейка имеет требуемые координаты = ['Y'] = ", each['Y'])
////            else:
////                Log.error("Проверка НЕ пройдена, ячейка имеет требуемые координаты = ['X'] = {}", each['X'])
////                Log.error("Проверка НЕ пройдена, ячейка имеет требуемые координаты = ['Y'] = {}", each['Y'])
////                Log.error("Проверка НЕ пройдена, ячейка ДОЛЖНА ИМЕТЬ координату Х = {}", counter_of_x)
////                Log.error("Проверка НЕ пройдена, ячейка ДОЛЖНА ИМЕТЬ координату Y = {}", counter_of_y)
////            if counter_of_y == MAP_WEIGHT:
////                counter_of_y = 1
////                counter_of_x = counter_of_x + 1
////            else:
////                counter_of_y = counter_of_y + 1
//        else:
//            Log.error("//2.1 Обнаружено некорректное значение поля CAN_CHOOSE_USERS = {}", data["CAN_CHOOSE_USERS"])
//            result = False



        //1. В карте поля должны быть по порядке {X1, все Y}, {X2, все Y} и т.д. Х - это вниз; Y - вправо (отсчет с верхнего левого угля от единиц)
        //1.1 Описание поля должно быть уникальным для одного поля
//        MAP_HEIGHT = int(data['MAP']['HEIGHT']) //X
//        MAP_WEIGHT = int(data['MAP']['WEIGHT']) //Y

//        counter_of_y : int = 1
//        counter_of_x : int = 1
//
//        for each in data['MAP']['MAP_LAYER_FLOOR']:
//            if each['X'] == counter_of_x and each['Y'] == counter_of_y:
//                logger.log(Level.DEBUG, "Проверка пройдена, ячейка имеет требуемые координаты = ['X'] = ", each['X'])
//                logger.log(Level.DEBUG, "Проверка пройдена, ячейка имеет требуемые координаты = ['Y'] = ", each['Y'])
//            else:
//                Log.error("Проверка НЕ пройдена, ячейка имеет требуемые координаты = ['X'] = {}", each['X'])
//                Log.error("Проверка НЕ пройдена, ячейка имеет требуемые координаты = ['Y'] = {}", each['Y'])
//                Log.error("Проверка НЕ пройдена, ячейка ДОЛЖНА ИМЕТЬ координату Х = {}", counter_of_x)
//                Log.error("Проверка НЕ пройдена, ячейка ДОЛЖНА ИМЕТЬ координату Y = {}", counter_of_y)
//            if counter_of_y == MAP_WEIGHT:
//                counter_of_y = 1
//                counter_of_x = counter_of_x + 1
//            else:
//                counter_of_y = counter_of_y + 1
//
//
//
//

        //Проверка 1. Если файл содержит CAN_CHOOSE_USERS = YES, то в MAP - CITY_PLACES должно быть не меньше городов, чем описано в пункте CITIES - CITIES; в городах CITIES - CITIES - MAP - X,Y должна стоять координата X,Y = N/A
            //Также у героев не должно стоять расы и координаты = N/A

        //Проверка 2. Количество и наименование рас у героя и городов должно быть одинаковым (для карт с выбором?)

        //Проверка 3. У юзеров если CAN_CHOOSE_USERS = YES должны быть поля ISPLAYER, COLOR = N/A, иначе значения из справочников (PLAYER, COMPUTER) / (7 штук)

        //Проверка 4. У юзера поле ISSTEPPING и CANWINLOSE должны иметь одно значение
        //Проверка 5. У юзеров независимо от полей должны быть проставлены все города и герои, бесхозных не должно быть.
        //Проверка 6. юзер отношения тоже должны быть пустые для невозможности выбирать, иначе должны быть нормальные значения

        //Проверка на отсутствие "" у числовых значений - у характеристик (HEALTH)
        //доделать:
        //Проверка должна проводиться так:
        //-4. Перечень рас для городов и героев должны быть одинаковыми (наименования совпадать), а также не должны быть нулевыми!!! хотя бы один
        //-3. При выборе расы для города, если есть герои, то им присваивается такая же раса как и у города. То есть на начало загрузки значение равно N/A
        //-2. Если у миссии стоит CAN_CHOOSE = NO, значит должна стоять координата в городах, указанных у юзеров (всегда должны быть указаны все города) и CITY_PLACES в MAP не должны быть заполнены; иначе должны быть заполнены CITY_PLACES в MAP, а координаты нет.
        //-2.1. Количество городов должно быть не более чем указано в CITIES
        //-1. Перечень цветов должен быть не меньше числа юзеров; из определенного перечня (наверно, корректноее сказать, что все используемы ев файле цвета должны быть строго из одного перечня)
        //0. Имена Юзеров должны быть уникальны
        //1. Проверка отношений если у одного юзера с другим стоит отношение с определенным значением, то и у другого с этим же должно стоять оно же.
        //2. Наименования уникальных объектов (героев, вещей, городов и т.д.) должны быть уникальными.
        //3. Значения справочников, используемых в объектах (ADD_DATA, DESIGN полей, умения, например), должны быть обязательно в этом же файле.
        //4. У всех объектов Layer1,2,3 должен быть владелец (только один)
        //5. Каждое описание ячейки должно быть уникальным в своей области (FLOOR, Layer2, Layer1)
        //6. Наличие необходимых блоков в описании файла
        //7. В MAP_LAYER_FLOOR не должно быть иных типов полей кроме 3-го уровня (Типы обработчиков ROAD, STOP только могут быть в FLOOR, иных нет!)
        //8. По умолчанию считается, что владелец полей 3-го уровня USER-NPC. Соответственно, должен быть только 1 юзер NPC.
        //9. По карте FLOOR должны быть все ячейки покрыты; без дубляжей. Для других Layer 2, 3: Не должно быть одного уровня ячейки.
        //10. Должен быть хотя бы один USERNPC.
        //11. У USER часть MAP всегда должна быть пустой (кроме USERNPC)
        //12. Объекты карты не должны перекрывать друг друга.
        //13. Для миссий (есть выбор) нужно проверить координаты равны N/A для героя (в файле), для города загружаются из GV; у юзера не должно быть героев
        //14. Для миссий нужно проверить у игроков наличие только или 0 или одного города!
        //15. Для миссий не должно быть воинов в городе (в файле)
        //16. POSSIBLE_PLACES_FOR_CITIES: для миссий должны быть заполнены; для определенных пусто (в части MAP)
        //17. Проверка на координаты мест городов: область "CITY_PLACES" (MAP): для свободного выбора должны быть заполнены; координаты не равны друг другу и не наезжать друг друга с учетом SIZE городов. Для заранее определённого сценария: область должны быть пустой.
        //18. В перечне отношений не должно быть нейтрального героя (в сражениях).
        //19. Проверка: размер карты должен быть равен количеству указанных полей
        //20. Вещи должны строго надеваться на одну или две части тел, но  рамках справочника частей тела.
        //21. По построенным зданиям - нужно чтобы они были в рамках выбранной расы как и умения у героя.
        //22. Проверить, что город, герой встречается у юзера только один раз. В тоже время в таверне может быть несколько.
        //23. Количество символов в заставке в блоке должно сопадать с описанным количеством символов
        //24. Возможно нужно, чтобы был хоть один герой или один город в миссии
        //25. Построенные здания должно обязатлеьно попадать в список разрешенных для этой расы, класса города.
        //26. В файле должны все обязательные блоки.
        //27. У USER-NPC должен быть в отношениях со всеми игроками NPC
        //28. При прописывании отношений между юзерами нельзя, чтоб был тот же юзер например описываются отношения юзера USER-1 к USER-2, тогда в этом блоке не могут быть описан USER-1
        //29. Сумма вероятностей должна быть равна 100% при описании в поле отряда
        //30. Каждый блок из указанных размеров карты должен быть прописан.
        //31. В карте MAP_LAYER_FLOOR TYPE_OF_HANDLER только из определенных констант
        //33. Описание и структура MAP должна быть уникальной
        //34. Цвета, указываемые в ['MAP']['DESIGN'], должны быть для FLOOR уровня не равны быть USER_COLORS, но должны быть равны одной из констант цветов: SYMBOL_COLOR = COLOR_BACK; аналогично для значений проставленных для мест городов
        //35. в MAP, DESIGN, ADD_DATA - уникальные значения.
        //36. TYPEOFHANDLER должны быть по константам: STOP, ...
        //37. В MAP - DESIGN должны быть три значения "SYMBOL_COLOR", "COLOR_BACK", "SYMBOL"
        //38. Наименования объектов должны быть уникальны HEROES, CITIES, BUILDINGonPOLES
        //39. Не должно быть USER_COLORS в MAP_FLOOR
        //40. Поле символ должно состоять только из 1 символа
        //41. Дизайн у двух городов на выбор должны различаться
        //42. Классы и расы должны быть строго по одному у каждого объекта.
        //43. У одного объекта лишь один владелец
        //44. ISPLAYER' содержит значения PLAYER и COMPUTER только!
        //45. ISSTEPPING, CANWINLOSE содержит значения лишь YES, NO
        //46. CAN_CHOOSE_ = YES => несколько рас на выбор, не должно быть пусто.
        //47. При возможности выбора - расы у городов и героев должны быть идентичны.
        //48. Если CAN_CHOOSE = YES, X,Y, ISPLAYING, RACE, ISPLAYER, TEAM = N/A (только у NPC должно стоять значение)
        //49. Уникальность имен всех объектов среди героев и городов и зданий на полях, а не среди своих групп.
        //50. Числа не больше Integer 2 млрд.
        //51. Должно быть не меньше двух команд
        //52. Команды TEAM-NPC для карт с возможностью выбора условий не должно быть в поле MAP -> TEAMS (это поле должно быть пустым для карт с предустановленными условиями)
        //53. Значения NULL опасны: вставить проверку на NULL, а также на значение "NULL" или другие отсутствующие значения, но указанные в файле.
        //54. NPC должен писаться с большими буквами

            return result;
        }
        catch(NoSuchElementException exception) //| CloneNotSupportedException
        {
            logger.log(Level.DEBUG, "(из MissionLoader/checkfile_low) Ошибка при получении значения из data = {}", exception.toString());        
            return false;
        }
    }
    
    private boolean isContainReqKeys(Dict<String> data, List<String> requiredkeys)
    {
        boolean result = true;

        //--------------------------------------------------------------------------------------
        //1. Наличие требуемых полей в файле согласно иерархии
        //--------------------------------------------------------------------------------------            
        logger.log(Level.DEBUG, "\"isContainReqKeys\" Поиск в массиве = {}", data);
        
        if (data != null)
        {
            logger.log(Level.DEBUG, "\"isContainReqKeys\"Требуемые значения = {}", requiredkeys);
            Dict<String> deepcopy_data = data.clone();
            logger.log(Level.DEBUG, "\"isContainReqKeys\"deepcopy_data = {}", deepcopy_data);        
            Set<String> KEYS_IN_DATA = deepcopy_data.getLinkHashMap().keySet();
            logger.log(Level.DEBUG, "\"isContainReqKeys\"KEYS_IN_DATA = {}", KEYS_IN_DATA);

            for (String each_req_element : requiredkeys)
            {
                if(KEYS_IN_DATA.contains(each_req_element))
                {
                    logger.log(Level.DEBUG, "\"isContainReqKeys\"// each_req_element = {}", each_req_element);
                    KEYS_IN_DATA.remove(each_req_element);
                    logger.log(Level.DEBUG, "\"isContainReqKeys\"// KEYS_IN_DATA стал= {}", KEYS_IN_DATA);
                }
                else
                {
                    logger.log(Level.ERROR, "\"isContainReqKeys\"// В файле не обнаружено значение {} в иерархии. result = false", each_req_element);
                    result = false;
                }
            }

            //--------------------------------------------------------------------------------------
            //2. Отсутствие лишних полей в иерархии
            //--------------------------------------------------------------------------------------

            if (KEYS_IN_DATA.isEmpty())
            {
                logger.log(Level.DEBUG, "\"isContainReqKeys\"// Лишних полей не обнаружено");
            }
            else
            {
                logger.log(Level.ERROR, "\"isContainReqKeys\"// Обнаружено лишнее поле KEYS_IN_DATA стал = {}", KEYS_IN_DATA);
                result = false;
            }
        }
        else
        {
            logger.log(Level.DEBUG, "\"isContainReqKeys\" data = null");            
            result = false;
        }
        
        return result;
    }
}
