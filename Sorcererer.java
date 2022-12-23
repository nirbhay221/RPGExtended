public class Sorcererer extends Heroes implements Fightable,CastSpellable {//handle the sorecererer type of heroes.
    int typePointer;
    public String[] WarriorType = {"Gaerdal_Ironhand", "Sehanine_Monnbow", "Muamman_Duathall", "Flandal_Steelskin", "Undefeated_Yoj", "Eunoia_Cyn"};

    public Sorcererer(String name, int maxHP, int HP, int XP, int MP, int strengthVal, int dexVal, int agileVal, int gold, int inventory, int type) {
        this.typePointer = 0;
        chooseSorcerer();
    }


    public String[] SorcererType = {"Rillifane_Rallathil", "Segojan_Earthcaller", "Reign_Havoc", "Reverie_Ashels", "Kalabar", "Skye_Soar"};

    public Object chooseSorcerer() {
        Logic.printHeader("Choose a type of sorcerer for your hero :");
        Heroes hero = new Heroes();
        System.out.println("(1)" + SorcererType[typePointer]);
        System.out.println("(2)" + SorcererType[typePointer + 1]);
        System.out.println("(3)" + SorcererType[typePointer + 2]);
        System.out.println("(4)" + SorcererType[typePointer + 3]);
        System.out.println("(5)" + SorcererType[typePointer + 4]);
        System.out.println("(6)" + SorcererType[typePointer + 5]);
        int Choice = Logic.UserInput("->", 6);
        if (Choice == 1) {
            Logic.printHeader("You Choose : " + SorcererType[typePointer] + "!");
            hero = new Heroes(SorcererType[typePointer], 100, 100, 7, 1300, 750, 500, 450, 2500, 0, 2, 1, 0);
            return hero;


        } else if (Choice == 2) {
            Logic.printHeader("You Choose : " + SorcererType[typePointer + 1] + "!");
            hero = new Heroes(SorcererType[typePointer + 1], 100, 100, 8, 900, 800, 650, 500, 2500, 0, 2, 1, 0);
            return hero;
        } else if (Choice == 3) {
            Logic.printHeader("You Choose : " + SorcererType[typePointer + 2] + "!");
            hero = new Heroes(SorcererType[typePointer + 2], 100, 100, 6, 800, 800, 800, 800, 2500, 0, 2, 1, 0);
            return hero;

        } else if (Choice == 4) {
            Logic.printHeader("You Choose : " + SorcererType[typePointer + 3] + "!");
            hero = new Heroes(SorcererType[typePointer + 3], 100, 100, 7, 900, 850, 400, 700, 2500, 0, 2, 1, 0);
            return hero;
        } else if (Choice == 5) {
            Logic.printHeader("You Choose : " + SorcererType[typePointer + 4] + "!");
            hero = new Heroes(SorcererType[typePointer + 4], 100, 100, 7, 800, 850, 600, 400, 2500, 0, 2, 1, 0);
            return hero;
        } else if (Choice == 6) {
            Logic.printHeader("You Choose : " + SorcererType[typePointer + 5] + "!");
            hero = new Heroes(SorcererType[typePointer + 5], 100, 100, 6, 1000, 700, 500, 400, 2500, 0, 2, 1, 0);
            return hero;
        }
        return hero;

    }
}