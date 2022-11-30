public class Gameplay {
    private int turnCount;

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
        Player player = new Player();
        player.fileReader();

    }


}
