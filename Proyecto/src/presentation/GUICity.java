package presentation;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JScrollPane;

public class GUICity extends JFrame{
  
  //boton de movimiento
  private JButton jMove;
  //botones de exterminar las razas
  private JButton jKillAliens;
  private JButton jKillZombs;
  //boton de volver
  private JButton jBack;
  
  //matriz de labels
   JLabel jla;
  //label de fondo
  private JLabel jlFondo;
  public JLabel matCity[][];
  
  //panel para poner la matriz
  private JPanel putMat;
  
  //textfield de los acontecimientos
  private JTextArea jEvents;
  private JScrollPane scroll;
  //instancia de la matriz de labels
  
  public GUICity(int size){
    
    //botones de erradicar
    setJKillAliens("Erradicar aliens");
    setJKillZombs("Erradicar zombies");
    add(jKillAliens);
    add(jKillZombs);
    //para el movimiento
    setJMove("Movimiento");
    add(jMove);
    //volver
    setJBack("Volver");
    add(jBack);
    //acontecimientos
    setEvents("Acontecimientos");
    setScrollPane(jEvents,"Acontecimientos");
     add(scroll);
    //matriz
    matCity= new JLabel[size][size];
    //panel
    setJPanel(size);
    add(putMat);
    generateMatCity(0,0);//le paso la i y j
    
    setJLFondo();
    add(jlFondo);
    
    initializer();
  }
  public void initializer(){
    getContentPane().setBackground(Color.orange);//color
    jlFondo.setIcon(new ImageIcon(getClass().getResource("/images/fairy.jpg")));
    setTitle("Batalla en Guacimo");
    setResizable(false);
    //setSize(Toolkit.getDefaultToolkit().getScreenSize());//x y //tamaño de mi pantalla
    setExtendedState(MAXIMIZED_BOTH);
    setVisible(true);
    setLocationRelativeTo(null);//centrar la ventana
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//cerrar la ventana de memoria
  }
  //metodo para el fondo de la pelea
  public void setJLFondo(){
    jlFondo = new JLabel();
    jlFondo.setBounds(0,0,800,500);
  }
  public JLabel getJlFondo(){
    return jlFondo;
  }  
  //text area de los acontecimientos
  public void setEvents(String message){
    jEvents=new JTextArea();
    jEvents.setText(message);
    jEvents.setEditable(false);
   
  }
  public JTextArea getEvents(){
    return jEvents;
  }
  //set y get del scrollpane
  public void setScrollPane(JTextArea jEvents,String message){
    scroll= new JScrollPane(jEvents);
    scroll.setBounds(800,40,500,450);
    
  }
  public JScrollPane getScrollPane(){
    return scroll;
  }
  //panel para poner encima la matriz
  public void setJPanel(int size){
    putMat=new JPanel();
    putMat.setBackground(Color.yellow);
    putMat.setLayout(new GridLayout(size,size));
    putMat.setBounds(20,20,700,700);
  }
  public JPanel getJPanel(){
    return putMat;
  }

  public JLabel [][]generateMatCity(int i,int j){
    if(i==matCity.length){
      return matCity;
    }
    if(j!=matCity[0].length){
      jla=new JLabel();
      jla.setBorder(new LineBorder(Color.black));
      matCity[i][j]=jla;
      getJPanel().add(matCity[i][j]);
      return generateMatCity(i,j+1);
    } else {
      j=0;
      return generateMatCity(i+1,j);
    }
  }
  
  public JLabel [][]getMatCity(){
    return matCity;
  }     
  
  //botones de erradicar aliens y zombies
  public void setJKillAliens(String message){
    jKillAliens=new JButton();
    jKillAliens.setText(message);
    jKillAliens.setBounds(800,600,200,50);
  }
  public JButton getJKillAliens(){
    return jKillAliens;
  }
  //zombies
  public void setJKillZombs(String message){
    jKillZombs=new JButton();
    jKillZombs.setText(message);
    jKillZombs.setBounds(800,670,200,50);
  }
  public JButton getJKillZombs(){
    return jKillZombs;
  }
  //boton de movimiento
  public void setJMove(String message){
    jMove=new JButton();
    jMove.setText(message);
    jMove.setBounds(800,500,200,50);
  }
  public JButton getJMove(){
    return jMove;
  }
  //boton de volver
  public void setJBack(String message){
    jBack=new JButton();
    jBack.setText(message);
    jBack.setVisible(false);
    jBack.setBounds(800,500,200,50);
  }
  public JButton getJBack(){
    return jBack;
  }
  
}