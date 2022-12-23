import java.util.Random;

public class SpiritsDatabase extends Villains implements Fightable{
    public SpiritsDatabase(){//handles the spirit type of the villains

    }

    public Object chooseSpirits(){
        Random a = new Random();
        Villains enemysublist = new Villains();
        int Choice = a.nextInt(11)+1;
        int b = Choice;
        if(Choice == 1){
            enemysublist= new Villains(SpiritType[typePointer],2,600,500,40,0,0);
            return enemysublist;


        }
        else if(Choice == 2){
            enemysublist= new Villains(SpiritType[typePointer+1],1,450,350,35,0,0);
            return enemysublist;


        }
        else if(Choice == 3){
            enemysublist= new Villains(SpiritType[typePointer+2],3,550,450,25,0,0);
            return enemysublist;


        }
        else if(Choice == 4){
            enemysublist= new Villains(SpiritType[typePointer+3],4,700,600,40,0,0);
            return enemysublist;


        }
        else if(Choice == 5){
            enemysublist= new Villains(SpiritType[typePointer+4],5,800,700,50,0,0);
            return enemysublist;


        }
        else if(Choice == 6){
            enemysublist= new Villains(SpiritType[typePointer+5],6,950,450,35,0,0);


        }
        else if(Choice == 7){
            enemysublist= new Villains(SpiritType[typePointer+6],7,350,150,75,0,0);
            return enemysublist;


        }
        else if(Choice == 8){
            enemysublist= new Villains(SpiritType[typePointer+7],8,600,900,20,0,0);


        }
        else if(Choice == 9){
            enemysublist= new Villains(SpiritType[typePointer+8],9,550,600,35,0,0);
            return enemysublist;


        }
        else if(Choice == 10){
            enemysublist= new Villains(SpiritType[typePointer+9],10,300,200,50,0,0);
            return enemysublist;


        }
        else if(Choice == 11){
            enemysublist= new Villains(SpiritType[typePointer+10],1,100,100,50,0,0);
            return enemysublist;


        }
        return enemysublist;

    }
    public String[] SpiritType = {"Andrealphus", "Blinky", "Andromalius","Chiang-shih","FallenAngel","Ereshkigall","Melchiresas","Jormunngand","Rakkshasass","Taltecuhtli","Casper"};


}
