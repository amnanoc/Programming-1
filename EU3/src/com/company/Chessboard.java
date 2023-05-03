package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Chessboard {
    public static class Field {
        private char row;
        private byte column;
        private Chesspiece piece = null;
        private boolean marked = false;
        public Field (char row , byte column) {
            this.row = row;
            this.column = column;
        }
        public void put (Chesspiece piece) throws NotValidFieldException {
           this.piece = piece;
        }
        public Chesspiece take () { //remove the piece
            Chesspiece returning = this.piece;
            this.piece = null;
            return returning;
        }
        public void mark () {
            this.marked = true;
        }
        public void unmark () {
            this.marked = false;
        }
        public String toString (){
            String s = (marked )? "xx" : "--";
            return (piece == null)? s : piece.toString ();
        }
    }
        public static final int NUMBER_OF_ROWS = 8;
        public static final int NUMBER_OF_COLUMNS = 8;
        public static final int FIRST_ROW = 'a';
        public static final int FIRST_COLUMN = 1;
        private Field [][] fields;

        public Chessboard () {
            fields = new Field[NUMBER_OF_ROWS ][ NUMBER_OF_COLUMNS ];
            char row = 0;
            byte column = 0;
            for (int r = 0; r < NUMBER_OF_ROWS; r++){
                row = (char) (FIRST_ROW + r);
                column = FIRST_COLUMN;
                for (int c = 0; c < NUMBER_OF_COLUMNS; c++){
                    fields[r][c] = new Field (row , column );
                    column ++;
                }
            }
        }
        public String toString () {
            StringBuilder str = new StringBuilder();
            for(int i=1; i<=NUMBER_OF_COLUMNS; i++)
                str.append("  ").append(i);
            str.append("\n");
            for(int r=0; r<NUMBER_OF_ROWS; r++) {
                str.append(fields[r][0].row).append(" ");
                for(int c=0; c<NUMBER_OF_COLUMNS; c++) {
                    str.append(fields[r][c].toString()).append(" ");
                }
                str.append("\n");
            }
            return str.toString();
        }

        //checks if its a valid field format is inserted
        public boolean isValidField (char row , byte column) {
            return row >='a' && row <= 'h' && column>0 && column<=NUMBER_OF_COLUMNS;
        }

        public abstract class Chesspiece {
            private char color;
            // w - white , b - black
            private char name;
            // K - King , Q - Queen , R - Rook , B - Bishop , N - Knight ,
            // P Pawn
            protected char row = 0;
            protected byte column = -1;
            protected Chesspiece (char color , char name) {
                this.color = color;
                this.name = name;
            }
            public String toString (){
                return "" + color + name;
            }

            //idk what this does
            public boolean isOnBoard (){
                return Chessboard.this.isValidField (row , column);
            }


            public void moveTo (char row , byte column) throws NotValidFieldException{
                if (! Chessboard.this.isValidField (row , column )) throw new NotValidFieldException("bad field: " + row + column );
                this.row = row;
                this.column = column;
                int r = row - FIRST_ROW;
                int c = column - FIRST_COLUMN;
                Chessboard.this.fields[r][c].put (this);
            }

            public void moveOut () {
                Chessboard.this.fields[row-FIRST_ROW][column-FIRST_COLUMN].take();
            }

            public abstract void markReachableFields ();
            public abstract void unmarkReachableFields ();
        }
        public class Pawn extends Chesspiece {
            public Pawn (char color , char name){
                super (color , name);
            }

            public void markReachableFields (){
                byte col = (byte) (column + 1);
                if (Chessboard.this.isValidField (row , col)){
                    int r = row - FIRST_ROW;
                    int c = col - FIRST_COLUMN;
                    Chessboard.this.fields[r][c].mark ();
                }

            }
            public void unmarkReachableFields (){
                byte col = (byte) (column + 1);
                if (Chessboard.this.isValidField (row , col)) {
                    int r = row - FIRST_ROW;
                    int c = col - FIRST_COLUMN;
                    Chessboard.this.fields[r][c]. unmark ();
                }
            }
        }
        public class Rook extends Chesspiece {
            protected Rook(char color, char name) {
                super(color, name);
            }

            @Override
            public void markReachableFields() {
                for (char ro = 'a'; ro <= 'h'; ro++) {
                    for (byte col = 1; col <= 8; col++) {
                        //when we are in the same row or same column, but not that exact spot so xor
                        if (Chessboard.this.isValidField(row, col) && ((ro==row && col!=column) || (ro!=row && col==column)) ){
                            Chessboard.this.fields[ro - FIRST_ROW][col - FIRST_COLUMN].mark();
                        }
                    }
                }
            }

            @Override
            public void unmarkReachableFields() {
                for (byte col = 1; col <= 8; col++) {
                    if (Chessboard.this.isValidField(row, col) && (col != column)) {
                        int r = row - FIRST_ROW;
                        int c = col - FIRST_COLUMN;
                        Chessboard.this.fields[r][c].unmark();
                    }
                }

                for (char ro = 'a'; ro <= 'h'; ro++) {
                    if (Chessboard.this.isValidField(ro, column) && (ro != row)) {
                        int r = ro - FIRST_ROW;
                        int c = column - FIRST_COLUMN;
                        Chessboard.this.fields[r][c].unmark();
                    }
                }
            }
        }
        public class Knight extends Chesspiece {
            protected Knight(char color, char name) {
                super(color, name);
            }

            @Override
            public void markReachableFields() {
                for (char ro = 'a'; ro <= 'h'; ro++) {
                    for (byte col = 1; col <= 8; col++) {
                        if (Chessboard.this.isValidField(row, col) && ((Math.abs(column-col)==2 && Math.abs(row-ro)==1) ||
                                (Math.abs(column-col)==1 && Math.abs(row-ro)==2)) && ro!=row && col!=column){
                                     Chessboard.this.fields[ro - FIRST_ROW][col - FIRST_COLUMN].mark();
                        }
                    }
                }
            }

            @Override
            public void unmarkReachableFields() {
                for (char ro = 'a'; ro <= 'h'; ro++) {
                    for (byte col = 1; col <= 8; col++) {
                        if (Chessboard.this.isValidField(row, col) && ((Math.abs(column-col)==2 && Math.abs(row-ro)==1) ||
                                (Math.abs(column-col)==1 && Math.abs(row-ro)==2)) && ro!=row && col!=column){
                            Chessboard.this.fields[ro - FIRST_ROW][col - FIRST_COLUMN].unmark();
                        }
                    }
                }


            }
        }
        public class Bishop extends Chesspiece {
            protected Bishop(char color, char name) {
                super(color, name);
            }

            @Override
            public void markReachableFields() {
                for (char ro = 'a'; ro <= 'h'; ro++) {
                    for (byte col = 1; col <= 8; col++) {
                        //moved horizontally and vertically by the same amount
                        if (Chessboard.this.isValidField(row, col) && Math.abs(column-col)==Math.abs(row-ro) && ro!=row && col!=column){
                            Chessboard.this.fields[ro - FIRST_ROW][col - FIRST_COLUMN].mark();
                        }
                    }
                }
            }

            @Override
            public void unmarkReachableFields() {
                for (char ro = 'a'; ro <= 'h'; ro++) {
                    for (byte col = 1; col <= 8; col++) {
                        //moved horizontally and vertically by the same amount
                        if (Chessboard.this.isValidField(row, col) && Math.abs(column-col)==Math.abs(row-ro) && ro!=row && col!=column){
                            Chessboard.this.fields[ro - FIRST_ROW][col - FIRST_COLUMN].unmark();
                        }
                    }
                }
            }
        }
        public class Queen extends Chesspiece {
            protected Queen(char color, char name) {
                super(color, name);
            }

            @Override
            public void markReachableFields() {
                //queen works like a rook+bishop
                for (char ro = 'a'; ro <= 'h'; ro++) {
                    for (byte col = 1; col <= 8; col++) {
                        if (Chessboard.this.isValidField(row, col) && (Math.abs(column-col)==Math.abs(row-ro) ||
                                ((ro==row && col!=column )|| (col==column && ro!=row)))){
                            Chessboard.this.fields[ro - FIRST_ROW][col - FIRST_COLUMN].mark();
                        }
                    }
                }
            }

            @Override
            public void unmarkReachableFields() {
                for (char ro = 'a'; ro <= 'h'; ro++) {
                    for (byte col = 1; col <= 8; col++) {
                        if (Chessboard.this.isValidField(row, col) && (Math.abs(column-col)==Math.abs(row-ro) ||
                                ((ro==row && col!=column )|| (col==column && ro!=row)))){
                            Chessboard.this.fields[ro - FIRST_ROW][col - FIRST_COLUMN].unmark();
                        }
                    }
                }
            }
        }
        public class King extends Chesspiece {
            protected King(char color, char name) {
                super(color, name);
            }

            @Override
            public void markReachableFields() {
                for (char ro = 'a'; ro <= 'h'; ro++) {
                    for (byte col = 1; col <= 8; col++) {
                        //moved by 1 in any direction
                        boolean test = false;
                        if (Chessboard.this.isValidField(row, col) && ((Math.abs(column-col)==1 && Math.abs(row-ro)==1)
                                || (Math.abs(column-col)==1 && ro==row) || ( Math.abs(row-ro)==1 && col==column))){
                            test = true;
                            Chessboard.this.fields[ro - FIRST_ROW][col - FIRST_COLUMN].mark();
                        }
                    }
                }
            }

            @Override
            public void unmarkReachableFields() {

            }
        }
}
