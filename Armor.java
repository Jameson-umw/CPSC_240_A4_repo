public class Armor extends Item{

    private int strength;
    private int durability;
    private boolean equipped;

    // Constructor
    public Armor(int weight, String itemName, int strength, int durability, boolean equipped) {
        super(weight, itemName);
        this.strength = strength;
        this.durability = durability;
        this.equipped = equipped;
    }

    // getter methods
    public int getStrength(){
        return strength;
    }

    // getter methods
    public int getDurability(){
        return durability;
    }

    // getter methods
    public boolean getEquipped(){
        return equipped;
    }

    // getter methods
    public String getItemType(){
        return "Armor";
    }

    // Just used to change the equipped value. We will need to have this boolean here and a value for equipped armor/weapon in inventory for reading and writing between files
    public void changeEquip(){
        if(equipped==true){
            equipped=false;
        } else {
            equipped=true;
        }
    }
}
