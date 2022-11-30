import java.util.Random;

public abstract class Character {
    protected int health;
    protected int power;
    protected int defense;

    //A number that we are able to change to our needs
    protected static final int maxInventoryWeight = 35;

    public Character(){
        health=100;
        power=1;
        defense=1;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDefense() {
        return defense;
    }

    public Weapon weaponlist(int turnCount){
        Random random = new Random();
        int rand = (random.nextInt(3));
        if(rand==0){
            turnCount--;
        } else if(rand==1){
            turnCount++;
        }
        random = new Random();
        switch (random.nextInt(6)){
            case 0: return new Weapon(5, "Iron Sword",  30+turnCount,15+turnCount,false);
            case 1: return new Weapon(2, "Wood Sword", 16+turnCount,12+turnCount, false);
            case 2: return new Weapon(10, "Copper Spear", 20+turnCount,16+turnCount, false);
            case 3: return new Weapon(5, "Copper Sword", 22+turnCount,15+turnCount, false);
            case 4: return new Weapon(8, "Iron Mace", 25+turnCount,18+turnCount, false);
            case 5: return new Weapon(2, "Iron Dagger", 17+turnCount, 20+turnCount,false);
        }
        return new Weapon(5, "Iron Sword", 30+turnCount, 15+turnCount,false);
    }

    public Armor armorList(int turnCount){
        Random random = new Random();
        int rand = (random.nextInt(3));
        if(rand==0){
            turnCount--;
        } else if(rand==1){
            turnCount++;
        }
        random = new Random();
        switch (random.nextInt(6)){
            case 0: return new Armor(20, "Iron Armor",  25+turnCount, 4+turnCount,false);
            case 1: return new Armor(14, "Chainmail Armor", 22 +turnCount, 3+turnCount, false);
            case 2: return new Armor(20, "Spiked armor", 10+turnCount, 6+turnCount, false);
            case 3: return new Armor(8, "Agile armor", 12+turnCount, 2+turnCount, false);
            case 4: return new Armor(30, "Stone Armor", 40+turnCount, 4+turnCount, false);
            case 5: return new Armor(3, "Rusty Armor", 2+turnCount, 1+turnCount, false);
        } return new Armor(20, "Iron Armor",  25+turnCount, 4+turnCount,false);
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

}
