public class Weapon extends Item{

    private int strength;
    private boolean equipped;

    // Constructor
    public Weapon(int weight, String itemName, int durability, int strength, boolean equipped) {
        super(weight, itemName, durability);
        this.strength=strength;
        this.equipped = equipped;
    }

    public int getStrength(){
        return strength;
    }

    //Inheritence methods
    public boolean getEquipped(){
        return equipped;
    }
    public String getItemType(){ return "Weapon"; }

    // Just used to change the equipped value. We will need to have this boolean here and a value for equipped armor/weapon in inventory for reading and writing between files
    public void changeEquip(){
        if(equipped){
            equipped=false;
        } else {
            equipped=true;
        }
    }

    //TODO change this to just say return strength. It may mess up things though
    public int getValue(){
        if(getItemType().equals("Weapon")){
            return strength;
        }
        return 0;
    }
}
