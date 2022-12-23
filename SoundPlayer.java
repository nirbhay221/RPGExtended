
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class SoundPlayer {
    SoundPlayer sp;
    AudioInputStream audioInput ;
    long currentPosition ;
        String currentStatus;
        Clip firstClip;
        String firstFile;
    public SoundPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {//handles the sound of the game
        {


        }
}

public static Clip SpecifyPathAndOptions(String Filepath,SoundPlayer sp,AudioInputStream audioInput,Clip firstClip,String firstFile) throws UnsupportedAudioFileException, LineUnavailableException, IOException {

                   try{

                       String filePath = Filepath;
                       audioInput = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                       firstClip = AudioSystem.getClip();
                       firstClip.open(audioInput);
                       firstClip.loop(Clip.LOOP_CONTINUOUSLY);
                       System.out.println("It Enters");
                       sp = new SoundPlayer();
                       System.out.println("Sound should start!");
                    sp.start();

                    }
                   catch(Exception e){
                   }
                   return firstClip;



}
public static int Choices(){
    int Option;
    while(true){
        System.out.println("(1) Start The Song .");
        System.out.println("(2) Stop The Song .");

        Option =Logic.UserInput("-->" , 2);
        if(Option == 1 ||Option == 2  ){
            break;
        }
    }
    return Option;
}
    public void SoundOptions(int options,Clip firstClip) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {

if( options == 1) {
    start();

}
        if( options == 2){
            Stopmusic(firstClip);
        }


}
    public void start(){
    firstClip.start();
    currentStatus = "Start";
        firstClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void startContinuous(){
        firstClip.start();
        firstClip.loop(Clip.LOOP_CONTINUOUSLY);
        currentStatus = "Start";
    }


public static void Stopmusic(Clip firstClip) throws InterruptedException {
        Thread.sleep(500);
    firstClip.stop();
    firstClip.close();
}


}


