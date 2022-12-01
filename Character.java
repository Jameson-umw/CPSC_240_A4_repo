import java.util.Random;

public abstract class Character {
    protected int health;
    protected int power;
    protected int defense;
    protected static final int maxInventoryWeight = 55;

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

    public void setDefense(int defense) {
        this.defense = defense;
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
            case 0: return new Weapon(5, "Iron Sword",  50+turnCount,14+turnCount,false);
            case 1: return new Weapon(2, "Wood Sword", 30+turnCount,13+turnCount, false);
            case 2: return new Weapon(10, "Copper Spear", 40+turnCount,15+turnCount, false);
            case 3: return new Weapon(5, "Copper Sword", 42+turnCount,14+turnCount, false);
            case 4: return new Weapon(8, "Iron Mace", 45+turnCount,17+turnCount, false);
            case 5: return new Weapon(2, "Iron Dagger", 37+turnCount, 20+turnCount,false);
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
            case 0: return new Armor(20, "Iron Armor",  65+turnCount, 4+turnCount,false);
            case 1: return new Armor(14, "Chainmail Armor", 62 +turnCount, 3+turnCount, false);
            case 2: return new Armor(20, "Spiked armor", 50+turnCount, 5+turnCount, false);
            case 3: return new Armor(8, "Agile armor", 52+turnCount, 2+turnCount, false);
            case 4: return new Armor(30, "Stone Armor", 100+turnCount, 3+turnCount, false);
            case 5: return new Armor(3, "Rusty Armor", 22+turnCount, 1+turnCount, false);
        } return new Armor(20, "Iron Armor",  25+turnCount, 4+turnCount,false);
    }

    // Inheritence methods
    abstract public Weapon getEWeapon();
    abstract public Armor getEArmor();
    abstract public void removeItem(Item item);


}
