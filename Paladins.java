public class Paladins extends Heroes implements Fightable,CastSpellable{//handles the paladin type of heroes
    int typePointer ;
    public String[] WarriorType = {"Gaerdal_Ironhand", "Sehanine_Monnbow", "Muamman_Duathall","Flandal_Steelskin","Undefeated_Yoj","Eunoia_Cyn"};

    public Paladins(String name, int maxHP, int HP, int XP, int MP, int strengthVal, int dexVal, int agileVal, int gold, int inventory, int type) {
        this.typePointer = 0;
        choosePaladinType();
    }



    public String[] PaladinType = {"Parzival", "Sehanine_Moonbow", "Skoraeus_Stonebones","Garl_Glittergold","Amaryllis_Astra","Caliber_Heist"};
    public Object choosePaladinType(){
        Logic.printHeader("Choose a type of paladin for your hero :");
        Heroes hero = new Heroes();
        System.out.println("(1)"+ PaladinType[typePointer]);
        System.out.println("(2)"+ PaladinType[typePointer+1]);
        System.out.println("(3)"+ PaladinType[typePointer+2]);
        System.out.println("(4)"+ PaladinType[typePointer+3]);
        System.out.println("(5)"+ PaladinType[typePointer+4]);
        System.out.println("(6)"+ PaladinType[typePointer+5]);
        int Choice = Logic.UserInput("->",6);
        if(Choice == 1){
            Logic.printHeader("You Choose : "+ PaladinType[typePointer] +"!");
            hero = new Heroes(PaladinType[typePointer],100,100,7,300,750, 700 ,650,2500 ,0,3,1,0);
            return hero;


        }
        else if(Choice == 2){
            Logic.printHeader("You Choose : "+ PaladinType[typePointer+1] +"!");
            hero = new Heroes(PaladinType[typePointer+1],100,100,7,300,750, 700,700,2500 ,0,3,1,0);
            return hero;
        }
        else if(Choice == 3){
            Logic.printHeader("You Choose : "+ PaladinType[typePointer+2] +"!");
            hero = new Heroes(PaladinType[typePointer+2],100,100,4,250,650, 350 ,600,2500 ,0,3,1,0);
            return hero;

        }
        else if(Choice == 4){
            Logic.printHeader("You Choose : "+ PaladinType[typePointer+3] +"!");
            hero = new Heroes(PaladinType[typePointer+3],100,100,5,100,600, 400 ,500,2500 ,0,3,1,0);
            return hero;
        }
        else if(Choice == 5){
            Logic.printHeader("You Choose : "+ PaladinType[typePointer+4] +"!");
            hero = new Heroes(PaladinType[typePointer+4],100,100,5,500,500, 500 ,400,2500 ,0,3,1,0);
            return hero;
        } else if(Choice == 6){
            Logic.printHeader("You Choose : "+ PaladinType[typePointer+5] +"!");
            hero = new Heroes(PaladinType[typePointer+5],100,100,8,400,400, 400 ,400,2500 ,0,3,1,0);
            return hero;
        }
        return hero;

    }
}