package edu.colostate.cs.cs414.StringCheese.src;


import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Invitation {

    private String senderName;
    private int gameID;

    //need gameID to send in email
    public Invitation(String senderName, int gameID) {
        this.gameID = gameID;
        this.senderName = senderName;
    }

    //working
    private boolean sendEmail(String[] recipientEmailAddresses) {
        //String to = "chris@yahoo.com";
        //Recipient's email
        InternetAddress[] recipients = new InternetAddress[recipientEmailAddresses.length];
        for(int i=0; i<recipients.length;i++){
            try {
                recipients[i]=new InternetAddress(recipientEmailAddresses[i]);
            } catch (AddressException e) {
                System.out.println("Email address " + recipientEmailAddresses[i] + " is invalid");
                e.printStackTrace();
                return false;
            }
        }

        //Senders email
        String from = "info.rollerball@gmail.com";
        String password = "stringcheese";
        //server sending email from
        String host = "smtp.gmail.com";
        String port = "587";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        //get defualt Session object
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            //Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            //Set From: header field
            message.setFrom(new InternetAddress(from));
            //Set To: header field
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setRecipients(Message.RecipientType.TO, recipients);
            //Set Subject: header field
            message.setSubject(senderName + " has invited you to a game of rollerball chess!");
            //Set Body of message
            message.setText("This is an automated message from rollerball.\n\n" +
                    senderName + " would like to play a game with you.\n\n" +
                    "To accept the invitation please log in and enter the Game_ID: " + gameID + " under JOIN GAME tab");
            Transport.send(message);
            System.out.println("Invitation sent.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean sendInvitation(ArrayList<User> users){
        String[] emails = new String[users.size()];
        for(int i = 0; i< users.size(); i++){
            emails[i] = users.get(i).getEmail();
        }
        return sendEmail(emails);
    }

    public static void main(String args[]){
        Invitation invitation = new Invitation("",2);
        invitation.sendEmail(new String[0]);
    }

}

