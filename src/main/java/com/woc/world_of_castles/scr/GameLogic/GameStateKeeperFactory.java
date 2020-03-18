/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic;

import com.Util_Classes.Dict;;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mfayzelgay001
 */
public class GameStateKeeperFactory 
{
    private GameStateKeeper GameStateKeeper;
    private static final Logger logger = LogManager.getLogger(GameStateKeeperFactory.class.getName());    

    public void createGSK(Dict<String> LoadedFile)
    {
        this.GameStateKeeper = new GameStateKeeper(LoadedFile);

        logger.log(Level.DEBUG, "(из GameStateKeeperFactory) Был инициализирован: this.GameStateKeeper: значение {}", this.GameStateKeeper);
    }
    
    public GameStateKeeper returnGameStateKeeper()
    {
        logger.log(Level.DEBUG, "(из GameStateKeeperFactory) Был возвращен: this.GameStateKeeper: значение {}", this.GameStateKeeper);
        return this.GameStateKeeper;
    }
}
