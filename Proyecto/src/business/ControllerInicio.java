package business;

import java.awt.event.ActionListener;

import data.Messages;
import presentation.GUIInicio;

import java.awt.event.ActionEvent;
public class ControllerInicio implements ActionListener{
  
  private Messages mes;
  private GUIInicio gi;
  public ControllerInicio(){
    
    gi = new GUIInicio();
    mes= new Messages();
    initializerActions();
  }
  
  public void initializerActions(){
    //agregar la propiedad de ser escuchada
    gi.getJInfoProgram().addActionListener(this);
    gi.getJBPlay().addActionListener(this);
    gi.getInfoGame().addActionListener(this);
  }
  
  public void actionPerformed(ActionEvent e){
    //boton de reportes
    if(e.getSource()==gi.getJInfoProgram()){
      
    }
    //boton de la info del juego
     if(e.getSource()==gi.getJInfoProgram()){
      mes.showMessage("Creado por el informatico \n-Jallmar Samuels \n-Carnet C17194 \n- 11-2022");
    }
    //para el boton de jugar
    if(e.getSource()==gi.getJBPlay()){
      gi.dispose();
      new Controller();
    }
    //para el boton de instrucciones
    if(e.getSource()==gi.getInfoGame()){
      mes.showMessage("Bienvenido al juego de Zombies vs Humanos vs Aliens"+"\nEl juego acaba cuando una raza sea eliminada en su totalidad."+
                      "\n¡A JUGAR!"); 
                        
    }
  }
}
