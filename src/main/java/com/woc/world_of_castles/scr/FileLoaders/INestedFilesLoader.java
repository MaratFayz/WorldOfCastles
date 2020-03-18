/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.FileLoaders;

import com.Util_Classes.Dict;
import java.util.ArrayList;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public abstract class INestedFilesLoader extends IStandAloneFilesLoader
{
    IStandAloneFilesLoader NestedFile;
    private static final Logger logger = LogManager.getLogger(INestedFilesLoader.class.getName());

    public INestedFilesLoader(String Path, String TypeOfLoadingFile, String FileExtension)
    {
        super(Path, TypeOfLoadingFile, FileExtension);
    }
    
    public abstract void createNestedFile();

    public void nested_loadfilelist(boolean useFINISHED)
    {
        this.NestedFile.loadfilelist(useFINISHED);
    }

    public void nested_loadfullfilefull(String counteroffiles)
    {
        this.NestedFile.loadfullfilefull(counteroffiles);
    }

    public void nested_loadfullfilefinished(String counteroffiles)
    {
        this.NestedFile.loadfullfilefinished(counteroffiles);
    }
    
    public void nested_loadfullfilenotfinished(String counteroffiles)
    {
        this.NestedFile.loadfullfilenotfinished(counteroffiles);
    }

    public ArrayList<String> nested_returnloadedlistFull()
    {
        return this.NestedFile.returnloadedlistFull();
    }

    public ArrayList<String> nested_returnloadedlistFinished()
    {
        return this.NestedFile.returnloadedlistFinished();
    }

    public ArrayList<String> nested_returnloadedlistNotFinished()
    {
        return this.NestedFile.returnloadedlistNotFinished();
    }
    
    public Dict<String> nested_returnloadedfile()
    {
        return this.NestedFile.returnloadedfile();
    }
    
    public ArrayList<String> nested_returnreqsymbolsfull()
    {
        return this.NestedFile.returnreqsymbolsfull();
    }
    
    public ArrayList<String> nested_returnreqsymbolsnotfinished()
    {
        return this.NestedFile.returnreqsymbolsnotfinished();
    }
    
    public ArrayList<String> nested_returnreqsymbolsfinished()
    {
        return this.NestedFile.returnreqsymbolsfinished();
    }

    public void nested_clearalldata()
    {
        this.NestedFile.clearalldata();
    }

    @Override    
    protected boolean checkfile_low(Dict<String> data)
    {
        return this.checkMainfile_low(data);
    }

    protected abstract boolean checkMainfile_low(Dict<String> data);

    @Override
    public void clearalldata()
    {
        super.clearalldata();
        //this.NestedFile.clearalldata();
    }
    
    public boolean nested_isFileLoaded()
    {
        return this.NestedFile.isFileLoaded();
    }

    @Override
    public void injectData(Dict<String> data)
    {
        this.NestedFile.injectData(data);
    }
}
