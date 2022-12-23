import java.util.ArrayList;

public class Potions extends ItemInventory  implements ChooseItem, ItemInitializable,Marketable{//handles the potions for heroes.
    String Name;
    int price , level , attributeIncrease  ;
    int typePointer = 0 ;
    public ArrayList<Potions> MarketPotions;
    String Attribute ;
    public Potions(String Name, int price , int level , int attributeIncrease ,String attributeAffected){
        this.Name = Name;
        this.price = price;
        this.level = level;
        this.attributeIncrease = attributeIncrease;

        this.Attribute = attributeAffected;
        this.MarketPotions = new ArrayList<>();

    }
    public ArrayList<String> PotionType = new ArrayList<String>();

    public Potions() {

    }

    public void initialize(){
        PotionType.add("Healing_Potion");
        PotionType.add("Strength_Potion");
        PotionType.add("Magic_Potion");
        PotionType.add("Luck_Elixir");
        PotionType.add("Mermaid_Tears");
        PotionType.add("Ambrosia");
    }


    public Object choose(){
            Potions potion = new Potions();
        Logic.printHeader("Choose a Potion for your player (At the start you can choose only one Potion):");
        for(int i=0 ; i < PotionType.size() ;i++){
            System.out.println("("+(i+1)+")"+" "+PotionType.get(typePointer+i));
        }

        int Choice = Logic.UserInput("->",6);
        if(Choice == 1){
            Logic.printHeader("You Choose : "+ PotionType.get(typePointer) +"!");
            potion = new Potions(PotionType.get(typePointer),250,1,100,"Health");
            return potion;
        }
        else if(Choice == 2){
            Logic.printHeader("You Choose : "+ PotionType.get(typePointer+1) +"!");
            potion = new Potions(PotionType.get(typePointer+1),200,1,75,"Strength");
            return potion;
        }
        else if(Choice == 3){
            Logic.printHeader("You Choose : "+ PotionType.get(typePointer+2) +"!");
            potion = new Potions(PotionType.get(typePointer+2),350,2,100,"Mana");
            return potion;

        }
        else if(Choice == 4){
            Logic.printHeader("You Choose : "+ PotionType.get(typePointer+3) +"!");
            potion = new Potions(PotionType.get(typePointer+3),500,4,65,"Agility");
            return potion;
        }
        else if(Choice == 5){
            Logic.printHeader("You Choose : "+ PotionType.get(typePointer+4) +"!");
            potion = new Potions(PotionType.get(typePointer+4),850,5,100,"Any");
            return potion;
        }
        else if(Choice == 6){
            Logic.printHeader("You Choose : "+ PotionType.get(typePointer+5) +"!");
            potion = new Potions(PotionType.get(typePointer+5),1000,8,150,"All");
            return potion;
        }
        return potion;

    }
    public ArrayList<Potions> MarketPotions(){

        MarketPotions.add(new Potions("Healing_Potion",250,1,100,"Health"));

        MarketPotions.add(new Potions("Strength_Potion",200,1,75,"Strenght"));

        MarketPotions.add(new Potions("Magic_Potion",350,2,100,"Mana"));
        MarketPotions.add(new Potions("Luck_Elixir",500,4,65,"Agility"));

        MarketPotions.add(new Potions("Mermaid_Tears",850,5,100,"Any"));
        MarketPotions.add(new Potions("Ambrosia",1000,8,150,"All"));

        return MarketPotions;
    }
}