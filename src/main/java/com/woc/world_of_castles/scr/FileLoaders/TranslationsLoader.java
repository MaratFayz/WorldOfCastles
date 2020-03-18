/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.FileLoaders;

import com.Util_Classes.Dict;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class TranslationsLoader extends IStandAloneFilesLoader
{
    private static final Logger logger = LogManager.getLogger(TranslationsLoader.class.getName());        
    
    public TranslationsLoader(String Path, String TypeOfLoadingFile, String FileExtension)
    {
        super(Path, TypeOfLoadingFile, FileExtension);
    }
    
    @Override
    protected boolean checkfile_low(Dict data)
    {
        return true;
    }
    
    @Override
    public void injectData(Dict data)
    {
        
    }

}
