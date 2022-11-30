public abstract class Item {

    protected int weight;
    protected String itemName;
    private int durability;

    // Constructor
    public Item(int weight, String itemName, int durability){
        this.weight=weight;
        this.itemName=itemName;
        this.durability=durability;

    }

    public int getDurability(){
        return durability;
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