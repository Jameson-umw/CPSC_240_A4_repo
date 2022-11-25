public abstract class Item {

    protected int weight;
    protected String itemName;

    // Constructor
    public Item(int weight, String itemName){
        this.weight=weight;
        this.itemName=itemName;
    }

    // getter methods
    public String getItemName(){
        return itemName;
    }

    // getter methods
    public int getWeight(){
        return weight;
    }

    abstract public boolean getEquipped();
    abstract public String getItemType();
    abstract public void changeEquip();
}
