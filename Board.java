
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Board { // This class is used for handling the board events , collisions and resurrections and other events of lanes .This is used to move the players on the board in order to reach monster's nexus
    //handles the movement for each hero and each villain and their collision on the board and interaction with the market .
    Clip firstClip;
    int row;
    public ArrayList<Integer> NexusX;
    public ArrayList<Integer>NexusY;
    int columns;
    boolean Alive = true;
    int numberOfVillains;
    Set<Integer> marketXelements;
    ArrayList<String> VillainOptions;
    public ArrayList<Integer>Hero1XOrigin;
    public ArrayList<Integer>Hero1YOrigin;
    public ArrayList<Integer>Hero2XOrigin;
    public ArrayList<Integer>Hero2YOrigin;
    public ArrayList<Integer>Hero3XOrigin;
    public ArrayList<Integer>Hero3YOrigin;
    Set<Integer> marketYelements;
    Set<Integer> LandXelements;
    Set<Integer> LandYelements;
    ArrayList<Integer> marketX;
    ArrayList<Integer> marketY;
    ArrayList<Integer> CaveX;
    ArrayList<Integer> CaveY;
    ArrayList<Integer> BushX;
    ArrayList<Integer> BushY;
    ArrayList<Integer> KoulouX;
    ArrayList<Integer> KoulouY;
    ArrayList<Integer> LanesIndication;


    int TeleportBoardRowRange;
    int TeleportBoardColumnRange;
    public ArrayList<ArrayList<Integer>> HeroTeleportRange;
    ArrayList<Integer> LandX;
    ArrayList<Integer> LandY;
    ArrayList<Integer> NoManX;
    ArrayList<Integer> NoManY;
    boolean isMarket = false;
    boolean isCave = false;
    boolean isKoulou = false;
    boolean isBush = false;
    boolean isLand = false;
    AudioInputStream audioInput;
    String firstFile;


    public ArrayList<Integer> Villain1X;//****************************
    public ArrayList<Integer> Villain2X;
    public ArrayList<Integer> Villain3X;
    public ArrayList<Integer> Villain1Y;
    public ArrayList<Integer> Villain2Y;
    public ArrayList<Integer> Villain3Y;




    Hashtable<Integer,Integer> PathTable ;
    int Hero1X;
    int Hero1RetainX;
    int Hero1RetainY;
    int Hero2RetainX;
    int Hero2RetainY;
    int Hero3RetainX;
    int Hero3RetainY;
    int UpperColumn ;
    int LowerColumn ;
    int Diagonal1Column;
    int Diagonal2Column;
    int Diagonal3Column;
    int Diagonal4Column;
    public ArrayList<Integer> RowCollisionVicinity;
    public ArrayList<Integer> ColumnCollisionVicinity;
    public ArrayList<Integer> RowTeleportVicinity;
    public ArrayList<Integer> ColumnTeleportVicinity;
    int LeftSideColumn;
    int RightSideColumn;
    int UpperRow ;
    int LowerRow ;
    int Diagonal1Row;
    int Diagonal2Row;

    int Diagonal3Row;
    int Diagonal4Row;
    int LeftSideRow;
    int RightSideRow;

    int Hero2X;
    int Hero2Y;
    boolean isSuccessful = false;
    int Hero3X;
    int Hero3Y;
    public ArrayList<Integer> LaneOccupied;
    int Hero1Y;
    public ArrayList<Integer> BorderX;
    public ArrayList<Integer> BorderY;
    public char board[][] ;
    public char Lanes[][];
    public ArrayList<char[][]> NumLanes;
    int turn =0;

    Scanner sc = new Scanner(System.in);
    public Board(int Rows,int Cols){
this.row = Rows;
this.columns = Cols;
board = new char[Rows][Cols];
Lanes = new char[Rows][Cols];
NumLanes = new ArrayList<char[][]>();
Hero1XOrigin = new ArrayList<Integer>();
Villain1Y = new ArrayList<Integer>();
        Villain1X= new ArrayList<Integer>();//****************************

        Villain2Y = new ArrayList<Integer>();
        Villain2X= new ArrayList<Integer>();
        NexusX= new ArrayList<Integer>();
        NexusY = new ArrayList<Integer>();

        Villain3Y = new ArrayList<Integer>();
        Villain3X= new ArrayList<Integer>();
        Hero1YOrigin = new ArrayList<Integer>();
        Hero2XOrigin = new ArrayList<Integer>();
        Hero2YOrigin = new ArrayList<Integer>();
        Hero3XOrigin = new ArrayList<Integer>();
        Hero3YOrigin = new ArrayList<Integer>();
marketXelements = new LinkedHashSet<Integer>();
marketYelements = new LinkedHashSet<Integer>();
        LandXelements = new LinkedHashSet<Integer>();
        LandYelements = new LinkedHashSet<Integer>();
        RowCollisionVicinity = new ArrayList<Integer>();
        ColumnCollisionVicinity = new ArrayList<Integer>();
        RowTeleportVicinity = new ArrayList<Integer>();
        ColumnTeleportVicinity = new ArrayList<Integer>();
        LanesIndication = new ArrayList<Integer>();
        marketX = new ArrayList<Integer>(marketXelements);
        marketY = new ArrayList<Integer>(marketYelements);
        LandX = new ArrayList<Integer>(LandXelements);
        LandY = new ArrayList<Integer>(LandYelements);
        NoManX= new ArrayList<Integer>();
        NoManY = new ArrayList<Integer>();
        CaveX = new ArrayList<Integer>();
        CaveY = new ArrayList<Integer>();
        BushX = new ArrayList<Integer>();
        BushY = new ArrayList<Integer>();
        KoulouY= new ArrayList<Integer>();
        KoulouX = new ArrayList<Integer>();
HeroTeleportRange = new ArrayList<ArrayList<Integer>>();
        VillainOptions = new ArrayList<String>();
        PathTable = new Hashtable<Integer , Integer>();
        BorderX= new ArrayList<Integer>();
        BorderY = new ArrayList<Integer>();

        LaneOccupied = new ArrayList<Integer>();
    }
    public void initialize(int rows, int columnss){//initializing the board
     for(int i = 0; i < rows; i++) {
        for(int j = 0 ; j < columnss ; j++){

                board[i][j] =' ';


        }
    }
    }
    //Method to Go back to nexus for the Heroes
    public void SpawnToNexus(int size , int HeroIdentity, char symbol) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        System.out.println("Do you really want to really go to your Nexus ? ");
        System.out.println("(1) Yes ");
        System.out.println("(2) No ");
        int Input = Logic.UserInput("-->",2);
        if(Input == 1){
            System.out.println("Do you want To Head To Nexus Randomly or with Specified Path ? ");
            System.out.println("(1) Randomly Go To Nexus . ");
            System.out.println("(2) Specify the Nexus Path .");
            System.out.println("(3) Go Back .");
            int Choice = Logic.UserInput("-->",3);
            if(Choice == 1){
                if(HeroIdentity == 0){

                    Random rnd = new Random();
                    int randomNexus = rnd.nextInt(1+1);
                    Hero1Y = randomNexus;
                    Hero1X= size-1;
                    System.out.println("Hero Values : "+Hero1X+" , "+Hero1Y);
                    for (int i = 0; i < size; i++) {
                        for(int j = 0 ; j < size ; j++){
                            if(board[i][j] == 'H'){
                               board[i][j] = ' ';
                               board[Hero1X][Hero1Y] = 'H';

                            }
                        }
                    }


                }
                else  if(HeroIdentity == 1){

                    Random rnd = new Random();
                    int randomNexus = rnd.nextInt(4-3+1)+3;
                    Hero2Y = randomNexus;
                    Hero2X= size-1;
                    System.out.println("Hero Values : "+Hero2X+" , "+Hero2Y);
                    for (int i = 0; i < size; i++) {
                        for(int j = 0 ; j < size ; j++){
                            if(board[i][j] == 'W'){
                                board[i][j] = ' ';
                                board[Hero2X][Hero2Y] = 'W';
                            }
                        }
                    }
                }
                else if(HeroIdentity == 2){

                    Random rnd = new Random();
                    int randomNexus = rnd.nextInt(7-6+1)+6;
                    Hero3Y = randomNexus;
                    Hero3X= size-1;
                    System.out.println("Hero Values : "+Hero3X+" , "+Hero3Y);
                    for (int i = 0; i < size; i++) {
                        for(int j = 0 ; j < size ; j++){
                            if(board[i][j] == 'G'){
                                board[i][j] = ' ';
                                board[Hero3X][Hero3Y] = 'G';
                            }
                        }
                    }
                }
            }
            else if(Choice == 2){
                if(HeroIdentity == 0){
                    Hero1X = size-1;
                    System.out.println("Choose the nexus you want to enter :");
                    System.out.println("(1) Origin Left");
                    System.out.println("(2) Origin Right");
                    int spawnChoice = Logic.UserInput("-->",2);
                    if(spawnChoice == 1){
                        Hero1Y = 0;
                        System.out.println("Hero Values : "+Hero1X+" , "+Hero1Y);
                        for (int i = 0; i < size; i++) {
                            for(int j = 0 ; j < size ; j++){
                                if(board[i][j] == 'H'){
                                    board[i][j] = ' ';
                                    board[Hero1X][Hero1Y] = 'H';
                                }
                            }
                        }
                    } else if (spawnChoice == 2) {
                        Hero1Y = 1;
                        System.out.println("Hero Values : "+Hero1X+" , "+Hero1Y);
                        for (int i = 0; i < size; i++) {
                            for(int j = 0 ; j < size ; j++){
                                if(board[i][j] == 'H'){
                                    board[i][j] = ' ';
                                    board[Hero1X][Hero1Y] = 'H';
                                }
                            }
                        }
                    }
                }
                else  if(HeroIdentity == 1){
                    Hero2X = size-1;
                    System.out.println("Choose the nexus you want to enter :");
                    System.out.println("(1) Origin Left");
                    System.out.println("(2) Origin Right");
                    int spawnChoice = Logic.UserInput("-->",2);
                    if(spawnChoice == 1){
                        Hero2Y = 3;
                        System.out.println("Hero Values : "+Hero2X+" , "+Hero2Y);
                        for (int i = 0; i < size; i++) {
                            for(int j = 0 ; j < size ; j++){
                                if(board[i][j] == 'W'){
                                    board[i][j] = ' ';
                                    board[Hero2X][Hero2Y] = 'W';
                                }
                            }
                        }
                    } else if (spawnChoice == 2) {
                        Hero2Y = 4;
                        System.out.println("Hero Values : "+Hero2X+" , "+Hero2Y);
                        for (int i = 0; i < size; i++) {
                            for(int j = 0 ; j < size ; j++){
                                if(board[i][j] == 'W'){
                                    board[i][j] = ' ';
                                    board[Hero2X][Hero2Y] = 'W';
                                }
                            }
                        }
                    }
                }
                else if(HeroIdentity == 2){
                    Hero3X = size-1;
                    System.out.println("Choose the nexus you want to enter :");
                    System.out.println("(1) Origin Left");
                    System.out.println("(2) Origin Right");
                    int spawnChoice = Logic.UserInput("-->",2);
                    if(spawnChoice == 1){
                        Hero3Y = 6;
                        System.out.println("Hero Values : "+Hero3X+" , "+Hero3Y);
                        for (int i = 0; i < size; i++) {
                            for(int j = 0 ; j < size ; j++){
                                if(board[i][j] == 'G'){
                                    board[i][j] = ' ';
                                    board[Hero3X][Hero3Y] = 'G';
                                }
                            }
                        }
                    } else if (spawnChoice == 2) {
                        Hero3Y = 7;
                        for (int i = 0; i < size; i++) {
                            for(int j = 0 ; j < size ; j++){
                                if(board[i][j] == 'G'){
                                    board[i][j] = ' ';
                                    board[Hero3X][Hero3Y] = 'G';
                                }
                            }
                        }
                    }
                }
            }
            else if(Choice == 3){
            userMove(size,symbol,HeroIdentity);
            }

        }
        else if(Input == 2){

            userMove(size,symbol,HeroIdentity);
        }
    }
    //This method is used to check the options for the heroes to check the range of the adjacent heroes that it can teleport to .

    public void TeleportationVicinityOptions(int size, int HeroIdentity){
        if(! RowTeleportVicinity.isEmpty() || !ColumnTeleportVicinity.isEmpty()){
            for(int k  = 0 ; k< RowTeleportVicinity.size() ; k++){
                RowTeleportVicinity.remove(k);
                ColumnTeleportVicinity.remove(k);
            }
            System.out.println("Size "+RowTeleportVicinity.size()+" , "+ ColumnTeleportVicinity.size());


        }
        if(HeroIdentity == 1){
            if(!RowTeleportVicinity.isEmpty() || !ColumnTeleportVicinity.isEmpty()){
                System.out.println("Path Table not Empty!");
                for(int k = 0; k< RowTeleportVicinity.size() ; k++){
                    RowTeleportVicinity.remove(k);
                    ColumnTeleportVicinity.remove(k);
                }

            }
            if(!RowTeleportVicinity.isEmpty() || !ColumnTeleportVicinity.isEmpty()){
                System.out.println("Path Table not Empty!");
                for(int k = 0; k< RowTeleportVicinity.size() ; k++){
                    RowTeleportVicinity.remove(k);
                    ColumnTeleportVicinity.remove(k);
                }
            }
            System.out.println("Size "+RowTeleportVicinity.size()+" , "+ ColumnTeleportVicinity.size());
            System.out.println("It enters .");

            System.out.println("Path Table Empty ");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == 'H') {
                        System.out.println("Found H at "+ i+" , "+j);
                        LowerRow = i+1;
                        LowerColumn = j;
                        System.out.println("Row : "+ LowerRow+" Column : "+LowerColumn);

                        if (LowerRow >-1 && LowerRow<8 && LowerColumn>-1 && LowerColumn < 8 ) {
                            if(LowerRow > 7){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }

                            if (board[LowerRow][LowerColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }
                            else{
                                RowTeleportVicinity.add(LowerRow);
                                ColumnTeleportVicinity.add(LowerColumn);
                            }
                        }
                        LeftSideRow = i;
                        LeftSideColumn = j-1;
                        System.out.println("Row : "+ LeftSideRow+" Column : "+LeftSideColumn);
                        if (LeftSideColumn >-1&& LeftSideRow>-1 && LeftSideColumn<8 && LeftSideRow < 8) {

                            if (LeftSideColumn<0){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }

                            else if (board[LeftSideRow][LeftSideColumn] == 'X' ) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }
                            else{

                                RowTeleportVicinity.add(LeftSideRow);
                                ColumnTeleportVicinity.add(LeftSideColumn);
                            }
                        }

                        RightSideRow = i;
                        RightSideColumn = j+1;
                        System.out.println("Row : "+ RightSideRow+" Column : "+RightSideColumn);
                        if (RightSideColumn >-1 && RightSideRow >-1 && RightSideColumn<8 && RightSideRow < 8) {

                            if (RightSideColumn>7){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }

                            if (board[RightSideRow][RightSideColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            else{

                                RowTeleportVicinity.add(RightSideRow);
                                ColumnTeleportVicinity.add(RightSideColumn);

                            }
                        }
                        Logic.printHeader("Your Vicinity Paths are as follows : ");
                        for(int m = 0; m< RowTeleportVicinity.size();m++){
                            System.out.println(" ("+m+")"+" Row : "+RowTeleportVicinity.get(m)+" Column"+ColumnTeleportVicinity.get(m));
                        }

                    }
                }
            }
        }

        if(HeroIdentity == 2){
            if(!RowTeleportVicinity.isEmpty() || !ColumnTeleportVicinity.isEmpty()){
                System.out.println("Path Table not Empty!");
                for(int k = 0; k< RowTeleportVicinity.size() ; k++){
                    RowTeleportVicinity.remove(k);
                    ColumnTeleportVicinity.remove(k);
                }
            }
            if(!RowTeleportVicinity.isEmpty() || !ColumnTeleportVicinity.isEmpty()){
                System.out.println("Path Table not Empty!");
                for(int k = 0; k< RowTeleportVicinity.size() ; k++){
                    RowTeleportVicinity.remove(k);
                    ColumnTeleportVicinity.remove(k);
                }
            }
            System.out.println("Size "+RowTeleportVicinity.size()+" , "+ ColumnTeleportVicinity.size());
            System.out.println("It enters .");

            System.out.println("Path Table Empty ");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == 'W') {
                        System.out.println("Found W at "+ i+" , "+j);
                        LowerRow = i+1;
                        LowerColumn = j;
                        System.out.println("Row : "+ LowerRow+" Column : "+LowerColumn);

                        if (LowerRow >-1 && LowerRow<8 && LowerColumn>-1 && LowerColumn < 8) {
                            if(LowerRow > 7){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (board[LowerRow][LowerColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }
                            else{
                                RowTeleportVicinity.add(LowerRow);
                                ColumnTeleportVicinity.add(LowerColumn);
                            }
                        }
                        LeftSideRow = i;
                        LeftSideColumn = j-1;
                        System.out.println("Row : "+ LeftSideRow+" Column : "+LeftSideColumn);
                        if (LeftSideColumn >-1&& LeftSideRow>-1 && LeftSideColumn<8 && LeftSideRow < 8) {

                            if (LeftSideColumn<0){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else if (board[LeftSideRow][LeftSideColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }
                            else{

                                RowTeleportVicinity.add(LeftSideRow);
                                ColumnTeleportVicinity.add(LeftSideColumn);
                            }
                        }

                        RightSideRow = i;
                        RightSideColumn = j+1;
                        System.out.println("Row : "+ RightSideRow+" Column : "+RightSideColumn);
                        if (RightSideColumn >-1 && RightSideRow >-1 && RightSideColumn<8 && RightSideRow < 8) {

                            if (RightSideColumn>7){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }
                            if (board[RightSideRow][RightSideColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            else{

                                RowTeleportVicinity.add(RightSideRow);
                                ColumnTeleportVicinity.add(RightSideColumn);

                            }
                        }
                        Logic.printHeader("Your Vicinity Paths are as follows : ");
                        for(int m = 0; m< RowTeleportVicinity.size();m++){
                            System.out.println(" ("+m+")"+" Row : "+RowTeleportVicinity.get(m)+" Column"+ColumnTeleportVicinity.get(m));
                        }

                    }
                }
            }
        }

        else if(HeroIdentity == 3){
            if(!RowTeleportVicinity.isEmpty() || !ColumnTeleportVicinity.isEmpty()){
                System.out.println("Path Table not Empty!");
                for(int k = 0; k< RowTeleportVicinity.size() ; k++){
                    RowTeleportVicinity.remove(k);
                    ColumnTeleportVicinity.remove(k);
                }
            }
            if(!RowTeleportVicinity.isEmpty() || !ColumnTeleportVicinity.isEmpty()){
                System.out.println("Path Table not Empty!");
                for(int k = 0; k< RowTeleportVicinity.size() ; k++){
                    RowTeleportVicinity.remove(k);
                    ColumnTeleportVicinity.remove(k);
                }
            }
            System.out.println("Size "+RowTeleportVicinity.size()+" , "+ ColumnTeleportVicinity.size());
            System.out.println("It enters .");

            System.out.println("Path Table Empty ");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == 'G') {
                        System.out.println("Found G at "+ i+" , "+j);
                        LowerRow = i+1;
                        LowerColumn = j;
                        System.out.println("Row : "+ LowerRow+" Column : "+LowerColumn);

                        if (LowerRow >-1 && LowerRow<8 && LowerColumn>-1 && LowerColumn < 8) {
                            if(LowerRow > 7){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (board[LowerRow][LowerColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }
                            else{
                                RowTeleportVicinity.add(LowerRow);
                                ColumnTeleportVicinity.add(LowerColumn);
                            }
                        }
                        LeftSideRow = i;
                        LeftSideColumn = j-1;
                        System.out.println("Row : "+ LeftSideRow+" Column : "+LeftSideColumn);
                        if (LeftSideColumn >-1&& LeftSideRow>-1 && LeftSideColumn<8 && LeftSideRow < 8) {

                            if (LeftSideColumn<0){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else if (board[LeftSideRow][LeftSideColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }
                            else{

                                RowTeleportVicinity.add(LeftSideRow);
                                ColumnTeleportVicinity.add(LeftSideColumn);
                            }
                        }

                        RightSideRow = i;
                        RightSideColumn = j+1;
                        System.out.println("Row : "+ RightSideRow+" Column : "+RightSideColumn);
                        if (RightSideColumn >-1 && RightSideRow >-1 && RightSideColumn<8 && RightSideRow < 8) {

                            if (RightSideColumn>7){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }
                            if (board[RightSideRow][RightSideColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            else{

                                RowTeleportVicinity.add(RightSideRow);
                                ColumnTeleportVicinity.add(RightSideColumn);

                            }
                        }
                        Logic.printHeader("Your Vicinity Paths are as follows : ");
                        for(int m = 0; m< RowTeleportVicinity.size();m++){
                            System.out.println(" ("+m+")"+" Row : "+RowTeleportVicinity.get(m)+" Column"+ColumnTeleportVicinity.get(m));
                        }

                    }
                }
            }
        }


    }
 //This method is invoked when the villains hp gets below 0
    public void DeadVillains(int VillainIdentity,int size , char hero,Villains villain,int numberofVillains,char villainsymbol){//****************************
        System.out.println("Hero Symbol :"+ hero);
        if(villainsymbol == 'V'){
            CollisionVicinity(size,0,'H');
            for(int i = 0 ; i < RowCollisionVicinity.size();i++){
                for(int j = 0 ; j< ColumnCollisionVicinity.size();j++) {
                    for(int k = 0 ; k < Villain1X.size();k++) {//****************************
                        if (RowCollisionVicinity.get(i) == Villain1X.get(k)) {
                            if (ColumnCollisionVicinity.get(i) == Villain1Y.get(k)) {

                                if (board[Villain1X.get(k)][Villain1Y.get(k)] == 'V') {
                                    board[Villain1X.get(k)][Villain1Y.get(k)] = ' ';

                                    Villain1X.set(k, 16);
                                    Villain1Y.set(k, 16);
                                    retainprintBoard();
//        Villain1X = 16;
//        Villain1Y = 16;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            CollisionVicinity(size,1,'W');
            for(int i = 0 ; i < RowCollisionVicinity.size();i++){
                for(int j = 0 ; j< ColumnCollisionVicinity.size();j++) {
                    for(int k = 0 ; k < Villain1X.size();k++) {//****************************
                        if (RowCollisionVicinity.get(i) == Villain1X.get(k)) {
                            if (ColumnCollisionVicinity.get(i) == Villain1Y.get(k)) {

                                if (board[Villain1X.get(k)][Villain1Y.get(k)] == 'V') {
                                    board[Villain1X.get(k)][Villain1Y.get(k)] = ' ';

                                    Villain1X.set(k, 16);
                                    Villain1Y.set(k, 16);
                                    retainprintBoard();
//        Villain1X = 16;
//        Villain1Y = 16;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            CollisionVicinity(size,2,'G');
            for(int i = 0 ; i < RowCollisionVicinity.size();i++){
                for(int j = 0 ; j< ColumnCollisionVicinity.size();j++) {
                    for(int k = 0 ; k < Villain1X.size();k++) {//****************************
                        if (RowCollisionVicinity.get(i) == Villain1X.get(k)) {
                            if (ColumnCollisionVicinity.get(i) == Villain1Y.get(k)) {

                                if (board[Villain1X.get(k)][Villain1Y.get(k)] == 'V') {
                                    board[Villain1X.get(k)][Villain1Y.get(k)] = ' ';

                                    Villain1X.set(k, 16);
                                    Villain1Y.set(k, 16);
                                    retainprintBoard();
//        Villain1X = 16;
//        Villain1Y = 16;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(villainsymbol == 'E'){
            CollisionVicinity(size,0,'H');
            for(int i = 0 ; i < RowCollisionVicinity.size();i++){
                for(int j = 0 ; j< ColumnCollisionVicinity.size();j++) {
                    for(int k = 0 ; k < Villain2X.size();k++) {//****************************
                        if (RowCollisionVicinity.get(i) == Villain2X.get(k)) {
                            if (ColumnCollisionVicinity.get(i) == Villain2Y.get(k)) {

                                if (board[Villain2X.get(k)][Villain2Y.get(k)] == 'E') {
                                    board[Villain2X.get(k)][Villain2Y.get(k)] = ' ';

                                    Villain2X.set(k, 16);
                                    Villain2Y.set(k, 16);
                                    retainprintBoard();
//        Villain1X = 16;
//        Villain1Y = 16;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            CollisionVicinity(size,1,'W');
            for(int i = 0 ; i < RowCollisionVicinity.size();i++){
                for(int j = 0 ; j< ColumnCollisionVicinity.size();j++) {
                    for(int k = 0 ; k < Villain2X.size();k++) {//****************************
                        if (RowCollisionVicinity.get(i) == Villain2X.get(k)) {
                            if (ColumnCollisionVicinity.get(i) == Villain2Y.get(k)) {

                                if (board[Villain2X.get(k)][Villain2Y.get(k)] == 'E') {
                                    board[Villain2X.get(k)][Villain2Y.get(k)] = ' ';

                                    Villain2X.set(k, 16);
                                    Villain2Y.set(k, 16);
                                    retainprintBoard();
//        Villain1X = 16;
//        Villain1Y = 16;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            CollisionVicinity(size,2,'G');
            for(int i = 0 ; i < RowCollisionVicinity.size();i++){
                for(int j = 0 ; j< ColumnCollisionVicinity.size();j++) {
                    for(int k = 0 ; k < Villain2X.size();k++) {//****************************
                        if (RowCollisionVicinity.get(i) == Villain2X.get(k)) {
                            if (ColumnCollisionVicinity.get(i) == Villain2Y.get(k)) {

                                if (board[Villain2X.get(k)][Villain2Y.get(k)] == 'E') {
                                    board[Villain2X.get(k)][Villain2Y.get(k)] = ' ';

                                    Villain2X.set(k, 16);
                                    Villain2Y.set(k, 16);
                                    retainprintBoard();
//        Villain1X = 16;
//        Villain1Y = 16;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(villainsymbol == 'S'){
            CollisionVicinity(size,0,'H');
            for(int i = 0 ; i < RowCollisionVicinity.size();i++){
                for(int j = 0 ; j< ColumnCollisionVicinity.size();j++) {
                    for(int k = 0 ; k < Villain3X.size();k++) {//****************************
                        if (RowCollisionVicinity.get(i) == Villain3X.get(k)) {
                            if (ColumnCollisionVicinity.get(i) == Villain3Y.get(k)) {

                                if (board[Villain3X.get(k)][Villain3Y.get(k)] == 'S') {
                                    board[Villain3X.get(k)][Villain3Y.get(k)] = ' ';

                                    Villain3X.set(k, 16);
                                    Villain3Y.set(k, 16);
                                    retainprintBoard();
//        Villain1X = 16;
//        Villain1Y = 16;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            CollisionVicinity(size,1,'W');
            for(int i = 0 ; i < RowCollisionVicinity.size();i++){
                for(int j = 0 ; j< ColumnCollisionVicinity.size();j++) {
                    for(int k = 0 ; k < Villain3X.size();k++) {//****************************
                        if (RowCollisionVicinity.get(i) == Villain3X.get(k)) {
                            if (ColumnCollisionVicinity.get(i) == Villain3Y.get(k)) {

                                if (board[Villain3X.get(k)][Villain3Y.get(k)] == 'S') {
                                    board[Villain3X.get(k)][Villain3Y.get(k)] = ' ';

                                    Villain3X.set(k, 16);
                                    Villain3Y.set(k, 16);
                                    retainprintBoard();
//        Villain1X = 16;
//        Villain1Y = 16;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            CollisionVicinity(size,2,'G');
            for(int i = 0 ; i < RowCollisionVicinity.size();i++){
                for(int j = 0 ; j< ColumnCollisionVicinity.size();j++) {
                    for(int k = 0 ; k < Villain3X.size();k++) {//****************************
                        if (RowCollisionVicinity.get(i) == Villain3X.get(k)) {
                            if (ColumnCollisionVicinity.get(i) == Villain3Y.get(k)) {

                                if (board[Villain3X.get(k)][Villain3Y.get(k)] == 'S') {
                                    board[Villain3X.get(k)][Villain3Y.get(k)] = ' ';

                                    Villain3X.set(k, 16);
                                    Villain3Y.set(k, 16);
                                    retainprintBoard();
//        Villain1X = 16;
//        Villain1Y = 16;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

if(VillainIdentity == 0){
if(hero == 'H'){
    CollisionVicinity(size,0,hero);
    for(int i = 0 ; i < RowCollisionVicinity.size();i++){
for(int j = 0 ; j< ColumnCollisionVicinity.size();j++){
    for(int k = 0 ; k < Villain1X.size();k++){//****************************
    if(RowCollisionVicinity.get(i) == Villain1X.get(k)){
        if(ColumnCollisionVicinity.get(i) == Villain1Y.get(k)){

    if(board[Villain1X.get(k)][Villain1Y.get(k)] == 'V'){
        board[Villain1X.get(k)][Villain1Y.get(k)]  = ' ';

        Villain1X.set(k,16);
        Villain1Y.set(k,16);
        retainprintBoard();
//        Villain1X = 16;
//        Villain1Y = 16;
        break;
    }
        }
    }
}}
    }
}
    if(hero == 'W'){
        CollisionVicinity(size,1,hero);
       outer: for(int i = 0 ; i < RowCollisionVicinity.size();i++){
            for(int j = 0 ; j< ColumnCollisionVicinity.size();j++){
                for(int k = 0 ; k < Villain1X.size();k++){
                    if(RowCollisionVicinity.get(i) == Villain1X.get(k)){
                        if(ColumnCollisionVicinity.get(i) == Villain1Y.get(k)){//****************************

                            if(board[Villain1X.get(k)][Villain1Y.get(k)] == 'V'){
                                board[Villain1X.get(k)][Villain1Y.get(k)]  = ' ';
//        Villain1X = 16;
//        Villain1Y = 16;

                                Villain1X.set(k,16);
                                Villain1Y.set(k,16);
                                retainprintBoard();
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Villain Dead");
    }
    if(hero == 'G'){
        CollisionVicinity(size,2,hero);
       outer: for(int i = 0 ; i < RowCollisionVicinity.size();i++){
            for(int j = 0 ; j< ColumnCollisionVicinity.size();j++){
                for(int k = 0 ; k < Villain1X.size();k++){//****************************
                    if(RowCollisionVicinity.get(i) == Villain1X.get(k)){
                        if(ColumnCollisionVicinity.get(i) == Villain1Y.get(k)){

                            if(board[Villain1X.get(k)][Villain1Y.get(k)] == 'V'){
                                board[Villain1X.get(k)][Villain1Y.get(k)]  = ' ';
//        Villain1X = 16;
//        Villain1Y = 16;

                                Villain1X.set(k,16);
                                Villain1Y.set(k,16);
                                retainprintBoard();
                                break;
                            }
                        }
                    }
                }
            }
        }System.out.println("Villain Dead");
    }
//    for (int i = 0; i < size; i++) {
//        for(int j = 0 ; j < size ; j++){
//            if(board[i][j] == 'V'){
//                board[i][j] = ' ';
//                Villain1X = 16;
//                Villain1Y = 16;
//                break;
//            }
//}
//    }
}
      else if(VillainIdentity == 1){
    if(hero == 'H'){
        CollisionVicinity(size,0,hero);
        outer : for(int i = 0 ; i < RowCollisionVicinity.size();i++){
            for(int j = 0 ; j< ColumnCollisionVicinity.size();j++){
                for(int k = 0 ; k < Villain2X.size();k++){
                    if(RowCollisionVicinity.get(i) == Villain2X.get(k)){
                        if(ColumnCollisionVicinity.get(i) == Villain2Y.get(k)){
//****************************
                            if(board[Villain2X.get(k)][Villain2Y.get(k)] == 'E'){
                                board[Villain2X.get(k)][Villain2Y.get(k)]  = ' ';
//        Villain1X = 16;
//        Villain1Y = 16;

                                Villain2X.set(k,16);
                                Villain2Y.set(k,16);
                                retainprintBoard();
                                break;
                            }
                        }
                    }
                }
            }
        }System.out.println("Villain Dead");
    }
    if(hero == 'W'){
        CollisionVicinity(size,1,hero);
       outer : for(int i = 0 ; i < RowCollisionVicinity.size();i++){
            for(int j = 0 ; j< ColumnCollisionVicinity.size();j++){
                for(int k = 0 ; k < Villain2X.size();k++){
                    if(RowCollisionVicinity.get(i) == Villain2X.get(k)){
                        if(ColumnCollisionVicinity.get(i) == Villain2Y.get(k)){
//****************************
                            if(board[Villain2X.get(k)][Villain2Y.get(k)] == 'E'){
                                board[Villain2X.get(k)][Villain2Y.get(k)]  = ' ';
//        Villain1X = 16;
//        Villain1Y = 16;

                                Villain2X.set(k,16);
                                Villain2Y.set(k,16);
                                retainprintBoard();
                                break;
                            }
                        }
                    }
                }
            }
        }System.out.println("Villain Dead");
    }
    if(hero == 'G'){
        CollisionVicinity(size,2,hero);
     outer :   for(int i = 0 ; i < RowCollisionVicinity.size();i++){
            for(int j = 0 ; j< ColumnCollisionVicinity.size();j++){
                for(int k = 0 ; k < Villain2X.size();k++){
                    if(RowCollisionVicinity.get(i) == Villain2X.get(k)){
                        if(ColumnCollisionVicinity.get(i) == Villain2Y.get(k)){

                            if(board[Villain2X.get(k)][Villain2Y.get(k)] == 'E'){//****************************
                                board[Villain2X.get(k)][Villain2Y.get(k)]  = ' ';
//        Villain1X = 16;
//        Villain1Y = 16;

                                Villain2X.set(k,16);
                                Villain2Y.set(k,16);
                                retainprintBoard();
                                break;
                            }
                        }
                    }
                }
            }
        }System.out.println("Villain Dead");
    }
        }
else if(VillainIdentity == 2){
    if(hero == 'H'){
        CollisionVicinity(size,0,hero);//****************************
      outer :  for(int i = 0 ; i < RowCollisionVicinity.size();i++){
            for(int j = 0 ; j< ColumnCollisionVicinity.size();j++){
                for(int k = 0 ; k < Villain3X.size();k++){
                    if(RowCollisionVicinity.get(i) == Villain3X.get(k)){
                        if(ColumnCollisionVicinity.get(i) == Villain3Y.get(k)){

                            if(board[Villain3X.get(k)][Villain3Y.get(k)] == 'S'){
                                board[Villain3X.get(k)][Villain3Y.get(k)]  = ' ';
//        Villain1X = 16;
//        Villain1Y = 16;

                                Villain3X.set(k,16);
                                Villain3Y.set(k,16);
                                retainprintBoard();
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Villain Dead");
    }
    if(hero == 'W'){
        CollisionVicinity(size,1,hero);
        for(int i = 0 ; i < RowCollisionVicinity.size();i++){
            for(int j = 0 ; j< ColumnCollisionVicinity.size();j++){
                for(int k = 0 ; k < Villain3X.size();k++){
                    if(RowCollisionVicinity.get(i) == Villain3X.get(k)){
                        if(ColumnCollisionVicinity.get(i) == Villain3Y.get(k)){

                            if(board[Villain3X.get(k)][Villain3Y.get(k)] == 'S'){
                                board[Villain3X.get(k)][Villain3Y.get(k)]  = ' ';
//        Villain1X = 16;//****************************
//        Villain1Y = 16;
                                Villain3X.set(k,16);
                                Villain3Y.set(k,16);
                                retainprintBoard();
                                break;
                            }
                        }
                    }
                }
            }
        }System.out.println("Villain Dead");
    }
    if(hero == 'G'){
        CollisionVicinity(size,2,hero);
     outer :   for(int i = 0 ; i < RowCollisionVicinity.size();i++){
            for(int j = 0 ; j< ColumnCollisionVicinity.size();j++){
                for(int k = 0 ; k < Villain3X.size();k++){
                    if(RowCollisionVicinity.get(i) == Villain3X.get(k)){
                        if(ColumnCollisionVicinity.get(i) == Villain3Y.get(k)){

                            if(board[Villain3X.get(k)][Villain3Y.get(k)] == 'S'){
                                board[Villain3X.get(k)][Villain3Y.get(k)]  = ' ';
//        Villain1X = 16;
//        Villain1Y = 16;
                                Villain3X.set(k,16);
                                Villain3Y.set(k,16);
                                retainprintBoard();
                                break;
                            }
                        }
                    }
                }
            }
        }System.out.println("Villain Dead");
    }
}
//       else if(VillainIdentity == 2){
//            for (int i = 0; i < size; i++) {
//                for(int j = 0 ; j < size ; j++){
//                    if(board[i][j] == 'S'){
//                        board[i][j] = ' ';
//                        Villain3X = 16;
//                        Villain3Y = 16;
//                        break;
//                    }
//                }
//            }
//        }
    }
    //This is used to retain heroes after 1 round ends .
    public void retainHeroesAfterFight(int HeroIdentity,int size){
        if(Logic.HeroList.size()==3){
            board[Hero1X][Hero1Y] = 'H';
            board[Hero2X][Hero2Y] = 'W';
            board[Hero3X][Hero3Y] = 'G';
        }
        if(Logic.HeroList.size()==2){
            if(Logic.DeadHeroIdentity.get(0) == 0){

                board[Hero2X][Hero2Y] = 'W';
                board[Hero3X][Hero3Y] = 'G';
            }
            if(Logic.DeadHeroIdentity.get(0) == 1){

                board[Hero1X][Hero1Y] = 'H';
                board[Hero3X][Hero3Y] = 'G';
            }
            if(Logic.DeadHeroIdentity.get(0) == 2){

                board[Hero1X][Hero1Y] = 'H';
                board[Hero2X][Hero2Y] = 'W';
            }
        }
        if(Logic.HeroList.size()==1){
            if(Logic.DeadHeroIdentity.get(0) == 0){
                if(Logic.DeadHeroIdentity.get(1) == 1){
                board[Hero3X][Hero3Y] = 'G';
            }}
            if(Logic.DeadHeroIdentity.get(0) == 1){
                if(Logic.DeadHeroIdentity.get(1) == 0){
                    board[Hero3X][Hero3Y] = 'G';
                }}
            if(Logic.DeadHeroIdentity.get(0) == 2){
                if(Logic.DeadHeroIdentity.get(1) == 0){
                    board[Hero2X][Hero2Y] = 'W';
                }}
            if(Logic.DeadHeroIdentity.get(0) == 0){
                if(Logic.DeadHeroIdentity.get(1) == 2){
                    board[Hero2X][Hero2Y] = 'W';
                }}
            if(Logic.DeadHeroIdentity.get(0) == 1){
                if(Logic.DeadHeroIdentity.get(1) == 2){
                    board[Hero1X][Hero1Y] = 'H';
                }}
            if(Logic.DeadHeroIdentity.get(0) == 2){
                if(Logic.DeadHeroIdentity.get(1) == 1){
                    board[Hero1X][Hero1Y] = 'H';
                }}

        }
        if(Logic.HeroList.size()==3){
            board[Hero1X][Hero1Y] = 'H';
            board[Hero2X][Hero2Y] = 'W';
            board[Hero3X][Hero3Y] = 'G';
        }
    }
    public void checkHeroDeadOrAlive(int HeroIdentity ,int size , char symbol){
        if(Hero1X>8 && Hero1Y>8){
             Alive = false;
        }
        if(Hero2X>8 && Hero2Y>8){
             Alive = false;
        }if(Hero3X>8 && Hero3Y>8){
             Alive = false;
        }

    }
    //IT IS INVOVKED WHEN the player hp falls below 0.
    public void DeadPlayers(int HeroIdentity,int size){
        if(HeroIdentity == 0){
            for (int i = 0; i < size; i++) {
                for(int j = 0 ; j < size ; j++){
                    if(board[i][j] == 'H'){
                        board[i][j] = ' ';
                        Hero1X = 16;
                        Hero1Y = 16;
                        retainprintBoard();
                    }
                }
            }
        }
        else if(HeroIdentity == 1){
            for (int i = 0; i < size; i++) {
                for(int j = 0 ; j < size ; j++){
                    if(board[i][j] == 'W'){
                        board[i][j] = ' ';
                        Hero2X = 16;
                        Hero2Y = 16;
                        retainprintBoard();
                    }
                }
            }
        }
        else if(HeroIdentity == 2){
            for (int i = 0; i < size; i++) {
                for(int j = 0 ; j < size ; j++){
                    if(board[i][j] == 'G'){
                        board[i][j] = ' ';
                        Hero3X = 16;
                        Hero3Y = 16;
                        retainprintBoard();
                    }
                }
            }
        }
    }
    //Teleport on the basis of options
    public void TeleportWithOptions(int size , char symbol , int HeroIdentity) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        if(HeroIdentity == 0){
            Logic.printHeader("TELEPORTATION PROGRAM PROCESSING ...........");
            System.out.println("Which Hero do you wanna help or transport to ?");
            System.out.println("(1) Go Back .");
            System.out.println("(2) Player (2)");
            System.out.println("(3) Player (3)");

            int TeleportChoice  = Logic.UserInput("-->",3);
            if(TeleportChoice == 1){
                userMove(size,symbol,HeroIdentity);
            }
            if(TeleportChoice == 2){
                Logic.HeroList.get(HeroIdentity).lane = 1;
            }
            else if (TeleportChoice == 3){
                Logic.HeroList.get(HeroIdentity).lane = 2;
            }
            TeleportationVicinityOptions(size,TeleportChoice);
            System.out.println("Paths you can travel to near Hero "+ TeleportChoice+" are as follows :");
            for(int l = 0 ; l < RowTeleportVicinity.size();l++){
                System.out.println(" ("+(l+1)+")  -->"+ RowTeleportVicinity.get(l)+" , "+ColumnTeleportVicinity.get(l) );
            }
            int TeleportPathChoice  = Logic.UserInput("-->",RowTeleportVicinity.size());
            board[Hero1X][Hero1Y] = ' ';
            board[RowTeleportVicinity.get(TeleportPathChoice-1)][ColumnTeleportVicinity.get(TeleportPathChoice-1)] = 'H';
            System.out.println("x: "+ RowTeleportVicinity.get(TeleportPathChoice-1)+" && y : "+ColumnTeleportVicinity.get(TeleportPathChoice-1));
            Hero1XOrigin.add(Hero1X);
            Hero1YOrigin.add(Hero1Y);
            Hero1RetainX=Hero1X;
            Hero1RetainY = Hero1Y;
            Hero1X=RowTeleportVicinity.get(TeleportPathChoice-1);
            Hero1Y = ColumnTeleportVicinity.get(TeleportPathChoice-1);



        }
        if(HeroIdentity == 1){
            Logic.printHeader("TELEPORTATION PROGRAM PROCESSING ...........");
            System.out.println("Which Hero do you wanna help or transport to ?");
            System.out.println("(1) Player (1)");
            System.out.println("(2) Go Back. ");
            System.out.println("(3) Player (3)");

            int TeleportChoice  = Logic.UserInput("-->",3);
            if(TeleportChoice == 2){
                userMove(size,symbol,HeroIdentity);
            }
            if(TeleportChoice == 1){
                Logic.HeroList.get(HeroIdentity).lane = 0;
            }
            else if (TeleportChoice == 3){
                Logic.HeroList.get(HeroIdentity).lane = 2;
            }
            TeleportationVicinityOptions(size,TeleportChoice);
            System.out.println("Paths you can travel to near Hero "+ TeleportChoice+" are as follows :");
            for(int l = 0 ; l < RowTeleportVicinity.size();l++){
                System.out.println(" ("+(l+1)+")  -->"+ RowTeleportVicinity.get(l)+" , "+ColumnTeleportVicinity.get(l) );
            }
            int TeleportPathChoice  = Logic.UserInput("-->",RowTeleportVicinity.size());
            board[Hero2X][Hero2Y] = ' ';
            board[RowTeleportVicinity.get(TeleportPathChoice-1)][ColumnTeleportVicinity.get(TeleportPathChoice-1)] = 'W';
            System.out.println("x: "+ RowTeleportVicinity.get(TeleportPathChoice-1)+" && y : "+ColumnTeleportVicinity.get(TeleportPathChoice-1));
            Hero2XOrigin.add(Hero2X);
            Hero2YOrigin.add(Hero2Y);
            Hero2RetainX=Hero2X;
            Hero2RetainY = Hero2Y;
            Hero2X=RowTeleportVicinity.get(TeleportPathChoice-1);
            Hero2Y = ColumnTeleportVicinity.get(TeleportPathChoice-1);


        }
        if(HeroIdentity == 2){
            Logic.printHeader("TELEPORTATION PROGRAM PROCESSING ...........");
            System.out.println("Which Hero do you wanna help or transport to ?");
            System.out.println("(1) Player (1)");
            System.out.println("(2)  Player (2) ");
            System.out.println("(3) Go Back");

            int TeleportChoice  = Logic.UserInput("-->",3);
            if(TeleportChoice == 3){
                userMove(size,symbol,HeroIdentity);
            }
            if(TeleportChoice == 1){
                Logic.HeroList.get(HeroIdentity).lane = 0;
            }
            else if (TeleportChoice == 2){
                Logic.HeroList.get(HeroIdentity).lane = 1;
            }
            TeleportationVicinityOptions(size,TeleportChoice);
            System.out.println("Paths you can travel to near Hero "+ TeleportChoice+" are as follows :");
            for(int l = 0 ; l < RowTeleportVicinity.size();l++){
                System.out.println(" ("+(l+1)+")  -->"+ RowTeleportVicinity.get(l)+" , "+ColumnTeleportVicinity.get(l) );
            }
            int TeleportPathChoice  = Logic.UserInput("-->",RowTeleportVicinity.size());
            board[Hero3X][Hero3Y] = ' ';
            board[RowTeleportVicinity.get(TeleportPathChoice-1)][ColumnTeleportVicinity.get(TeleportPathChoice-1)] = 'G';
            System.out.println("x: "+ RowTeleportVicinity.get(TeleportPathChoice-1)+" && y : "+ColumnTeleportVicinity.get(TeleportPathChoice-1));
            Hero3XOrigin.add(Hero3X);
            Hero3YOrigin.add(Hero3Y);
            Hero3RetainX=Hero3X;
            Hero3RetainY = Hero3Y;
            Hero3X=RowTeleportVicinity.get(TeleportPathChoice-1);
            Hero3Y = ColumnTeleportVicinity.get(TeleportPathChoice-1);


        }
    }
    // Method to check the collision range for the heroes and the villains
    public void CollisionVicinity(int size , int HeroIdentity,char symbol){

        System.out.println("Something Works !");

        if(!RowCollisionVicinity.isEmpty() || !ColumnCollisionVicinity.isEmpty()){
            System.out.println("Path Table not Empty!");
            for(int k  = 0 ; k< RowCollisionVicinity.size() ; k++){
                RowCollisionVicinity.remove(k);
                ColumnCollisionVicinity.remove(k);
            }
            System.out.println("Size "+RowCollisionVicinity.size()+" , "+ ColumnCollisionVicinity.size());

        }
        if(HeroIdentity == 0){
            if(!RowCollisionVicinity.isEmpty() || !ColumnCollisionVicinity.isEmpty()){
                System.out.println("Path Table not Empty!");
                for(int k  = 0 ; k< RowCollisionVicinity.size() ; k++){
                    RowCollisionVicinity.remove(k);
                    ColumnCollisionVicinity.remove(k);
                }
            }
            if(!RowCollisionVicinity.isEmpty() || !ColumnCollisionVicinity.isEmpty()){
                System.out.println("Path Table not Empty!");
                for(int k  = 0 ; k< RowCollisionVicinity.size() ; k++){
                    RowCollisionVicinity.remove(k);
                    ColumnCollisionVicinity.remove(k);
                }
            }
            System.out.println("Size "+RowCollisionVicinity.size()+" , "+ ColumnCollisionVicinity.size());
            System.out.println("It enters .");

            System.out.println("Path Table Empty ");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == 'H') {
                        System.out.println("Found H at "+ i+" , "+j);

                        RowCollisionVicinity.add(i);
                        ColumnCollisionVicinity.add(j);
                        UpperRow = i-1;
                        UpperColumn = j;
                        System.out.println("Row : "+ UpperRow+" Column : "+UpperColumn);
                        if (UpperRow  >-1 && UpperRow<8 && UpperColumn>-1 && UpperColumn < 8) {
                            if (UpperRow <0){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (board[UpperRow][UpperColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }

                            else{
                                RowCollisionVicinity.add(UpperRow);
                                ColumnCollisionVicinity.add(UpperColumn);

                            }
                        }
                        LowerRow = i+1;
                        LowerColumn = j;
                        System.out.println("Row : "+ LowerRow+" Column : "+LowerColumn);

                        if (LowerRow >-1 && LowerRow<8 && LowerColumn>-1 && LowerColumn < 8) {
                            if(LowerRow > 7){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (board[LowerRow][LowerColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }
                            else{
                                RowCollisionVicinity.add(LowerRow);
                                ColumnCollisionVicinity.add(LowerColumn);
                            }
                        }
                        LeftSideRow = i;
                        LeftSideColumn = j-1;
                        System.out.println("Row : "+ LeftSideRow+" Column : "+LeftSideColumn);
                        if (LeftSideColumn >-1&& LeftSideRow>-1 && LeftSideColumn<8 && LeftSideRow < 8) {

                            if (LeftSideColumn<0){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else if (board[LeftSideRow][LeftSideColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }
                            else{

                                RowCollisionVicinity.add(LeftSideRow);
                                ColumnCollisionVicinity.add(LeftSideColumn);
                            }
                        }

                        RightSideRow = i;
                        RightSideColumn = j+1;
                        System.out.println("Row : "+ RightSideRow+" Column : "+RightSideColumn);
                        if (RightSideColumn >-1 && RightSideRow >-1 && RightSideColumn<8 && RightSideRow < 8) {

                            if (RightSideColumn>7){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }
                            if (board[RightSideRow][RightSideColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            else{

                                RowCollisionVicinity.add(RightSideRow);
                                ColumnCollisionVicinity.add(RightSideColumn);

                            }
                        }
                        Diagonal1Row = i-1;
                        Diagonal1Column = j-1;
                        System.out.println("Row : "+ Diagonal1Row+" Column : "+Diagonal1Column);
                        if (Diagonal1Column >-1 && Diagonal1Row >-1 && Diagonal1Column<8 && Diagonal1Row < 8) {

                            if (Diagonal1Row < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (Diagonal1Column < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else if (board[Diagonal1Row][Diagonal1Column] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else {

                                RowCollisionVicinity.add(Diagonal1Row);
                                ColumnCollisionVicinity.add(Diagonal1Column);
                            }
                        }
                        Diagonal2Row = i-1;
                        Diagonal2Column = j+1;
                        System.out.println("Row : "+ Diagonal2Row+" Column : "+Diagonal2Column);
                        if (Diagonal2Column >-1 && Diagonal2Row>-1 && Diagonal2Column<8 && Diagonal2Row < 8) {

                            if (Diagonal2Row < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (Diagonal2Column > 7) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (board[RightSideRow][RightSideColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            else {

                                RowCollisionVicinity.add(Diagonal2Row);
                                ColumnCollisionVicinity.add(Diagonal2Column);
                            }
                        }
                        Diagonal3Row = i+1;
                        Diagonal3Column = j-1;
                        System.out.println("Row : "+ Diagonal3Row+" Column : "+Diagonal3Column);
                        if (Diagonal3Column >-1 && Diagonal3Row>-1 && Diagonal3Column<8 && Diagonal3Row < 8) {

                            if (Diagonal3Row>7){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (Diagonal3Column<0){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            else if (board[Diagonal3Row][Diagonal3Column] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            else{

                                RowCollisionVicinity.add(Diagonal3Row);
                                ColumnCollisionVicinity.add(Diagonal3Column);
                            }
                        }
                        Diagonal4Row = i+1;
                        Diagonal4Column = j+1;
                        System.out.println("Row : "+ Diagonal4Row+" Column : "+Diagonal4Column);
                        if (Diagonal4Column >-1&& Diagonal4Row>-1 && Diagonal4Column<8 && Diagonal4Row < 8) {

                            if (Diagonal4Row>7){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            else if (Diagonal4Column>7){
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (board[Diagonal4Row][Diagonal4Column] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }

                            else{

                                RowCollisionVicinity.add(Diagonal4Row);
                                ColumnCollisionVicinity.add(Diagonal4Column);
                            }}
                        Logic.printHeader("Your Vicinity Paths are as follows : ");
                        for(int m = 0; m< RowCollisionVicinity.size();m++){
                            System.out.println(" ("+m+")"+" Row : "+RowCollisionVicinity.get(m)+" Column"+ColumnCollisionVicinity.get(m));
                        }

                    }
                }
            }
        }


        else if (HeroIdentity == 1){  System.out.println("It enters .");
            if(!RowCollisionVicinity.isEmpty() || !ColumnCollisionVicinity.isEmpty()){
                System.out.println("Path Table not Empty!");
                for(int k  = 0 ; k< RowCollisionVicinity.size() ; k++){
                    RowCollisionVicinity.remove(k);
                    ColumnCollisionVicinity.remove(k);
                }
            }
            if(!RowCollisionVicinity.isEmpty() || !ColumnCollisionVicinity.isEmpty()){
                System.out.println("Path Table not Empty!");
                for(int k  = 0 ; k< RowCollisionVicinity.size() ; k++){
                    RowCollisionVicinity.remove(k);
                    ColumnCollisionVicinity.remove(k);
                }
            }
            System.out.println("Size "+RowCollisionVicinity.size()+" , "+ ColumnCollisionVicinity.size());
            System.out.println("Path Table Empty ");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == 'W') {
                        System.out.println("Found W at " + i + " , " + j);

                        RowCollisionVicinity.add(i);
                        ColumnCollisionVicinity.add(j);
                        UpperRow = i - 1;
                        UpperColumn = j;
                        System.out.println("Row : " + UpperRow + " Column : " + UpperColumn);
                        if (UpperRow > -1 && UpperRow < 8 && UpperColumn > -1 && UpperColumn < 8) {
                            if (UpperRow < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (board[UpperRow][UpperColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            } else {
                                RowCollisionVicinity.add(UpperRow);
                                ColumnCollisionVicinity.add(UpperColumn);

                            }
                        }
                        LowerRow = i + 1;
                        LowerColumn = j;
                        System.out.println("Row : " + LowerRow + " Column : " + LowerColumn);

                        if (LowerRow > -1 && LowerRow < 8 && LowerColumn > -1 && LowerColumn < 8) {
                            if (LowerRow > 7) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (board[LowerRow][LowerColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            } else {
                                RowCollisionVicinity.add(LowerRow);
                                ColumnCollisionVicinity.add(LowerColumn);
                            }
                        }
                        LeftSideRow = i;
                        LeftSideColumn = j - 1;
                        System.out.println("Row : " + LeftSideRow + " Column : " + LeftSideColumn);
                        if (LeftSideColumn > -1 && LeftSideRow > -1 && LeftSideColumn < 8 && LeftSideRow < 8) {

                            if (LeftSideColumn < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else if (board[LeftSideRow][LeftSideColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            } else {

                                RowCollisionVicinity.add(LeftSideRow);
                                ColumnCollisionVicinity.add(LeftSideColumn);
                            }
                        }

                        RightSideRow = i;
                        RightSideColumn = j + 1;
                        System.out.println("Row : " + RightSideRow + " Column : " + RightSideColumn);
                        if (RightSideColumn > -1 && RightSideRow > -1 && RightSideColumn < 8 && RightSideRow < 8) {

                            if (RightSideColumn > 7) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }
                            if (board[RightSideRow][RightSideColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else {

                                RowCollisionVicinity.add(RightSideRow);
                                ColumnCollisionVicinity.add(RightSideColumn);

                            }
                        }
                        Diagonal1Row = i - 1;
                        Diagonal1Column = j - 1;
                        System.out.println("Row : " + Diagonal1Row + " Column : " + Diagonal1Column);
                        if (Diagonal1Column > -1 && Diagonal1Row > -1 && Diagonal1Column < 8 && Diagonal1Row < 8) {

                            if (Diagonal1Row < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (Diagonal1Column < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else if (board[Diagonal1Row][Diagonal1Column] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else {

                                RowCollisionVicinity.add(Diagonal1Row);
                                ColumnCollisionVicinity.add(Diagonal1Column);
                            }
                        }
                        Diagonal2Row = i - 1;
                        Diagonal2Column = j + 1;
                        System.out.println("Row : " + Diagonal2Row + " Column : " + Diagonal2Column);
                        if (Diagonal2Column > -1 && Diagonal2Row > -1 && Diagonal2Column < 8 && Diagonal2Row < 8) {

                            if (Diagonal2Row < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (Diagonal2Column > 7) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (board[RightSideRow][RightSideColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else {

                                RowCollisionVicinity.add(Diagonal2Row);
                                ColumnCollisionVicinity.add(Diagonal2Column);
                            }
                        }
                        Diagonal3Row = i + 1;
                        Diagonal3Column = j - 1;
                        System.out.println("Row : " + Diagonal3Row + " Column : " + Diagonal3Column);
                        if (Diagonal3Column > -1 && Diagonal3Row > -1 && Diagonal3Column < 8 && Diagonal3Row < 8) {

                            if (Diagonal3Row > 7) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (Diagonal3Column < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else if (board[Diagonal3Row][Diagonal3Column] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else {

                                RowCollisionVicinity.add(Diagonal3Row);
                                ColumnCollisionVicinity.add(Diagonal3Column);
                            }
                        }
                        Diagonal4Row = i + 1;
                        Diagonal4Column = j + 1;
                        System.out.println("Row : " + Diagonal4Row + " Column : " + Diagonal4Column);
                        if (Diagonal4Column > -1 && Diagonal4Row > -1 && Diagonal4Column < 8 && Diagonal4Row < 8) {

                            if (Diagonal4Row > 7) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else if (Diagonal4Column > 7) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (board[Diagonal4Row][Diagonal4Column] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else {

                                RowCollisionVicinity.add(Diagonal4Row);
                                ColumnCollisionVicinity.add(Diagonal4Column);
                            }
                        }
                        Logic.printHeader("Your Vicinity Paths are as follows : ");
                        for (int m = 0; m < RowCollisionVicinity.size(); m++) {
                            System.out.println(" (" + m + ")" + " Row : " + RowCollisionVicinity.get(m) + " Column" + ColumnCollisionVicinity.get(m));
                        }
                    }
                }


            }
        }
        else if (HeroIdentity == 2){
            System.out.println("It enters .");
            if(!RowCollisionVicinity.isEmpty() || !ColumnCollisionVicinity.isEmpty()){
                System.out.println("Path Table not Empty!");
                for(int k  = 0 ; k< RowCollisionVicinity.size() ; k++){
                    RowCollisionVicinity.remove(k);
                    ColumnCollisionVicinity.remove(k);
                }
            }
            if(!RowCollisionVicinity.isEmpty() || !ColumnCollisionVicinity.isEmpty()){
                System.out.println("Path Table not Empty!");
                for(int k  = 0 ; k< RowCollisionVicinity.size() ; k++){
                    RowCollisionVicinity.remove(k);
                    ColumnCollisionVicinity.remove(k);
                }
            }
            System.out.println("Size "+RowCollisionVicinity.size()+" , "+ ColumnCollisionVicinity.size());

            System.out.println("Path Table Empty ");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == 'G') {
                        System.out.println("Found G at " + i + " , " + j);

                        RowCollisionVicinity.add(i);
                        ColumnCollisionVicinity.add(j);
                        UpperRow = i - 1;
                        UpperColumn = j;
                        System.out.println("Row : " + UpperRow + " Column : " + UpperColumn);
                        if (UpperRow > -1 && UpperRow < 8 && UpperColumn > -1 && UpperColumn < 8) {
                            if (UpperRow < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (board[UpperRow][UpperColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            } else {
                                RowCollisionVicinity.add(UpperRow);
                                ColumnCollisionVicinity.add(UpperColumn);

                            }
                        }
                        LowerRow = i + 1;
                        LowerColumn = j;
                        System.out.println("Row : " + LowerRow + " Column : " + LowerColumn);

                        if (LowerRow > -1 && LowerRow < 8 && LowerColumn > -1 && LowerColumn < 8) {
                            if (LowerRow > 7) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (board[LowerRow][LowerColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            } else {
                                RowCollisionVicinity.add(LowerRow);
                                ColumnCollisionVicinity.add(LowerColumn);
                            }
                        }
                        LeftSideRow = i;
                        LeftSideColumn = j - 1;
                        System.out.println("Row : " + LeftSideRow + " Column : " + LeftSideColumn);
                        if (LeftSideColumn > -1 && LeftSideRow > -1 && LeftSideColumn < 8 && LeftSideRow < 8) {

                            if (LeftSideColumn < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else if (board[LeftSideRow][LeftSideColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            } else {

                                RowCollisionVicinity.add(LeftSideRow);
                                ColumnCollisionVicinity.add(LeftSideColumn);
                            }
                        }

                        RightSideRow = i;
                        RightSideColumn = j + 1;
                        System.out.println("Row : " + RightSideRow + " Column : " + RightSideColumn);
                        if (RightSideColumn > -1 && RightSideRow > -1 && RightSideColumn < 8 && RightSideRow < 8) {

                            if (RightSideColumn > 7) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");

                            }
                            if (board[RightSideRow][RightSideColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else {

                                RowCollisionVicinity.add(RightSideRow);
                                ColumnCollisionVicinity.add(RightSideColumn);

                            }
                        }
                        Diagonal1Row = i - 1;
                        Diagonal1Column = j - 1;
                        System.out.println("Row : " + Diagonal1Row + " Column : " + Diagonal1Column);
                        if (Diagonal1Column > -1 && Diagonal1Row > -1 && Diagonal1Column < 8 && Diagonal1Row < 8) {

                            if (Diagonal1Row < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (Diagonal1Column < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else if (board[Diagonal1Row][Diagonal1Column] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else {

                                RowCollisionVicinity.add(Diagonal1Row);
                                ColumnCollisionVicinity.add(Diagonal1Column);
                            }
                        }
                        Diagonal2Row = i - 1;
                        Diagonal2Column = j + 1;
                        System.out.println("Row : " + Diagonal2Row + " Column : " + Diagonal2Column);
                        if (Diagonal2Column > -1 && Diagonal2Row > -1 && Diagonal2Column < 8 && Diagonal2Row < 8) {

                            if (Diagonal2Row < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (Diagonal2Column > 7) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (board[RightSideRow][RightSideColumn] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else {

                                RowCollisionVicinity.add(Diagonal2Row);
                                ColumnCollisionVicinity.add(Diagonal2Column);
                            }
                        }
                        Diagonal3Row = i + 1;
                        Diagonal3Column = j - 1;
                        System.out.println("Row : " + Diagonal3Row + " Column : " + Diagonal3Column);
                        if (Diagonal3Column > -1 && Diagonal3Row > -1 && Diagonal3Column < 8 && Diagonal3Row < 8) {

                            if (Diagonal3Row > 7) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (Diagonal3Column < 0) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else if (board[Diagonal3Row][Diagonal3Column] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else {

                                RowCollisionVicinity.add(Diagonal3Row);
                                ColumnCollisionVicinity.add(Diagonal3Column);
                            }
                        }
                        Diagonal4Row = i + 1;
                        Diagonal4Column = j + 1;
                        System.out.println("Row : " + Diagonal4Row + " Column : " + Diagonal4Column);
                        if (Diagonal4Column > -1 && Diagonal4Row > -1 && Diagonal4Column < 8 && Diagonal4Row < 8) {

                            if (Diagonal4Row > 7) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else if (Diagonal4Column > 7) {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            }
                            if (board[Diagonal4Row][Diagonal4Column] == 'X') {
                                System.out.println("Cannot Push this As it will be invalid vicinity ");
                            } else {

                                RowCollisionVicinity.add(Diagonal4Row);
                                ColumnCollisionVicinity.add(Diagonal4Column);
                            }
                        }
                        Logic.printHeader("Your Vicinity Paths are as follows : ");
                        for (int m = 0; m < RowCollisionVicinity.size(); m++) {
                            System.out.println(" (" + m + ")" + " Row : " + RowCollisionVicinity.get(m) + " Column" + ColumnCollisionVicinity.get(m));
                        }
                    }
                }

            }
        }
    }
    //This method is used to check the teleport range for the heroes

    public boolean TeleportVicinity(int rows, int columns, int HeroToTeleport,int HeroIdentity, char symbol){
         if(HeroIdentity == 0 ) {



             if (HeroToTeleport == 1) {
                 Random a = new Random();
                 int RandomChoice = a.nextInt(2-0+1)+0;
                 Logic.HeroList.get(HeroIdentity).lane = 1;
                 System.out.println("Choice" + RandomChoice);
                 if (RandomChoice == 0) {
                     for (int i = 0; i < rows; i++) {
                         for (int j = 0; j < columns; j++) {
                             if (board[i][j] == 'W') {

                                 System.out.println("Found at : " + i + " , " + j);
//                                int FirstchoiceRow = i+1;
//                                int FirstchoiceColumn = i+1;
//                                int teleportAdjacentChoice = Logic.UserInput("-->",2);

                                 System.out.println("CHOICE: " + RandomChoice);

                                 int row = i - 1;

                                 if (row < 0) {
                                     System.out.println("Invalid Choice");
                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (j < 0) {
                                     System.out.println("Invalid Choice");
                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (j  > 7) {
                                     System.out.println("Invalid Choice");
                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (row > 7) {
                                     System.out.println("Invalid Choice");
                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (board[row][j] == 'X') {
                                     System.out.println("No Man's Land . Go Somewhere Else.");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 }

                                 System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                                 System.out.println("Row Updated : " + row + "Column Updated : " + j);


                                 TeleportBoardRowRange = row;
                                 TeleportBoardColumnRange = j;
                                 System.out.println("Row Added : " + TeleportBoardRowRange + "  Column Added : " + TeleportBoardColumnRange);
                                 return true;
                             }
                         }
                     }
                     return true;
                 } else if (RandomChoice == 1) {
                     for (int i = 0; i < rows; i++) {
                         for (int j = 0; j < columns; j++) {
                             if (board[i][j] == 'W') {

                                 System.out.println("Found at : " + i + " , " + j);
                                 System.out.println("CHOICE: " + RandomChoice);
                                 int column = j + 1;

                                 if (i < 0) {

                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (column < 0) {
                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (column >7) {
                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (i >7) {

                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (board[i][column] == 'X') {
                                     System.out.println("No Man's Land . Go Somewhere Else.");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 }

                                 System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                                 System.out.println("Row Updated : " + i + " Column Updated : " + column);

                                 TeleportBoardRowRange = i;
                                 TeleportBoardColumnRange =column;
                                 System.out.println("Row Added : " + TeleportBoardRowRange+ "  Column Added : " + TeleportBoardColumnRange);
                                 return true;
                             }
                         }

                     }return true;

                 } else if (RandomChoice == 2) {

                     for (int i = 0; i < rows; i++) {
                         System.out.println("No of Times : i : " + i);
                         for (int j = 0; j < columns; j++) {
                             System.out.println("No of Times : j : " + j);
                             if (board[i][j] == 'W') {

                                 System.out.println("Found at : " + i + " , " + j);

                                 System.out.println("cHOICE: " + RandomChoice);
                                 int column = j - 1;

                                 if (i < 0) {

                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (column < 0) {
                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (column > 7) {
                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (i > 7) {

                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 }
                                 if (board[i][column] == 'X') {
                                     System.out.println("No Man's Land . Go Somewhere Else.");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 }

                                 System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                                 System.out.println("Row Updated : " + i + " Column Updated : " + column);

                                 TeleportBoardRowRange=i;
                                 TeleportBoardColumnRange=column;

                                 System.out.println("Row Added : " + TeleportBoardRowRange + "  Column Added : " + TeleportBoardColumnRange);
                                 System.out.println("I am stuck here need to break out !");
                                 return true;

                             }

                         }

                     }
                     return true;

                 }
                 System.out.println("Exited");
                 return true;
             } else if (HeroToTeleport == 2) {
                 Logic.HeroList.get(HeroIdentity).lane = 2;
                 Random a = new Random();
                 int RandomChoice = a.nextInt(2-0+1)+0;

                 System.out.println("Choice" + RandomChoice);

                 if (RandomChoice == 0) {
                     for (int i = 0; i < rows; i++) {
                         System.out.println("No of Times : i : " + i);
                         for (int j = 0; j < columns; j++) {
                             System.out.println("No of Times : j : " + j);

                             if (board[i][j] == 'G') {
                                 System.out.println("Found at : " + i + " , " + j);
                                 System.out.println("cHOICE: " + RandomChoice);
                                 int row = i - 1;

                                 if (row < 0) {

                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (j < 0) {
                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (j > 7) {
                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (row > 7) {

                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 }
                                else if (board[row][j] == 'X') {
                                     System.out.println("No Man's Land . Go Somewhere Else.");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 }
                                 System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                                 System.out.println("Row Updated : " + row + "Column Updated : " + j);

                                 TeleportBoardRowRange=row;
                                 TeleportBoardColumnRange=j;
                                 System.out.println("Row Added : " + TeleportBoardRowRange + "  Column Added : " + TeleportBoardColumnRange);
                                 return true;


                             }
                         }
                     }return true;
                 } else if (RandomChoice == 1) {
                     for (int i = 0; i < rows; i++) {
                         System.out.println("No of Times : i : " + i);
                         for (int j = 0; j < columns; j++) {
                             System.out.println("No of Times : j : " + j);

                             if (board[i][j] == 'G') {
                                 System.out.println("Found at : " + i + " , " + j);
                                 System.out.println("cHOICE: " + RandomChoice);
                                 int column = j + 1;
                                 System.out.println("i is : " + i + "j is :" + j);
                                 if (i < 0) {

                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (column < 0) {
                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (column == 8) {
                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (i == 8) {

                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 }
                                else if (board[i][column] == 'X') {
                                     System.out.println("No Man's Land . Go Somewhere Else.");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 }

                                 System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                                 System.out.println("Row Updated : " + i + "Column Updated : " + column);
                                 TeleportBoardRowRange=i;
                                 TeleportBoardColumnRange=column;
                                 System.out.println("Row Added : " + TeleportBoardRowRange + "  Column Added : " + TeleportBoardColumnRange);
                                 return true;
                             }
                         }
                     }return true;
                     }
                  else if (RandomChoice == 2) {
                     for (int i = 0; i < rows; i++) {
                         System.out.println("No of Times : i : " + i);
                         for (int j = 0; j < columns; j++) {
                             System.out.println("No of Times : j : " + j);

                             if (board[i][j] == 'G') {
                                 System.out.println("Found at : " + i + " , " + j);
                                 System.out.println("cHOICE: " + RandomChoice);
                                 int column = j - 1;

                                 if (i < 0) {

                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (column < 0) {
                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (column > 7) {
                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 } else if (i > 7) {

                                     System.out.println("Start Again");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                 }

                                 if (board[i][column] == 'X' || column == 5 || column == 2) {

                                     System.out.println("No Man's Land . Go Somewhere Else.");
                                     TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);                                 }

                                 System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                                 System.out.println("Row Updated : " + i + "Column Updated : " + column);

                                 TeleportBoardRowRange=i;
                                 TeleportBoardColumnRange=column;
                                 System.out.println("Row Added : " + TeleportBoardRowRange + "  Column Added : " + TeleportBoardColumnRange);
                                 return true;
                             }

                         }
                     }
                     return true;
                 }
                  return true;
             }
             return true;



         }


        if(HeroIdentity == 1 ) {
            symbol = 'W';
            isSuccessful = false;

            if (HeroToTeleport == 1) {
                Logic.HeroList.get(HeroIdentity).lane = 0;
                Random a = new Random();
                int RandomChoice = a.nextInt(2-0+1)+0;

                System.out.println("Choice" + RandomChoice);


                if (RandomChoice == 0) {
                    for (int i = 0; i < rows; i++) {
                        System.out.println("No of Times : i : " + i);
                        for (int j = 0; j < columns; j++) {
                            System.out.println("No of Times : j : " + j);

                            if (board[i][j] == 'H') {
                                System.out.println("Found at : " + i + " , " + j);
                                System.out.println("cHOICE: " + RandomChoice);
                                int row = i - 1;

                                if (row < 0) {

                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                } else if (j < 0) {
                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                } else if (j > 7) {
                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                } else if (row > 7) {

                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                }
                                if (board[row][j] == 'X') {
                                    System.out.println("No Man's Land . Go Somewhere Else.");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                }

                                System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                                System.out.println("Row Updated : " + row + "Column Updated : " + j);

                                TeleportBoardRowRange=row;
                                TeleportBoardColumnRange=j;

                                System.out.println("Size of BoardRowRange " + TeleportBoardRowRange + "Size of BoardColumnRange" + TeleportBoardColumnRange);
                                System.out.println("Row Added : " + TeleportBoardRowRange + "  Column Added : " + TeleportBoardColumnRange);
                                System.out.println("I am stuck here need to break out !");
                                return true;
                            }
                        }
                    }
                    return true;
                } else if (RandomChoice == 1) {
                    for (int i = 0; i < rows; i++) {
                        System.out.println("No of Times : i : " + i);
                        for (int j = 0; j < columns; j++) {
                            System.out.println("No of Times : j : " + j);

                            if (board[i][j] == 'H') {
                                System.out.println("Found at : " + i + " , " + j);
                                System.out.println("cHOICE: " + RandomChoice);
                                int column = j + 1;

                                if (i < 0) {

                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                } else if (column < 0) {
                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                } else if (column > 7) {
                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                } else if (i > 7) {

                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                }
                                if (board[i][column] == 'X') {
                                    System.out.println("No Man's Land . Go Somewhere Else.");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                }

                                System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                                System.out.println("Row Updated : " + i + "Column Updated : " + column);

                                TeleportBoardRowRange=i;
                                TeleportBoardColumnRange=column;
                                System.out.println("Row Added : " + TeleportBoardRowRange+ "  Column Added : " + TeleportBoardColumnRange);
                                return true;
                            }
                        }
                    }
                    return true;
                } else if (RandomChoice == 2) {
                    for (int i = 0; i < rows; i++) {
                        System.out.println("No of Times : i : " + i);
                        for (int j = 0; j < columns; j++) {
                            System.out.println("No of Times : j : " + j);

                            if (board[i][j] == 'H') {
                                System.out.println("Found at : " + i + " , " + j);
                                System.out.println("cHOICE: " + RandomChoice);
                                int column = j - 1;

                                if (i < 0) {

                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                } else if (column < 0) {
                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                } else if (column > 7) {
                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                } else if (i > 7) {

                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                }
                                if (board[i][column] == 'X') {
                                    System.out.println("No Man's Land . Go Somewhere Else.");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                }

                                System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                                System.out.println("Row Updated : " + i + "Column Updated : " + column);

                                TeleportBoardRowRange=i;
                                TeleportBoardColumnRange=column;
                                System.out.println("Row Added : " + TeleportBoardRowRange+ "  Column Added : " + TeleportBoardColumnRange);
                                System.out.println("I am stuck here need to break out !");
                                return true;
                            }

                        }
                    }
                    return true;

                }
                return true;
            } else if (HeroToTeleport == 2) {

                Random a = new Random();
                int RandomChoice = a.nextInt(2-0+1)+0;
                Logic.HeroList.get(HeroIdentity).lane = 2;
                System.out.println("Choice" + RandomChoice);

                System.out.println("cHOICE: " + RandomChoice);

                if (RandomChoice == 0) {
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < columns; j++) {

                            if (board[i][j] == 'G') {
                                System.out.println("Found at : " + i + " , " + j);
                                int row = i - 1;

                                if (row < 0) {

                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                } else if (j < 0) {
                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                } else if (j > 7) {
                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                } else if (row > 7) {

                                    System.out.println("Start Again");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                }
                                if (board[row][j] == 'X') {
                                    System.out.println("No Man's Land . Go Somewhere Else.");
                                    TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                                }
                                System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                                System.out.println("Row Updated : " + row + "Column Updated : " + j);

                                TeleportBoardRowRange=row;
                                TeleportBoardColumnRange=j;
                                System.out.println("Row Added : " + TeleportBoardRowRange + "  Column Added : " + TeleportBoardColumnRange);
                                System.out.println("I am stuck here need to break out !");
                                return true;
                            }
                        }
                    }
                    return true;
                } else if (RandomChoice == 1) {
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < columns; j++) {

                            if (board[i][j] == 'G') {
                                System.out.println("Found at : " + i + " , " + j);
                                int column = j + 1;
                                System.out.println("i is : " + i + "j is :" + j);
                                if (i < 0) {

                                    System.out.println("Start Again");
                                    TeleportVicinity(rows, columns, HeroToTeleport, HeroIdentity, symbol);
                                } else if (column < 0) {
                                    System.out.println("Start Again");
                                    TeleportVicinity(rows, columns, HeroToTeleport, HeroIdentity, symbol);
                                } else if (column > 7) {
                                    System.out.println("Start Again");
                                    TeleportVicinity(rows, columns, HeroToTeleport, HeroIdentity, symbol);
                                } else if (i > 7) {

                                    System.out.println("Start Again");
                                    TeleportVicinity(rows, columns, HeroToTeleport, HeroIdentity, symbol);
                                }
                                System.out.println("How can it enter here ?");

                                System.out.println("i is : " + i + "j is :" + j);
                                if (board[i][column] == 'X') {
                                    System.out.println("No Man's Land . Go Somewhere Else.");
                                    TeleportVicinity(rows, columns, HeroToTeleport, HeroIdentity, symbol);
                                }


                                System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                                System.out.println("Row Updated : " + i + "Column Updated : " + column);

                                TeleportBoardRowRange=i;
                                TeleportBoardColumnRange=column;
                                System.out.println("Row Added : " + TeleportBoardRowRange + "  Column Added : " + TeleportBoardColumnRange);
                                System.out.println("I am stuck here need to break out !");
                                return true;
                            }
                        }
                    }
                    return true;
                } else if (RandomChoice == 2) {
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < columns; j++) {

                            if (board[i][j] == 'G') {
                                System.out.println("Found at : " + i + " , " + j);
                                int column = j - 1;

                                if (i < 0) {

                                    System.out.println("Start Again");
                                    TeleportVicinity(rows, columns, HeroToTeleport, HeroIdentity, symbol);
                                } else if (column < 0) {
                                    System.out.println("Start Again");
                                    TeleportVicinity(rows, columns, HeroToTeleport, HeroIdentity, symbol);
                                } else if (column > 7) {
                                    System.out.println("Start Again");
                                    TeleportVicinity(rows, columns, HeroToTeleport, HeroIdentity, symbol);
                                } else if (i > 7) {

                                    System.out.println("Start Again");
                                    TeleportVicinity(rows, columns, HeroToTeleport, HeroIdentity, symbol);
                                }
                                if (board[i][column] == 'X') {
                                    System.out.println("No Man's Land . Go Somewhere Else.");
                                    TeleportVicinity(rows, columns, HeroToTeleport, HeroIdentity, symbol);
                                }

                                System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                                System.out.println("Row Updated : " + i + "Column Updated : " + column);

                                TeleportBoardRowRange=i;
                                TeleportBoardColumnRange=column;
                                System.out.println("Row Added : " + TeleportBoardRowRange + "  Column Added : " + TeleportBoardColumnRange);
                                System.out.println("I am stuck here need to break out !");
                                return true;
                            }

                        }
                    }
                    return true;

                }return true;
            }
            return true;


        }
     if(HeroIdentity == 2 ){
            symbol = 'G';
         if (HeroToTeleport == 1) {

             Random a = new Random();
             int RandomChoice = a.nextInt(2-0+1)+0;
             Logic.HeroList.get(HeroIdentity).lane = 0;
             System.out.println("Choice" + RandomChoice);


             if (RandomChoice == 0) {
                 for (int i = 0; i < rows; i++) {
                     System.out.println("No of Times : i : " + i);
                     for (int j = 0; j < columns; j++) {
                         System.out.println("No of Times : j : " + j);

                         if (board[i][j] == 'H') {
                             System.out.println("Found at : " + i + " , " + j);
                             System.out.println("cHOICE: " + RandomChoice);
                             int row = i - 1;

                             if (row < 0) {

                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (j < 0) {
                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (j > 7) {
                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (row > 7) {

                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             }
                             if (board[row][j] == 'X') {
                                 System.out.println("No Man's Land . Go Somewhere Else.");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             }

                             System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                             System.out.println("Row Updated : " + row + "Column Updated : " + j);

                             TeleportBoardRowRange=row;
                             TeleportBoardColumnRange=j;

                             System.out.println("Size of BoardRowRange " + TeleportBoardRowRange + "Size of BoardColumnRange" + TeleportBoardColumnRange);
                             System.out.println("Row Added : " + TeleportBoardRowRange + "  Column Added : " + TeleportBoardColumnRange);
                             System.out.println("I am stuck here need to break out !");
                             return true;
                         }
                     }
                 }
                 return true;
             } else if (RandomChoice == 1) {
                 for (int i = 0; i < rows; i++) {
                     System.out.println("No of Times : i : " + i);
                     for (int j = 0; j < columns; j++) {
                         System.out.println("No of Times : j : " + j);

                         if (board[i][j] == 'H') {
                             System.out.println("Found at : " + i + " , " + j);
                             System.out.println("cHOICE: " + RandomChoice);
                             int column = j + 1;

                             if (i < 0) {

                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (column < 0) {
                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (column > 7) {
                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (i > 7) {

                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             }
                             if (board[i][column] == 'X') {
                                 System.out.println("No Man's Land . Go Somewhere Else.");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             }

                             System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                             System.out.println("Row Updated : " + i + "Column Updated : " + column);

                             TeleportBoardRowRange=i;
                             TeleportBoardColumnRange=column;
                             System.out.println("Row Added : " + TeleportBoardRowRange+ "  Column Added : " + TeleportBoardColumnRange);
                             return true;
                         }
                     }
                 }
                 return true;
             } else if (RandomChoice == 2) {
                 for (int i = 0; i < rows; i++) {
                     System.out.println("No of Times : i : " + i);
                     for (int j = 0; j < columns; j++) {
                         System.out.println("No of Times : j : " + j);

                         if (board[i][j] == 'H') {
                             System.out.println("Found at : " + i + " , " + j);
                             System.out.println("cHOICE: " + RandomChoice);
                             int column = j - 1;

                             if (i < 0) {

                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (column < 0) {
                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (column > 7) {
                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (i > 7) {

                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             }
                             if (board[i][column] == 'X') {
                                 System.out.println("No Man's Land . Go Somewhere Else.");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             }

                             System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                             System.out.println("Row Updated : " + i + "Column Updated : " + column);

                             TeleportBoardRowRange=i;
                             TeleportBoardColumnRange=column;
                             System.out.println("Row Added : " + TeleportBoardRowRange+ "  Column Added : " + TeleportBoardColumnRange);
                             System.out.println("I am stuck here need to break out !");
                             return true;
                         }

                     }
                 }
                 return true;

             }
             return true;
         }
     else if (HeroToTeleport == 2) {
             Random a = new Random();
             int RandomChoice = a.nextInt(2-0+1)+0;
             Logic.HeroList.get(HeroIdentity).lane = 1;
             System.out.println("Choice" + RandomChoice);
             if (RandomChoice == 0) {
                 for (int i = 0; i < rows; i++) {
                     for (int j = 0; j < columns; j++) {
                         if (board[i][j] == 'W') {

                             System.out.println("Found at : " + i + " , " + j);
//                                int FirstchoiceRow = i+1;
//                                int FirstchoiceColumn = i+1;
//                                int teleportAdjacentChoice = Logic.UserInput("-->",2);

                             System.out.println("CHOICE: " + RandomChoice);

                             int row = i - 1;

                             if (row < 0) {
                                 System.out.println("Invalid Choice");
                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (j < 0) {
                                 System.out.println("Invalid Choice");
                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (j  > 7) {
                                 System.out.println("Invalid Choice");
                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (row > 7) {
                                 System.out.println("Invalid Choice");
                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (board[row][j] == 'X') {
                                 System.out.println("No Man's Land . Go Somewhere Else.");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             }

                             System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                             System.out.println("Row Updated : " + row + "Column Updated : " + j);


                             TeleportBoardRowRange = row;
                             TeleportBoardColumnRange = j;
                             System.out.println("Row Added : " + TeleportBoardRowRange + "  Column Added : " + TeleportBoardColumnRange);
                             return true;
                         }
                     }
                 }
                 return true;
             } else if (RandomChoice == 1) {
                 for (int i = 0; i < rows; i++) {
                     for (int j = 0; j < columns; j++) {
                         if (board[i][j] == 'W') {

                             System.out.println("Found at : " + i + " , " + j);
                             System.out.println("CHOICE: " + RandomChoice);
                             int column = j + 1;

                             if (i < 0) {

                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (column < 0) {
                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (column >7) {
                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (i >7) {

                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (board[i][column] == 'X') {
                                 System.out.println("No Man's Land . Go Somewhere Else.");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             }

                             System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                             System.out.println("Row Updated : " + i + " Column Updated : " + column);

                             TeleportBoardRowRange = i;
                             TeleportBoardColumnRange =column;
                             System.out.println("Row Added : " + TeleportBoardRowRange+ "  Column Added : " + TeleportBoardColumnRange);
                             return true;
                         }
                     }

                 }return true;

             } else if (RandomChoice == 2) {

                 for (int i = 0; i < rows; i++) {
                     System.out.println("No of Times : i : " + i);
                     for (int j = 0; j < columns; j++) {
                         System.out.println("No of Times : j : " + j);
                         if (board[i][j] == 'W') {

                             System.out.println("Found at : " + i + " , " + j);

                             System.out.println("cHOICE: " + RandomChoice);
                             int column = j - 1;

                             if (i < 0) {

                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (column < 0) {
                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (column > 7) {
                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             } else if (i > 7) {

                                 System.out.println("Start Again");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             }
                             if (board[i][column] == 'X') {
                                 System.out.println("No Man's Land . Go Somewhere Else.");
                                 TeleportVicinity( rows, columns,  HeroToTeleport, HeroIdentity,  symbol);
                             }

                             System.out.println("Everything Works Successfully Choice " + RandomChoice + " Hero " + HeroToTeleport + " Identity " + HeroIdentity);
                             System.out.println("Row Updated : " + i + " Column Updated : " + column);

                             TeleportBoardRowRange=i;
                             TeleportBoardColumnRange=column;

                             System.out.println("Row Added : " + TeleportBoardRowRange + "  Column Added : " + TeleportBoardColumnRange);
                             System.out.println("I am stuck here need to break out !");
                             return true;

                         }

                     }

                 }
                 return true;

             }
             System.out.println("Exited");
             return true;
         }

    }
        return true;
    }
//this method is used to initialize lanes and borders on the map

    public void initializeLanes(int rows,ArrayList<Integer> BorderIndication ){
        int numBorders = BorderIndication.size();
        int arr;
        BorderIndication.add(rows);
        for(int z = 0; z < numBorders+1; z++) {
            System.out.println("Lane Number :" +z);
            for(int i = 0; i < rows; i++) {
            for(int j = 0 ; j < BorderIndication.get(z); j++){
                if(z==0){

                        System.out.println("i:"+i+"j:"+j);
                        Lanes[i][j] =' ';


                }
                if(z==1){
                    if(j>BorderIndication.get(z-1) && j<BorderIndication.get(z)){
                        System.out.println("i:"+i+"j:"+j);
                        Lanes[i][j] =' ';
                    }
                }
                if(z==2){
                    System.out.println("Res"+BorderIndication.get(1));
                    if(j>BorderIndication.get(1) ){
                        System.out.println("i:"+i+"j:"+j);
                        Lanes[i][j] =' ';

                    }
                }



            }
        }

        }
        for(int z = 0; z < BorderIndication.size(); z++) {
            for(int i = 0; i < rows; i++) {
            for(int j = 0 ; j < BorderIndication.get(z) ; j++){

                    if(z==0){

                        System.out.println("i:"+i+"j:"+j);
                        board[i][j] = Lanes[i][j];


                    }
                    if(z==1){
                        if(j>BorderIndication.get(z-1) && j<BorderIndication.get(z)){
                            System.out.println("i:"+i+"j:"+j);
                            board[i][j] = Lanes[i][j];
                        }
                    }
                if(z==2){
                    if(j>BorderIndication.get(z-1) ){
                        System.out.println("i:"+i+"j:"+j);
                        board[i][j] = Lanes[i][j];
                    }
                }

                }
            }

        }
    }
    //this method allows hero to choose the lane
    public int ChooseLanes(){

        retainprintBoard();
        System.out.println("PLease enter the Lane you want to choose to fight the monsters .");
        int LaneChoosen  = Logic.UserInput("-->",3);
        LaneChoosen =CheckLane(LaneChoosen);
        LaneOccupied.add(LaneChoosen);
        return LaneChoosen;

    }
    //this method checks if the lane is occupied
    public int CheckLane(int LaneChoosen){
        if(!LaneOccupied.isEmpty()){
            System.out.println("Checking if the Lane is occupied...");
            for(int i = 0 ; i < LaneOccupied.size();i++){
                if(LaneOccupied.get(i) == LaneChoosen){
                    System.out.println("Sir , this Lane is Already occupied , please choose another Lane .");
                    LaneChoosen  = Logic.UserInput("-->",3);
                    LaneChoosen = CheckLane(LaneChoosen);
                }
            }
        }
        return LaneChoosen;
    }
    //This is used to test if the villain is in the collision range for heroes
    public void CollisionCheckPosition(int HeroIdentity,int size,int numberofVillains) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        System.out.println("It does enter !");
        if(HeroIdentity == 0){
            if(Hero1X == Villain1X.get(numberofVillains)){//***********
                if(Hero1Y == Villain1Y.get(numberofVillains)){

                    System.out.println("Battle will definitely Happen .");
                    Logic.gameConditions(0,0,size,'H','V');
                    retainprintBoard();
                }
            }}

            if(Hero1X == Villain2X.get(numberofVillains)){
                if(Hero1Y == Villain2Y.get(numberofVillains)){
                    System.out.println("Battle will definitely Happen .");
                    Logic.gameConditions(0,1,size,'H','E');
                    retainprintBoard();


            }
            if(Hero1X == Villain3X.get(numberofVillains)){
                if(Hero1Y == Villain3Y.get(numberofVillains)){
                    System.out.println("Battle will definitely Happen .");
                    Logic.gameConditions(0,2,size,'H','S');
                    retainprintBoard();

                }
            }}
        if(!(RowCollisionVicinity.size() <=  2) && !(ColumnCollisionVicinity.size() <= 2)){
            if(RowCollisionVicinity.get(2) == Villain1X.get(numberofVillains)){
                if(ColumnCollisionVicinity.get(2) == Villain1Y.get(numberofVillains)){//NEW*************************
                    System.out.println("Battle might Happen .");
                    Logic.RandomCollision(0,0,size,'H','V');
                    retainprintBoard();


                }}
        }
        if(!(RowCollisionVicinity.size() <=  3) && !(ColumnCollisionVicinity.size() <= 3)) {
            if (RowCollisionVicinity.get(3) == Villain1X.get(numberofVillains)) {
                if (ColumnCollisionVicinity.get(3) == Villain1Y.get(numberofVillains)) {//NEW*************************
                    System.out.println("Battle might Happen .");
                    Logic.RandomCollision(0, 0, size, 'H', 'V');
                    retainprintBoard();


                }
            }
        }
        if(!(RowCollisionVicinity.size() <=  2) && !(ColumnCollisionVicinity.size() <= 2)){
            if(RowCollisionVicinity.get(2) == Villain3X.get(numberofVillains)){
                if(ColumnCollisionVicinity.get(2) == Villain3Y.get(numberofVillains)){//NEW*************************
                    System.out.println("Battle might Happen .");
                    Logic.RandomCollision(0,0,size,'H','S');
                    retainprintBoard();


                }}
        }
        if(!(RowCollisionVicinity.size() <=  3) && !(ColumnCollisionVicinity.size() <= 3)) {
            if (RowCollisionVicinity.get(3) == Villain3X.get(numberofVillains)) {
                if (ColumnCollisionVicinity.get(3) == Villain3Y.get(numberofVillains)) {//NEW*************************
                    System.out.println("Battle might Happen .");
                    Logic.RandomCollision(0, 0, size, 'H', 'S');
                    retainprintBoard();


                }
            }
        }
        if(!(RowCollisionVicinity.size() <=  2) && !(ColumnCollisionVicinity.size() <= 2)){
            if(RowCollisionVicinity.get(2) == Villain2X.get(numberofVillains)){
                if(ColumnCollisionVicinity.get(2) == Villain2Y.get(numberofVillains)){//NEW*************************
                    System.out.println("Battle might Happen .");
                    Logic.RandomCollision(0,0,size,'H','E');
                    retainprintBoard();


                }}
        }
        if(!(RowCollisionVicinity.size() <=  3) && !(ColumnCollisionVicinity.size() <= 3)) {
            if (RowCollisionVicinity.get(3) == Villain2X.get(numberofVillains)) {
                if (ColumnCollisionVicinity.get(3) == Villain2Y.get(numberofVillains)) {//NEW*************************
                    System.out.println("Battle might Happen .");
                    Logic.RandomCollision(0, 0, size, 'H', 'E');
                    retainprintBoard();


                }
            }
        }
            for(int i = 0 ;i<RowCollisionVicinity.size();i++){

                if(RowCollisionVicinity.get(i) == Villain1X.get(numberofVillains)){//***********
                    if(ColumnCollisionVicinity.get(i) == Villain1Y.get(numberofVillains)){
                        System.out.println("Battle might Happen .");
                        Logic.gameConditions(0,0,size,'H','V');
                        retainprintBoard();


                }}

                if(RowCollisionVicinity.get(i) == Villain2X.get(numberofVillains)){
                    if(ColumnCollisionVicinity.get(i) == Villain2Y.get(numberofVillains)){
                        System.out.println("Battle might Happen .");
                        Logic.gameConditions(0,1,size,'H','E');
                        retainprintBoard();

                    }
                }

                if(RowCollisionVicinity.get(i) == Villain3X.get(numberofVillains)){
                    if(ColumnCollisionVicinity.get(i) == Villain3Y.get(numberofVillains)){
                        System.out.println("Battle might Happen .");
                        Logic.gameConditions(0,2,size,'H','S');
                        retainprintBoard();

                    }
                }}

        if(HeroIdentity == 1){
            if(Hero2X == Villain1X.get(numberofVillains)) {//***********
                if (Hero2Y == Villain1Y.get(numberofVillains)) {
                    System.out.println("Battle will definitely Happen .");
                    Logic.gameConditions(1, 0, size, 'W', 'V');
                    retainprintBoard();

            }
            }

            if(Hero2X == Villain2X.get(numberofVillains)){
                if(Hero2Y == Villain2Y.get(numberofVillains)){
                    System.out.println("Battle will definitely Happen .");
                    Logic.gameConditions(1,1,size,'W','E');
                    retainprintBoard();


            }}

            if(Hero2X == Villain3X.get(numberofVillains)){
                if(Hero2Y == Villain3Y.get(numberofVillains)){
                    System.out.println("Battle will definitely Happen .");
                    Logic.gameConditions(1,2,size,'W','S');
                    retainprintBoard();

                }
            }

            if(!(RowCollisionVicinity.size() <=  2) && !(ColumnCollisionVicinity.size() <= 2)){
                if(RowCollisionVicinity.get(2) == Villain1X.get(numberofVillains)){
                    if(ColumnCollisionVicinity.get(2) == Villain1Y.get(numberofVillains)){//NEW*************************
                        System.out.println("Battle might Happen .");
                        Logic.RandomCollision(0,0,size,'H','V');
                        retainprintBoard();


                    }}
            }
            if(!(RowCollisionVicinity.size() <=  3) && !(ColumnCollisionVicinity.size() <= 3)) {
                if (RowCollisionVicinity.get(3) == Villain1X.get(numberofVillains)) {
                    if (ColumnCollisionVicinity.get(3) == Villain1Y.get(numberofVillains)) {//NEW*************************
                        System.out.println("Battle might Happen .");
                        Logic.RandomCollision(0, 0, size, 'H', 'V');
                        retainprintBoard();


                    }
                }
            }
            if(!(RowCollisionVicinity.size() <=  2) && !(ColumnCollisionVicinity.size() <= 2)){
                if(RowCollisionVicinity.get(2) == Villain3X.get(numberofVillains)){
                    if(ColumnCollisionVicinity.get(2) == Villain3Y.get(numberofVillains)){//NEW*************************
                        System.out.println("Battle might Happen .");
                        Logic.RandomCollision(0,0,size,'H','S');
                        retainprintBoard();


                    }}
            }
            if(!(RowCollisionVicinity.size() <=  3) && !(ColumnCollisionVicinity.size() <= 3)) {
                if (RowCollisionVicinity.get(3) == Villain3X.get(numberofVillains)) {
                    if (ColumnCollisionVicinity.get(3) == Villain3Y.get(numberofVillains)) {//NEW*************************
                        System.out.println("Battle might Happen .");
                        Logic.RandomCollision(0, 0, size, 'H', 'S');
                        retainprintBoard();


                    }
                }
            }
            if(!(RowCollisionVicinity.size() <=  2) && !(ColumnCollisionVicinity.size() <= 2)){
                if(RowCollisionVicinity.get(2) == Villain2X.get(numberofVillains)){
                    if(ColumnCollisionVicinity.get(2) == Villain2Y.get(numberofVillains)){//NEW*************************
                        System.out.println("Battle might Happen .");
                        Logic.RandomCollision(0,0,size,'H','E');
                        retainprintBoard();


                    }}
            }
            if(!(RowCollisionVicinity.size() <=  3) && !(ColumnCollisionVicinity.size() <= 3)) {
                if (RowCollisionVicinity.get(3) == Villain2X.get(numberofVillains)) {
                    if (ColumnCollisionVicinity.get(3) == Villain2Y.get(numberofVillains)) {//NEW*************************
                        System.out.println("Battle might Happen .");
                        Logic.RandomCollision(0, 0, size, 'H', 'E');
                        retainprintBoard();


                    }
                }
            }            for(int i = 0 ;i<RowCollisionVicinity.size();i++){

                if(RowCollisionVicinity.get(i) == Villain1X.get(numberofVillains)){//***********
                    if(ColumnCollisionVicinity.get(i) == Villain1Y.get(numberofVillains)){
                        System.out.println("Battle might Happen .");
                        Logic.gameConditions(1,0,size,'W','V');
                        retainprintBoard();

                    }
                }

                if(RowCollisionVicinity.get(i) == Villain2X.get(numberofVillains)){
                    if(ColumnCollisionVicinity.get(i) == Villain2Y.get(numberofVillains)){
                        System.out.println("Battle might Happen .");
                        Logic.gameConditions(1,1,size,'W','E');
                        retainprintBoard();
                    }}


                if(RowCollisionVicinity.get(i) == Villain3X.get(numberofVillains)){
                    if(ColumnCollisionVicinity.get(i) == Villain3Y.get(numberofVillains)){
                        System.out.println("Battle might Happen .");
                        Logic.gameConditions(1,2,size,'W','S');
                        retainprintBoard();
                    }
                }}

        }
        if(HeroIdentity == 2){

            if(Hero3X == Villain1X.get(numberofVillains)){//***********
                if(Hero3Y == Villain1Y.get(numberofVillains)){
                    System.out.println("Battle will definitely Happen .");
                    Logic.gameConditions(2,0,size,'G','V');
                    retainprintBoard();
                }
            }

            if(Hero3X == Villain2X.get(numberofVillains)){
                if(Hero3Y == Villain2Y.get(numberofVillains)){
                    System.out.println("Battle will definitely Happen .");
                    Logic.gameConditions(2,1,size,'G','E');
                    retainprintBoard();

            }}
            if(Hero3X == Villain3X.get(numberofVillains)){
                if(Hero3Y == Villain3Y.get(numberofVillains)){
                    System.out.println("Battle will definitely Happen .");
                    Logic.gameConditions(2,2,size,'G','S');
                    retainprintBoard();
                }
            }

            if(!(RowCollisionVicinity.size() <=  2) && !(ColumnCollisionVicinity.size() <= 2)){
                if(RowCollisionVicinity.get(2) == Villain1X.get(numberofVillains)){
                    if(ColumnCollisionVicinity.get(2) == Villain1Y.get(numberofVillains)){//NEW*************************
                        System.out.println("Battle might Happen .");
                        Logic.RandomCollision(0,0,size,'G','V');
                        retainprintBoard();


                    }}
            }
            if(!(RowCollisionVicinity.size() <=  3) && !(ColumnCollisionVicinity.size() <= 3)) {
                if (RowCollisionVicinity.get(3) == Villain1X.get(numberofVillains)) {
                    if (ColumnCollisionVicinity.get(3) == Villain1Y.get(numberofVillains)) {//NEW*************************
                        System.out.println("Battle might Happen .");
                        Logic.RandomCollision(0, 0, size, 'G', 'V');
                        retainprintBoard();


                    }
                }
            }
            if(!(RowCollisionVicinity.size() <=  2) && !(ColumnCollisionVicinity.size() <= 2)){
                if(RowCollisionVicinity.get(2) == Villain3X.get(numberofVillains)){
                    if(ColumnCollisionVicinity.get(2) == Villain3Y.get(numberofVillains)){//NEW*************************
                        System.out.println("Battle might Happen .");
                        Logic.RandomCollision(0,0,size,'G','S');
                        retainprintBoard();


                    }}
            }
            if(!(RowCollisionVicinity.size() <=  3) && !(ColumnCollisionVicinity.size() <= 3)) {
                if (RowCollisionVicinity.get(3) == Villain3X.get(numberofVillains)) {
                    if (ColumnCollisionVicinity.get(3) == Villain3Y.get(numberofVillains)) {//NEW*************************
                        System.out.println("Battle might Happen .");
                        Logic.RandomCollision(0, 0, size, 'G', 'S');
                        retainprintBoard();


                    }
                }
            }
            if(!(RowCollisionVicinity.size() <=  2) && !(ColumnCollisionVicinity.size() <= 2)){
                if(RowCollisionVicinity.get(2) == Villain2X.get(numberofVillains)){
                    if(ColumnCollisionVicinity.get(2) == Villain2Y.get(numberofVillains)){//NEW*************************
                        System.out.println("Battle might Happen .");
                        Logic.RandomCollision(0,0,size,'G','E');
                        retainprintBoard();


                    }}
            }
            if(!(RowCollisionVicinity.size() <=  3) && !(ColumnCollisionVicinity.size() <= 3)) {
                if (RowCollisionVicinity.get(3) == Villain2X.get(numberofVillains)) {
                    if (ColumnCollisionVicinity.get(3) == Villain2Y.get(numberofVillains)) {//NEW*************************
                        System.out.println("Battle might Happen .");
                        Logic.RandomCollision(0, 0, size, 'G', 'E');
                        retainprintBoard();


                    }
                }
            }
            for(int i = 0 ;i<RowCollisionVicinity.size();i++){

                if(RowCollisionVicinity.get(i) == Villain1X.get(numberofVillains)){//***********
                    if(ColumnCollisionVicinity.get(i) == Villain1Y.get(numberofVillains)){
                        System.out.println("Battle might Happen .");
                        Logic.gameConditions(2,0,size,'G','V');
                        retainprintBoard();
                    }
                }
                if(RowCollisionVicinity.get(i) == Villain2X.get(numberofVillains)){
                    if(ColumnCollisionVicinity.get(i) == Villain2Y.get(numberofVillains)){
                        System.out.println("Battle might Happen .");
                        Logic.gameConditions(2,1,size,'G','E');
                        retainprintBoard();
                    }
                }

                if(RowCollisionVicinity.get(i) == Villain3X.get(numberofVillains)){
                    if(ColumnCollisionVicinity.get(i) == Villain3Y.get(numberofVillains)){
                        System.out.println("Battle might Happen .");
                        Logic.gameConditions(2,2,size,'G','S');
                        retainprintBoard();
                    }}
                }


        }




    }
    //this method is used to check if the hero is on a market
    public void MarketcheckPosition(int HeroIdentity,char symbol) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
       if(symbol == 'H'){
           HeroIdentity =0;
            for(int j =0 ; j< marketX.size() ;j++)
            {
                if(Hero1X == marketX.get(j)){
                    if(Hero1Y == marketY.get(j)){
                        System.out.println("You are at the market .Do you want to buy something ?");
                        isMarket = true;
                        Logic.ShopFromMarket(0);
                    }}



        }}
       if(symbol == 'W'){
           HeroIdentity =1;
            for(int j =0 ; j< marketX.size() ;j++)
            {
                if(Hero2X == marketX.get(j)){
                    if(Hero2Y == marketY.get(j)){
                        System.out.println("You are at the market .Do you want to buy something ?");
                        isMarket = true;
                        Logic.ShopFromMarket(1);
                    }}
            }
        }
       if(symbol == 'G'){
       HeroIdentity =2;
            for(int j =0 ; j< marketX.size() ;j++)
            {
                if(Hero3X == marketX.get(j)){
                    if(Hero3Y == marketY.get(j)){
                        System.out.println("You are at the market .Do you want to buy something ?");
                        isMarket = true;
                        Logic.ShopFromMarket(2);
                    }}
            }


     }
    }
    //this method is used to check if the hero is on a common land
    public void LandcheckPosition(int HeroIdentity,char symbol){
        if(symbol == 'H'){

        HeroIdentity = 0;
            for(int j =0 ; j< LandX.size() ;j++)
            {

                if(Hero1X == LandX.get(j)){
                    if(Hero1Y == LandY.get(j)){

                        System.out.println("You are on the common Land .");

                        isLand = true;

                    }



                }

            }

        }if(symbol == 'W'){
            HeroIdentity = 1;
            for(int j =0 ; j< LandX.size() ;j++)
            {  if(Hero2X == LandX.get(j)){
                if(Hero2Y == LandY.get(j)){

                    System.out.println("You are on the common Land .");
                    isLand = true;

                }



            }
            }
        }
        if(symbol == 'G'){
            HeroIdentity = 2;
            for(int j =0 ; j< LandX.size() ;j++)
            {  if(Hero3X == LandX.get(j)){
                if(Hero3Y == LandY.get(j)){
//
//                    }

                    System.out.println("You are on the common Land .");
                    isLand = true;

                }



            }
        }}

    }
    //this method is used to assign hero's nexus on the map
    public void AssignHeroesOriginNexus(int size,char symbol,ArrayList<Integer> BorderIndication){



        int numBorders = BorderIndication.size();
        BorderIndication.add((int) Math.sqrt(size));
        for(int z = 0; z < numBorders+1; z++) {
                if (z == 0) {


                    for(int i =0;i<2;i++) {
                        int x = 7;
                        int y = i;
                        Lanes[x][y] = symbol;
                        board[x][y] = Lanes[x][y];
                    }

                }
                if (z == 1) {
                    for(int i =3;i<5;i++) {
                        int x = 7;
                        int y = i;
                        Lanes[x][y] = symbol;
                        board[x][y] = Lanes[x][y];
                    }

                }
                if (z == 2) {
                    for(int i =6;i<8;i++) {
                        int x = 7;
                        int y = i;
                        Lanes[x][y] = symbol;
                        board[x][y] = Lanes[x][y];
                    }

                }



            }}

//this is used to assign villain nexus on the board

    public void AssignVillainsOriginNexus(int size,char symbol,ArrayList<Integer> BorderIndication){

        int numBorders = BorderIndication.size();
        BorderIndication.add((int) Math.sqrt(size));
        for(int z = 0; z < numBorders+1; z++) {
            if (z == 0) {


                for(int i =0;i<2;i++) {
                    int x = 0;
                    int y = i;
                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];
                }

            }
            if (z == 1) {
                for(int i =3;i<5;i++) {
                    int x = 0;
                    int y = i;
                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];
                }

            }
            if (z == 2) {
                for(int i =6;i<8;i++) {
                    int x = 0;
                    int y = i;
                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];
                }

            }



        }}
    //this method is used to assign cave on the map
    public void AssignCave(int size,char symbol,ArrayList<Integer> BorderIndication){
        int var = (int) Math.floor( 0.2 * Math.floor(Math.sqrt(size)));

        int numBorders = BorderIndication.size();
        BorderIndication.add((int) Math.sqrt(size));
        for(int z = 0; z < numBorders+1; z++) {
            for(int i = 0; i < var; i++) {
                if (z == 0) {

                    Random a = new Random();

                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;
                    int y = a.nextInt((int) ((Math.floor(Math.sqrt(size) / 3) / 2)) - 0 + 1) + 0;
//                    int y = a.nextInt(1-0+1)+0;
                    System.out.println();
                    CaveX.add(x);
                    CaveY.add(y);
                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];


                }
                if (z == 1) {
                    Random a = new Random();
                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;

                    int y = a.nextInt((int) ((Math.floor(Math.sqrt(size) / 3) * 2)) - 3 + 1) + 3;
                    CaveX.add(x);
                    CaveY.add(y);
                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];

                }
                if (z == 2) {
                    Random a = new Random();
                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;

                    int y = a.nextInt((int) (Math.floor(Math.sqrt(size) / 3) * 4 - 1) - 6 + 1) + 6;
                    CaveX.add(x);
                    CaveY.add(y);

                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];


                }


//        for(i=0;i<var;i++){
//            Random a = new Random();
//            int x = a.nextInt((int) Math.sqrt(size));
//            int y = a.nextInt((int) Math.sqrt(size));
//
//            marketX.add(x);
//            marketY.add(y);
//            board[x][y] = symbol;
//
//        }

            }}for (int j = 0; j < CaveX.size(); j++) {
            System.out.println("Cave is at positions on the land : " + CaveX.get(j) + "," + CaveY.get(j));
        }

    }
    //this method is used to assign market on the board
    public void AssignMarket(int size,char symbol,ArrayList<Integer> BorderIndication){
        int var = (int) Math.floor( 0.2 * Math.floor(Math.sqrt(size)));
        marketX.add(0);
        marketY.add(0);
        marketX.add(0);
        marketY.add(1);
        marketX.add(0);
        marketY.add(3);
        marketX.add(0);
        marketY.add(4);
        marketX.add(0);
        marketY.add(6);
        marketX.add(0);
        marketY.add(7);

        marketX.add(7);
        marketY.add(0);

        marketX.add(7);
        marketY.add(1);

        marketX.add(7);
        marketY.add(4);

        marketX.add(7);
        marketY.add(3);

        marketX.add(7);
        marketY.add(6);

        marketX.add(7);
        marketY.add(7);

        int numBorders = BorderIndication.size();
        BorderIndication.add((int) Math.sqrt(size));
        for(int z = 0; z < numBorders+1; z++) {
            for(int i = 0; i < var; i++) {
                if (z == 0) {

                    Random a = new Random();

                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;
                    int y = a.nextInt((int) ((Math.floor(Math.sqrt(size) / 3) / 2)) - 0 + 1) + 0;
//                    int y = a.nextInt(1-0+1)+0;
                    System.out.println();
                    marketX.add(x);
                    marketY.add(y);
                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];


                }
                if (z == 1) {
                    Random a = new Random();
                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;

                    int y = a.nextInt((int) ((Math.floor(Math.sqrt(size) / 3) * 2)) - 3 + 1) + 3;
                    marketX.add(x);
                    marketY.add(y);
                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];

                }
                if (z == 2) {
                    Random a = new Random();
                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;

                    int y = a.nextInt((int) (Math.floor(Math.sqrt(size) / 3) * 4 - 1) - 6 + 1) + 6;
                    marketX.add(x);
                    marketY.add(y);

                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];


                }


//        for(i=0;i<var;i++){
//            Random a = new Random();
//            int x = a.nextInt((int) Math.sqrt(size));
//            int y = a.nextInt((int) Math.sqrt(size));
//
//            marketX.add(x);
//            marketY.add(y);
//            board[x][y] = symbol;
//
//        }

            }}for (int j = 0; j < marketX.size(); j++) {
            System.out.println("Market is at positions on the land : " + marketX.get(j) + "," + marketY.get(j));
        }

    }
    //this method is used to assign Koulou to the board
    public void AssignKoulou(int size,char symbol,ArrayList<Integer> BorderIndication){
        int var = (int) Math.floor( 0.2 * Math.floor(Math.sqrt(size)));
        int numBorders = BorderIndication.size();
        BorderIndication.add((int) Math.sqrt(size));
        for(int z = 0; z < numBorders+1; z++) {
            for(int i = 0; i < var; i++) {
                if (z == 0) {

                    Random a = new Random();

                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;
                    int y = a.nextInt((int) ((Math.floor(Math.sqrt(size) / 3) / 2)) - 0 + 1) + 0;
//                    int y = a.nextInt(1-0+1)+0;
                    System.out.println();
                    KoulouX.add(x);
                    KoulouY.add(y);
                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];


                }
                if (z == 1) {
                    Random a = new Random();
                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;

                    int y = a.nextInt((int) ((Math.floor(Math.sqrt(size) / 3) * 2)) - 3 + 1) + 3;
                    KoulouX.add(x);
                    KoulouY.add(y);
                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];

                }
                if (z == 2) {
                    Random a = new Random();
                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;

                    int y = a.nextInt((int) (Math.floor(Math.sqrt(size) / 3) * 4 - 1) - 6 + 1) + 6;
                    KoulouX.add(x);
                    KoulouY.add(y);

                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];


                }


//        for(i=0;i<var;i++){
//            Random a = new Random();
//            int x = a.nextInt((int) Math.sqrt(size));
//            int y = a.nextInt((int) Math.sqrt(size));
//
//            marketX.add(x);
//            marketY.add(y);
//            board[x][y] = symbol;
//
//        }

            }}for (int j = 0; j < KoulouX.size(); j++) {
            System.out.println("Koulou is at positions on the land : " + KoulouX.get(j) + "," + KoulouY.get(j));
        }

    }
    //this method is used to assign bushes on the board .
    public void AssignBush(int size,char symbol,ArrayList<Integer> BorderIndication){
        int var = (int) Math.floor( 0.2 * Math.floor(Math.sqrt(size)));

        int numBorders = BorderIndication.size();
        BorderIndication.add((int) Math.sqrt(size));
        for(int z = 0; z < numBorders+1; z++) {
            for(int i = 0; i < var; i++) {
                if (z == 0) {

                    Random a = new Random();

                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;
                    int y = a.nextInt((int) ((Math.floor(Math.sqrt(size) / 3) / 2)) - 0 + 1) + 0;
//                    int y = a.nextInt(1-0+1)+0;
                    System.out.println();
                    BushX.add(x);
                    BushY.add(y);
                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];


                }
                if (z == 1) {
                    Random a = new Random();
                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;

                    int y = a.nextInt((int) ((Math.floor(Math.sqrt(size) / 3) * 2)) - 3 + 1) + 3;
                    BushX.add(x);
                    BushY.add(y);
                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];

                }
                if (z == 2) {
                    Random a = new Random();
                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;

                    int y = a.nextInt((int) (Math.floor(Math.sqrt(size) / 3) * 4 - 1) - 6 + 1) + 6;
                    BushX.add(x);
                    BushY.add(y);

                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];


                }


//        for(i=0;i<var;i++){
//            Random a = new Random();
//            int x = a.nextInt((int) Math.sqrt(size));
//            int y = a.nextInt((int) Math.sqrt(size));
//
//            marketX.add(x);
//            marketY.add(y);
//            board[x][y] = symbol;
//
//        }

            }}for (int j = 0; j < BushX.size(); j++) {
            System.out.println("Bush is at positions on the land : " + BushX.get(j) + "," + BushY.get(j));
        }

    }
    // this method is used to assign Unaccessible land to the board

    public void AssignNoManLand(int size,char symbol){
        int var = (int) Math.floor( 0.2 * size);
        System.out.println("var size :"+var);
        int i = 0 ;

        for(i=0;i<var;i++){
            Random a = new Random();
            int x = a.nextInt((int) Math.sqrt(size));
            int y = a.nextInt((int) Math.sqrt(size));

            NoManX.add(x);
            NoManY.add(y);
            board[x][y] = symbol;

        }
        for(int j =0 ; j< NoManX.size() ;j++)
        {
            System.out.println("No Man's Land is at positions on the land : "+NoManX.get(j)+","+NoManY.get(j));
        }


    }
    public ArrayList<Integer> AssignLanes(int size,char symbol,int numPlayers){
        int var = (int) Math.floor(size / numPlayers);
        System.out.println("Lane size :"+var);
        int i = 0 ;
        int firstLaneColumn = 0+var;
        LanesIndication.add(firstLaneColumn);
        System.out.println("First Border:" +firstLaneColumn);
        for(i = 0;i<size ; i++){
            board[i][firstLaneColumn] = symbol;
//            laneColumn2.add(i,LastlaneColumn);
            BorderX.add(i);
            BorderY.add(firstLaneColumn);
        }
        int LastlaneColumn = size - var-1 ;
        LanesIndication.add(LastlaneColumn);

        System.out.println("Last Border:" +LastlaneColumn);
        for(i = 0;i<size ; i++){
            board[i][LastlaneColumn] = symbol;
            BorderX.add(i);
            BorderY.add(firstLaneColumn);
//            laneColumn2.add(i,LastlaneColumn);
        }
        for(int j =0 ; j < LanesIndication.size();j++){
        System.out.println("Lanes Indication :"+LanesIndication.get(j));}
            return LanesIndication;
    }
    //this method is used to assign land on the board
    public void AssignLand(int size,char symbol,ArrayList<Integer> BorderIndication){
        int var = (int) Math.floor( (size));
        System.out.println("var size :"+var);
//        int i = 0 ;
//        LandX.add(4);
//        LandY.add(4);
//        for(i=0;i<var;i++){
//            Random a = new Random();
//            int x = a.nextInt((int) Math.sqrt(size))+0;
//            int y = a.nextInt((int) Math.sqrt(size))+0;
//            LandX.add(x);
//            LandY.add(y);
//            board[x][y] = symbol;
//
//        }

        int numBorders = BorderIndication.size();
        BorderIndication.add((int) Math.sqrt(size));
        for(int z = 0; z < numBorders+1; z++) {
            System.out.println("Lane Number :" +z);
            for(int i = 0; i < var; i++) {
                if (z == 0) {

                    Random a = new Random();

                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;
                    int y = a.nextInt((int) ((Math.floor(Math.sqrt(size) / 3) / 2)) - 0 + 1) + 0;
//                    int y = a.nextInt(1-0+1)+0;
                    System.out.println();
                    LandX.add(x);
                    LandY.add(y);
                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];


                }
                if (z == 1) {
                    Random a = new Random();
                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;

                    int y = a.nextInt((int) ((Math.floor(Math.sqrt(size) / 3) * 2)) - 3 + 1) + 3;
                    LandX.add(x);
                    LandY.add(y);
                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];

                }
                if (z == 2) {
                    Random a = new Random();
                    int x = a.nextInt((int) Math.floor(Math.sqrt(size) - 2) - 1 + 1) + 1;

                    int y = a.nextInt((int) (Math.floor(Math.sqrt(size) / 3) * 4 - 1) - 6 + 1) + 6;
                    LandX.add(x);
                    LandY.add(y);
                    Lanes[x][y] = symbol;
                    board[x][y] = Lanes[x][y];


                }


//        for(i=0;i<var;i++){
//            Random a = new Random();
//            int x = a.nextInt((int) Math.sqrt(size));
//            int y = a.nextInt((int) Math.sqrt(size));
//
//            marketX.add(x);
//            marketY.add(y);
//            board[x][y] = symbol;
//
//        }

            }}


        for(int j =0 ; j< LandX.size() ;j++)
        {
            System.out.println("Land is at positions on the land : "+LandX.get(j)+","+LandY.get(j));
        }


    }
//this method is used to spawn the heroes on the nexus
    public void AddDefaultPos(int size,char symbol,int LaneChoosen,int HeroIdentification){
        Random rand = new Random();
        if(HeroIdentification == 0){
            if(LaneChoosen == 1){
            int x =7;

     int y = rand.nextInt((int) ((Math.floor(size/ 3) / 2)) - 0 + 1) + 0;
            board[x][y] = symbol;
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'H'){

                        Hero1X=i;
                        Hero1Y = j;
                    }
                }}
        }
        else if(LaneChoosen == 2){
            int x =7;
            int y = rand.nextInt((int) ((Math.floor(size / 3) * 2)) - 3 + 1) + 3;
            board[x][y] = symbol;
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'H'){

                        Hero1X=i;
                        Hero1Y = j;
                    }
                }}
        }
        else if(LaneChoosen == 3){
            int x =7;

            int y = rand.nextInt((int) (Math.floor(size/ 3) * 4 - 1) - 6 + 1) + 6;
            board[x][y] = symbol;
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'H'){

                        Hero1X=i;
                        Hero1Y = j;
                    }
                }}
        }
        } else if (HeroIdentification == 1) {
            if(LaneChoosen == 1){
                int x =7;

                int y = rand.nextInt((int) ((Math.floor(size/ 3) / 2)) - 0 + 1) + 0;
                board[x][y] = symbol;
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'W'){

                            Hero2X=i;
                            Hero2Y = j;
                        }
                    }}
            }
            else if(LaneChoosen == 2){
                int x =7;
                int y = rand.nextInt((int) ((Math.floor(size / 3) * 2)) - 3 + 1) + 3;
                board[x][y] = symbol;
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'W'){

                            Hero2X=i;
                            Hero2Y = j;
                        }
                    }}
            }
            else if(LaneChoosen == 3){
                int x =7;

                int y = rand.nextInt((int) (Math.floor(size/ 3) * 4 - 1) - 6 + 1) + 6;
                board[x][y] = symbol;
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'W'){

                            Hero2X=i;
                            Hero2Y = j;
                        }
                    }}
            }
        } else if (HeroIdentification ==2 ) {if(LaneChoosen == 1){
            int x =7;

            int y = rand.nextInt((int) ((Math.floor(size/ 3) / 2)) - 0 + 1) + 0;
            board[x][y] = symbol;
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'G'){

                        Hero3X=i;
                        Hero3Y = j;
                    }
                }}
        }
        else if(LaneChoosen == 2){
            int x =7;
            int y = rand.nextInt((int) ((Math.floor(size / 3) * 2)) - 3 + 1) + 3;
            board[x][y] = symbol;
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'G'){

                        Hero3X=i;
                        Hero3Y = j;
                    }
                }}
        }
        else if(LaneChoosen == 3){
            int x =7;

            int y = rand.nextInt((int) (Math.floor(size/ 3) * 4 - 1) - 6 + 1) + 6;
            board[x][y] = symbol;
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'G'){

                        Hero3X=i;
                        Hero3Y = j;
                    }
                }
            }
        }

        }


//        checkPosition();



    }
    public void checkTeleportChoice(int choice){
        if(choice == 0){
            System.out.println("You Can't Teleport to your own location .Try Again .");
            choice  = Logic.UserInput("-->",2);
            checkTeleportChoice(choice);

        }
    }
    public void checkNewTeleportChoice(int choice){
        if(choice == 1){
            System.out.println("You Can't Teleport to your own location .Try Again .");
            choice  = Logic.UserInput("-->",2);
            checkNewTeleportChoice(choice);

        }
    }
    public void checkOneMoreTeleportChoice(int choice){
        if(choice == 2){
            System.out.println("You Can't Teleport to your own location .Try Again .");
            choice  = Logic.UserInput("-->",2);
            checkOneMoreTeleportChoice(choice);

        }
    }
    //this method provides the choice if you want to teleport randomly or with options
    public void Teleport(int size,char symbol , int HeroIdentity) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        if(HeroIdentity == 0){
        Logic.printHeader("TELEPORTATION PROGRAM PROCESSING ...........");
        System.out.println("Which Hero do you wanna help or transport to ?");
            System.out.println("(1) Player 2");
            System.out.println("(2) Player 3");
            System.out.println("(3) Go Back .");
            int TeleportChoice  = Logic.UserInput("-->",3);
            if(TeleportChoice == 3){
                userMove(size,symbol,HeroIdentity);
            }
//        checkTeleportChoice(TeleportChoice);
            boolean isWorking= TeleportVicinity(size,size,TeleportChoice,HeroIdentity,symbol);
            System.out.println("Status :"+isWorking);
            System.out.println("It breaks out fine .");

                System.out.println("Row : "+TeleportBoardRowRange);
                System.out.println("Column : "+TeleportBoardColumnRange);

                 if(TeleportBoardRowRange< 0){

                    System.out.println("Start Again");


                    Teleport(size,symbol,HeroIdentity);
                }
                else if (TeleportBoardColumnRange<0) {
                    System.out.println("Start Again");

                    Teleport(size,symbol,HeroIdentity);
                }
                else if (TeleportBoardColumnRange>7) {
                    System.out.println("Start Again");

                    Teleport(size,symbol,HeroIdentity);
                }
                else if(TeleportBoardRowRange>7){

                    System.out.println("Start Again");
                    Teleport(size,symbol,HeroIdentity);
                }
                if(board[TeleportBoardRowRange][TeleportBoardColumnRange] == 'X'){
                    System.out.println("No Man's Land . Go Somewhere Else.");

                    Teleport(size,symbol,HeroIdentity);
                }
                board[Hero1X][Hero1Y] = ' ';
                board[TeleportBoardRowRange][TeleportBoardColumnRange] = 'H';
                System.out.println("x: "+ TeleportBoardRowRange+" && y : "+TeleportBoardColumnRange);
                Hero1XOrigin.add(Hero1X);
                Hero1YOrigin.add(Hero1Y);
                Hero1RetainX=Hero1X;
                Hero1RetainY = Hero1Y;
                Hero1X=TeleportBoardRowRange;
                Hero1Y = TeleportBoardColumnRange;

                return;


            }




        if(HeroIdentity == 1){
            Logic.printHeader("TELEPORTATION PROGRAM PROCESSING ...........");
            System.out.println("Which Hero do you wanna help or transport to ?");
            System.out.println("(1) Player 1");
            System.out.println("(2) Player 3");
            System.out.println("(3) Go Back .");
            int TeleportChoice  = Logic.UserInput("-->",3);
            if(TeleportChoice == 3){
                userMove(size,symbol,HeroIdentity);
            }
//            checkNewTeleportChoice(TeleportChoice);
            Random b = new Random();
            int ch = b.nextInt(2-0+1)+1;
//        checkTeleportChoice(TeleportChoice);
            boolean isWorking= TeleportVicinity(size,size,TeleportChoice,HeroIdentity,symbol);


                    System.out.println("Row : "+TeleportBoardRowRange);
                    System.out.println("Column : "+TeleportBoardColumnRange);
                      if (TeleportBoardColumnRange>7 ||TeleportBoardColumnRange<0||TeleportBoardRowRange<0) {
                        System.out.println("Start Again");
                        Teleport(size,symbol,HeroIdentity);
                    }
                    else if(TeleportBoardRowRange>7){

                        System.out.println("Start Again");

                          Teleport(size,symbol,HeroIdentity);
                    }

                    else if(TeleportBoardRowRange< 0){

                        System.out.println("Start Again");

                        Teleport(size,symbol,HeroIdentity);
                    }
                    else if (TeleportBoardColumnRange<0) {
                        System.out.println("Start Again");

                        Teleport(size,symbol,HeroIdentity);
                    }
                    else if (TeleportBoardColumnRange>7) {
                        System.out.println("Start Again");

                        Teleport(size,symbol,HeroIdentity);
                    }
                    else if(TeleportBoardRowRange>7){

                        System.out.println("Start Again");
                        Teleport(size,symbol,HeroIdentity);
                    }
                    if(board[TeleportBoardRowRange][TeleportBoardColumnRange] == 'X'){
                        System.out.println("No Man's Land . Go Somewhere Else.");

                        System.out.println("Hero x: "+ Hero2X+" && Hero y : "+Hero2Y);

                        Teleport(size,symbol,HeroIdentity);
                    }
                    board[Hero2X][Hero2Y] = ' ';
                    board[TeleportBoardRowRange][TeleportBoardColumnRange] = 'W';
                    System.out.println("x: "+ TeleportBoardRowRange+" && y : "+TeleportBoardColumnRange);
            Hero2XOrigin.add(Hero2X);
            Hero2YOrigin.add(Hero2Y);
                    Hero2RetainX=Hero2X;
                    Hero2RetainY = Hero2Y;
                    Hero2X=TeleportBoardRowRange;
                    Hero2Y = TeleportBoardColumnRange;

                    System.out.println("Hero x: "+ Hero2X+" && Hero y : "+Hero2Y);

                }


        if(HeroIdentity == 2){
            Logic.printHeader("TELEPORTATION PROGRAM PROCESSING ...........");
            System.out.println("Which Hero do you wanna help or transport to ?");
            System.out.println("(1) Player 1");
            System.out.println("(2) Player 2");
            System.out.println("(3) Go Back .");
            int TeleportChoice  = Logic.UserInput("-->",3);
            if(TeleportChoice == 3){
                userMove(size,symbol,HeroIdentity);
            }
//            checkOneMoreTeleportChoice(TeleportChoice);

            Random b = new Random();
            int ch = b.nextInt(2-0+1)+1;
//        checkTeleportChoice(TeleportChoice);
            boolean isWorking= TeleportVicinity(size,size,TeleportChoice,HeroIdentity,symbol);


                    System.out.println("Row : "+TeleportBoardRowRange);
                    System.out.println("Column : "+TeleportBoardColumnRange);
                    if(board[TeleportBoardRowRange][TeleportBoardColumnRange] == 'X'){
                        System.out.println("No Man's Land . Go Somewhere Else.");

                        Teleport(size,symbol,HeroIdentity);
                    }
                    else if(TeleportBoardRowRange< 0){

                        System.out.println("Start Again");

                        Teleport(size,symbol,HeroIdentity);
                    }
                    else if (TeleportBoardColumnRange<0) {
                        System.out.println("Start Again");
                        Teleport(size,symbol,HeroIdentity);
                    }
                    else if (TeleportBoardColumnRange>7) {
                        System.out.println("Start Again");

                        Teleport(size,symbol,HeroIdentity);
                    }
                    else if(TeleportBoardRowRange>7){

                        System.out.println("Start Again");

                        Teleport(size,symbol,HeroIdentity);
                    }
                    board[Hero3X][Hero3Y] = ' ';
                    board[TeleportBoardRowRange][TeleportBoardColumnRange] = 'G';
                    System.out.println("x: "+ TeleportBoardRowRange+" && y : "+TeleportBoardColumnRange);
            Hero3XOrigin.add(Hero3X);
            Hero3YOrigin.add(Hero3Y);
                    Hero3RetainX=Hero3X;
                    Hero3RetainY = Hero3Y;
                    Hero3X=TeleportBoardRowRange;
                    Hero3Y = TeleportBoardColumnRange;

                }
            }

//this method allows the user to move on the board

    public void userMove(int size,char symbol,int HeroIdentity) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {

      if(HeroIdentity == 0){
        CheckPreviousBush(HeroIdentity,isBush,symbol);
          CheckPreviousCave(HeroIdentity,isCave,symbol);
          CheckPreviousKoulou(HeroIdentity,isKoulou,symbol);


        retainprintBoard();
//        MarketcheckPosition();
//        LandcheckPosition();
        System.out.println("Enter the place you want to move to : W : Up , A : Left , D : Right , S : Down ,TB : Teleport Back, T : Teleport, , Q : Quit , I : Player Info , P: Print Board , K: Inventory Actions,V : Collision Vicinity , SB : Spawn Back To Nexus  ");
        String Input = sc.nextLine();
        while(!Input.equalsIgnoreCase("SB") && !Input.equalsIgnoreCase("V")&&!Input.equalsIgnoreCase("TB") && !Input.equalsIgnoreCase("T") && !Input.equalsIgnoreCase("K") && !Input.equalsIgnoreCase("Q") && !Input.equalsIgnoreCase("I")  && !Input.equalsIgnoreCase("P") && !Input.equalsIgnoreCase("W") && !Input.equalsIgnoreCase("A") && !Input.equalsIgnoreCase("S") && !Input.equalsIgnoreCase("D")){//if none of the above keywords are pressed ,
            // Invalid Message is called and user is asked to type the input again .
            System.out.println("Invalid input");
            Input = sc.nextLine();
        }

          if(Input.equalsIgnoreCase("SB")){
              SpawnToNexus(size,HeroIdentity,symbol);
          }
          if(Input.equalsIgnoreCase("V")){
              CollisionVicinity(size,HeroIdentity,symbol);
              userMove(size,symbol,HeroIdentity);
          }
        if(Input.equalsIgnoreCase("Q")){
            System.out.println("Are you sure ?");
            System.out.println("Thanks for playing this , do try again for enjoying more of the adventure .");
            System.out.println("Your Game Ended. Sorry Try Again Next Time.");
            System.out.println("Do you want to Play Again ?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            int lastChoice = Logic.UserInput("-->",2);
            if(lastChoice == 1){
                Logic.StartGame();
            }
            else{
                System.out.println("Have a nice day ahead .");
                System.exit(0);
            }
        }
        if(Input.equalsIgnoreCase("I")){
            Logic.printallstats();
            userMove(size,symbol,HeroIdentity);
        }
        if(Input.equalsIgnoreCase("K")){
            Logic.inventorydisplay();
            userMove(size,symbol,HeroIdentity);
        }
        if(Input.equalsIgnoreCase("P")){


            retainprintBoard();
            System.out.println("");
            System.out.println("");
            userMove(size,symbol,HeroIdentity);
        }
          if(Input.equalsIgnoreCase("T")){
              System.out.println("Choose Type Of Teleportation you want to use :");
              System.out.println("(1) Teleportation With Options .");
              System.out.println("(2) Teleportation Randomly . ");
              System.out.println("(3) Go Back ");
              int TeleportPath = Logic.UserInput("-->",3);
              if(TeleportPath == 1){
                  TeleportWithOptions(size,symbol,HeroIdentity);

              }
              else if(TeleportPath == 2){
                  Teleport(size,symbol,HeroIdentity);

                  System.out.println(" ");
              }
              else if(TeleportPath == 3){
                  userMove(size,symbol,HeroIdentity);
              }

          }
          if(Input.equalsIgnoreCase("TB")){
              TeleportBack(size,symbol,HeroIdentity);
              System.out.println(" ");
          }
        if(Input.equalsIgnoreCase("W")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'H'){
                        if(i-1 < 0 ){
                            System.out.println("Start Again");
                            userMove(size,symbol,HeroIdentity);
                        } else if (board[i-1][j] == 'X') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i-1][j] == 'W') {
                            System.out.println("You Cannot Overlap your Teammate");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i-1][j] == 'G') {
                            System.out.println("You Cannot Overlap your Teammate");
                            userMove(size,symbol,HeroIdentity);
                        }

                        board[i][j] =' ';

                        board[--i][j] ='H';
                       firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                        SoundPlayer.Stopmusic(firstClip);
                        System.out.println("x: "+ i+" && y : "+j);
                        Hero1X=i;
                        Hero1Y = j;

                    }
                }
            }



            System.out.println(" ");
        }
        if(Input.equalsIgnoreCase("A")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'H'){
                        if(j-1 <0 ){
                            System.out.println("Start Again");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j-1] == 'X') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j-1] == 'W') {
                            System.out.println("You Cannot Overlap your Teammate");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j-1] == 'G') {
                            System.out.println("You Cannot Overlap your Teammate");
                            userMove(size,symbol,HeroIdentity);
                        }

                        board[i][j] =' ';
                        board[i][--j] ='H';
                        System.out.println("x: "+ i+" && y : "+j);
                        firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                        SoundPlayer.Stopmusic(firstClip);
                        Hero1X=i;
                        Hero1Y = j;

                    }
                }
            }



            System.out.println(" ");
        }

        if(Input.equalsIgnoreCase("S")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'H'){
                        if(i+1 == size ){
                            System.out.println("Start Again");
                            userMove(size,symbol,HeroIdentity);
                        }else if (board[i+1][j] == 'X') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i+1][j] == 'W') {
                            System.out.println("You Cannot Overlap your Teammate");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i+1][j] == 'G') {
                            System.out.println("You Cannot Overlap your Teammate");
                            userMove(size,symbol,HeroIdentity);
                        }
                        board[i][j] =' ';
                        board[++i][j] ='H';
                        System.out.println("x: "+ i+" && y : "+j);
                        firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                        SoundPlayer.Stopmusic(firstClip);
                        Hero1X=i;
                        Hero1Y = j;

                    }
                }
            }



            System.out.println(" ");
        }



        if(Input.equalsIgnoreCase("D")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'H'){
                        if(j+1 == size ){
                            System.out.println("Start Again");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j+1] == 'X') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j+1] == 'G') {
                            System.out.println("You cannot overlap your Teammate .");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j+1] == 'W') {
                            System.out.println("You cannot overlap your Teammate .");
                            userMove(size,symbol,HeroIdentity);
                        }
                        board[i][j] =' ';
                        board[i][++j] ='H';
                        System.out.println("x: "+ i+" && y : "+j);
                        firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                        SoundPlayer.Stopmusic(firstClip);
                        Hero1X=i;
                        Hero1Y = j;

                    }
                }
            }



            System.out.println(" ");
        }

//        checkPosition();
          WinConditionHero(HeroIdentity);
        CollisionVicinity(size,HeroIdentity,symbol);
          CollisionCheckPosition(HeroIdentity,size,numberOfVillains);

          MarketcheckPosition(HeroIdentity,symbol);

          KoulouCheckPosition(HeroIdentity,symbol);
          BushCheckPosition(HeroIdentity,symbol);
          CheckCavePosition(HeroIdentity,symbol);
        LandcheckPosition(HeroIdentity,symbol);


        retainprintBoard();
        System.out.println("  ");
          Logic.permissionToMove(size, symbol , HeroIdentity);

        }

    if(HeroIdentity == 1){


        retainprintBoard();

        CheckPreviousBush(HeroIdentity,isBush,symbol);
        CheckPreviousCave(HeroIdentity,isCave,symbol);
        CheckPreviousKoulou(HeroIdentity,isKoulou,symbol);
//        MarketcheckPosition();
//        LandcheckPosition();

        System.out.println("Enter the place you want to move to : W : Up , A : Left , D : Right , S : Down ,TB : Teleport Back, T : Teleport, , Q : Quit , I : Player Info , P: Print Board , K: Inventory Actions,V : Collision Vicinity , SB : Spawn Back To Nexus  ");
        String Input = sc.nextLine();
        while(!Input.equalsIgnoreCase("SB") && !Input.equalsIgnoreCase("V")&&!Input.equalsIgnoreCase("TB") && !Input.equalsIgnoreCase("T") && !Input.equalsIgnoreCase("K") && !Input.equalsIgnoreCase("Q") && !Input.equalsIgnoreCase("I")  && !Input.equalsIgnoreCase("P") && !Input.equalsIgnoreCase("W") && !Input.equalsIgnoreCase("A") && !Input.equalsIgnoreCase("S") && !Input.equalsIgnoreCase("D")){//if none of the above keywords are pressed ,
            // Invalid Message is called and user is asked to type the input again .
            System.out.println("Invalid input");
            Input = sc.nextLine();
        }

        if(Input.equalsIgnoreCase("SB")){
            SpawnToNexus(size,HeroIdentity,symbol);
        }
        if(Input.equalsIgnoreCase("V")){
            CollisionVicinity(size,HeroIdentity,symbol);
            userMove(size,symbol,HeroIdentity);
        }
        if(Input.equalsIgnoreCase("T")){
            System.out.println("Choose Type Of Teleportation you want to use :");
            System.out.println("(1) Teleportation With Options .");

            System.out.println("(2) Teleportation Randomly . ");
            System.out.println("(3) Go Back ");
            int TeleportPath = Logic.UserInput("-->",2);
            if(TeleportPath == 1){
                TeleportWithOptions(size,symbol,HeroIdentity);

            }
            else if(TeleportPath == 2){
                Teleport(size,symbol,HeroIdentity);

                System.out.println(" ");
            }
            else if(TeleportPath == 3){
                userMove(size,symbol,HeroIdentity);
            }
        }
        if(Input.equalsIgnoreCase("TB")){
            TeleportBack(size,symbol,HeroIdentity);
            System.out.println(" ");
        }
        if(Input.equalsIgnoreCase("Q")){
            System.out.println("Are you sure ?");
            System.out.println("Thanks for playing this , do try again for enjoying more of the adventure .");
            System.out.println("Your Game Ended. Sorry Try Again Next Time.");
            System.out.println("Do you want to Play Again ?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            int lastChoice = Logic.UserInput("-->",2);
            if(lastChoice == 1){
                Logic.StartGame();
            }
            else{
                System.out.println("Have a nice day ahead .");
                System.exit(0);
            }
        }
        if(Input.equalsIgnoreCase("I")){
            Logic.printallstats();
            userMove(size,symbol,HeroIdentity);

        }
        if(Input.equalsIgnoreCase("K")){
            Logic.inventorydisplay();
            userMove(size,symbol,HeroIdentity);
        }
        if(Input.equalsIgnoreCase("P")){


            retainprintBoard();
            System.out.println("");
            System.out.println("");
            userMove(size,symbol,HeroIdentity);
        }

        if(Input.equalsIgnoreCase("W")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'W'){
                        if(i-1 < 0 ){
                            System.out.println("Start Again");
                            userMove(size,symbol,HeroIdentity);
                        } else if (board[i-1][j] == 'X') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i-1][j] == 'H') {
                            System.out.println("You cannot overlap your Teammate.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i-1][j] == 'G') {
                            System.out.println("You cannot overlap your Teammate.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        board[i][j] =' ';

                        board[--i][j] ='W';
                        System.out.println("x: "+ i+" && y : "+j);
                        firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                        SoundPlayer.Stopmusic(firstClip);
                        Hero2X=i;
                        Hero2Y = j;

                    }
                }
            }



            System.out.println(" ");
        }
        if(Input.equalsIgnoreCase("A")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'W'){
                        if(j-1 <0 ){
                            System.out.println("Start Again");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j-1] == 'X') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j-1] == 'H') {
                            System.out.println("You cannot overlap your Teammate");
                            userMove(size,symbol,HeroIdentity);
                        }else if (board[i][j-1] == 'G') {
                            System.out.println("You cannot overlap your Teammate");
                            userMove(size,symbol,HeroIdentity);
                        }

                        board[i][j] =' ';
                        board[i][--j] ='W';
                        System.out.println("x: "+ i+" && y : "+j);
                        firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                        SoundPlayer.Stopmusic(firstClip);
                        Hero2X=i;
                        Hero2Y = j;

                    }
                }
            }



            System.out.println(" ");
        }

        if(Input.equalsIgnoreCase("S")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'W'){
                        if(i+1 == size ){
                            System.out.println("Start Again");
                            userMove(size,symbol,HeroIdentity);
                        }else if (board[i+1][j] == 'X') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i+1][j] == 'G') {
                            System.out.println("You cannot overlap your Teammate");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i+1][j] == 'H') {
                            System.out.println("You cannot overlap your Teammate");
                            userMove(size,symbol,HeroIdentity);
                        }
                        board[i][j] =' ';
                        board[++i][j] ='W';
                        System.out.println("x: "+ i+" && y : "+j);
                        firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                        SoundPlayer.Stopmusic(firstClip);
                        Hero2X=i;
                        Hero2Y = j;

                    }
                }
            }



            System.out.println(" ");
        }



        if(Input.equalsIgnoreCase("D")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'W'){
                        if(j+1 == size ){
                            System.out.println("Start Again");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j+1] == 'X') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j+1] == 'G') {
                            System.out.println("You cannot overlap your Teammate . ");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j+1] == 'H') {
                            System.out.println("You cannot overlap your Teammate . ");
                            userMove(size,symbol,HeroIdentity);
                        }
                        board[i][j] =' ';
                        board[i][++j] ='W';
                        System.out.println("x: "+ i+" && y : "+j);
                        firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                        SoundPlayer.Stopmusic(firstClip);

                        Hero2X=i;
                        Hero2Y = j;

                    }
                }
            }



            System.out.println(" ");
        }

//        checkPosition();
        WinConditionHero(HeroIdentity);

        CollisionVicinity(size,HeroIdentity,symbol);

        CollisionCheckPosition(HeroIdentity,size,numberOfVillains);
        MarketcheckPosition(HeroIdentity,symbol);
        LandcheckPosition(HeroIdentity,symbol);

        KoulouCheckPosition(HeroIdentity,symbol);
        BushCheckPosition(HeroIdentity,symbol);
        CheckCavePosition(HeroIdentity,symbol);


        retainprintBoard();
        System.out.println("  ");
        Logic.permissionToMove(size, symbol , HeroIdentity);
    }

    if(HeroIdentity == 2 ){


        retainprintBoard();

        CheckPreviousBush(HeroIdentity,isBush,symbol);
        CheckPreviousCave(HeroIdentity,isCave,symbol);
        CheckPreviousKoulou(HeroIdentity,isKoulou,symbol);
//        MarketcheckPosition();
//        LandcheckPosition();
        System.out.println("Enter the place you want to move to : W : Up , A : Left , D : Right , S : Down ,TB : Teleport Back, T : Teleport, , Q : Quit , I : Player Info , P: Print Board , K: Inventory Actions,V : Collision Vicinity , SB : Spawn Back To Nexus ");
        String Input = sc.nextLine();
        while(!Input.equalsIgnoreCase("SB") && !Input.equalsIgnoreCase("V")&&!Input.equalsIgnoreCase("TB") && !Input.equalsIgnoreCase("T") && !Input.equalsIgnoreCase("K") && !Input.equalsIgnoreCase("Q") && !Input.equalsIgnoreCase("I")  && !Input.equalsIgnoreCase("P") && !Input.equalsIgnoreCase("W") && !Input.equalsIgnoreCase("A") && !Input.equalsIgnoreCase("S") && !Input.equalsIgnoreCase("D")){//if none of the above keywords are pressed ,
            // Invalid Message is called and user is asked to type the input again .
            System.out.println("Invalid input");
            Input = sc.nextLine();
        }
        if(Input.equalsIgnoreCase("SB")){
            SpawnToNexus(size,HeroIdentity,symbol);
        }
        if(Input.equalsIgnoreCase("T")){
            System.out.println("Choose Type Of Teleportation you want to use :");
            System.out.println("(1) Teleportation With Options .");
            System.out.println("(2) Teleportation Randomly . ");
            System.out.println("(3) Go Back ");
            int TeleportPath = Logic.UserInput("-->",2);
            if(TeleportPath == 1){
                TeleportWithOptions(size,symbol,HeroIdentity);
            }
            else if(TeleportPath == 2){
                Teleport(size,symbol,HeroIdentity);
                System.out.println(" ");
            }
            else if(TeleportPath == 3){
                userMove(size,symbol,HeroIdentity);
            }
        }
        if(Input.equalsIgnoreCase("TB")){
            TeleportBack(size,symbol,HeroIdentity);
            System.out.println(" ");
        }
        if(Input.equalsIgnoreCase("Q")){
            System.out.println("Are you sure ?");
            System.out.println("Thanks for playing this , do try again for enjoying more of the adventure .");
            System.out.println("Your Game Ended. Sorry Try Again Next Time.");
            System.out.println("Do you want to Play Again ?");
            System.out.println("(1) Yes");
            System.out.println("(2) No");
            int lastChoice = Logic.UserInput("-->",2);
            if(lastChoice == 1){
                Logic.StartGame();
            }
            else{
                System.out.println("Have a nice day ahead .");
                System.exit(0);
            }
        }
        if(Input.equalsIgnoreCase("I")){
            Logic.printallstats();
            userMove(size,symbol,HeroIdentity);
        }
        if(Input.equalsIgnoreCase("K")){
            Logic.inventorydisplay();
            userMove(size,symbol,HeroIdentity);
        }
        if(Input.equalsIgnoreCase("P")){


            retainprintBoard();
            System.out.println("");
            System.out.println("");
            userMove(size,symbol,HeroIdentity);
        }
        if(Input.equalsIgnoreCase("V")){
            CollisionVicinity(size,HeroIdentity,symbol);
            userMove(size,symbol,HeroIdentity);
        }
        if(Input.equalsIgnoreCase("W")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'G'){
                        if(i-1 < 0 ){
                            System.out.println("Start Again");
                            userMove(size,symbol,HeroIdentity);
                        } else if (board[i-1][j] == 'X') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i-1][j] == 'H') {
                            System.out.println("You cannot overlap your Teammate.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i-1][j] == 'G') {
                            System.out.println("You cannot overlap your Teammate.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        board[i][j] =' ';

                        board[--i][j] ='G';
                        System.out.println("x: "+ i+" && y : "+j);
                        firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                        SoundPlayer.Stopmusic(firstClip);
                        Hero3X=i;
                        Hero3Y = j;
                    }
                }
            }



            System.out.println(" ");
        }
        if(Input.equalsIgnoreCase("A")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'G'){
                        if(j-1 <0 ){
                            System.out.println("Start Again");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j-1] == 'X') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j-1] == 'H') {
                            System.out.println("You cannot overlap your Teammate.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j-1] == 'W') {
                            System.out.println("You cannot overlap your Teammate.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        board[i][j] =' ';
                        board[i][--j] ='G';
                        System.out.println("x: "+ i+" && y : "+j);
                        firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                        SoundPlayer.Stopmusic(firstClip);
                        Hero3X=i;
                        Hero3Y = j;
                    }
                }
            }



            System.out.println(" ");
        }

        if(Input.equalsIgnoreCase("S")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'G'){
                        if(i+1 == size ){
                            System.out.println("Start Again");
                            userMove(size,symbol,HeroIdentity);
                        }else if (board[i+1][j] == 'X') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i+1][j] == 'H') {
                            System.out.println("You cannot overlap your Teammate");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i+1][j] == 'W') {
                            System.out.println("You cannot overlap your Teammate");
                            userMove(size,symbol,HeroIdentity);
                        }
                        board[i][j] =' ';
                        board[++i][j] ='G';
                        System.out.println("x: "+ i+" && y : "+j);
                        firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                        SoundPlayer.Stopmusic(firstClip);
                        Hero3X=i;
                        Hero3Y = j;
                    }
                }
            }



            System.out.println(" ");
        }



        if(Input.equalsIgnoreCase("D")){
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'G'){
                        if(j+1 == size ){
                            System.out.println("Start Again");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j+1] == 'X') {
                            System.out.println("No Man's Land . Go Somewhere Else.");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j+1] == 'H') {
                            System.out.println("You cannot overlap your Teammate . ");
                            userMove(size,symbol,HeroIdentity);
                        }
                        else if (board[i][j+1] == 'W') {
                            System.out.println("You cannot overlap your Teammate .");
                            userMove(size,symbol,HeroIdentity);
                        }
                        board[i][j] =' ';
                        board[i][++j] ='G';
                        System.out.println("x: "+ i+" && y : "+j);
                        firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                        SoundPlayer.Stopmusic(firstClip);
                        Hero3X=i;
                        Hero3Y = j;
                    }
                }
            }



            System.out.println(" ");
        }

//        checkPosition();
        WinConditionHero(HeroIdentity);
        CollisionVicinity(size,HeroIdentity,symbol);
        CollisionCheckPosition(HeroIdentity,size,numberOfVillains);

        MarketcheckPosition(HeroIdentity,symbol);
        LandcheckPosition(HeroIdentity,symbol);
        KoulouCheckPosition(HeroIdentity,symbol);
        BushCheckPosition(HeroIdentity,symbol);
        CheckCavePosition(HeroIdentity,symbol);


        retainprintBoard();
        System.out.println("  ");
        Logic.permissionToMove(size, symbol , HeroIdentity);
    }
    }
    //this method is used to check if the user is on the bush
    public void BushCheckPosition(int HeroIdentity,char symbol) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        if(symbol == 'H'){
            HeroIdentity = 0;
            for(int j =0 ; j< BushX.size() ;j++)
            {
                if(Hero1X == BushX.get(j)){
                    if(Hero1Y == BushY.get(j)){
                        System.out.println("You are on the Bush .Your Dexterity will increase by 10%");
                        isBush=true;
                        Logic.BushActivated(HeroIdentity);
                    }}

            }

        }if(symbol == 'W'){
            HeroIdentity = 1;
            for(int j =0 ; j< BushX.size() ;j++)
            {
                if(Hero2X == BushX.get(j)){
                    if(Hero2Y == BushY.get(j)){
                        System.out.println("You are on the Bush .Your Dexterity will increase by 10%");
                        isBush=true;
                        Logic.BushActivated(HeroIdentity);
                    }}
            }
        }
        if(symbol == 'G'){
            HeroIdentity =2 ;
            for(int j =0 ; j< BushX.size() ;j++)
            {
                if(Hero3X == BushX.get(j)){
                    if(Hero3Y == BushY.get(j)){
                        System.out.println("You are on the Bush .Your Dexterity will increase by 10%");
                        isBush=true;
                        Logic.BushActivated(HeroIdentity);
                    }}
            }
        }

    }
    // this method is used to check if the hero is on a koulou on the board
    public void KoulouCheckPosition(int HeroIdentity,char symbol) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        if(symbol == 'H'){
            HeroIdentity = 0;
            for(int j =0 ; j< KoulouX.size() ;j++)
            {
                if(Hero1X == KoulouX.get(j)){
                    if(Hero1Y == KoulouY.get(j)){
                        System.out.println("You are at the Koulou .Your strength will increase .");
                        isKoulou = true;
                        Logic.KoulouActivated(HeroIdentity);
                    }}

            }

        }if(symbol == 'W'){
            HeroIdentity = 1;
            for(int j =0 ; j< KoulouX.size() ;j++)
            {
                if(Hero2X == KoulouX.get(j)){
                    if(Hero2Y == KoulouY.get(j)){
                        System.out.println("You are at the Koulou .Your strength will increase .");
                        isKoulou = true;
                        Logic.KoulouActivated(HeroIdentity);
                    }}
            }
        }
        if(symbol == 'G'){
            HeroIdentity =2;

            for(int j =0 ; j< KoulouX.size() ;j++)
            {
                if(Hero3X == KoulouX.get(j)){
                    if(Hero3Y == KoulouY.get(j)){
                        System.out.println("You are at the Koulou .Your strength will increase .");
                        isKoulou = true;
                        Logic.KoulouActivated(HeroIdentity);
                    }}
            }
        }

    }

// this method is used to check if the hero was previously on the Koulou symbol
    public void CheckPreviousKoulou(int HeroIdentity,boolean isKoulou,char symbol) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
     if(isKoulou == true){
        if(symbol == 'H'){
            HeroIdentity = 0;
            for(int j =0 ; j< KoulouX.size() ;j++)
            {
                if(Hero1X == KoulouX.get(j)){
                    if(Hero1Y == KoulouY.get(j)){
                        System.out.println("You were previously at the Koulou .Your strength will increase .");
                        isKoulou = false;
                        Logic.KoulouDeactivated(HeroIdentity);
                    }}

            }

        }if(symbol == 'W'){
            HeroIdentity = 1;
            for(int j =0 ; j< KoulouX.size() ;j++)
            {
                if(Hero2X == KoulouX.get(j)){
                    if(Hero2Y == KoulouY.get(j)){
                        System.out.println("You were previously at the Koulou .Your strength will increase .");
                        isKoulou = false;
                        Logic.KoulouDeactivated(HeroIdentity);
                    }}
            }
        }
        if(symbol == 'G'){
            HeroIdentity =2;

            for(int j =0 ; j< KoulouX.size() ;j++)
            {
                if(Hero3X == KoulouX.get(j)){
                    if(Hero3Y == KoulouY.get(j)){
                        System.out.println("You were previously at the Koulou .Your strength will increase .");
                        isKoulou = false;
                        Logic.KoulouDeactivated(HeroIdentity);
                    }}
            }
        }}

    }

    // this method is used to check if the hero was previously on the Cave symbol
    public void CheckPreviousCave(int HeroIdentity,boolean isCave,char symbol) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        if(isCave == true){
            if(symbol == 'H'){
                HeroIdentity = 0;
                for(int j =0 ; j< CaveX.size() ;j++)
                {
                    if(Hero1X == CaveX.get(j)){
                        if(Hero1Y == CaveY.get(j)){
                            System.out.println("You were previously at the Cave .Your agility will return to normal .");
                            isCave = false;
                            Logic.CaveDeActivated(HeroIdentity);
                        }}

                }

            }if(symbol == 'W'){
                HeroIdentity = 1;
                for(int j =0 ; j< CaveX.size() ;j++)
                {
                    if(Hero2X == CaveX.get(j)){
                        if(Hero2Y == CaveY.get(j)){
                            System.out.println("You were previously at the Cave .Your agility will return to normal .");
                            isCave = false;
                            Logic.CaveDeActivated(HeroIdentity);
                        }}
                }
            }
            if(symbol == 'G'){
                HeroIdentity =2;

                for(int j =0 ; j< CaveX.size() ;j++)
                {
                    if(Hero3X == CaveX.get(j)){
                        if(Hero3Y == CaveY.get(j)){
                            System.out.println("You were previously at the Cave .Your agility will return to normal .");
                            isCave = false;
                            Logic.CaveDeActivated(HeroIdentity);
                        }}
                }
            }}

    }
// this method is used to check if the hero was previously on the Bush symbol

    public void CheckPreviousBush(int HeroIdentity,boolean isBush,char symbol) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        if(isBush == true){
            if(symbol == 'H'){
                HeroIdentity = 0;
                for(int j =0 ; j< BushX.size() ;j++)
                {
                    if(Hero1X == BushX.get(j)){
                        if(Hero1Y == BushY.get(j)){
                            System.out.println("You were previously at the Bush .Your dexterity will return to normal .");
                            isBush = false;
                            Logic.BushDeactivated(HeroIdentity);
                        }}

                }

            }if(symbol == 'W'){
                HeroIdentity = 1;
                for(int j =0 ; j< BushX.size() ;j++)
                {
                    if(Hero2X == BushX.get(j)){
                        if(Hero2Y == BushY.get(j)){
                            System.out.println("You were previously at the Bush .Your dexterity will return to normal .");
                            isBush = false;

                            Logic.BushDeactivated(HeroIdentity);
                        }}
                }
            }
            if(symbol == 'G'){
                HeroIdentity =2;

                for(int j =0 ; j< BushX.size() ;j++)
                {
                    if(Hero3X == BushX.get(j)){
                        if(Hero3Y == BushY.get(j)){
                            System.out.println("You were previously at the Bush .Your dexterity will return to normal .");
                            isBush = false;

                            Logic.BushDeactivated(HeroIdentity);
                        }}
                }
            }}

    }

// this method is used to check if the hero was previously on the Cave symbol

    public void CheckCavePosition(int HeroIdentity,char symbol) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        if(symbol == 'H'){
            HeroIdentity = 0;
            for(int j =0 ; j< CaveX.size() ;j++)
            {
                if(Hero1X == CaveX.get(j)){
                    if(Hero1Y == CaveY.get(j)){
                        System.out.println("You are at the Cave .Your Agility will increase.");
                        isCave= true;
                        Logic.CaveActivated(HeroIdentity);
                    }}

            }

        }if(symbol == 'W'){
            HeroIdentity = 1;
            for(int j =0 ; j< CaveX.size() ;j++)
            {
                if(Hero2X == CaveX.get(j)){
                    if(Hero2Y == CaveY.get(j)){
                        System.out.println("You are at the Cave .Your Agility will increase.");
                        isCave= true;
                        Logic.CaveActivated(HeroIdentity);
                    }}
            }
        }
        if(symbol == 'G'){
        HeroIdentity = 2;
            for(int j =0 ; j< CaveX.size() ;j++)
            {
                if(Hero3X == CaveX.get(j)){
                    if(Hero3Y == CaveY.get(j)){
                        System.out.println("You are at the Cave .Your Agility will increase.");
                        isCave= true;
                        Logic.CaveActivated(HeroIdentity);
                    }}
            }
        }

    }
// this is used to check if the villain reached the hero nexus
    public void WinConditionVillain (int VillainIdentity,int numberofVillains) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        if(VillainIdentity == 0){
            if(Villain1X.get(numberofVillains) == 7){//***********
                if(Villain1Y.get(numberofVillains) == 0 || Villain1Y.get(numberofVillains) == 1 || Villain1Y.get(numberofVillains) == 2|| Villain1Y.get(numberofVillains) == 3|| Villain1Y.get(numberofVillains) == 4|| Villain1Y.get(numberofVillains) == 5|| Villain1Y.get(numberofVillains) == 6||Villain1Y.get(numberofVillains) == 7){
                    System.out.println("Monsters Won the Game !");
                    System.out.println("Sorry on Losing this game !");

                    System.out.println("Do you want to Play Again ?");
                    System.out.println("(1) Yes");
                    System.out.println("(2) No");
                    int lastChoice = Logic.UserInput("-->",2);
                    if(lastChoice == 1){
                        Logic.StartGame();
                    }
                    else{
                        System.out.println("Have a nice day ahead .");
                        System.exit(0);
                    }

                }
            }
        }
        if(VillainIdentity == 1){
                if(Villain2X.get(numberofVillains) == 7){
                    if(Villain2Y.get(numberofVillains) == 0 || Villain2Y.get(numberofVillains) == 1 || Villain2Y.get(numberofVillains) == 2|| Villain2Y.get(numberofVillains) == 3|| Villain2Y.get(numberofVillains) == 4|| Villain2Y.get(numberofVillains) == 5|| Villain2Y.get(numberofVillains) == 6||Villain2Y.get(numberofVillains) == 7){
                        System.out.println("Monsters Won the Game !");
                        System.out.println("Sorry on Losing this game !");

                    System.out.println("Do you want to Play Again ?");
                    System.out.println("(1) Yes");
                    System.out.println("(2) No");
                    int lastChoice = Logic.UserInput("-->",2);
                    if(lastChoice == 1){
                        Logic.StartGame();
                    }
                    else{
                        System.out.println("Have a nice day ahead .");
                        System.exit(0);
                    }

                }
            }
        }
        if(VillainIdentity == 2){
            if(Villain3X.get(numberofVillains) == 7){
                if(Villain3Y.get(numberofVillains) == 0 || Villain3Y.get(numberofVillains) == 1 || Villain3Y.get(numberofVillains) == 2|| Villain3Y.get(numberofVillains) == 3|| Villain3Y.get(numberofVillains) == 4|| Villain3Y.get(numberofVillains) == 5|| Villain3Y.get(numberofVillains) == 6||Villain3Y.get(numberofVillains) == 7){
                    System.out.println("Monsters Won the Game !");
                    System.out.println("Sorry on Losing this game !");
                    System.out.println("Do you want to Play Again ?");
                    System.out.println("(1) Yes");
                    System.out.println("(2) No");
                    int lastChoice = Logic.UserInput("-->",2);
                    if(lastChoice == 1){
                        Logic.StartGame();
                    }
                    else{
                        System.out.println("Have a nice day ahead .");
                        System.exit(0);
                    }

                }
            }
        }
    }
    //this is used to check if the hero reached the villain nexus
    public void WinConditionHero(int HeroIdentity) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        if(HeroIdentity == 0){
            if(Hero1X == 0){
                if(Hero1Y == 0 || Hero1Y == 1 || Hero1Y == 2|| Hero1Y == 3|| Hero1Y == 4|| Hero1Y == 5|| Hero1Y == 6||Hero1Y == 7){
                    System.out.println("Heroes Won the Game !");
                    System.out.println("Congratulations on winning this game !");
                    SoundPlayer.Stopmusic(Logic.newClip);
                    System.out.println("Do you want to Play Again ?");
                    System.out.println("(1) Yes");
                    System.out.println("(2) No");

                    int lastChoice = Logic.UserInput("-->",2);
                    if(lastChoice == 1){
                        Logic.StartGame();
                    }
                    else{
                        System.out.println("Have a nice day ahead .");
                        System.exit(0);
                    }

                }
            }
        }
        if(HeroIdentity == 1){
            if(Hero2X == 0){
                if(Hero2Y == 0 || Hero2Y == 1 || Hero2Y == 2|| Hero2Y == 3|| Hero2Y == 4|| Hero2Y == 5|| Hero2Y == 6||Hero2Y == 7){
                    System.out.println("Heroes Won the Game !");
                    System.out.println("Congratulations on winning this game !");

                    System.out.println("Do you want to Play Again ?");
                    System.out.println("(1) Yes");
                    System.out.println("(2) No");
                    int lastChoice = Logic.UserInput("-->",2);
                    if(lastChoice == 1){
                        Logic.StartGame();
                    }
                    else{
                        System.out.println("Have a nice day ahead .");
                        System.exit(0);
                    }

                }
            }
        }
        if(HeroIdentity == 2){
            if(Hero3X == 0){
                if(Hero3Y == 0 || Hero3Y == 1 || Hero3Y == 2|| Hero3Y == 3|| Hero3Y == 4|| Hero3Y == 5|| Hero3Y == 6||Hero3Y == 7){
                    System.out.println("Heroes Won the Game !");
                    System.out.println("Congratulations on winning this game !");

                    System.out.println("Do you want to Play Again ?");
                    System.out.println("(1) Yes");
                    System.out.println("(2) No");
                    int lastChoice = Logic.UserInput("-->",2);
                    if(lastChoice == 1){
                        Logic.StartGame();
                    }
                    else{
                        System.out.println("Have a nice day ahead .");
                        System.exit(0);
                    }

                }
            }
        }
    }
    //this allows the hero to teleport back to the position it actually teleported from
    private void TeleportBack(int size, char symbol, int heroIdentity) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        System.out.println("Teleporting Back ....");
if(heroIdentity == 0){
    System.out.println("Do you want to Teleport Back to Previous Position , Or your Original Position ?");
    System.out.println("(1) Teleport Back To Previous Position .");
    System.out.println("(2) Teleport Back To Origin .");
    System.out.println("(3) Go Back .");

    int TeleportBackChoice = Logic.UserInput("-->",3);
    if(TeleportBackChoice == 1){

        board[Hero1X][Hero1Y] = ' ';
        Hero1X = Hero1RetainX;
                Hero1Y= Hero1RetainY;
        board[Hero1X][Hero1Y] = 'H';
    } else if (TeleportBackChoice == 2) {

        board[Hero1X][Hero1Y] = ' ';
        Hero1X = Hero1XOrigin.get(0);
        Hero1Y= Hero1YOrigin.get(0);
        board[Hero1X][Hero1Y] = 'H';
    }
    else if (TeleportBackChoice == 3) {

        System.out.println("Going Back .");
        userMove(size,symbol,heroIdentity);
    }
}
      else  if(heroIdentity == 1){
    System.out.println("Do you want to Teleport Back to Previous Position , Or your Original Position ?");
    System.out.println("(1) Teleport Back To Previous Position .");
    System.out.println("(2) Teleport Back To Origin .");
    System.out.println("(3) Go Back .");
    int TeleportBackChoice = Logic.UserInput("-->",3);
    if(TeleportBackChoice == 1){


        board[Hero2X][Hero2Y] = ' ';
        Hero2X= Hero2RetainX;
                Hero2Y=Hero2RetainY;

        board[Hero2X][Hero2Y] = 'H';
    } else if (TeleportBackChoice == 2) {

        board[Hero2X][Hero2Y] = ' ';
        Hero2X = Hero2XOrigin.get(0);
        Hero2Y = Hero2YOrigin.get(0);
        board[Hero2X][Hero2Y] = 'W';
    }
    else if (TeleportBackChoice == 3) {

        System.out.println("Going Back .");
        userMove(size,symbol,heroIdentity);
    }
        }
       else if(heroIdentity == 2){
    System.out.println("Do you want to Teleport Back to Previous Position , Or your Original Position ?");
    System.out.println("(1) Teleport Back To Previous Position .");
    System.out.println("(2) Teleport Back To Origin .");
    System.out.println("(3) Go Back.");
    int TeleportBackChoice = Logic.UserInput("-->",3);
    if(TeleportBackChoice == 1){

        board[Hero3X][Hero3Y] = ' ';
        Hero3X= Hero3RetainX;
        Hero3Y = Hero3RetainY;
        board[Hero3X][Hero3Y] = 'H';
    } else if (TeleportBackChoice == 2) {

        board[Hero3X][Hero3Y] = ' ';
        Hero3X= Hero3XOrigin.get(0);
        Hero3Y = Hero3YOrigin.get(0);
        board[Hero3X][Hero3Y] = 'G';
    }
    else if (TeleportBackChoice == 3) {

        System.out.println("Going Back .");
        userMove(size,symbol,heroIdentity);
    }
        }
    }
//Allows the villain to move randomly
    public void VillainMove(int size,char symbol,int VillainIdentity,int numberOfVillains) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        if(VillainIdentity == 0){
            VillainOptions.add("W");
            VillainOptions.add("A");
            VillainOptions.add("S");
            VillainOptions.add("D");
            Random rnd = new Random();
            int rndChoice = rnd.nextInt(3-0+1)-0;
            String Input =VillainOptions.get(2);// use 2 mainly for going down
            while(!Input.equalsIgnoreCase("W") && !Input.equalsIgnoreCase("A") && !Input.equalsIgnoreCase("S") && !Input.equalsIgnoreCase("D")){//if none of the above keywords are pressed ,
                // Invalid Message is called and user is asked to type the input again .
                System.out.println("Invalid input");
                Input = VillainOptions.get(2);
            }


            if(Input.equalsIgnoreCase("W")){
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'V'){
                            if(i-1 < 0 ){
                                System.out.println("Start Again");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            } else if (board[i-1][j] == 'X') {
                                System.out.println("No Man's Land . Go Somewhere Else.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i-1][j] == 'V') {
                                System.out.println("Cannot Overlap Villain");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i-1][j] == 'E') {
                                System.out.println("Cannot Overlap Villain");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i-1][j] == 'S') {
                                System.out.println("Cannot Overlap Villain");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            board[i][j] =' ';

                            board[--i][j] ='V';
                            firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                            SoundPlayer.Stopmusic(firstClip);
                            System.out.println("x: "+ i+" && y : "+j);
                            Villain1X.set(numberOfVillains,i);
                            Villain1Y.set(numberOfVillains,j);//***********
                            for(int z = 0 ; z < Villain1X.size();z++){
                                System.out.println("Villain :" + VillainIdentity + " has moved to coordinates  : "+Villain1X.get(numberOfVillains)+","+Villain1Y.get(numberOfVillains)+" and the number of villains right now is : " + numberOfVillains);
                            }
                        }
                    }
                }



                System.out.println(" ");
            }
            if(Input.equalsIgnoreCase("A")){
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'V'){
                            if(j-1 <0 ){
                                System.out.println("Start Again");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j-1] == 'X') {
                                System.out.println("No Man's Land . Go Somewhere Else.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j-1] == 'V') {
                                System.out.println("Cannot Overlap Villain.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j-1] == 'E') {
                                System.out.println("Cannot Overlap Villain.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j-1] == 'S') {
                                System.out.println("Cannot Overlap Villain.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            board[i][j] =' ';
                            board[i][--j] ='V';
                            firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                            SoundPlayer.Stopmusic(firstClip);
                            System.out.println("x: "+ i+" && y : "+j);
                            Villain1X.set(numberOfVillains,i);
                            Villain1Y.set(numberOfVillains,j);//***********
                            for(int z = 0 ; z < Villain1X.size();z++){
                                System.out.println("Villain :" + VillainIdentity + " has moved to coordinates  : "+Villain1X.get(numberOfVillains)+","+Villain1Y.get(numberOfVillains)+" and the number of villains right now is : " + numberOfVillains);
                            }
                        }
                    }
                }



                System.out.println(" ");
            }

            if(Input.equalsIgnoreCase("S")){
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'V'){
                            if(i+1 == size ){
                                System.out.println("Start Again");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }else if (board[i+1][j] == 'X') {
                                System.out.println("No Man's Land . Go Somewhere Else.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'V') {
                                System.out.println("Cannot Overlap Villain .");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'E') {
                                System.out.println("Cannot Overlap Villain .");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'S') {
                                System.out.println("Cannot Overlap Villain .");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            board[i][j] =' ';
                            board[++i][j] ='V';
                            System.out.println("x: "+ i+" && y : "+j);
                            firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                            SoundPlayer.Stopmusic(firstClip);
                            Villain1X.set(numberOfVillains,i);
                            Villain1Y.set(numberOfVillains,j);//***********
                            for(int z = 0 ; z < Villain1X.size();z++){
                                System.out.println("Villain :" + VillainIdentity + " has moved to coordinates  : "+Villain1X.get(numberOfVillains)+","+Villain1Y.get(numberOfVillains)+" and the number of villains right now is : " + numberOfVillains);
                            }

                        }
                    }
                }



                System.out.println(" ");
            }



            if(Input.equalsIgnoreCase("D")){
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'V'){
                            if(j+1 == size ){
                                System.out.println("Start Again");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j+1] == 'X') {
                                System.out.println("No Man's Land . Go Somewhere Else.");
                                System.out.println();
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j+1] == 'V') {
                                System.out.println("Cannot Overlap Villain.");
                                System.out.println();
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j+1] == 'E') {
                                System.out.println("Cannot Overlap Villain.");
                                System.out.println();
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j+1] == 'S') {
                                System.out.println("Cannot Overlap Villain.");
                                System.out.println();
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            board[i][j] =' ';
                            board[i][++j] ='V';
                            System.out.println("x: "+ i+" && y : "+j);
                            firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                            SoundPlayer.Stopmusic(firstClip);
                            Villain1X.set(numberOfVillains,i);//***********
                            Villain1Y.set(numberOfVillains,j);
                            for(int z = 0 ; z < Villain1X.size();z++){
                                System.out.println("Villain :" + VillainIdentity + " has moved to coordinates  : "+Villain1X.get(numberOfVillains)+","+Villain1Y.get(numberOfVillains)+" and the number of villains right now is : " + numberOfVillains);
                            }
                        }
                    }
                }



                System.out.println(" ");
            }

//        checkPosition();
//            MarketcheckPosition();
//            LandcheckPosition();
//            printBoard();
//            System.out.println("  ");
            WinConditionVillain(VillainIdentity,numberOfVillains);
            CollisionVicinity(size,VillainIdentity,symbol);
            CollisionCheckPosition(VillainIdentity,size,numberOfVillains);
            VillainMove(size,'E',1,numberOfVillains);
        }
        if(VillainIdentity == 1){
            VillainOptions.add("W");
            VillainOptions.add("A");
            VillainOptions.add("S");
            VillainOptions.add("D");
            Random rnd = new Random();
            int rndChoice = rnd.nextInt(3-0+1)-0;
            String Input =VillainOptions.get(2);// use 2 mainly for going down
            while(!Input.equalsIgnoreCase("W") && !Input.equalsIgnoreCase("A") && !Input.equalsIgnoreCase("S") && !Input.equalsIgnoreCase("D")){//if none of the above keywords are pressed ,
                // Invalid Message is called and user is asked to type the input again .
                System.out.println("Invalid input");
                Input = VillainOptions.get(2);
//                rnd.nextInt(VillainOptions.size()-1+1)+0
            }


            if(Input.equalsIgnoreCase("W")){
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'E'){
                            if(i-1 < 0 ){
                                System.out.println("Start Again");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            } else if (board[i-1][j] == 'X') {
                                System.out.println("No Man's Land . Go Somewhere Else.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i-1][j] == 'V') {
                                System.out.println("Cannot Overlap Villains");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i-1][j] == 'E') {
                                System.out.println("Cannot Overlap Villains");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i-1][j] == 'S') {
                                System.out.println("Cannot Overlap Villains");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            board[i][j] =' ';

                            board[--i][j] ='E';
                            System.out.println("x: "+ i+" && y : "+j);

                            Villain2X.set(numberOfVillains,i);
                            firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                            SoundPlayer.Stopmusic(firstClip);
                            Villain2Y.set(numberOfVillains,j);
                            for(int z = 0 ; z < Villain2X.size();z++){
                                System.out.println("Villain :" + VillainIdentity + " has moved to coordinates  : "+Villain2X.get(numberOfVillains)+","+Villain2Y.get(numberOfVillains)+" and the number of villains right now is : " + numberOfVillains);
                            }
                        }
                    }
                }



                System.out.println(" ");
            }
            if(Input.equalsIgnoreCase("A")){
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'E'){
                            if(j-1 <0 ){
                                System.out.println("Start Again");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j-1] == 'X') {
                                System.out.println("No Man's Land . Go Somewhere Else.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j-1] == 'V') {
                                System.out.println("Cannot Overlap Villain.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j-1] == 'E') {
                                System.out.println("Cannot Overlap Villain.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j-1] == 'S') {
                                System.out.println("Cannot Overlap Villain.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            board[i][j] =' ';
                            board[i][--j] ='E';
                            System.out.println("x: "+ i+" && y : "+j);
                            firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                            SoundPlayer.Stopmusic(firstClip);
                            Villain2X.set(numberOfVillains,i);
                            Villain2Y.set(numberOfVillains,j);
                            for(int z = 0 ; z < Villain2X.size();z++){
                                System.out.println("Villain :" + VillainIdentity + " has moved to coordinates  : "+Villain2X.get(numberOfVillains)+","+Villain2Y.get(numberOfVillains)+" and the number of villains right now is : " + numberOfVillains);
                            }
                        }
                    }
                }



                System.out.println(" ");
            }

            if(Input.equalsIgnoreCase("S")){
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'E'){
                            if(i+1 == size ){
                                System.out.println("Start Again");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }else if (board[i+1][j] == 'X') {
                                System.out.println("No Man's Land . Go Somewhere Else.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'V') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'E') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'S') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            board[i][j] =' ';
                            board[++i][j] ='E';
                            System.out.println("x: "+ i+" && y : "+j);
                            firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                            SoundPlayer.Stopmusic(firstClip);
                            Villain2X.set(numberOfVillains,i);
                            Villain2Y.set(numberOfVillains,j);
                            for(int z = 0 ; z < Villain2X.size();z++){
                                System.out.println("Villain :" + VillainIdentity + " has moved to coordinates  : "+Villain2X.get(numberOfVillains)+","+Villain2Y.get(numberOfVillains)+" and the number of villains right now is : " + numberOfVillains);
                            }
                        }
                    }
                }



                System.out.println(" ");
            }



            if(Input.equalsIgnoreCase("D")){
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'E'){
                            if(j+1 == size ){
                                System.out.println("Start Again");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j+1] == 'X') {
                                System.out.println("No Man's Land . Go Somewhere Else.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }

                            else if (board[i+1][j] == 'V') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'E') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'S') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            board[i][j] =' ';
                            board[i][++j] ='E';
                            System.out.println("x: "+ i+" && y : "+j);
                            firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                            SoundPlayer.Stopmusic(firstClip);
                            Villain2X.set(numberOfVillains,i);
                            Villain2Y.set(numberOfVillains,j);
                            for(int z = 0 ; z < Villain2X.size();z++){
                                System.out.println("Villain :" + VillainIdentity + " has moved to coordinates  : "+Villain2X.get(numberOfVillains)+","+Villain2Y.get(numberOfVillains)+" and the number of villains right now is : " + numberOfVillains);
                            }
                        }
                    }
                }



                System.out.println(" ");
            }

//        checkPosition();
//            MarketcheckPosition();
//            LandcheckPosition();
//            printBoard();
//            System.out.println("  ");
            WinConditionVillain(VillainIdentity,numberOfVillains);
            CollisionVicinity(size,VillainIdentity,symbol);
            CollisionCheckPosition(VillainIdentity,size,numberOfVillains);
            VillainMove(size,'S',2,numberOfVillains);

        }
        if(VillainIdentity == 2 ){
            VillainOptions.add("W");
            VillainOptions.add("A");
            VillainOptions.add("S");
            VillainOptions.add("D");
            Random rnd = new Random();
            int rndChoice = rnd.nextInt(3-0+1)-0;
            String Input =VillainOptions.get(2);// use 2 mainly for going down
            while(!Input.equalsIgnoreCase("W") && !Input.equalsIgnoreCase("A") && !Input.equalsIgnoreCase("S") && !Input.equalsIgnoreCase("D")){//if none of the above keywords are pressed ,
                // Invalid Message is called and user is asked to type the input again .
                System.out.println("Invalid input");
                Input = VillainOptions.get(2);
            }


            if(Input.equalsIgnoreCase("W")){
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'S'){
                            if(i-1 < 0 ){
                                System.out.println("Start Again");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            } else if (board[i-1][j] == 'X') {
                                System.out.println("No Man's Land . Go Somewhere Else.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }

                            else if (board[i+1][j] == 'V') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'E') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'S') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            board[i][j] =' ';

                            board[--i][j] ='S';
                            System.out.println("x: "+ i+" && y : "+j);
                            Villain3X.set(numberOfVillains,i);
                            firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                            SoundPlayer.Stopmusic(firstClip);
                            Villain3Y.set(numberOfVillains,j);
                            for(int z = 0 ; z < Villain3X.size();z++){
                                System.out.println("Villain :" + VillainIdentity + " has moved to coordinates  : "+Villain3X.get(numberOfVillains)+","+Villain3Y.get(numberOfVillains)+" and the number of villains right now is : " + numberOfVillains);
                            }
                        }
                    }
                }



                System.out.println(" ");
            }
            if(Input.equalsIgnoreCase("A")){
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'S'){
                            if(j-1 <0 ){
                                System.out.println("Start Again");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j-1] == 'X') {
                                System.out.println("No Man's Land . Go Somewhere Else.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }

                            else if (board[i+1][j] == 'V') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'E') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'S') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            board[i][j] =' ';
                            board[i][--j] ='S';
                            System.out.println("x: "+ i+" && y : "+j);
                            firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                            SoundPlayer.Stopmusic(firstClip);
                            Villain3X.set(numberOfVillains,i);
                            Villain3Y.set(numberOfVillains,j);
                            for(int z = 0 ; z < Villain3X.size();z++){
                                System.out.println("Villain :" + VillainIdentity + " has moved to coordinates  : "+Villain3X.get(numberOfVillains)+","+Villain3Y.get(numberOfVillains)+" and the number of villains right now is : " + numberOfVillains);
                            }
                        }
                    }
                }



                System.out.println(" ");
            }

            if(Input.equalsIgnoreCase("S")){
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'S'){
                            if(i+1 == size ){
                                System.out.println("Start Again");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }else if (board[i+1][j] == 'X') {
                                System.out.println("No Man's Land . Go Somewhere Else.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }

                            else if (board[i+1][j] == 'V') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'E') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'S') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            board[i][j] =' ';
                            board[++i][j] ='S';
                            System.out.println("x: "+ i+" && y : "+j);
                            firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                            SoundPlayer.Stopmusic(firstClip);
                            Villain3X.set(numberOfVillains,i);
                            Villain3Y.set(numberOfVillains,j);
                            for(int z = 0 ; z < Villain3X.size();z++){
                                System.out.println("Villain :" + VillainIdentity + " has moved to coordinates  : "+Villain3X.get(numberOfVillains)+","+Villain3Y.get(numberOfVillains)+" and the number of villains right now is : " + numberOfVillains);
                            }
                        }
                    }
                }



                System.out.println(" ");
            }



            if(Input.equalsIgnoreCase("D")){
                for (int i = 0; i < row; i++) {
                    for(int j = 0 ; j < columns ; j++){
                        if(board[i][j] == 'S'){
                            if(j+1 == size ){
                                System.out.println("Start Again");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i][j+1] == 'X') {
                                System.out.println("No Man's Land . Go Somewhere Else.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }

                            else if (board[i+1][j] == 'V') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'E') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            else if (board[i+1][j] == 'S') {
                                System.out.println("Cannot Overlap Villains.");
                                VillainMove(size,symbol,VillainIdentity,numberOfVillains);
                            }
                            board[i][j] =' ';
                            board[i][++j] ='S';
                            System.out.println("x: "+ i+" && y : "+j);
                            firstClip= SoundPlayer.SpecifyPathAndOptions("C:\\RPGWalkExtended\\src\\videoplayback (7).wav",new SoundPlayer(),audioInput, firstClip, firstFile);
                            SoundPlayer.Stopmusic(firstClip);
                            Villain3X.set(numberOfVillains,i);
                            Villain3Y.set(numberOfVillains,j);
                            for(int z = 0 ; z < Villain3X.size();z++){
                                System.out.println("Villain :" + VillainIdentity + " has moved to coordinates  : "+Villain3X.get(numberOfVillains)+","+Villain3Y.get(numberOfVillains)+" and the number of villains right now is : " + numberOfVillains);
                            }
                        }
                    }
                    }
                }



                System.out.println(" ");
            }

//        checkPosition();
//            MarketcheckPosition();
//            LandcheckPosition();
//            printBoard();
//            System.out.println("  ");
            WinConditionVillain(VillainIdentity,numberOfVillains);
            CollisionVicinity(size,VillainIdentity,symbol);
            CollisionCheckPosition(VillainIdentity,size,numberOfVillains);
            ++turn;
        Logic.printHeader("Turn "+turn+ " Begins !!!!! ");
            SpawnEnemies(turn);
            numberOfVillains = numberOfVillains;
            RecoverHeroes(size,turn);
          if(Logic.HeroList.size() == 3){
              System.out.println("Hero 1 move begins ");
            userMove(size,'H', 0);
          }
          else if(Logic.HeroList.size() == 2){
              if(Logic.DeadHeroIdentity.get(0)== 0){
                  userMove(size,'W', 1);
                  System.out.println("It enters here wv");
              }
              else if(Logic.DeadHeroIdentity.get(0)== 1){
                  userMove(size,'H', 0);
                  System.out.println("It enters here hv");
              }
              else if(Logic.DeadHeroIdentity.get(0)== 2){
                  userMove(size,'H', 0);
                  System.out.println("It enters here wv");
              }

          }
          else if(Logic.HeroList.size() == 1){

              if(Logic.DeadHeroIdentity.get(0)== 0){
                  if(Logic.DeadHeroIdentity.get(0)== 1){
                      userMove(size,'G', 2);
                      System.out.println("It enters here wvv");
                  }
              }
              if(Logic.DeadHeroIdentity.get(0)== 1){
                  if(Logic.DeadHeroIdentity.get(0)== 0){
                      userMove(size,'G',2);
                      System.out.println("It enters here wvv");
                  }
              }
              if(Logic.DeadHeroIdentity.get(0)== 1){
                  if(Logic.DeadHeroIdentity.get(0)== 2){
                      userMove(size,'H', 0);
                      System.out.println("It enters here gvv");
                  }              }
              if(Logic.DeadHeroIdentity.get(0)== 2){
                  if(Logic.DeadHeroIdentity.get(0)== 1){
                      userMove(size,'H', 0);
                      System.out.println("It enters here gvv");

                  }              }
              if(Logic.DeadHeroIdentity.get(0)== 0){
                  if(Logic.DeadHeroIdentity.get(0)== 2){
                      userMove(size,'W', 1);
                      System.out.println("It enters here hvv");

                  }              }
              if(Logic.DeadHeroIdentity.get(0)== 2){
                  if(Logic.DeadHeroIdentity.get(0)== 0){
                      userMove(size,'W', 1);
                      System.out.println("It enters here hvv");

                  }              }

          }

        }

// recovering heroes after 1 round
    private void RecoverHeroes(int size,int turn) {
        if(turn%8 == 0){

        if(!Logic.FaintedHeroList.isEmpty()){
                                for (int i = 0; i < Logic.FaintedHeroList.size(); i++) {
                        System.out.println("Heroes Fainted" + Logic.FaintedHeroList.get(i).CharacterName);
                Logic.FaintedHeroList.get(i).HP = Logic.FaintedHeroList.get(i).MaxHP;
                RespawnHeroes(size,Logic.FaintedHeroList.get(i).lane);
                Logic.HeroList.add(Logic.FaintedHeroList.get(i));
                Logic.FaintedHeroList.remove(i);

        }}
    }
    }
//allows the hero to respawn again at the nexus
    private void RespawnHeroes(int size,int lane) {
        if(lane == 0){
            if(Hero1X > 8 && Hero1Y > 8) {
                Random rnd = new Random();
                int randomNexus = rnd.nextInt(1 + 1);
                Hero1Y = randomNexus;
                Hero1X = size - 1;
                System.out.println("Hero Values : " + Hero1X + " , " + Hero1Y);

                board[Hero1X][Hero1Y] = 'H';
            }
            }


        if(lane == 1 ){
            if(Hero2X > 8 && Hero2Y > 8) {
                Random rnd = new Random();
                int randomNexus = rnd.nextInt(1 + 1);
                Hero2Y = randomNexus;
                Hero2X = size - 1;
                System.out.println("Hero Values : " + Hero2X + " , " + Hero2Y);

                board[Hero2X][Hero2Y] = 'W';
            }

        }if(lane == 2){
            if(Hero3X > 8 && Hero3Y > 8) {
                Random rnd = new Random();
                int randomNexus = rnd.nextInt(1 + 1);
                Hero3Y = randomNexus;
                Hero3X = size - 1;
                System.out.println("Hero Values : " + Hero3X + " , " + Hero3Y);

                board[Hero3X][Hero3Y] = 'G';
            }

        }
    }
// spawning enemies after every 8 turns
    public int SpawnEnemies(int turn){

        if(turn % 8  == 0){
           ++numberOfVillains ;
            Logic.printHeader("Round "+numberOfVillains+ "Begins  !!!!");
            Logic.GenerateEnemies(Logic.NumberOfHeroes);
            ArrayList<Villains> EnemyList = Logic.randomVillain(Logic.EnemyList);
            System.out.println("Number of Demon : " + EnemyList.size());
            for (int i = 0; i < EnemyList.size() + 1; i++) {
                if (i == 0) {
                    EnemyList.get(i).lane = i;
                    System.out.println("Demon "+ EnemyList.get(i).CharacterName+" Enters the Lane  "+i);
                    Logic.newBoard.AssignVillains(Logic.size, 'V', i+1,numberOfVillains);

                }
                else if (i == 1) {
                    EnemyList.get(i).lane = i;
                    System.out.println("Demon "+ EnemyList.get(i).CharacterName+" Enters the Lane  "+i);

                    Logic.newBoard.AssignVillains(Logic.size, 'E', i+1,numberOfVillains);
                    ;
                } else if (i == 2) {
                    EnemyList.get(i).lane = i;
                    System.out.println("Demon "+ EnemyList.get(i).CharacterName+" Enters the Lane  "+i);
                    Logic.newBoard.AssignVillains(Logic.size, 'S', i+1,numberOfVillains);
                }
            }

        }
        return numberOfVillains;
    }
    // addding nexuses on the board

public void addNexus(){

    NexusX.add(0);
    NexusY.add(0);
    NexusX.add(0);
    NexusY.add(1);
    NexusX.add(0);
    NexusY.add(3);
    NexusX.add(0);
    NexusY.add(4);
    NexusX.add(0);
    NexusY.add(6);
    NexusX.add(0);
    NexusY.add(7);

    NexusX.add(7);
    NexusY.add(0);

    NexusX.add(7);
    NexusY.add(1);

    NexusX.add(7);
    NexusY.add(4);

    NexusX.add(7);
    NexusY.add(3);

    NexusX.add(7);
    NexusY.add(6);

    NexusX.add(7);
    NexusY.add(7);
}

    public void UserChoiceMove(int wholeRow , int wholeColumn,char symbol){
        int newrow = 0 , column = 0 ;
        while(true){

        System.out.println("Enter the place you want to place on the board ");
        System.out.println("Enter the row ");
        newrow = sc.nextInt();
        System.out.println("Enter the column ");
        column = sc.nextInt();
        if(newrow< 0 || column < 0 || newrow> wholeRow || column > wholeColumn)
        {
            System.out.println("Rows or Columns are out of bounds ");
        }
//        else if(board[newrow][column] != 'M'){
//            System.out.println("Someone is on that tile ");
//
//        }
        else  {
            break;
        }


    }
        board[newrow][column] = symbol;


        retainprintBoard();

    }
public void printBoard() {

//    for (int i = 0; i < Rows; i++) {
//        System.out.print("----------");
//    }
//
//    for (int i = 0; i < row; i++) {
//        for (int j= 0; j< columns; j++) {
//           board[i][j] = '_';
//        }
//    }

    for (int i = 0; i < row; i++) {

        System.out.print("| ");
        for(int j = 0 ; j < columns ; j++){
//            board[marketX.get(i)][marketY.get(i)]='M';
//            board[LandX.get(i)][LandY.get(i)]='L';
            System.out.print(board[i][j]);
            System.out.print( " | ");


        }
     System.out.println("");
//        for (int k = 0; k < Rows; k++) {
//            System.out.print("----------");
    }

}
//printing the whole board with different values
    public void retainprintBoard() {

//    for (int i = 0; i < Rows; i++) {
//        System.out.print("----------");
//    }
//
//    for (int i = 0; i < row; i++) {
//        for (int j= 0; j< columns; j++) {
//           board[i][j] = '_';
//        }
//    }

        for(int i =0 ; i< LandX.size();i++){
                board[LandX.get(i)][LandY.get(i)]= 'L';
            }

        for(int i =0 ; i< marketX.size();i++){
            board[marketX.get(i)][marketY.get(i)]= 'M';
        }
        for(int i =0 ; i< CaveX.size();i++){
            board[CaveX.get(i)][CaveY.get(i)]= 'C';
        }
        for(int i =0 ; i< KoulouX.size();i++){
            board[KoulouX.get(i)][KoulouY.get(i)]= 'K';
        }
        for(int i =0 ; i< BushX.size();i++){
            board[BushX.get(i)][BushY.get(i)]= 'B';
        }

        for(int i =0 ; i< NexusX.size();i++){
            board[NexusX.get(i)][NexusY.get(i)]= 'N';
        }
if(!(Hero1X>8 && Hero1Y>8 )){
    board[Hero1X][Hero1Y] = 'H';
}
        if(!(Hero2X>8 && Hero2Y>8 )){
            board[Hero2X][Hero2Y] = 'W';
        }
        if(!(Hero3X>8 && Hero3Y>8 )){
            board[Hero3X][Hero3Y] = 'G';
        }
        for(int i = 0 ; i< Villain1X.size();i++){
            if(!Villain1X.isEmpty() &&!Villain1Y.isEmpty()){
                System.out.println("Villain x : "+Villain1X.get(i)+ ", Villain y : "+Villain1Y.get(i));
if(Villain1X.get(i)<8 && Villain1Y.get(i)<8  ){
    board[Villain1X.get(i)][Villain1Y.get(i)] = 'V';
}
            }
        }
        for(int i = 0 ; i< Villain2X.size();i++){
            if(!Villain2X.isEmpty() &&!Villain2Y.isEmpty() ){
                System.out.println("Villain x : "+Villain2X.get(i)+ ", Villain y : "+Villain2Y.get(i));
                if(Villain2X.get(i)<8 && Villain2Y.get(i)<8  ){
                    board[Villain2X.get(i)][Villain2Y.get(i)] = 'E';
                }
            }
        }
        for(int i = 0 ; i< Villain3X.size();i++){
            if(!Villain3X.isEmpty() &&!Villain3Y.isEmpty() ){
                System.out.println("Villain x : "+Villain3X.get(i)+ ", Villain y : "+Villain3Y.get(i));
                if(Villain3X.get(i)<8 && Villain3Y.get(i)<8  ){
                    board[Villain3X.get(i)][Villain3Y.get(i)] = 'S';
                }
            }
        }
        for (int i = 0; i < row; i++) {

            System.out.print("| ");
            for(int j = 0 ; j < columns ; j++){
//            board[marketX.get(i)][marketY.get(i)]='M';
//            board[LandX.get(i)][LandY.get(i)]='L';
                System.out.print(board[i][j]);
                System.out.print( " | ");


            }
            System.out.println("");
//        for (int k = 0; k < Rows; k++) {
//            System.out.print("----------");
        }

    }
//assigning villains on the board
    public void AssignVillains(int size, char symbol, int LaneChoosen,int numberOfVillains) {
        Random rand = new Random();
        if(LaneChoosen == 1){
            int x =0;
            System.out.println("This Lane Chosen");
            int y = rand.nextInt((int) ((Math.floor(size/ 3) / 2)) - 0 + 1) + 0;
            board[x][y] = symbol;
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'V'){

                        Villain1X.add(numberOfVillains,i);
                        Villain1Y.add(numberOfVillains,j);
                    }
                }
            }
        }
        else if(LaneChoosen == 2){
            int x =0;

            System.out.println("This Lane Choosen");
            int y = rand.nextInt((int) ((Math.floor(size / 3) * 2)) - 3 + 1) + 3;
            board[x][y] = symbol;
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'E'){

                        Villain2X.add(numberOfVillains,i);
                        Villain2Y.add(numberOfVillains,j);
                    }
                }}
        }
        else if(LaneChoosen == 3){
            int x =0;

            System.out.println("This Lane Choosen");
            int y = rand.nextInt((int) (Math.floor(size/ 3) * 4 - 1) - 6 + 1) + 6;
            board[x][y] = symbol;
            for (int i = 0; i < row; i++) {
                for(int j = 0 ; j < columns ; j++){
                    if(board[i][j] == 'S'){

                        Villain3X.add(numberOfVillains,i);
                        Villain3Y.add(numberOfVillains,j);
                    }
                }}
        }

//        checkPosition();



    }
}
