/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woc.world_of_castles.scr.GameLogic;

/**
 *
 * @author mfayzelgay001
 */
public abstract class UserAction 
{
    protected boolean isPlayer;

    public UserAction()
    {
        
    }

    public abstract void getReaction();

    public boolean isPlayer()
    {
        return this.isPlayer;
    }
}

class PlayerAction extends UserAction
{
  public PlayerAction()
  {
      this.isPlayer = true;
  }

  @Override
  public void getReaction()
  {
      
  }
}

class ComputerAction extends UserAction
{    
  @Override
  public void getReaction()
  {
      
  }
}

