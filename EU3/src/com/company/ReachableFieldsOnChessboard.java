package com.company;

public class ReachableFieldsOnChessboard {

    public static void main(String[] args) throws NotValidFieldException {
        Chessboard chessBoard = new Chessboard();
        System.out.println (chessBoard + "\n");
        Chessboard.Chesspiece [] pieces = new Chessboard.Chesspiece [6];
        pieces [0] = chessBoard.new King ('w', 'K');
        pieces [1] = chessBoard.new Knight ('w', 'N');
        pieces [2] = chessBoard.new Queen ('w', 'Q');
        pieces [3] = chessBoard.new Rook ('w', 'R');
        pieces [4] = chessBoard.new Pawn ('w', 'P');
        pieces [5] = chessBoard.new Bishop ('w', 'B');

        for(int i=0; i<pieces.length; i++) {
            pieces[i].moveTo('b', (byte) 1);
            pieces[i].markReachableFields();
            System.out.println(chessBoard);
            System.out.println();
            pieces[i].unmarkReachableFields();
        }
    }
}
