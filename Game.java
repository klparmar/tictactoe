package eecs1022.lab8.tictactoe.model;

public class Game {

    char[][] board = {
            {'_', '_', '_'},
            {'_', '_', '_'},
            {'_', '_', '_'}
    };;
    int mode = 0;
    String p1, p2;
    String status;
    String currPlayer;
    public Game (String x, String y){
        this.p1 = x;
        this.p2 = y;
        this.currPlayer = x;
        this.status = currPlayer+"'s turn to play...";

    }

    public String getStatus(){return this.status;}

    public char[][] getBoard(){return this.board;}

    public String getCurrentPlayer(){return this.currPlayer;}

    public void setWhoPlaysFirst(char x){
        if(x =='x') this.currPlayer = this.p1;
        if(x == 'o') this.currPlayer = this.p2;
        this.status = currPlayer+"'s turn to play...";
    }

    public void move(int x, int y){
        if(this.mode ==1){
            this.status = "Error: game is already over with a winner.";
        }else if (this.mode ==2){
            this.status = "Error: game is already over with a tie.";
        } else if(x <1 || x>3){
            this.status = "Error: row "+x+" is invalid.";
        }else if (y <1 || y>3){
            this.status = "Error: col "+y+" is invalid.";
        }else if(this.board[x-1][y-1] != '_'){
            this.status = "Error: slot @ ("+x+", "+y+") is already occupied.";
        }else{
            if(this.currPlayer.equals(this.p1)){
                this.board[x-1][y-1] = 'x';
                this.currPlayer= p2;
            } else if(this.currPlayer.equals(this.p2)){
                this.board[x-1][y-1] = 'o';
                this.currPlayer= p1;
            }

            int cnt = 0;
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    if(this.board[i][j] != '_') cnt++;
                }
            }

            if((this.board[0][0] == this.board[1][1] && this.board[0][0] == this.board[2][2] && this.board[0][0] != '_') || (this.board[0][0] == this.board[0][1] && this.board[0][0] == this.board[0][2] && this.board[0][0] != '_') || (this.board[1][0] == this.board[1][1] && this.board[1][0] == this.board[1][2] && this.board[1][0] != '_') || (this.board[2][0] == this.board[2][1] && this.board[2][0] == this.board[2][2] && this.board[2][0] != '_')){
                if(this.currPlayer.equals(this.p1)){
                    this.status = "Game is over with "+this.p2+" being the winner.";
                } else{
                    this.status = "Game is over with "+this.p1+" being the winner.";
                }
                this.mode = 1;
                this.currPlayer = null;
            } else if((this.board[1][2] == this.board[1][1] && this.board[0][2] == this.board[2][2] && this.board[1][2] != '_') || (this.board[0][0] == this.board[1][0] && this.board[0][0] == this.board[2][0] && this.board[0][0] != '_') || (this.board[0][1] == this.board[1][1] && this.board[0][1] == this.board[2][1] && this.board[0][1] != '_') || (this.board[0][2] == this.board[1][2] && this.board[0][2] == this.board[2][2] && this.board[0][2] != '_')){
                if(this.currPlayer.equals(this.p1)){
                    this.status = "Game is over with "+this.p2+" being the winner.";
                } else{
                    this.status = "Game is over with "+this.p1+" being the winner.";
                }
                this.mode = 1;
                this.currPlayer = null;
            }
            else if(cnt==9 && this.mode == 0){
                this.status = "Game is over with a tie between "+this.p1+" and "+this.p2+".";
                this.currPlayer = null;
                this.mode = 2;
            }else{this.status = currPlayer+"'s turn to play...";}

        }
    }

}
