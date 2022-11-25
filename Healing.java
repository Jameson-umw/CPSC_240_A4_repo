public class Healing extends Item{

    private int healValue;

    // Constructor
    public Healing(int weight, String itemName, int healValue){
        super(weight, itemName);
        this.healValue=healValue;
    }

    // getter methods
    public int getHealValue(){
        return healValue;
    }

    // getter methods
    public String getItemType(){
        return "Healing";
    }

    //TODO: Program a failure mechanism if this method is called in Healing
    public void changeEquip(){
    }
    //TODO: Program a failure mechanism if this method is called in Healing
    public boolean getEquipped(){
        return false;
    }
}
