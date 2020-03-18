/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.FileLoaders;

import com.Util_Classes.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import com.google.gson.stream.JsonReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public abstract class IStandAloneFilesLoader 
{   
    String Path = new String();
    String FileExtension = new String();
    
    ArrayList<Dict<String>> FileWithNestedFiles = new ArrayList<>();
    String TypeOfLoadingFile = new String();
    boolean isStandAloneFile;

    protected Dict<String> FullLoadedFile = new Dict<>();

    int filecounterfull = 0;
    ArrayList<String> reqsymbolsfull = new ArrayList();
    ArrayList<String> ListOfFilesFull = new ArrayList();
    Dict<String> DictNumberFileFull = new Dict<>();

    int filecounterfinished = 0;
    ArrayList<String> reqsymbolsfinished = new ArrayList();
    ArrayList<String> ListOfFilesFinished = new ArrayList();
    Dict<String> DictNumberFileFinished = new Dict<>();

    int filecounternotfinished = 0;
    ArrayList<String> reqsymbolsnotfinished = new ArrayList();
    ArrayList<String> ListOfFilesNotFinished = new ArrayList();
    Dict<String> DictNumberFileNotFinished = new Dict<>();

    ArrayList<String> AllIDlist = new ArrayList();
    private static final Logger logger = LogManager.getLogger(IStandAloneFilesLoader.class.getName());

    public IStandAloneFilesLoader(String Path, String TypeOfLoadingFile, String FileExtension)
    {
        //this.clearalldata();

        this.Path = Path;
        this.TypeOfLoadingFile = TypeOfLoadingFile;
        this.isStandAloneFile = true;
        this.FileExtension = FileExtension;
    }
    
    public IStandAloneFilesLoader(ArrayList<Dict<String>> FileWithNestedFiles, String TypeOfLoadingFile)
    {
        //this.clearalldata();

        this.TypeOfLoadingFile = TypeOfLoadingFile;
        this.isStandAloneFile = false;
        this.FileWithNestedFiles = FileWithNestedFiles;
    }
    
    @FunctionalInterface
    private interface funcIterateEachFile
    {
        void func(Dict<String> filejson, boolean useFINISHED, String ID);
    }

    private void iterator(String[] tokensNeedToCheckFile, funcIterateEachFile funcIterateEachFile, boolean useFINISHED, String ID)
    {
        if (this.isStandAloneFile)
        {
            File directory_for_files = new File("./" + this.Path);
            logger.log(Level.DEBUG, "Получение всех файлов в директории: {}", "./" + this.Path);
                     
            File[] all_filtered_files = directory_for_files.listFiles((file, filename) -> filename.endsWith(this.FileExtension));
         
            for (File each_file: all_filtered_files)
            {
                logger.log(Level.DEBUG, "Проверка json файлов. Файл с именем: {}", each_file.getName());

                try
                {
                    Dict<String> LoadedFile = new Dict<>();

                    FileReader reader = new FileReader(each_file);  // FileNotFoundException - if the file does not exist, is a directory rather than a regular file, or for some other reason cannot be opened for reading.

                    if(tokensNeedToCheckFile.length == 0) LoadedFile = Dict.JSON_to_Dict(reader);
                    else LoadedFile = Dict.JSON_to_Dict(reader, tokensNeedToCheckFile); //new String[]{"NAME", "ID", "ISFINISHED"}

                    reader.close();

                    logger.log(Level.DEBUG, "LoadedFile.isEmpty() = {}", LoadedFile.isEmpty());

                    try
                    {
                        logger.log(Level.DEBUG, "Был импортирован из json файл с заголовком: {}", LoadedFile.getValue("NAME"));
                        funcIterateEachFile.func(LoadedFile, useFINISHED, ID);
                    }
                    catch(NoSuchElementException exception)
                    {
                        logger.log(Level.DEBUG, "Был импортирован из json файл с отсутствующим заголовком /' NAME /': {}", exception);
                    }
                }
                catch(FileNotFoundException exception)
                {
                    logger.log(Level.DEBUG, "The file does not exist, is a directory rather than a regular file, or for some other reason cannot be opened for reading: {}", exception);
                }
                catch(java.io.IOException exception)
                {
                    logger.log(Level.DEBUG, "an I/O error occured: {}", exception);
                }
            }
        }
        else
        {
            for (Dict<String> each_file : this.FileWithNestedFiles)
            {
                try
                {
                    logger.log(Level.DEBUG, "Проверка вложенных файлов в файл json. Файл с именем: {}", each_file.getValue("NAME"));
                    funcIterateEachFile.func(each_file, useFINISHED, ID);
                }
                catch(NoSuchElementException exception)
                {
                    logger.log(Level.DEBUG, "Был импортирован из json файл с отсутствующим заголовком /' NAME /' = {}", exception);                        
                }           
            }
        }
    }
                            
    public void loadfilelist(boolean useFINISHED)
    {
        this.iterator(new String[]{"NAME", "ID", "ISFINISHED", "TYPE_OF_FILE"}, this::allnotDoubleIDFileLoader, useFINISHED, "");

        logger.log(Level.DEBUG, "this.AllIDlist до убирания дублирующихся ID = {}", this.AllIDlist);

        this.AllIDlist.removeIf(element -> this.AllIDlist.stream().filter(e -> e.equals(element)).count() > 1);

        logger.log(Level.DEBUG, "this.AllIDlist после убирания дублирующихся ID = {}", this.AllIDlist);

        this.iterator(new String[]{"NAME", "ID", "ISFINISHED", "TYPE_OF_FILE"}, this::foundFile, useFINISHED, "");
    }
    
    private void allnotDoubleIDFileLoader(Dict<String> data, boolean useFINISHED, String ID)
    {
        try
        {
            String result = data.getValue("ID");
        
            logger.log(Level.DEBUG, "Получен результат при получении из файла ID функции allnotDoubleIDFileLoader: result = {}", result);        

            this.AllIDlist.add(result);
            logger.log(Level.DEBUG, "В this.AllIDlist был добавлен новый ID. this.AllIDlist стал равен = {}", this.AllIDlist);            
        }
        catch(NoSuchElementException exception)
        {
            logger.log(Level.DEBUG, "Был импортирован из json файл с отсутствующим полем /' ID /' = {}", exception);            
        }
    }
    
    private void foundFile(Dict<String> data, boolean useFINISHED, String ID)
    {
        logger.log(Level.DEBUG, "Запуск this.foundFile(LoadedFile, useFINISHED, ID) : {}", ID);        
        if (this.checkfile_high(data) && this.checkID(data))
        {
            this.filecounterfull = this.filecounterfull + 1;
            logger.log(Level.DEBUG, "Счетчик файлов (this.filecounterfull) стал {}", this.filecounterfull);

            this.reqsymbolsfull.add(Integer.toString(this.filecounterfull));
            logger.log(Level.DEBUG, "Были добавлены новые требования на ввод. Требования (this.reqsymbolsfull) стали: {}", this.reqsymbolsfull);

            try
            {
                logger.log(Level.DEBUG, "Файл добавлен в массив ListOfFilesFull {}", data.getValue("NAME"));
                this.ListOfFilesFull.add(">> " + data.getValue(new LinkedList<String>(Arrays.asList("NAME"))) + " (" + Integer.toString(this.filecounterfull) + ")");

                logger.log(Level.DEBUG, "Файл добавлен в массив DictNumberFileFull {}", data.getValue("NAME"));
                this.DictNumberFileFull.saveValue(new LinkedList<String>(Arrays.asList(Integer.toString(this.filecounterfull))), data.getValue("ID"));

                if (useFINISHED)
                {
                    logger.log(Level.DEBUG, "useFINISHED = {}", useFINISHED);

                    if (data.getValue("ISFINISHED").equals("YES"))
                    {
                        this.filecounterfinished = this.filecounterfinished + 1;
                        logger.log(Level.DEBUG, "Счетчик файлов (this.filecounterfinished) стал {}", this.filecounterfinished);

                        this.reqsymbolsfinished.add(Integer.toString(this.filecounterfinished));
                        logger.log(Level.DEBUG, "Были добавлены новые требования на ввод. Требования (this.reqsymbolsfinished) стали: {}", this.reqsymbolsfinished);

                        this.ListOfFilesFinished.add(">> " + data.getValue("NAME") + " (" + Integer.toString(this.filecounterfinished) + ")");
                        logger.log(Level.DEBUG, "В массив ListOfFilesFinished добавлено значение: {}", data.getValue("NAME"));

                        logger.log(Level.DEBUG, "Файл добавлен в массив DictNumberFileFinished {}", data.getValue("ID"));
                        this.DictNumberFileFinished.saveValue(new LinkedList<String>(Arrays.asList(Integer.toString(this.filecounterfinished))), data.getValue("ID"));
                    }                   
                    else
                    {
                        this.filecounternotfinished = this.filecounternotfinished + 1;
                        logger.log(Level.DEBUG, "Счетчик файлов (this.filecounternotfinished) стал {}", this.filecounternotfinished);

                        this.reqsymbolsnotfinished.add(Integer.toString(this.filecounternotfinished));
                        logger.log(Level.DEBUG, "Были добавлены новые требования на ввод. Требования (this.reqsymbolsnotfinished) стали: {}", this.reqsymbolsnotfinished);

                        this.ListOfFilesNotFinished.add(">> " + data.getValue("NAME") + " (" + Integer.toString(this.filecounternotfinished) + ")");
                        logger.log(Level.DEBUG, "В массив ListOfFilesNotFinished добавлено значение: {}", data.getValue("NAME"));

                        logger.log(Level.DEBUG, "Файл добавлен в массив DictNumberFileNotFinished {}", data.getValue("ID"));
                        this.DictNumberFileNotFinished.saveValue(new LinkedList<String>(Arrays.asList(Integer.toString(this.filecounternotfinished))), data.getValue("ID"));
                    }
                }
                else
                {
                    logger.log(Level.DEBUG, "Нет крыжика useFINISHED (=0) - не нужно делить на завершенные и незавершенные");
                }
            }
            catch(NoSuchElementException exception)
            {
                logger.log(Level.DEBUG, "Был импортирован из json файл с отсутствующим полем = {}", exception);    
            }
        }
    }
                
    public void loadfullfilefull(String counteroffiles)
	{
        logger.log(Level.DEBUG, "Запущена функция loadfullfilefull в общем Loader");
        this.iterator(new String[]{}, this::loadfile, false, this.DictNumberFileFull.getValue(counteroffiles));
    }
    
    public void loadfullfilefinished(String counteroffiles)
    {
        logger.log(Level.DEBUG, "Запущена функция loadfullfilefinished в общем Loader");
        this.iterator(new String[]{}, this::loadfile, false, this.DictNumberFileFinished.getValue(counteroffiles));
    }
    
    public void loadfullfilenotfinished(String counteroffiles)
    {
        logger.log(Level.DEBUG, "Запущена функция loadfullfilenotfinished в общем Loader");

        logger.log(Level.DEBUG, "Передача = {}", this.DictNumberFileNotFinished.getValue(counteroffiles));
        this.iterator(new String[]{}, this::loadfile, false, this.DictNumberFileNotFinished.getValue(counteroffiles));
    }
    
    private void loadfile(Dict<String> data, boolean useFINISHED, String ID)
    {
        logger.log(Level.DEBUG, "Запуск this.loadfile(LoadedFile, ID) : {}", ID);  
       
        try
        {
            if (data.getValue("ID").equals(ID))
            {
                logger.log(Level.DEBUG, "\"loadfile\" Обнаружен файл с требуемым ID {}, загружаем", data.getValue("ID"));
                logger.log(Level.DEBUG, "\"loadfile\" Требуемый ID {}: ", ID);
                
                if (this.checkfile_low(data))
                {
                    logger.log(Level.DEBUG, "\"loadfile\" Файл прошёл проверку checkfile_low");
                    this.FullLoadedFile = data;
                    return;
                }
                else
                {
                    logger.log(Level.DEBUG, "\"loadfile\" Файл НЕ прошёл проверку checkfile_low");
                    return;
                }
            }
            else
            {
                logger.log(Level.DEBUG, "Не обнаружен файл с требуемым ID {}, НЕ загружаем", data.getValue("ID"));
                logger.log(Level.DEBUG, "Требуемый ID {}: ", ID);
            }
            
            logger.log(Level.DEBUG, "FullLoadedFile стал {}", this.FullLoadedFile);  
        }
        catch(NoSuchElementException exception)
        {
            logger.log(Level.DEBUG, "Не получилось загрузить файл в функции loadfile. Ошибка = {}", exception);  
            logger.log(Level.DEBUG, "FullLoadedFile стал {}", this.FullLoadedFile);            
        }                      
    }
                    
    private boolean checkfile_high(Dict<String> data)
    {
        //------------проверка на тип файла -------------------------------
        try
        {
            logger.log(Level.DEBUG, "(checkfile_high/ data = {}", data.toString());
            if (data.getValue("TYPE_OF_FILE").equals(this.TypeOfLoadingFile))
            {                
                logger.log(Level.DEBUG, "Тип файла соответствует требуемому {}, загрузка доступна", data.getValue("TYPE_OF_FILE"));
                return true;
            }
            else
            {
                logger.log(Level.DEBUG, "Тип файла НЕ соответствует требуемому {}, загрузка НЕ доступна", data.getValue("TYPE_OF_FILE"));
                return false;
            }
        }
        catch(NoSuchElementException exception)
        {
            logger.log(Level.DEBUG, "Обнаружен файл без требуемых аналитик - TYPE_OF_FILE. Exception = {}", exception);
            return false;
        }
    }
        
    private boolean checkID(Dict<String> data)
    {
        try
        {
            //------------проверка на длину ID -----------------------------------------------------------
            if (data.getValue("ID").length() == 128)
            {
                logger.log(Level.DEBUG, "Длина ID равна 128 {}, загрузка доступна", data.getValue("ID"));
            }
            else
            {
                logger.log(Level.DEBUG, "Длина ID не равна 128 {}, загрузка НЕ доступна", data.getValue("ID"));
                return false;
            }
            
            //------------проверка на отсутствие уже загруженного файла по ID ----------------------------
            //this.AllIDlist.stream()

            int counterForID = 0;
            for (String eachID : this.AllIDlist)
            {
                if (eachID.equals(data.getValue("ID")))
                {
                    counterForID++;
                }
            }
                       
            if (counterForID == 1)
            {
                logger.log(Level.DEBUG, "Отсутствие файла-дубляжа по ID {}, загрузка доступна", data.getValue("ID"));
            }
            else
            {
                logger.log(Level.DEBUG, "Файл-дубляж по ID {}, загрузка НЕ доступна", data.getValue("ID"));
                return false;
            }
        }
        catch(NoSuchElementException exception)
        {
            logger.log(Level.DEBUG, "Обнаружен файл без требуемых аналитик - ID. Exception = {}", exception);
            return false;
        }
        
        return true;
    }   
        
    abstract boolean checkfile_low(Dict<String> data);

    public ArrayList<String> returnloadedlistFull()
    {
        return this.ListOfFilesFull;
    }
    
    public ArrayList<String> returnloadedlistFinished()
    {            
        return this.ListOfFilesFinished;
    }
    
    public ArrayList<String> returnloadedlistNotFinished()
    {            
        return this.ListOfFilesNotFinished;
    }
    
    public Dict<String> returnloadedfile()
    {
        //logger.log(Level.DEBUG, "Файл this.FullLoadedFile был возвращен. Значения {}", this.FullLoadedFile);
        return this.FullLoadedFile;
    }
    
    public ArrayList<String> returnreqsymbolsfull()
    {
        return this.reqsymbolsfull;
    }
    
    public ArrayList<String> returnreqsymbolsnotfinished()
    {
        return this.reqsymbolsfinished;
    }
    
    public ArrayList<String> returnreqsymbolsfinished()
    {
        return this.reqsymbolsnotfinished;
    }
    
    public void clearalldata()
    {
        this.FullLoadedFile = new Dict<>();

        this.filecounterfull  = 0;
        this.reqsymbolsfull.clear();
        this.ListOfFilesFull.clear();
        this.DictNumberFileFull = new Dict<>();

        this.filecounterfinished = 0;
        this.reqsymbolsfinished.clear();
        this.ListOfFilesFinished.clear();
        this.DictNumberFileFinished = new Dict<>();

        this.filecounternotfinished = 0;
        this.reqsymbolsnotfinished.clear();
        this.ListOfFilesNotFinished.clear();
        this.DictNumberFileNotFinished = new Dict<>();

        this.AllIDlist.clear();
    }
        
    public boolean isFileLoaded()
    {
        logger.log(Level.DEBUG, "isFileLoaded() = {}", this.FullLoadedFile);
        return !this.FullLoadedFile.isEmpty();
    }
    
    public abstract void injectData(Dict<String> data);
}