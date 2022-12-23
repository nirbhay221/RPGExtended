import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        Board newBoard = new Board(8,8);


//
//        newBoard.UserChoiceMove(8,8,'H');
//        newBoard.initialize(8,8);
//
//        newBoard.AssignMarket(64,'M');
//newBoard.AssignLand(64,'L');
//        newBoard.AddDefaultPos(8,'H');
//
//        System.out.println(" ");
//newBoard.userMove(8,'H');
        Logic.StartGame();
//        for(int i=1 ; i < 100 ; i++) {
//            Random a = new Random();
//            int b = a.nextInt(3-0+1)+0;
//            System.out.println("Random :" + b);
//        }
    }
}
