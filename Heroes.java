import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Heroes extends Characters implements Fightable,CastSpellable{//handles all the heroes

    public int AttackUpgradePtr, DefendUpgradePtr;
    Scanner sc = new Scanner(System.in);
    boolean isEquippedEmpty=true;
    boolean isArmorEquippedEmpty = true;
    boolean useLightningEmpty = true;
    boolean useFireEmpty = true;
    boolean useIceEmpty = true;
    int weaponDamage = 0;
    int armorDefense = 0;
    public int gold , restsLeft , pots;
    int ManaPoints;
    int StrengthValue ;
    int dValue ;
    int agileValue ;
    int inventory;
    public ArrayList<Weapons>weaponInventory ;
    public ArrayList<Weapons>equippedWeapon ;
    public ArrayList<Armor> armorInventory;
    public ArrayList<Armor>equippedArmor ;
    public ArrayList<LightningSpells> lightningSpellsInventory ;
    public ArrayList<LightningSpells>equippedlightningSpells ;
    public ArrayList<IceSpells>iceSpellsInventory ;
    public ArrayList<IceSpells>equippediceSpells ;
    public ArrayList<FireSpells>fireSpellsInventory ;
    public ArrayList<FireSpells>equippedfireSpells ;
    public ArrayList<LightningSpells>useLightningSpell ;
    public ArrayList<FireSpells>useFireSpell ;
    public ArrayList<IceSpells>useIceSpell ;
    public ArrayList<Potions> potionsInventory;
    public ArrayList<Potions> equippedPotions;
    public ArrayList<Potions> usePotions;

    Warrior newWarrior;
    int typePointer ;
    int newType;
    int lane;
    public String[] HeroType = {"Warrior", "Sorcerer", "Paladin"};
    public String[] AttackUpgrades = {"Strength","Power","Might","GodLikeStrength"};
    public String[] DefendUpgrades = {"Heavy Bones","Stoneskin","Scale Armor","Holy Aura"};
    int level;
    public Heroes(String name,int maxHP,int HP,int XP,int MP ,int strengthVal, int dexVal,int agileVal,int gold , int inventory,int type,int level,int lane){
        super(name, maxHP,HP,XP);
        this.ManaPoints = MP ;
        this.StrengthValue = strengthVal;
        this.dValue = dexVal;
        this.agileValue = agileVal;
        this.AttackUpgradePtr= 0;
        this.DefendUpgradePtr= 0;
        this.gold= gold;
        this.inventory = inventory;
        this.restsLeft = 1;
        this.pots = 0 ;
        newType = type;
        this.level = level;
        this.typePointer= 0;
        weaponInventory = new ArrayList<Weapons>();
        equippedWeapon = new ArrayList<Weapons>();
this.lane = lane;
        armorInventory = new ArrayList<Armor>();
        equippedArmor = new ArrayList<Armor>();
        lightningSpellsInventory = new ArrayList<LightningSpells>();
        equippedlightningSpells = new ArrayList<LightningSpells>();
        iceSpellsInventory = new ArrayList<IceSpells>();
        equippediceSpells = new ArrayList<IceSpells>();
        fireSpellsInventory = new ArrayList<FireSpells>();
        equippedfireSpells = new ArrayList<FireSpells>();
        useFireSpell= new ArrayList<FireSpells>();
        useIceSpell = new ArrayList<IceSpells>();
        useLightningSpell = new ArrayList<LightningSpells>();
        potionsInventory = new ArrayList<Potions>();
        equippedPotions = new ArrayList<Potions>();
        usePotions = new ArrayList<Potions>();

//        chooseType();

    }
    public Heroes(){

    }

    @Override
    public int Attack() {
        if(equippedWeapon.isEmpty()){
            isEquippedEmpty = true;
        }
        else{
            isEquippedEmpty=false;
        }
        if(equippedArmor.isEmpty()){
            isArmorEquippedEmpty = true;
        }
        else{
            isArmorEquippedEmpty = false;
        }
        if(!isEquippedEmpty){
        for(int i =0 ;i< equippedWeapon.size();i++){
             weaponDamage += equippedWeapon.get(i).damageValue;
        }
        }
        if(!isArmorEquippedEmpty){
            for(int i =0 ;i< equippedArmor.size();i++){
                armorDefense += equippedArmor.get(i).damageReductionValue;
            }
        }
        if(!isEquippedEmpty){
            Random rnd = new Random();
            int randmAttack  = (int) (rnd.nextInt((int) (XP/4+((weaponDamage+StrengthValue)*0.006)+4))+XP/10+(weaponDamage+StrengthValue)*0.01);
            System.out.println(randmAttack);
            return randmAttack;
//            return (int)(Math.random()*(XP/4+((weaponDamage+StrengthValue)*0.0005)+4)+XP/10+(weaponDamage+StrengthValue)*0.0005+AttackUpgradePtr*0.5 + DefendUpgradePtr +0.5);
        }
        return (int)(Math.random()*(XP/4+((weaponDamage+StrengthValue*0.5)*0.04)+4)+XP/10+(weaponDamage+StrengthValue*0.5)*0.09+AttackUpgradePtr*0.5 + DefendUpgradePtr);
    }

    public int HandAttack() {
        return (int)(Math.random()*(XP/4+((StrengthValue*0.5)*0.04)+4)+XP/10+(StrengthValue*0.5)*0.09+AttackUpgradePtr*0.5 + DefendUpgradePtr);
    }    @Override
    public int Defend() {
        if(equippedArmor.isEmpty()){
            isArmorEquippedEmpty = true;
        }
        else{
            isArmorEquippedEmpty = false;
        }
        if(!isArmorEquippedEmpty){
            for(int i =0 ;i< equippedArmor.size();i++){
                armorDefense += equippedArmor.get(i).damageReductionValue;
            }
        }

        if(!isArmorEquippedEmpty) {

            return (int) (Math.random() * (XP / 4 + agileValue * 0.004 + 1+armorDefense*0.005) + XP / 10 + agileValue * 0.002 + 1+armorDefense*0.0005+ DefendUpgradePtr);
        }
        return (int) (Math.random() * (XP / 4 + agileValue * 0.02 + 1) + XP / 10 + agileValue * 0.002 + DefendUpgradePtr);
    }
    public int LightningSpellAttack(){
        if(useLightningSpell.isEmpty()){
            useLightningEmpty = true;
        }
        else{
            useLightningEmpty = false;
        }
        if(!useLightningEmpty){
            ManaPoints -= useLightningSpell.get(0).MPCost;
            return (int) (useLightningSpell.get(0).level/4+2*useLightningSpell.get(0).Damage*0.005+1+dValue*0.001);
        }
        return (int)(Math.random()*(XP/4+((StrengthValue)*0.005)+4)+XP/10+(StrengthValue)*0.0005+AttackUpgradePtr*0.5 + DefendUpgradePtr +0.5);

    }
    public int IceSpellAttack(){
        if(useIceSpell.isEmpty()){
            useIceEmpty = true;
        }
        else{
            useIceEmpty = false;
        }
        if(!useIceEmpty){
            ManaPoints -= useIceSpell.get(0).MPCost;
            return (int) (useIceSpell.get(0).level/4+2*useIceSpell.get(0).Damage*0.005+1+dValue*0.001);
        }
        return (int)(Math.random()*(XP/4+((StrengthValue)*0.005)+4)+XP/10+(StrengthValue)*0.0005+AttackUpgradePtr*0.5 + DefendUpgradePtr +0.5);

    }
    public int FireSpellAttack(){
        if(useFireSpell.isEmpty()){
            useFireEmpty = true;
        }
        else{
            useFireEmpty = false;
        }
        if(!useFireEmpty){
            ManaPoints -= useFireSpell.get(0).MPCost;
            return (int) (useFireSpell.get(0).level/4+2*useFireSpell.get(0).Damage*0.005+1+dValue*0.001);
        }
        return (int)(Math.random()*(XP/4+((StrengthValue)*0.005)+4)+XP/10+(StrengthValue)*0.0005+AttackUpgradePtr*0.5 + DefendUpgradePtr +0.5);

    }
    public String[] PaladinType = {"Parzival", "Sehanine_Moonbow", "Skoraeus_Stonebones","Garl_Glittergold","Amaryllis_Astra","Caliber_Heist"};
    public Object choosePaladinType(){
        Logic.printHeader("Choose a Type for your player :");
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
    public String[] SorcererType = {"Rillifane_Rallathil", "Segojan_Earthcaller", "Reign_Havoc","Reverie_Ashels","Kalabar","Skye_Soar"};
    public Object chooseSorcerer(){
        Logic.printHeader("Choose a Type for your player :");
        Heroes hero = new Heroes();
        System.out.println("(1)"+ SorcererType[typePointer]);
        System.out.println("(2)"+ SorcererType[typePointer+1]);
        System.out.println("(3)"+ SorcererType[typePointer+2]);
        System.out.println("(4)"+ SorcererType[typePointer+3]);
        System.out.println("(5)"+ SorcererType[typePointer+4]);
        System.out.println("(6)"+ SorcererType[typePointer+5]);
        int Choice = Logic.UserInput("->",6);
        if(Choice == 1){
            Logic.printHeader("You Choose : "+ SorcererType[typePointer] +"!");
            hero = new Heroes(SorcererType[typePointer],100,100,7,1300,750, 500 ,450,2500 ,0,2,1,0);
            return hero;


        }
        else if(Choice == 2){
            Logic.printHeader("You Choose : "+ SorcererType[typePointer+1] +"!");
            hero = new Heroes(SorcererType[typePointer+1],100,100,8,900,800, 650 ,500,2500 ,0,2,1,0);
            return hero;
        }
        else if(Choice == 3){
            Logic.printHeader("You Choose : "+ SorcererType[typePointer+2] +"!");
            hero = new Heroes(SorcererType[typePointer+2],100,100,6,800,800, 800 ,800,2500 ,0,2,1,0);
            return hero;

        }
        else if(Choice == 4){
            Logic.printHeader("You Choose : "+ SorcererType[typePointer+3] +"!");
            hero = new Heroes(SorcererType[typePointer+3],100,100,7,900,850, 400 ,700,2500 ,0,2,1,0);
            return hero;
        }
        else if(Choice == 5){
            Logic.printHeader("You Choose : "+ SorcererType[typePointer+4] +"!");
            hero = new Heroes(SorcererType[typePointer+4],100,100,7,800,850, 600 ,400,2500 ,0,2,1,0);
            return hero;
        } else if(Choice == 6){
            Logic.printHeader("You Choose : "+ SorcererType[typePointer+5] +"!");
            hero = new Heroes(SorcererType[typePointer+5],100,100,6,1000,700, 500 ,400,2500 ,0,2,1,0);
            return hero;
        }
        return hero;

    }
    public Object chooseWarrior(){
        Logic.printHeader("Choose a Type for your player :");
        Heroes hero = new Heroes();
        System.out.println("(1)"+ WarriorType[typePointer]);
        System.out.println("(2)"+ WarriorType[typePointer+1]);
        System.out.println("(3)"+ WarriorType[typePointer+2]);
        System.out.println("(4)"+ WarriorType[typePointer+3]);
        System.out.println("(5)"+ WarriorType[typePointer+4]);
        System.out.println("(6)"+ WarriorType[typePointer+5]);
        int Choice = Logic.UserInput("->",6);
        if(Choice == 1){
            Logic.printHeader("You Choose : "+ WarriorType[typePointer] +"!");
            hero = new Heroes(WarriorType[typePointer],100,100,7,100,700, 600 ,500,1354 ,0,1,1,0);
            return hero;


        }
        else if(Choice == 2){
            Logic.printHeader("You Choose : "+ WarriorType[typePointer+1] +"!");
           hero = new Heroes(WarriorType[typePointer+1],100,100,8,600,700, 500 ,800,1354 ,0,1,1,0);
            return hero;
        }
        else if(Choice == 3){
            Logic.printHeader("You Choose : "+ WarriorType[typePointer+2] +"!");
            hero = new Heroes(WarriorType[typePointer+2],100,100,6,300,900, 750 ,500,2500 ,0,1,1,0);
            return hero;

        }
        else if(Choice == 4){
            Logic.printHeader("You Choose : "+ WarriorType[typePointer+3] +"!");
           hero = new Heroes(WarriorType[typePointer+3],100,100,7,200,750, 700 ,650,2546 ,0,1,1,0);
            return hero;
        }
        else if(Choice == 5){
            Logic.printHeader("You Choose : "+ WarriorType[typePointer+4] +"!");
            hero = new Heroes(WarriorType[typePointer+4],100,100,7,400,800, 700 ,400,2500 ,0,1,1,0);
            return hero;
        } else if(Choice == 6){
            Logic.printHeader("You Choose : "+ WarriorType[typePointer+5] +"!");
          hero = new Heroes(WarriorType[typePointer+5],100,100,6,400,700, 600 ,800,2500 ,0,1,1,0);
            return hero;
        }
    return hero;

    }
    public String[] WarriorType = {"Gaerdal_Ironhand", "Sehanine_Monnbow", "Muamman_Duathall","Flandal_Steelskin","Undefeated_Yoj","Eunoia_Cyn"};

    public Object chooseType(){
        Logic.printHeader("Choose a Type for your player :");
        System.out.println("(1)"+ HeroType[typePointer]);
        Heroes hero = new Heroes();
        System.out.println("(2)"+ HeroType[typePointer+1]);
        System.out.println("(3)"+ HeroType[typePointer+2]);
        int Choice = Logic.UserInput("->",3);
        if(Choice == 1){
            Logic.printHeader("You Choose : "+ HeroType[typePointer] +"!");
            hero = (Heroes) chooseWarrior();
        }
        else if(Choice == 2){
            Logic.printHeader("You Choose : "+ HeroType[typePointer+1] +"!");
            hero = (Heroes) chooseSorcerer();
        }
        else if(Choice == 3){
            Logic.printHeader("You Choose : "+ HeroType[typePointer+2] +"!");
            hero = (Heroes) choosePaladinType();
        }
        return hero;
    }
    public void chooseTrait(){
        Logic.printHeader("Choose a Trait for your player :");
        System.out.println("(1)"+AttackUpgrades[AttackUpgradePtr]);
        System.out.println("(2)"+DefendUpgrades[DefendUpgradePtr]);
        int Choice = Logic.UserInput("->",2);
        if(Choice == 1){
            Logic.printHeader("You Choose : "+ AttackUpgrades[AttackUpgradePtr] +"!");
            AttackUpgradePtr++;
        }
        else {
            Logic.printHeader("You Choose : "+ DefendUpgrades[DefendUpgradePtr] +"!");
            DefendUpgradePtr++;
        }

    }
}
