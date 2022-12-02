import javax.swing.*;

public class Combat {
    private int aDurability = 0;
    private int wDurability = 0;
    private int toughness = 0;
    private int strength = 0;
    private boolean winner = false;

    // combat algorithm
    public Combat(int turnCount, Player player, Enemy enemy){
        player.updatePlayer(turnCount);
        while(player.getHealth() >0 || enemy.getHealth()>0){
            // player go first
            playerCombat(player,enemy);
            // enemy go next
            enemyCombat(player,enemy);
            // check winner
            if(player.getHealth()<=0){
                player.fileDeleter();
                break;
            } else if (enemy.getHealth()<=0){
                winner=true;
                break;
            }
        }
    }

    public boolean getWin(){
        return winner;
    }

    //Player combat method
    public void playerCombat(Player player, Enemy enemy){
        try {
            strength = player.getEWeapon().getStrength();
        } catch (NullPointerException e){
            strength = 0;
        }
        try {
            toughness = enemy.getEArmor().getToughness();
        } catch (NullPointerException e){
            toughness=0;
        }
        try {
            wDurability = player.getEWeapon().getDurability();
        } catch (NullPointerException e){
            wDurability=0;
        }
        try {
            aDurability = enemy.getEArmor().getDurability();
        } catch (NullPointerException e) {
            aDurability=0;
        }
        if ((strength + player.getPower()) >= (toughness + enemy.getDefense())) {
            enemy.setHealth(enemy.getHealth() - strength + toughness + enemy.getDefense() - 2*player.getPower());
        } else {
            enemy.setHealth(enemy.getHealth() - player.getPower());
        }
        try {
            player.getEWeapon().setDurability(wDurability - toughness);
            if (wDurability <= 0) {
                player.removeItem(player.getEWeapon());
            }
        }catch (NullPointerException e) {
        }
        try{
            enemy.getEArmor().setDurability(aDurability - strength);
            if (aDurability <= 0) {
                enemy.removeItem(enemy.getEArmor());
            }
        }catch (NullPointerException e) {
        }

    }

    // enemy combat method
    public void enemyCombat(Player enemy, Enemy player){
        try {
            strength = player.getEWeapon().getStrength();
        } catch (NullPointerException e){
            strength = 0;
        }
        try {
            toughness = enemy.getEArmor().getToughness();
        } catch (NullPointerException e){
            toughness=0;
        }
        try {
            wDurability = player.getEWeapon().getDurability();
        } catch (NullPointerException e){
            wDurability=0;
        }
        try {
            aDurability = enemy.getEArmor().getDurability();
        } catch (NullPointerException e) {
            aDurability=0;
        }
        if ((strength + player.getPower()) >= (toughness + enemy.getDefense())) {
            enemy.setHealth(enemy.getHealth() - strength + toughness + enemy.getDefense() - 2*player.getPower());
        } else {
            enemy.setHealth(enemy.getHealth() - player.getPower());
        }

        //Gameplay gameplay=new Gameplay();
        //TODO pop up window for items breaking
        try {
            player.getEWeapon().setDurability(wDurability - toughness);
            if (wDurability <= 0) {
                player.removeItem(player.getEWeapon());
                //System.out.println("Your equipped weapon broke");
                //JOptionPane.showMessageDialog(gameplay.sendFrametoNotif(),"Your equipped weapon broke");
            }
        }catch (NullPointerException e) {
        }
        try{
            enemy.getEArmor().setDurability(aDurability - strength);
            if (aDurability <= 0) {
                enemy.removeItem(enemy.getEArmor());
                //System.out.println("Your equipped armor broke");
                //JOptionPane.showMessageDialog(gameplay.sendFrametoNotif(),"Your equipped armor broke");
            }
        }catch (NullPointerException e) {
        }
    }
}
