public class Armor extends Item{

    private int toughness;
    private boolean equipped;

    // Constructor
    public Armor(int weight, String itemName, int durability, int toughness, boolean equipped) {
        super(weight, itemName, durability);
        this.toughness=toughness;
        this.equipped = equipped;
    }

    public int getToughness(){
        return toughness;
    }

    // Inheritence methods
    public boolean getEquipped(){
        return equipped;
    }
    public String getItemType(){
        return "Armor";
    }

    // Just used to change the equipped value. We will need to have this boolean here and a value for equipped armor/weapon in inventory for reading and writing between files
    public void changeEquip(){
        if(equipped){
            equipped=false;
        } else {
            equipped=true;
        }
    }

    //TODO change this to just say return toughness. It may mess up things though
    public int getValue(){
        if(getItemType().equals("Armor")){
            return toughness;
        }
        return 0;
    }
}
