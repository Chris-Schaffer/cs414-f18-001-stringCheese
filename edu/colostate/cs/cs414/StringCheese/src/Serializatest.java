package edu.colostate.cs.cs414.StringCheese.src;

import java.io.*;

public class Serializatest  {

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        String filename = "game.ser";
        Game temp = new Game(1, "chris", "meet", "today", null, null);
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(temp);
        //ByteArrayOutputStream bt=new ByteArrayOutputStream();
        System.out.println("Serialized Object: " + out);
        FileInputStream f1 = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(f1);

        Game meet = null;
        meet = (Game) in.readObject();
        System.out.println(" Deseralized Object ");
        System.out.println("Game id : " + meet.getGameID());
        System.out.println("Game host : " + meet.getHost());
        System.out.println("Game invitee : " + meet.getInvitee());
        System.out.println("Game Start time : " + meet.getStartTime());
        System.out.println("Game end Time : " + meet.getEndTime());
        System.out.println("Game Result : " + meet.getResult());


    }

    }
