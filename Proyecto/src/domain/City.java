package domain;

//mi objeto ciudad
public class City{
private int size;
private int builds;
private int trees;
private int aliens;
private int zombs;
private int humans;
private int pocims;
public City(int vect[]){
  this.size=vect[0];
  this.builds=vect[1];
  this.trees=vect[2];
  this.aliens=vect[3];
  this.zombs=vect[4];
  this.humans=vect[5];
  this.pocims=vect[6];
  
}
//metodos set
public void setSize(int size){
  size=this.size;
}
public void setBuilds(int builds){
  builds=this.builds;
}
public void setTrees(int trees){
  trees=this.trees;
}
public void setAliens(int aliens){
  aliens=this.aliens;
}
  public void setZombs(int zombs){
  zombs=this.zombs;
}
public void setHumans(int humans){
  humans=this.humans;
}
public void setPocims(int pocims){
  pocims=this.pocims;
}
//metodos get
public int getSize(){
  return this.size;
}
public int getBuilds(){
  return this.builds;
}
public int getTrees(){
  return this.trees;
}
public int getAliens(){
  return this.aliens;
}
 public int getZombs(){
  return this.zombs;
}
public int getHumans(){
  return this.humans;
}
public int getPocims(){
  return this.pocims;
}
}