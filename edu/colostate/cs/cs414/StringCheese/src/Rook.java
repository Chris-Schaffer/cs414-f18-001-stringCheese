package edu.colostate.cs.cs414.StringCheese.src;

import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rook extends ChessPiece {

    private HashSet<String> legalMoves;

    public Rook(ChessBoard board, Color color) {
        super(board, color);
        legalMoves = new HashSet<String>();
    }

    public HashSet<String> legalMoves(){
        String position = getPosition();
        
        HashSet<String> nextmove=new HashSet<String>();
        HashSet<String> sidemove=new HashSet<String>();
        HashSet<String> prevmove=new HashSet<String>();
        
        
        
        if(board.outerRing.contains(position))
        {
            int index=board.outerRing.indexOf(position);
             //try {
                    prevmove=getPrevBackward(position);
                    if(!prevmove.isEmpty()) {
                        legalMoves.addAll(prevmove);
                    }
               // } catch (IllegalPositionException ex) {
               //     Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                //}
            String nxtpos=position;
            String sidepos=position;
            if(index>=0 && index<6 )
            {
                if(index==5)
                {
                    HashSet<String> temp=new HashSet<>();
                    temp.add("b6");
                    temp.add("c6");
                    temp.add("d6");
                    temp.add("e6");
                    temp.add("f6");
                    temp.add("g6");
                    sidemove.addAll(temp);
                }
                else
                {
                    //try {
                            sidemove=getSideways(position);
                            if(!sidemove.isEmpty())
                                legalMoves.addAll(sidemove);
                        //} catch (IllegalPositionException ex) {
                        //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                        //}
                }
                int i=index;
                while(i<13)
                {
                    //try {
                         nextmove=getNextForward(nxtpos);
                         if(nextmove.isEmpty())break;
                        Iterator itr=nextmove.iterator();
                        nxtpos=itr.next().toString();
                        legalMoves.addAll(nextmove);
                    //} catch (IllegalPositionException ex) {
                    //    Logger. getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    i++;
                }
                
            }
            if(index>=6 && index<12)
            {
                 
                 if(index==11)
                 {
                     HashSet<String> temp=new HashSet<>();
                     temp.add("f1");
                     temp.add("f2");
                     temp.add("f3");
                     temp.add("f4");
                     temp.add("f5");
                     temp.add("f6");
                     sidemove.addAll(temp);
                }
                 else
                {
                    //try {
                            sidemove=getSideways(position);
                            if(!sidemove.isEmpty()) {
                                legalMoves.addAll(sidemove);
                            }
                    //    } catch (IllegalPositionException ex) {
                    //        Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //    }
                }
                int i=index;
                while(i<19)
                {
                    //try {
                        nextmove=getNextForward(nxtpos);
                        if(nextmove.isEmpty())break;
                        Iterator itr=nextmove.iterator();
                        nxtpos=itr.next().toString();
                        legalMoves.addAll(nextmove);
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    i++;
                }
                
            }
            if(index>=12 && index<18)
            {
                
                if(index==17)
                {
                    HashSet<String> temp=new HashSet<>();
                    temp.add("f2");
                    temp.add("e2");
                    temp.add("d2");
                    temp.add("c2");
                    temp.add("b2");
                    temp.add("a2");
                    sidemove.addAll(temp);
                }
                else
                {
                    //try {
                            sidemove=getSideways(position);
                            if(!sidemove.isEmpty())
                                legalMoves.addAll(sidemove);
                    //    } catch (IllegalPositionException ex) {
                    //        Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //    }
                }
                int i=index;
                while(i<25)
                {
                    //try {
                        
                        nextmove=getNextForward(nxtpos);
                        if(nextmove.isEmpty())break;
                        Iterator itr=nextmove.iterator();
                        nxtpos=itr.next().toString();
                        legalMoves.addAll(nextmove);
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    i++;
                }
               
            }
            if(index>=18 && index<24)
            {
                
                if(index==23)
                {
                    HashSet<String> temp=new HashSet<>();
                    temp.add("b2");
                    temp.add("b3");
                    temp.add("b4");
                    temp.add("b5");
                    temp.add("b6");
                    temp.add("b7");
                    sidemove.addAll(temp);
                }
                else
                {
                    //try {
                            sidemove=getSideways(position);
                            
                           if(!sidemove.isEmpty())
                                legalMoves.addAll(sidemove);
                        //} catch (IllegalPositionException ex) {
                        //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                        //}
                }
                int i=index;
                while(i<31)
                {
                    //try {
                        nextmove=getNextForward(nxtpos);
                        if(nextmove.isEmpty())break;
                        Iterator itr=nextmove.iterator();
                        nxtpos=itr.next().toString();
                        legalMoves.addAll(nextmove);
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    i++;
                }
                
            }
           
           return legalMoves;
        }
        else if(board.innerRing.contains(position))
        {
            String nxtpos=position;
            String sidepos=position;
            int index=board.innerRing.indexOf(position);
             //try {
                    prevmove=getPrevBackward(position);
                    if(!prevmove.isEmpty()) {
                        legalMoves.addAll(prevmove);
                    }
                    sidemove=getSideways(position);
                    if(!sidemove.isEmpty())
                    {
                       legalMoves.addAll(sidemove);
                    }
             //   } catch (IllegalPositionException ex) {
             //       Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
             //   }
            if(index>=0 && index<4)
            {
                int j=0;
                while(j<=4)
                {
                    //try {
                        nextmove=getNextForward(nxtpos);
                        if(nextmove.isEmpty())break;
                        Iterator itr=nextmove.iterator();
                        nxtpos=itr.next().toString();
                        legalMoves.addAll(nextmove);
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    j++;
                }
                
            }
            if(index>=4 && index<8)
            {
                int j=0;
                while(j<=4)
                {
                    //try {
                        nextmove=getNextForward(nxtpos);
                        if(nextmove.isEmpty())break;
                        Iterator itr=nextmove.iterator();
                        nxtpos=itr.next().toString();
                        legalMoves.addAll(nextmove);
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    j++;
                }
            }
            if(index>=8 && index<11)
            {
                int j=0;
                while(j<=4)
                {
                    //try {
                        nextmove=getNextForward(nxtpos);
                        if(nextmove.isEmpty())break;
                        Iterator itr=nextmove.iterator();
                        nxtpos=itr.next().toString();
                        
                        legalMoves.addAll(nextmove);
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    j++;
                }
                
            }
            if(index>=11 && index<15)
            {
                int j=0;
                while(j<=4)
                {
                    //try {
                        nextmove=getNextForward(nxtpos);
                        if(nextmove.isEmpty())break;
                        Iterator itr=nextmove.iterator();
                        nxtpos=itr.next().toString();
                        legalMoves.addAll(nextmove);
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    j++;
                }
            }
            return legalMoves;
        }
       else{
            System.out.println("Rook is not on board at position " + position + " something went wrong");
            System.exit(1);

            return legalMoves;
        }

        
       
        
    }

    public String toString(){
        if (getColor() == Color.White) { return "\u2656"; }
        else return "\u265C";
    }
}
