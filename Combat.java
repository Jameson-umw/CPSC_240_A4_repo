public class Combat {

    public Combat(Player player, Enemy enemy){
        player.updatePlayer();
        while(player.getHealth() >0 || enemy.getHealth()>0){
            // player go first
            playerCombat(player,enemy);
            // enemy go next
            enemyCombat(player,enemy);
            // check winner
            //@TODO Make this into a functioning thing
            if(player.getHealth()<=0){
                break;
            } else if (enemy.getHealth()<=0){
                break;
            }
        }
    }
    public void playerCombat(Player player, Enemy enemy){
        if((player.getEWeapon().getStrength()+player.getPower())>=(enemy.getEArmor().getToughness() + enemy.getDefense())){
            enemy.setHealth(enemy.getHealth()-player.getEWeapon().getStrength()+enemy.getEArmor().getToughness()+enemy.getDefense()-player.getPower());
            player.getEWeapon().setDurability(player.getEWeapon().getDurability()-enemy.getEArmor().getToughness());
            enemy.getEArmor().setDurability(enemy.getEArmor().getDurability()-player.getEWeapon().getStrength());
        } else if (player.getEWeapon().equals(null)){
            enemy.setHealth(enemy.getHealth()-player.getPower());
            enemy.getEArmor().setDurability(enemy.getEArmor().getDurability()-player.getPower());
        } else {
            enemy.setHealth(enemy.getHealth()-player.getPower());
            player.getEWeapon().setDurability(player.getEWeapon().getDurability()-enemy.getEArmor().getToughness());
            enemy.getEArmor().setDurability(enemy.getEArmor().getDurability()-player.getEWeapon().getStrength());
        }
        if(player.getEWeapon().getDurability()<=0){
            player.removeItem(player.getEWeapon());
        }
        if (enemy.getEArmor().getDurability()<=0){
            enemy.removeItem(enemy.getEArmor());
        }
    }

    public void enemyCombat(Player enemy, Enemy player){
        if(player.getEWeapon().getStrength()>=(enemy.getEArmor().getToughness() + enemy.getDefense())){
            enemy.setHealth(enemy.getHealth()-player.getEWeapon().getStrength()+enemy.getEArmor().getToughness()+enemy.getDefense());
            player.getEWeapon().setDurability(player.getEWeapon().getDurability()-enemy.getEArmor().getToughness());
            enemy.getEArmor().setDurability(enemy.getEArmor().getDurability()-player.getEWeapon().getStrength());
        } else if (player.getEWeapon().equals(null)){
            enemy.setHealth(enemy.getHealth()-player.getPower());
            enemy.getEArmor().setDurability(enemy.getEArmor().getDurability()-player.getPower());
        } else {
            enemy.setHealth(enemy.getHealth()-player.getPower());
            player.getEWeapon().setDurability(player.getEWeapon().getDurability()-enemy.getEArmor().getToughness());
            enemy.getEArmor().setDurability(enemy.getEArmor().getDurability()-player.getEWeapon().getStrength());
        }
        if(player.getEWeapon().getDurability()<=0){
            player.removeItem(player.getEWeapon());
        }
        if (enemy.getEArmor().getDurability()<=0){
            enemy.removeItem(enemy.getEArmor());
        }
    }
}
