public class Armor extends Item{

    private int toughness;
    private boolean equipped;

    // Constructor
    public Armor(int weight, String itemName, int durability, int toughness, boolean equipped) {
        super(weight, itemName, durability);
        this.toughness=toughness;
        this.equipped = equipped;
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

    public int getToughness(){
        return toughness;
    }
}
