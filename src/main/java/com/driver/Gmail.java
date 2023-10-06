package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gmail extends Email {
    public class mails{
        Date date;
        String sender;
        String message;

        mails(Date date,String sender,String message){
            this.date=date;
            this.sender=sender;
            this.message=message;
        }

    }

    int inboxCapacity; //maximum number of mails inbox can store

    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;


    }

    public void setInboxCapacity(int inboxCapacity) {
        this.inboxCapacity = inboxCapacity;
    }
    public List<mails>inbox=new ArrayList<>();
    public List<mails>trash=new ArrayList<>();

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        mails mail=new mails(date,sender,message);
        inbox.add(mail);
        inboxCapacity--;
        if(inboxCapacity==0){
            trash.add(inbox.get(0));
            inbox.remove(0);
            inboxCapacity+=1;
        }



    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i=0;i<inbox.size();i++){
            mails curr=inbox.get(i);
            if(curr.message.equals(message)){
                trash.add(curr);
                inbox.remove(i);
            }
        }


    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.size()==0){
            return null;
        }
        mails curr=inbox.get(inbox.size()-1);
        return curr.message;

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.size()==0)
            return null;
        mails curr=inbox.get(0);
        return curr.message;

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count=0;
        for(int i=0;i<inbox.size();i++){
            mails curr=inbox.get(i);
            if(curr.date.before(end)  && curr.date.after(start)){
                count++;
            }
        }
        return count;

    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        for(int i=0;i<trash.size();i++){
            trash.remove(i);
        }

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
