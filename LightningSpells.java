import java.util.ArrayList;

public class LightningSpells extends Spells  implements ChooseItem, ItemInitializable{//handles the lightning spells of the heroes
    String Name;
    int price , level , Damage, MPCost  ;
    public ArrayList<LightningSpells> marketLightningSpells;
    int typePointer = 0 ;
    public LightningSpells(String Name, int price , int level , int Damage,int MPCost ){
        this.Name = Name;
        this.price = price;
        this.level = level;
        this.Damage = Damage;
        this.MPCost =MPCost;
        marketLightningSpells =new ArrayList<>();
    }
    public ArrayList<String> LightningSpellType = new ArrayList<String>();

    public LightningSpells() {

    }

    public void initialize(){
        LightningSpellType.add("Lightning_Dagger");
        LightningSpellType.add("Thunder_Blast");
        LightningSpellType.add("Electric_Arrows");
        LightningSpellType.add("Spark_Needles");
    }


    public Object choose(){
        LightningSpells spell = new LightningSpells();
        Logic.printHeader("Choose a Lightning Spell for your player (At the start you can choose only one Lightning Spell):");
        for(int i=0 ; i < LightningSpellType.size() ;i++){
            System.out.println("("+(i+1)+")"+" "+LightningSpellType.get(typePointer+i));
        }

        int Choice = Logic.UserInput("->",4);
        if(Choice == 1){
            Logic.printHeader("You Choose : "+ LightningSpellType.get(typePointer) +"!");
            spell = new LightningSpells(LightningSpellType.get(typePointer),400,1,500,150);
            return spell;
        }
        else if(Choice == 2){
            Logic.printHeader("You Choose : "+ LightningSpellType.get(typePointer+1) +"!");
            spell = new LightningSpells(LightningSpellType.get(typePointer+1),750,4,950,400);
            return spell;
        }
        else if(Choice == 3){
            Logic.printHeader("You Choose : "+ LightningSpellType.get(typePointer+2) +"!");
            spell = new LightningSpells(LightningSpellType.get(typePointer+2),550,5,650,200);
            return spell;

        }
        else if(Choice == 4){
            Logic.printHeader("You Choose : "+ LightningSpellType.get(typePointer+3) +"!");
            spell = new LightningSpells(LightningSpellType.get(typePointer+3),500,2,600,200);
            return spell;
        }

        return spell;

    }
    public ArrayList<LightningSpells> MarketLightningSpells(){

        marketLightningSpells.add(new LightningSpells("Lightning Thunder",4500,9,4500,5));

        marketLightningSpells.add(new LightningSpells("Lightning Bolt Rage",4300,12,5000,4));

        marketLightningSpells.add(new LightningSpells("Thunder Blast",3500,8,4200,5));
        marketLightningSpells.add(new LightningSpells("Electric Arrows",3450,5,3850,3));

        marketLightningSpells.add(new LightningSpells("Thunder Rage",6400,10,6600,2));
        marketLightningSpells.add(new LightningSpells("Cloud Swamp",6200,1,6250,3));

        return marketLightningSpells;
    }
}