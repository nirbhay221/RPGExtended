import java.util.ArrayList;

public class FireSpells extends Spells  implements ChooseItem, ItemInitializable{
    String Name;
    int price , level , Damage, MPCost  ;
    public ArrayList<FireSpells> marketFireSpells;
    int typePointer = 0 ;
    public FireSpells(String Name, int price , int level , int Damage,int MPCost ){//handles the firespells of the heroes
        super();
        this.Name = Name;
        this.price = price;
        this.level = level;
        this.Damage = Damage;
        this.MPCost =MPCost;
        marketFireSpells =new ArrayList<>();

    }
    public ArrayList<String> FireSpellType = new ArrayList<String>();

    public FireSpells() {

    }

    public void initialize(){
        FireSpellType.add("Flame_Tornado");
        FireSpellType.add("Breath_of_Fire");
        FireSpellType.add("Heat_Wave");
        FireSpellType.add("Lava_Comet");
        FireSpellType.add("Hell_Storm");
    }


    public Object choose(){
        FireSpells spell = new FireSpells();
        Logic.printHeader("Choose a Fire Spell for your player (At the start you can choose only one Fire Spell):");
        for(int i=0 ; i < FireSpellType.size() ;i++){
            System.out.println("("+(i+1)+")"+" "+FireSpellType.get(typePointer+i));
        }

        int Choice = Logic.UserInput("->",5);
        if(Choice == 1){
            Logic.printHeader("You Choose : "+ FireSpellType.get(typePointer) +"!");
            spell = new FireSpells(FireSpellType.get(typePointer),700,4,850,300);
            return spell;
        }
        else if(Choice == 2){
            Logic.printHeader("You Choose : "+ FireSpellType.get(typePointer+1) +"!");
            spell = new FireSpells(FireSpellType.get(typePointer+1),350,1,450,100);
            return spell;
        }
        else if(Choice == 3){
            Logic.printHeader("You Choose : "+ FireSpellType.get(typePointer+2) +"!");
            spell = new FireSpells(FireSpellType.get(typePointer+2),450,2,600,150);
            return spell;

        }
        else if(Choice == 4){
            Logic.printHeader("You Choose : "+ FireSpellType.get(typePointer+3) +"!");
            spell = new FireSpells(FireSpellType.get(typePointer+3),800,7,1000,550);
            return spell;
        }
        else if(Choice == 5){
            Logic.printHeader("You Choose : "+ FireSpellType.get(typePointer+4) +"!");
            spell = new FireSpells(FireSpellType.get(typePointer+4),600,3,950,600);
            return spell;
        }

        return spell;

    }
    public ArrayList<FireSpells> MarketFireSpells(){

        marketFireSpells.add(new FireSpells("Fire Dragon Attack",3500,7,5500,2));

        marketFireSpells.add(new FireSpells("Fire Blasts",2300,6,4000,3));

        marketFireSpells.add(new FireSpells("Fireballs Attack",2500,9,2200,6));
        marketFireSpells.add(new FireSpells("Blue Rage",2450,4,4850,4));

        marketFireSpells.add(new FireSpells("White Dragon Attack",4400,5,5600,5));
        marketFireSpells.add(new FireSpells("Internal Flame",4200,12,5250,6));

        return marketFireSpells;
    }
}