import java.util.ArrayList;

public class IceSpells extends Spells  implements ChooseItem, ItemInitializable{//handles the icespells of the heroes
    String Name;
    int price , level , Damage, MPCost  ;
    int typePointer = 0 ;
    public ArrayList<IceSpells> marketIceSpells;
    public IceSpells(String Name, int price , int level , int Damage,int MPCost ){
        this.Name = Name;
        this.price = price;
        this.level = level;
        this.Damage = Damage;
        this.MPCost =MPCost;
        marketIceSpells = new ArrayList<>();
    }
    public ArrayList<String> IceSpellType = new ArrayList<String>();

    public IceSpells() {

    }

    public void initialize(){
        IceSpellType.add("Snow_Cannon");
        IceSpellType.add("Ice_Blade");
        IceSpellType.add("Frost_Blizzard");
        IceSpellType.add("Arctic_Storm");
    }


    public Object choose(){
        IceSpells spell = new IceSpells();
        Logic.printHeader("Choose a Ice Spell for your player (At the start you can choose only one Ice Spell):");
        for(int i=0 ; i < IceSpellType.size() ;i++){
            System.out.println("("+(i+1)+")"+" "+IceSpellType.get(typePointer+i));
        }

        int Choice = Logic.UserInput("->",4);
        if(Choice == 1){
            Logic.printHeader("You Choose : "+ IceSpellType.get(typePointer) +"!");
            spell = new IceSpells(IceSpellType.get(typePointer),500,2,650,250);
            return spell;
        }
        else if(Choice == 2){
            Logic.printHeader("You Choose : "+ IceSpellType.get(typePointer+1) +"!");
            spell = new IceSpells(IceSpellType.get(typePointer+1),250,1,450,100);
            return spell;
        }
        else if(Choice == 3){
            Logic.printHeader("You Choose : "+ IceSpellType.get(typePointer+2) +"!");
            spell = new IceSpells(IceSpellType.get(typePointer+2),750,5,850,350);
            return spell;

        }
        else if(Choice == 4){
            Logic.printHeader("You Choose : "+ IceSpellType.get(typePointer+3) +"!");
            spell = new IceSpells(IceSpellType.get(typePointer+3),700,6,800,300);
            return spell;
        }

        return spell;

    }
    public ArrayList<IceSpells> MarketIceSpells(){

        marketIceSpells.add(new IceSpells("Ice Crack Rage",3500,5,5500,3));

        marketIceSpells.add(new IceSpells("Frost Bite Attack",2300,7,5600,2));

        marketIceSpells.add(new IceSpells("Colossal Ice Swamp",2500,4,7200,2));
        marketIceSpells.add(new IceSpells("Ice Spear Attack",2450,8,6850,4));

        marketIceSpells.add(new IceSpells("Crystal Crack Attack",4400,6,4600,5));
        marketIceSpells.add(new IceSpells("Poisoned Ice gas",4200,9,3250,3));

        return marketIceSpells;
    }
}