/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.FileLoaders;

import com.Util_Classes.Dict;

import java.util.Arrays;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class CampaignLoader extends INestedFilesLoader
{
    private static final Logger logger = LogManager.getLogger(CampaignLoader.class.getName());
    
    public CampaignLoader(String Path, String TypeOfLoadingFile, String FileExtension)
    {
        super(Path, TypeOfLoadingFile, FileExtension);
    }   
    
    @Override    
    public void createNestedFile()
    {
        //создание вложенного файла
        logger.log(Level.DEBUG, "this.FullLoadedFile перед созданием файла = ", this.FullLoadedFile);  
        
        logger.log(Level.DEBUG, "В this.NestedFile был добавлен файл = ", this.returnloadedfile().getValues_Dict_Arrays("MISSION"));
        this.NestedFile = new MissionLoader(this.returnloadedfile().getValues_Dict_Arrays("MISSION"), "MISSION");
    }

    @Override
    public boolean checkMainfile_low(Dict<String> data)
    {
//    функция проверки файла кампании
//    Вставить проверки на проставление в файле миссии CAN_CHOOSE_ = NO
        return true;
    }
    
}
