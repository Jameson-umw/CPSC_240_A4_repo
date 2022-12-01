import java.awt.event.KeyEvent;

public class Gameplay {
    private int turnCount;
    public Player player = new Player();
    private int walkCount;


    public Gameplay(){
        this.turnCount=1;
        GAMEPLAY();

    }

    public int getTurnCount(){
        return turnCount;
    }

    public void setTurnCount(){
        this.turnCount=turnCount;
    }

    //@TODO We need the sprites and stuff and we need someone to import img files
    public void GAMEPLAY(){
        player.fileReader();


    }
    public void moveIt(KeyEvent evt){
        switchIm();
        int xPos=player.getxPos();
        int yPos= player.getyPos();
        switch(evt.getKeyCode()){
            case KeyEvent.VK_DOWN:
                if((yPos+5)>600){player.setyPos(600);}
                else{player.setyPos(yPos+=5);}
                break;
            case KeyEvent.VK_UP:
                if((yPos-5)<0){player.setyPos(0);}
                else{player.setyPos(yPos-=5);}
                break;
            case KeyEvent.VK_LEFT:
                if((xPos-5)<0){player.setxPos(0);}
                else{player.setxPos(xPos-=5);}
                break;
            case KeyEvent.VK_RIGHT:
                if((xPos+5)>970){player.setxPos(970);}
                else{player.setxPos(xPos+=5);}
                break;

        }
    }
    //switches image on every 5th keystroke
    public void switchIm(){
        if((walkCount%5)==0){
            if(player.getAnim()==player.getPlayerStill()){player.setAnim(player.getPlayerWalk());}
            else{player.setAnim(player.getPlayerStill());}}
        walkCount++;
    }

}
