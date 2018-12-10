package edu.colostate.cs.cs414.StringCheese.src.BusinessLayer;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Iterator;

public class Rook extends ChessPiece {

    private HashSet<String> legalMoves;
    HashSet<String> nextmove = new HashSet<String>();
    HashSet<String> sidemove = new HashSet<String>();
    HashSet<String> prevmove = new HashSet<String>();
    String nxtpos="";

    public Rook(ChessBoard board, Color color) {
        super(board, color);
        legalMoves = new HashSet<String>();
    }

    public HashSet<String> legalMoves(){
        legalMoves.clear();
        String position = getPosition();




        if (board.outerRing.contains(position)) {
            int index = board.outerRing.indexOf(position);
            nxtpos = position;
            // String sidepos=position;
            //try {
            prevmove = getPrevBackward(position, this.getColor());
            if (!prevmove.isEmpty()) {
                legalMoves.addAll(prevmove);
            }


            if (index >= 0 && index < 6) {
                if (index == 5) {
                    LinkedHashSet<String> temp = new LinkedHashSet<>();
                    temp.add("b6");
                    temp.add("c6");
                    temp.add("d6");
                    temp.add("e6");
                    temp.add("f6");
                    temp.add("g6");

                  //  sidemove.addAll(temp);
                    sidemove=outerRingValidSideMoves(temp);
                    legalMoves.addAll(sidemove);
                } else {
                    //try {
                    sidemove = getSideways(position, this.getColor());
                    sidemove = getSideways(position, this.getColor());
                    if (!sidemove.isEmpty())
                        legalMoves.addAll(sidemove);
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                }
                int i = index;
                outterRingValidNextMoves(index,12);
               /* while (i < 12) {
                    //try {
                    nextmove = getNextForward(nxtpos, this.getColor());
                    if (nextmove.isEmpty()) break;
                    Iterator itr = nextmove.iterator();
                    nxtpos = itr.next().toString();
                    legalMoves.addAll(nextmove);
                    if (board.getPiece(nxtpos) != null)
                        break;
                    //} catch (IllegalPositionException ex) {
                    //    Logger. getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    i++;
                }*/

            }
            if (index >= 6 && index < 12) {

                if (index == 11) {
                   LinkedHashSet<String> temp = new LinkedHashSet<>();
                    temp.add("f6");
                    temp.add("f5");
                    temp.add("f4");
                    temp.add("f3");
                    temp.add("f2");
                    temp.add("f1");
                   // sidemove.addAll(temp);
                    //fixme I am in sideMoves down Method
                   /* for(String moves:temp)
                    {
                        if(board.getPiece(moves)==null)
                            sidemove.add(moves);
                        else if(board.getPiece(moves).getColor()!=this.getColor())
                        {
                            sidemove.add(moves);
                            break;
                        }
                        else
                            break;
                    }*/
                    sidemove=outerRingValidSideMoves(temp);
                    legalMoves.addAll(sidemove);
                } else {
                    //try {
                    sidemove = getSideways(position, this.getColor());
                    if (!sidemove.isEmpty()) {
                        legalMoves.addAll(sidemove);
                    }
                    //    } catch (IllegalPositionException ex) {
                    //        Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //    }
                }
                int i = index;
                outterRingValidNextMoves(index,18);
              /*  while (i < 18) {
                    //try {
                    nextmove = getNextForward(nxtpos, this.getColor());
                    if (nextmove.isEmpty()) break;
                    Iterator itr = nextmove.iterator();
                    nxtpos = itr.next().toString();
                    legalMoves.addAll(nextmove);
                    if (board.getPiece(nxtpos) != null)
                        break;
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    i++;
                }*/

            }
            if (index >= 12 && index < 18) {

                if (index == 17) {
                    LinkedHashSet<String> temp = new LinkedHashSet<>();
                    temp.add("f2");
                    temp.add("e2");
                    temp.add("d2");
                    temp.add("c2");
                    temp.add("b2");
                    temp.add("a2");
                 //   sidemove.addAll(temp);
                    //fixme I am in sideMoves down Method
                 /*   for(String moves:temp)
                    {
                        if(board.getPiece(moves)==null)
                            sidemove.add(moves);
                        else if(board.getPiece(moves).getColor()!=this.getColor())
                        {
                            sidemove.add(moves);
                            break;
                        }
                        else
                            break;
                    }*/
                    sidemove=outerRingValidSideMoves(temp);
                    legalMoves.addAll(sidemove);
                } else {
                    //try {
                    sidemove = getSideways(position, this.getColor());
                    if (!sidemove.isEmpty())
                        legalMoves.addAll(sidemove);
                    //    } catch (IllegalPositionException ex) {
                    //        Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //    }
                    /*for(String move:sidemove)
                    {
                        System.out.println(move);
                    }
                    */
                }
                int i = index;
                outterRingValidNextMoves(index,24);
              /*  while (i < 24) {
                    //try {

                    nextmove = getNextForward(nxtpos, this.getColor());
                    if (nextmove.isEmpty()) break;
                    Iterator itr = nextmove.iterator();
                    nxtpos = itr.next().toString();
                    legalMoves.addAll(nextmove);
                    if (board.getPiece(nxtpos) != null)
                        break;
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    i++;
                }*/

            }
            if (index >= 18 && index < 24) {

                if (index == 23) {
                    LinkedHashSet<String> temp = new LinkedHashSet<>();
                    temp.add("b2");
                    temp.add("b3");
                    temp.add("b4");
                    temp.add("b5");
                    temp.add("b6");
                    temp.add("b7");
                   // sidemove.addAll(temp);
                    //fixme I am in sideMoves down Method
                  /*  for(String moves:temp)
                    {
                        if(board.getPiece(moves)==null)
                            sidemove.add(moves);
                        else if(board.getPiece(moves).getColor()!=this.getColor())
                        {
                            sidemove.add(moves);
                            break;
                        }
                        else
                            break;
                    }*/
                    sidemove=outerRingValidSideMoves(temp);

                    legalMoves.addAll(sidemove);
                } else {
                    //try {
                    sidemove = getSideways(position, this.getColor());

                    if (!sidemove.isEmpty())
                        legalMoves.addAll(sidemove);
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                }
                int i = index;
                outterRingValidNextMoves(index,30);
               /* while (i < 30) {
                    //try {
                    nextmove = getNextForward(nxtpos, this.getColor());
                    if (nextmove.isEmpty())
                        break;
                    Iterator itr = nextmove.iterator();
                    nxtpos = itr.next().toString();
                    legalMoves.addAll(nextmove);
                    if (board.getPiece(nxtpos) != null)
                        break;
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    i++;
                }*/

            }

            return legalMoves;
        } else if (board.innerRing.contains(position))
        {
            nxtpos = position;
            String sidepos = position;
            int index = board.innerRing.indexOf(position);
            //try {
            prevmove = getPrevBackward(position, this.getColor());
            if (!prevmove.isEmpty()) {
                legalMoves.addAll(prevmove);
            }
            sidemove = getSideways(position, this.getColor());
            if (!sidemove.isEmpty()) {
                legalMoves.addAll(sidemove);
            }
            //   } catch (IllegalPositionException ex) {
            //       Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
            //   }
            if (index >= 0 && index < 4) {
                int j = index;
                outterRingValidNextMoves(index,4);
                /*while (j < 4) {
                    //try {
                    nextmove = getNextForward(nxtpos, this.getColor());
                    if (nextmove.isEmpty()) break;
                    Iterator itr = nextmove.iterator();
                    nxtpos = itr.next().toString();

                    legalMoves.addAll(nextmove);

                    if (board.getPiece(nxtpos) != null)
                        break;
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    j++;
                }*/
                if(legalMoves.contains("b6") && board.getPiece(nxtpos)==null)
                {
                    if(board.getPiece("b7")==null || board.getPiece("b7").getColor()!=this.getColor()) {
                        legalMoves.add("b7");
                    }
                }

            }
            if (index >= 4 && index < 8) {
                int j = index;
                outterRingValidNextMoves(index,8);
              /*  while (j < 8)
                {
                    //try {
                    nextmove = getNextForward(nxtpos, this.getColor());
                    if (nextmove.isEmpty()) break;
                    Iterator itr = nextmove.iterator();
                    nxtpos = itr.next().toString();

                    legalMoves.addAll(nextmove);
                    if (board.getPiece(nxtpos) != null)
                        break;
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    j++;
                }*/
                if(legalMoves.contains("f6") &&  board.getPiece(nxtpos)==null)
                {
                    if(board.getPiece("g6")==null || board.getPiece("g6").getColor()!=this.getColor()) {
                        legalMoves.add("g6");
                    }
                }

            }
            if (index >= 8 && index < 12) {
                int j = index;
                outterRingValidNextMoves(index,12);
                /*while (j < 12) {
                    //try {
                    nextmove = getNextForward(nxtpos, this.getColor());
                    if (nextmove.isEmpty()) break;
                    Iterator itr = nextmove.iterator();
                    nxtpos = itr.next().toString();


                    legalMoves.addAll(nextmove);
                    if (board.getPiece(nxtpos) != null)
                        break;
                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    j++;
                }*/
                if(legalMoves.contains("f2") &&  board.getPiece(nxtpos)==null)
                {
                    if(board.getPiece("f1")==null || board.getPiece("f1").getColor()!=this.getColor()) {
                        legalMoves.add("f1");
                    }
                }

            }
            if (index >= 12 && index <= 15) {
                int j = index;
                String str=nxtpos;
                outterRingValidNextMoves(index,16);






               /* while (j < 16) {
                    //try {
                    //  System.out.println(nxtpos);
                    nextmove = getNextForward(nxtpos, this.getColor());
                    if (nextmove.isEmpty()) break;
                    Iterator itr = nextmove.iterator();
                    nxtpos = itr.next().toString();

                    //  System.out.println(nxtpos);
                    legalMoves.addAll(nextmove);
                    if (board.getPiece(nxtpos) != null)
                        break;

                    //} catch (IllegalPositionException ex) {
                    //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
                    //}
                    j++;
                }*/
                if(legalMoves.contains("b2") &&  board.getPiece(nxtpos)==null)
                {
                    if(board.getPiece("a2")==null || board.getPiece("a2").getColor()!=this.getColor()) {
                        legalMoves.add("a2");
                    }
                }
            }
                return legalMoves;
            } else {
                System.out.println("Rook is not on board at position " + position + " something went wrong");
               // System.exit(1);
                return legalMoves;

            }

    }
    /*
    HashSet<String> getNextValid(String nxtpos,HashSet<String> nextmove,Color color)
    {
        HashSet<String> validMoves = null;
        nextmove = getNextForward(nxtpos, this.getColor());
        if (nextmove.isEmpty())
        {
            return validMoves;
        }
        Iterator itr = nextmove.iterator();
        nxtpos = itr.next().toString();

    }*/
    HashSet<String> outerRingValidSideMoves(LinkedHashSet<String> temp)
    {
        HashSet<String> sides=new HashSet<>();
        for(String moves:temp)
        {
            if(board.getPiece(moves)==null) {
                sides.add(moves);
            }
            else if(board.getPiece(moves).getColor()!=this.getColor())
            {
                sides.add(moves);
                break;
            }
            else
                break;
        }
        return sides;

    }
    void outterRingValidNextMoves(int start,int end)
    {
        nxtpos=getPosition();
        while (start < end) {
            //try {
            //  System.out.println(nxtpos);

            nextmove = getNextForward(nxtpos, this.getColor());
            if (nextmove.isEmpty()) break;
            Iterator itr = nextmove.iterator();
            nxtpos = itr.next().toString();

            //  System.out.println(nxtpos);
            legalMoves.addAll(nextmove);
            if (board.getPiece(nxtpos) != null)
                break;

            //} catch (IllegalPositionException ex) {
            //    Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
            //}
            start++;
        }


    }

    public String toString(){
        if (getColor() == Color.White) { return "\u2656"; }
        else return "\u265C";
    }
}
