package business;

import java.awt.event.ActionListener;

import data.FilestTxt;
import data.Messages;
import domain.City;
import logics.Logic;
import presentation.GUICity;

import java.awt.event.ActionEvent;
public class Controller implements ActionListener {
  private Messages mes;
  private GUICity gCi;
  private City ci;
  private Logic l;
  private FilestTxt ft;
  public Controller(){
    l= new Logic();
    ci=new City(l.getSizeMat());
    mes= new Messages();
    gCi= new GUICity(ci.getSize());
    ft= new FilestTxt();
    l.setMatCity(gCi.getMatCity());
    l.stringMat(ci);
    initializerActions();
  }
  public void initializerActions(){
    gCi.getJKillZombs().addActionListener(this);
    gCi.getJKillAliens().addActionListener(this);
    gCi.getJMove().addActionListener(this);
    gCi.getJBack().addActionListener(this);
  }
  
  public void actionPerformed(ActionEvent e){
    if(e.getSource()==gCi.getJKillAliens()){
      
      //logica para eliminar a los aliens
      l.killAliens();
      //gCi.getEvents().setText("ALIENS MUERTOS");
      mes.showMessage("Has eliminado a todos los aliens \nTerminó el juego");
      gCi.getJKillAliens().setVisible(false);
      gCi.getJMove().setVisible(false);
      gCi.getJKillZombs().setVisible(false);
      gCi.getJBack().setVisible(true);
      
    }
    if(e.getSource()==gCi.getJKillZombs()){
      //logica para eliminar a los zombies
      l.killZombs();
      //gCi.getEvents().setText("ZOMBIES MUERTOS");
      //.............................
      mes.showMessage("Has eliminado a todos los zombies \nTerminó el juego");
      gCi.getJKillAliens().setVisible(false);
      gCi.getJMove().setVisible(false);
      gCi.getJKillZombs().setVisible(false);
      gCi.getJBack().setVisible(true);
      
    }
    //movimiento
    if(e.getSource()==gCi.getJMove()){
      
      l.makeMove(ci);
      //l.endGameZombs();
      gCi.getEvents().setText(ft.readFile("Acontecimientos.txt"));
      if(l.endGameZombs()){
        mes.showMessage("Han muerto todos los zombies \nTerminó el juego");
        gCi.getJKillAliens().setVisible(false);
        gCi.getJMove().setVisible(false);
        gCi.getJKillZombs().setVisible(false);
        gCi.getJBack().setVisible(true);
        
      }
      if(l.endGameAliens()){
        mes.showMessage("Han muerto todos los aliens \nTerminó el juego");
        gCi.getJKillAliens().setVisible(false);
        gCi.getJMove().setVisible(false);
        gCi.getJKillZombs().setVisible(false);
        gCi.getJBack().setVisible(true);
        
      }
      if(l.endGameHumans()){
        mes.showMessage("Han muerto todos los humanos \nTerminó el juego");
        gCi.getJKillAliens().setVisible(false);
        gCi.getJMove().setVisible(false);
        gCi.getJKillZombs().setVisible(false);
        gCi.getJBack().setVisible(true);
        
      }
    }
    //boton de volver
    if(e.getSource()==gCi.getJBack()){
      
      gCi.dispose();
      new ControllerInicio();
    }
  }
}