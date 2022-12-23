public class Warrior extends Heroes implements Fightable,CastSpellable{//handles the warrior type of heroes
    int typePointer ;
    public String[] WarriorType = {"Gaerdal_Ironhand", "Sehanine_Monnbow", "Muamman_Duathall","Flandal_Steelskin","Undefeated_Yoj","Eunoia_Cyn"};

    public Warrior(String name, int maxHP, int HP, int XP, int MP, int strengthVal, int dexVal, int agileVal, int gold, int inventory, int type) {
        this.typePointer = 0;
        chooseWarrior();
    }




    public Object chooseWarrior(){
        Logic.printHeader("Choose a Type for your player :");
        System.out.println("(1)"+ WarriorType[typePointer]);
        System.out.println("(2)"+ WarriorType[typePointer+1]);
        System.out.println("(3)"+ WarriorType[typePointer+2]);
        System.out.println("(4)"+ WarriorType[typePointer+3]);
        System.out.println("(5)"+ WarriorType[typePointer+4]);
        System.out.println("(6)"+ WarriorType[typePointer+5]);
        int Choice = Logic.UserInput("->",6);
        if(Choice == 1){
            Logic.printHeader("You Choose : "+ WarriorType[typePointer] +"!");
            Warrior warrior = new Warrior(WarriorType[typePointer],1000, 100, 7, 100, 700, 600, 500,1354, 0, 0);
        }
        else if(Choice == 2){
            Logic.printHeader("You Choose : "+ WarriorType[typePointer+1] +"!");
        }
        else if(Choice == 3){
            Logic.printHeader("You Choose : "+ WarriorType[typePointer+2] +"!");

        }
        else if(Choice == 4){
            Logic.printHeader("You Choose : "+ WarriorType[typePointer+3] +"!");

        }
        else if(Choice == 4){
            Logic.printHeader("You Choose : "+ WarriorType[typePointer+4] +"!");

        } else if(Choice == 5){
            Logic.printHeader("You Choose : "+ WarriorType[typePointer+5] +"!");

        }


        return null;
    }
}
