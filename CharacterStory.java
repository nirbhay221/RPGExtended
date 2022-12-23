import java.util.Random;

public class CharacterStory {//handles the story event for the game .
    public static void printPlotStart(){
        Logic.printSeparation(30);
        System.out.println("Your Introduction ");
        Logic.printSeparation(30);
        System.out.println("You are one of the hero (elite) . In order to protect the your homeland , it's your duty to fight the evil");
        System.out.println("Every Single one of your friends needs to be saved so that the town can live happily , a slight mistake can cause it to perish");
        System.out.println("All you need to do is get more experience and try to survive till the end and pass it on to the new generation .");

    }
    public static void printFirstPlotIntro(){
        Logic.printSeparation(30);
        System.out.println("FIRST PLOT INTRO : ");
        Logic.printSeparation(30);
        System.out.println("You are heading to the Mountain of Elixir , there is a monster protecting the drop of elixir .");
        System.out.println("You need to get the monster separated from the elixir or that monster will become the most dangerous and undefeatable.");
        System.out.println("Try your best to deal with the monster and don't drink the elixir .");

    }
    public static void printRandomIntro(int act){
        Logic.printSeparation(30);
        System.out.println(act+"st"+" PLOT INTRO : ");
        Logic.printSeparation(30);
        Random a = new Random();
        String[] Land = {"Northern Undead Asylum"," Firelink Shrine","Undead Burg"," Undead Parish","Depths","Blighttown","Quelaag's Domain","Sen's Fortress"," Anor Londo","Darkroot Garden","Darkroot Basin","New Londo Ruins","The Duke's Archives","Crystal Cave","Demon Ruins","Lost Izalith","The Catacombs","Tomb of Giants","The Valley of Drakes","The Abyss","Kiln of The First Flame"};
       int randomLand = a.nextInt(Land.length)+0;
       String[] Items = {"Florentine Diamond","Copper Scroll Treasures","Gold","Elixir","Cloak of Invisibility","Cloak of Invincibility","Excalibur","Sword of Eden","Shattered Staff of Eden","Imperial Sceptre","Staff of Hermes Trismegistus","Scepter of Alexander the Great","Sword of Damokles","Mjölnir","Trident of Eden","Odin's Eye","Fountain of Youth","Trident of Eden","Apple of Eden"};
        int randomItem = a.nextInt(Items.length)+0;
        System.out.println("You are heading to the "+Land[randomLand]+" , there is a monster protecting the "+Items[randomItem]+" .");
        System.out.println("You need to get the monster separated from the "+Items[randomItem]+" or that monster will become the most dangerous and undefeatable.");
        System.out.println("Try your best to deal with the monster .");

    }
    public static void printRandomOutro(int act){
        Logic.printSeparation(30);
        System.out.println(act+"st"+"PLOT INTRO : ");
        Logic.printSeparation(30);
        System.out.println("You are heading to the Mountain of Elixir , there is a monster protecting the drop of elixir .");
        System.out.println("You need to get the monster separated from the elixir or that monster will become the most dangerous and undefeatable.");
        System.out.println("Try your best to deal with the monster and don't drink the elixir .");

    }
    public static void printFirstPlotOutro(){
        Logic.printSeparation(30);
        System.out.println("FIRST PLOT OUTRO :");
        Logic.printSeparation(30);
        System.out.println("You saved everyone from the calamity .");
        System.out.println("As you saved everyone , you get the money and experience from the fight .");
        System.out.println("Now you can use your experience and upgraded weapons and tools in the inventory to take on worst monsters.");

    }
    public static void printSecondPlotIntro(){
        Logic.printSeparation(30);
        System.out.println("Your Introduction ");
        Logic.printSeparation(30);

        System.out.println("You are heading to the Landlines, there is a monster protecting the drop of Money.");
        System.out.println("You need to get the monster separated from the Money or that monster will become the most dangerous and undefeatable.");
        System.out.println("Try your best to deal with the monster and don't take the Money .");

    }
    public static void printSecondPlotOutro(){
        Logic.printSeparation(30);
        System.out.println("Your Introduction ");
        Logic.printSeparation(30);

        System.out.println("You saved everyone from the calamity .");
        System.out.println("As you saved everyone , you get the money and experience from the fight .");
        System.out.println("Now you can use your experience and upgraded weapons and tools in the inventory to take on worst monsters.");

    }
    public static void printThirdPlotIntro(){
        Logic.printSeparation(30);
        System.out.println("Your Introduction ");
        Logic.printSeparation(30);

        System.out.println("You are heading to the Wetlands, there is a monster protecting the Water.");
        System.out.println("You need to get the monster separated from the Water or that monster will become the most dangerous and undefeatable.");
        System.out.println("Try your best to deal with the monster and don't take the Water .");

    }
    public static void printThirdPlotOutro(){
        Logic.printSeparation(30);
        System.out.println("Your Introduction ");
        Logic.printSeparation(30);

        System.out.println("You saved everyone from the calamity .");
        System.out.println("As you saved everyone , you get the money and experience from the fight .");
        System.out.println("Now you can use your experience and upgraded weapons and tools in the inventory to take on worst monsters.");

    }
    public static void printFourthPlotIntro(){
        Logic.printSeparation(30);
        System.out.println("Your Introduction ");
        Logic.printSeparation(30);

        System.out.println("You are heading to the Woods, there is a monster protecting the Flower.");
        System.out.println("You need to get the monster separated from the Flower or that monster will become the most dangerous and undefeatable.");
        System.out.println("Try your best to deal with the monster and don't take the Flower .");

    }
    public static void printFourthPlotOutro(){
        Logic.printSeparation(30);
        System.out.println("Your Introduction ");
        Logic.printSeparation(30);

        System.out.println("You saved everyone from the calamity .");
        System.out.println("As you saved everyone , you get the money and experience from the fight .");
        System.out.println("Now you can use your experience and upgraded weapons and tools in the inventory to take on worst monsters.");

    }

    public static void printEndOfStory(Heroes player){
        Logic.printSeparation(30);
        System.out.println("Congratulations , "+player.CharacterName + " ! You defeated all the evils in the land for now . " );
        Logic.printSeparation(30);
        System.out.println("I wonder it was adventurous");
        System.exit(0);
    }
}
