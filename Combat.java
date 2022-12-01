public class Combat {
    private int aDurability = 0;
    private int wDurability = 0;
    private int toughness = 0;
    private int strength = 0;

    // combat algorithm
    public Combat(int turnCount, Player player, Enemy enemy){
        player.updatePlayer(turnCount);
        while(player.getHealth() >0 || enemy.getHealth()>0){
            // player go first
            playerCombat(player,enemy);
            // enemy go next
            enemyCombat(player,enemy);
            // check winner
            //@TODO Make this into a functioning thing
            //@TODO Play the youve been killed screen
            if(player.getHealth()<=0){
                player.fileDeleter();
                break;
            } else if (enemy.getHealth()<=0){
                break;
            }
        }
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
            enemy.setHealth(enemy.getHealth() - strength + toughness + enemy.getDefense() - player.getPower());
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
            enemy.setHealth(enemy.getHealth() - strength + toughness + enemy.getDefense() - player.getPower());
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
}
