import java.util.ArrayList;

public class Armor extends ItemInventory  implements ChooseItem, ItemInitializable{//handles the armor for individual heroes
    String Name;
    int price , level , damageReductionValue  ;
    public ArrayList<Armor> marketArmor;
    int typePointer = 0 ;
    public Armor(String Name, int price , int level , int damageReductionValue ){
        this.Name = Name;
        this.price = price;
        this.level = level;
        this.damageReductionValue = damageReductionValue;
        marketArmor = new ArrayList<Armor>();

    }
    public ArrayList<String> ArmorType = new ArrayList<String>();

    public Armor() {

    }

    public void initialize(){
        ArmorType.add("Platinum_Shield");
        ArmorType.add("Breastplate");
        ArmorType.add("Full_Body_Armor");
        ArmorType.add("Wizard_Shield");
        ArmorType.add("Guardian_Angel");
    }


    public Object choose(){
       Armor armor = new Armor();
            Logic.printHeader("Choose a Armor for your player (At the start you can choose only one armor):");
        for(int i=0 ; i < ArmorType.size() ;i++){
            System.out.println("("+(i+1)+")"+" "+ArmorType.get(typePointer+i));
        }

        int Choice = Logic.UserInput("->",5);
        if(Choice == 1){
            Logic.printHeader("You Choose : "+ ArmorType.get(typePointer) +"!");
            armor = new Armor(ArmorType.get(typePointer),150,1,200);
            return armor;
        }
        else if(Choice == 2){
            Logic.printHeader("You Choose : "+ ArmorType.get(typePointer+1) +"!");
            armor = new Armor(ArmorType.get(typePointer+1),350,3,600);
            return armor;
        }
        else if(Choice == 3){
            Logic.printHeader("You Choose : "+ ArmorType.get(typePointer+2) +"!");
            armor = new Armor(ArmorType.get(typePointer+2),1000,8,1100);
            return armor;

        }
        else if(Choice == 4){
            Logic.printHeader("You Choose : "+ ArmorType.get(typePointer+3) +"!");
            armor = new Armor(ArmorType.get(typePointer+3),1200,10,1500);
            return armor;
        }
        else if(Choice == 5){
            Logic.printHeader("You Choose : "+ ArmorType.get(typePointer+4) +"!");
            armor = new Armor(ArmorType.get(typePointer+4),1000,10,1000);
            return armor;
        }
        return armor;

    }
    public ArrayList<Armor> MarketArmor(){

        marketArmor.add(new Armor("Platinum_Shield",2000,20,2000));

        marketArmor.add(new Armor("Breast Plate",1000,5,1222));

        marketArmor.add(new Armor("Full_Body_Armor",3000,8,3000));
        marketArmor.add(new Armor("Wizard Shield",3021,22,4213));

        marketArmor.add(new Armor("Guardian Angel",1231,12,2134));
        marketArmor.add(new Armor("Internal Protection",5000,10,6000));

        return marketArmor;
    }

}