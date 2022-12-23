import java.util.ArrayList;

public class Weapons extends ItemInventory  implements ChooseItem, ItemInitializable{//handles the weapons of the heroes .
    String Name;
    int price , level , damageValue , noHands ;
    int typePointer = 0 ;
    public ArrayList<Weapons> MarketWeapons ;
    public Weapons(String Name, int price , int level , int damageVal ,int noHands){
    this.Name = Name;
    this.price = price;
    this.level = level;
    this.damageValue = damageVal;
    this.noHands = noHands;
    MarketWeapons =new ArrayList<>();

    }
    public ArrayList<String> WeaponType = new ArrayList<String>();

    public Weapons() {

    }

    public void initialize(){
       WeaponType.add("Sword");
       WeaponType.add("Bow");
       WeaponType.add("Scythe");
       WeaponType.add("Axe");
       WeaponType.add("TSwords");
       WeaponType.add("Dagger");
   }


    public Object choose(){
        Weapons weapon = new Weapons();
        Logic.printHeader("Choose a Weapon for your player (At the start you can choose only one weapon):");
        for(int i=0 ; i < WeaponType.size() ;i++){
            System.out.println("("+(i+1)+")"+" "+WeaponType.get(typePointer+i));
        }

        int Choice = Logic.UserInput("->",6);
        if(Choice == 1){
            Logic.printHeader("You Choose : "+ WeaponType.get(typePointer) +"!");
            weapon = new Weapons(WeaponType.get(typePointer),500,1,800,1);
            return weapon;
        }
        else if(Choice == 2){
            Logic.printHeader("You Choose : "+ WeaponType.get(typePointer+1) +"!");
            weapon = new Weapons(WeaponType.get(typePointer+1),300,2,500,2);
            return weapon;
        }
        else if(Choice == 3){
            Logic.printHeader("You Choose : "+ WeaponType.get(typePointer+2) +"!");
            weapon = new Weapons(WeaponType.get(typePointer+2),1000,6,1100,2);
            return weapon;

        }
        else if(Choice == 4){
            Logic.printHeader("You Choose : "+ WeaponType.get(typePointer+3) +"!");
            weapon = new Weapons(WeaponType.get(typePointer+3),550,5,850,1);
            return weapon;
        }
        else if(Choice == 5){
            Logic.printHeader("You Choose : "+ WeaponType.get(typePointer+4) +"!");
            weapon = new Weapons(WeaponType.get(typePointer+4),1400,8,1600,2);
            return weapon;
        } else if(Choice == 6){
            Logic.printHeader("You Choose : "+ WeaponType.get(typePointer+5) +"!");
            weapon = new Weapons(WeaponType.get(typePointer+5),200,1,250,1);
            return weapon;
        }
        return weapon;

    }
    public ArrayList<Weapons> MarketWeapons(){
        Weapons Weapons = new Weapons();
        Weapons =new Weapons("Sword",20,1,1500,1);
        MarketWeapons.add(Weapons);

       MarketWeapons.add(new Weapons("Bow",2300,2,1000,2));

        MarketWeapons.add(new Weapons("Scythe",1500,6,2200,2));
        MarketWeapons.add(new Weapons("Axe",1550,5,1850,1));

        MarketWeapons.add(new Weapons("Spear",3400,8,2600,2));
        MarketWeapons.add(new Weapons("Dagger",2200,1,3250,1));

        return MarketWeapons;
        }


}