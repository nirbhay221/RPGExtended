import java.util.Random;

public class DragonsDatabase extends Villains implements Fightable{//handles the dragons which are villains .

    public DragonsDatabase(){

    }

    public Object chooseDragons(){
        Random a = new Random();
        Villains enemysublist = new Villains();
        int Choice = a.nextInt(12)+1;
        int b = Choice;
        if(Choice == 1){
            enemysublist= new Villains(DragonType[typePointer],3,300,400,35,0,0);
//            System.out.println(DragonType[typePointer]);
            return enemysublist;


        }
        else if(Choice == 2){
            enemysublist= new Villains(DragonType[typePointer+1],2,200,400,20,0,0);
//            System.out.println(DragonType[typePointer+1]);
            return enemysublist;


        }
        else if(Choice == 3){
            enemysublist= new Villains(DragonType[typePointer+2],4,400,500,45,0,0);
//            System.out.println(DragonType[typePointer+2]);
            return enemysublist;


        }
        else if(Choice == 4){
            enemysublist= new Villains(DragonType[typePointer+3],1,100,200,10,0,0);
//            System.out.println(DragonType[typePointer+3]);
            return enemysublist;


        }
        else if(Choice == 5){
            enemysublist= new Villains(DragonType[typePointer+4],7,700,600,75,0,0);
//            System.out.println(DragonType[typePointer+4]);
            return enemysublist;


        }
        else if(Choice == 6){
            enemysublist= new Villains(DragonType[typePointer+5],5,600,500,65,0,0);
//            System.out.println(DragonType[typePointer+5]);
            return enemysublist;


        }
        else if(Choice == 7){
            enemysublist= new Villains(DragonType[typePointer+6],10,1000,9000,55,0,0);
//            System.out.println(DragonType[typePointer+6]);
            return enemysublist;


        }
        else if(Choice == 8){
            enemysublist= new Villains(DragonType[typePointer+7],6,600,700,60,0,0);
//            System.out.println(DragonType[typePointer+7]);
            return enemysublist;


        }
        else if(Choice == 9){
            enemysublist= new Villains(DragonType[typePointer+8],9,900,950,85,0,0);
//            System.out.println(DragonType[typePointer+8]);
            return enemysublist;


        }
        else if(Choice == 10){
            enemysublist= new Villains(DragonType[typePointer+9],8,800,900,80,0,0);
//            System.out.println(DragonType[typePointer+9]);
            return enemysublist;


        }
        else if(Choice == 11){
            enemysublist= new Villains(DragonType[typePointer+10],6,600,400,60,0,0);
//            System.out.println(DragonType[typePointer+10]);
            return enemysublist;


        } else if(Choice == 12){
            enemysublist= new Villains(DragonType[typePointer+11],9,900,600,75,0,0);
//            System.out.println(DragonType[typePointer+11]);
            return enemysublist;


        }
        return enemysublist;

    }
    public String[] DragonType = {"Desghidorrah", "Chrysophylax", "BunsenBurner","Natsunomeryu","TheScaleless","Kas-Ethelinh","Alexstraszan","Phaarthurnax","D-Maleficent","TheWeatherbe","Igneel","BlueEyesWhite"};

}
