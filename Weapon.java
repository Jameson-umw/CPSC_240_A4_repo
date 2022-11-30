public class Weapon extends Item{

    private int strength;
    private boolean equipped;

    // Constructor
    public Weapon(int weight, String itemName, int durability, int strength, boolean equipped) {
        super(weight, itemName, durability);
        this.strength=strength;
        this.equipped = equipped;
    }

    // getter methods
    public boolean getEquipped(){
        return equipped;
    }

    // getter methods
    public String getItemType(){
        return "Weapon";
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
