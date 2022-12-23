import java.util.Random;

public class ExoSkeletonDatabase extends Villains implements Fightable{//handles the exoskeletons which are villains .

    public String[] SpiritType = {"Andrealphus", "Blinky", "Andromalius","Chiang-shih","FallenAngel","Ereshkigall","Melchiresas","Jormunngand","Rakkshasass","Taltecuhtli","Casper"};

    public Object chooseExoskeleton(){
        Random a = new Random();
        Villains enemysublist = new Villains();
        int Choice = a.nextInt(12)+1;
        int b = Choice;
        if(Choice == 1){
            enemysublist= new Villains(ExoskeletonType[typePointer],7,700,800,75,0,0);
            return enemysublist;


        }
        else if(Choice == 2){
            enemysublist= new Villains(ExoskeletonType[typePointer+1],3,350,450,30,0,0);
            return enemysublist;


        }
        else if(Choice == 3){
            enemysublist= new Villains(ExoskeletonType[typePointer+2],1,150,250,15,0,0);
            return enemysublist;


        }
        else if(Choice == 4){
            enemysublist= new Villains(ExoskeletonType[typePointer+3],2,250,350,25,0,0);
            return enemysublist;


        }
        else if(Choice == 5){
            enemysublist= new Villains(ExoskeletonType[typePointer+4],4,400,500,45,0,0);
            return enemysublist;


        }
        else if(Choice == 6){
            enemysublist= new Villains(ExoskeletonType[typePointer+5],6,650,750,60,0,0);
            return enemysublist;


        }
        else if(Choice == 7){
            enemysublist= new Villains(ExoskeletonType[typePointer+6],8,850,950,85,0,0);
            return enemysublist;


        }
        else if(Choice == 8){
            enemysublist= new Villains(ExoskeletonType[typePointer+7],5,550,650,55,0,0);
            return enemysublist;


        }
        else if(Choice == 9){
            enemysublist= new Villains(ExoskeletonType[typePointer+8],10,1000,90,55,0,0);
            return enemysublist;


        }
        else if(Choice == 10){
            enemysublist= new Villains(ExoskeletonType[typePointer+9],9,950,850,90,0,0);
            return enemysublist;


        }
        else if(Choice == 11){
            enemysublist= new Villains(ExoskeletonType[typePointer+10],6,600,600,55,0,0);


        } else if(Choice == 12){
            enemysublist= new Villains(ExoskeletonType[typePointer+11],10,1000,1000,50,0,0);
            return enemysublist;


        }
        return enemysublist;

    }
    public String[] ExoskeletonType = {"Cyrrollalee", "Brandobaris", "BigBad-Wolf","WickedWitch","Aasterinian","Chronepsish","Kiaransalee","St-Shargaas","Merrshaullk","St-Yeenoghu","DocOck","Exodia"};

}

