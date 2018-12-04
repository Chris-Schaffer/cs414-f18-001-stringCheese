package edu.colostate.cs.cs414.StringCheese.src;

import java.util.HashSet;
import java.util.LinkedHashSet;
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
       legalMoves.clear();
        String position = getPosition();

        HashSet<String> nextmove = new HashSet<String>();
        HashSet<String> sidemove = new HashSet<String>();
        HashSet<String> prevmove = new HashSet<String>();


        if (board.outerRing.contains(position)) {
            int index = board.outerRing.indexOf(position);
            String nxtpos = position;
            // String sidepos=position;
            //try {
            prevmove = getPrevBackward(position, this.getColor());
            if (!prevmove.isEmpty()) {
                legalMoves.addAll(prevmove);
            }
            // } catch (IllegalPositionException ex) {
            //     Logger.getLogger(Rook.class.getName()).log(Level.SEVERE, null, ex);
            //}

            if (index >= 0 && index < 6) {
                if (index == 5) {
                    LinkedHashSet<String> temp = new LinkedHashSet<>();
                    temp.add("b6");
                    temp.add("c6");
                    temp.add("d6");
                    temp.add("e6");
                    temp.add("f6");
                    temp.add("g6");
                    for(String moves:temp)
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
                    }
                  //  sidemove.addAll(temp);

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
                while (i < 12) {
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
                }

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
                    for(String moves:temp)
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
                    }
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
                while (i < 18) {
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
                }

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
                    for(String moves:temp)
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
                    }
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
                while (i < 24) {
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
                }

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
                    for(String moves:temp)
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
                    }

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
                while (i < 30) {
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
                }

            }

            return legalMoves;
        } else if (board.innerRing.contains(position))
        {
            String nxtpos = position;
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
                while (j < 4) {
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
                }
                if(nxtpos=="b6" && board.getPiece(nxtpos)==null)
                {
                    if(board.getPiece("b7")==null || board.getPiece("b7").getColor()!=this.getColor()) {
                        legalMoves.add("b7");
                    }
                }

            }
            if (index >= 4 && index < 8) {
                int j = index;
                while (j < 8)
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
                }
                if(nxtpos=="f6" &&  board.getPiece(nxtpos)==null)
                {
                    if(board.getPiece("g6")==null || board.getPiece("g6").getColor()!=this.getColor()) {
                        legalMoves.add("g6");
                    }
                }

            }
            if (index >= 8 && index < 12) {
                int j = index;
                while (j < 12) {
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
                }
                if(nxtpos=="f2" &&  board.getPiece(nxtpos)==null)
                {
                    if(board.getPiece("f1")==null || board.getPiece("f1").getColor()!=this.getColor()) {
                        legalMoves.add("f1");
                    }
                }

            }
            if (index >= 12 && index <= 15) {
                int j = index;
                while (j < 16) {
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
                }
                if(nxtpos=="b2" &&  board.getPiece(nxtpos)==null)
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

    public String toString(){
        if (getColor() == Color.White) { return "\u2656"; }
        else return "\u265C";
    }
}
