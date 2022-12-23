public class Spells extends ItemInventory{//handles the spells of the heroes
    String Name;
    int price , level , Damage, MPCost  ;
    int typePointer = 0 ;
    public Spells(String Name, int price , int level , int Damage,int MPCost ){

        this.Name = Name;
        this.price = price;
        this.level = level;
        this.Damage = Damage;
        this.MPCost =MPCost;

    }


    public Spells() {

    }
}