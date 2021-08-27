package com.connect4;
class Connect4Game
{
    Connect4GUI discView;
    String[][] discs, discsReverse;
    int player1DiscsRemaining = 21;
	int player2DiscsRemaining = 21;
	int[] usedRowPositions; //for each column 0th Index - 1st colum number of rows used
    int rowIndex,columnIndex;
    UserInput ui;
    boolean win;
    String previous;
    //int currentRowIndex, currentColumnIndex;
    int row;
    public Connect4Game()
    {
        usedRowPositions = new int[7];
        discs = new String[6][7];
        for(int i =0;i<discs.length;i++)
        {
            for(int j = 0;j<discs[i].length;j++)
            {
                discs[i][j] ="";
            }
        }
        discsReverse = new String[6][7];
		for(int i = 0;i < 6; i++)
		{
			discsReverse[i] = discs[5-i];
        }
        showUserGUI("Waiting for Player 1 to insert disc","");
        showPlayerPrompt(1);
    }
    public void showUserGUI(String statusMessage, String previousStatusMessage)
    {
        if(discView!=null)
        {
            discView.dispose();
        }
        discView = new Connect4GUI(discsReverse,statusMessage,previousStatusMessage);
    }
    public void showPlayerPrompt(int player)
	{
        if(win==false&&player==1)
        {
            if(player1DiscsRemaining > 0)
		    {
                UserInput input = new UserInput(this);
                
		    }
        }
		else if(win==false&&player==2)
		{
            if(player2DiscsRemaining>0)
            {
                player2InsertDisc();
                win = checkForWin("G");
                if(win)
			    {
				    declareWinner("Player 2 THE COMPUTER");
				    endGame(ui,2);
			    }
			    //Game Over
			    else
			    {
                    if(player1DiscsRemaining>0)
                    {
                        UserInput input = new UserInput(this);
                    }
                    else
                    {
                        endGame(ui,0);
                    }
			    }
            }
		}
    }
    public void endGame(UserInput ui, int player)
    {
        //discView.dispose();
        ui.dispose();
        if(player==0)
        {
            UserPrompt up = new UserPrompt("No Remaining Discs",discView);
        }
        else
        {
            UserPrompt up = new UserPrompt("Player " + player + " wins",discView);
        }
        
    }
	public void setPlayerInput(int column, UserInput ui)
	{
        this.ui = ui;
		Validator validator = validateColumn(column);
		if(validator.valid == false)
		{
			ui.showError(validator.message);
		}
		else
		{
            columnIndex = column-1;
            row = usedRowPositions[columnIndex];
            rowIndex = row-1;
            discs[rowIndex+1][columnIndex] = "B";
            player1DiscsRemaining-=1;
            discsReverse = reverseDiscs(discs);
            row++;
            usedRowPositions[columnIndex] = usedRowPositions[columnIndex]+1;
            previous = "Player 1 put the disc in row "+(row)+" and column "+column;
            showUserGUI("Player 1 put the disc in row "+(row)+" and column "+column,"");
            rowIndex++;
            win = checkForWin("B");
            if(win)
			{
				declareWinner("Player 1");
				endGame(ui,1);
            }
            else
            {
                showPlayerPrompt(2);
            }
            
		}

    }
    public boolean checkForWin(String check)
    {
        int i,j;
        boolean flag=false;
        String s="",testString = check+check+check+check;
        //Horizontal Check
        //discsReverse = reverseDiscs(discs);
        for(i=0;i<4;i++)
        {
            s = discs[rowIndex][i] + discs[rowIndex][i+1] + discs[rowIndex][i+2] + discs[rowIndex][i+3];
            if(s.contains(testString))
            {
                flag=true;
                break;
            }
        }
        //Vertical Check
        if(!flag)
        {
            s="";
            for(i=0;i<usedRowPositions[columnIndex];i++)
            {
                s = s+ discs[i][columnIndex];
                if(s.contains(testString))
                {
                    flag=true;
                    break;
                }
            }
        }
            //Forward Diagonal
        s="";
        if(!flag)
        {
            for(i=0;i<3;i++)
            {
                for(j=0;j<4;j++)
                {
                    s = discs[i][j] + discs[i+1][j+1] + discs[i+2][j+2] + discs[i+3][j+3];
                    if(s.equals(check+check+check+check))
                    {
                        flag=true;
                        break;
                    }
                }
            }
        }
        s="";
        if(!flag)
        {
            //Back Diagonal
            for(i=0;i<3;i++)
            {
                for(j=6;j>2;j--)
                {
                    s = discs[i][j] + discs[i+1][j-1] + discs[i+2][j-2] + discs[i+3][j-3];
                    if(s.equals(check+check+check+check))
                    {
                        flag=true;
                        break;
                    }
                }
            }
        }
        return flag;
    }
    public String[][] reverseDiscs(String[][] discs)
    {
        String discsReverse[][] = new String[6][7];
		for(int i = 0;i < 6; i++)
		{
			discsReverse[i] = discs[5-i];
        }
        return discsReverse;
    }
    public void declareWinner(String winner)
	{
		System.out.println(winner);
    }
    public void player2InsertDisc()
	{
		int columnIndex = getColumnIndex();
		int column = columnIndex+1;
		//Whatever column you decide after that following code will come
        //Assumption is you will not decide invalid column. We are not doing validation check for the same for Player 2 that is you
            rowIndex = usedRowPositions[columnIndex];
            if(rowIndex>5)
            {
                int i;
                for(i=0;i<7;i++)
                {
                    if(usedRowPositions[i]<6)
                    {
                        columnIndex = i;
                        rowIndex = usedRowPositions[columnIndex];
                        break;
                    }
                }
            }
            discs[rowIndex][columnIndex]="G";
			//columnIndex = usedRowPositions[column-1];
			player2DiscsRemaining = player2DiscsRemaining - 1;
			discView.dispose();
            usedRowPositions[columnIndex] = usedRowPositions[columnIndex]+1;
            row = rowIndex+1;
            column = columnIndex+1;
			showUserGUI("Player 2 THE COMPUTER put the disc in row "+(row)+" and column "+column,previous);
		
    }
    public int getColumnIndex()
    {
        int columnIndex=this.columnIndex, c = this.columnIndex;
        System.out.println("columnIndex = "+c+"\nrow = "+row+"\nrowIndex = "+rowIndex);
        if(c>1 && c<6 && discs[row-1][c-1].equals("B") && discs[row-1][c-2].equals("B")&& ((usedRowPositions[c+1])==rowIndex))
        {
            System.out.println("BBI_");
            columnIndex = c+1; //BBI_
        }
        else if(c>2 && discs[row-1][c-1].equals("B") && discs[row-1][c-2].equals("B")&& (usedRowPositions[c-3])==rowIndex)
        {
            System.out.println("_BBI");
            columnIndex = c-3; //_BBI
        }
        else if(c<4 && discs[row-1][c+1].equals("B") && discs[row-1][c+2].equals("B")&& (usedRowPositions[c+3])==rowIndex)
        {
            System.out.println("IBB_");
            columnIndex = c+3; //IBB_
        }    
        else if(c>0 && c<5 && discs[row-1][c+1].equals("B") && discs[row-1][c+2].equals("B")&& (usedRowPositions[c-1])==rowIndex)
        {
            System.out.println("_IBB");
            columnIndex = c-1; //_IBB
        }
        else if(c>0 && c<5 && discs[row-1][c-1].equals("B")&& discs[row-1][c+2].equals("B") && (usedRowPositions[c+1])==rowIndex)
        {
            System.out.println("BI_B");
            columnIndex = c+1; //BI_B
        }
        else if(c>2 && discs[row-1][c-2].equals("B")&& discs[row-1][c-3].equals("B")&& (usedRowPositions[c-1])==rowIndex)
        {
            System.out.println("BB_I");
            columnIndex = c-1; //BB_I
        }
        else if(c<4 && discs[row-1][c+2].equals("B")&& discs[row-1][c+3].equals("B")&& (usedRowPositions[c+1])==rowIndex)
        {
            System.out.println("I_BB");
            columnIndex = c+1; //I_BB
        }
        else if((c>1 &&c<6)&&(discs[row-1][c-2].equals("B")&&discs[row-1][c+1].equals("B"))&& (usedRowPositions[c-1])==rowIndex)
        {
            System.out.println("B_IB");
            columnIndex = c-1; //B_IB
        }
        else if((c>2)&&(discs[row-1][c-1].equals("B")&&discs[row-1][c-3].equals("B"))&& (usedRowPositions[c-2])==rowIndex)
        {
            System.out.println("B_BI");
            columnIndex = c-2; //B_BI
        }
        else if(c<4 &&(discs[row-1][c+1].equals("B")&&discs[row-1][c+3].equals("B"))&& (usedRowPositions[c+2])==rowIndex)
        {
            System.out.println("IB_B");
            columnIndex = c+2; //IB_B
        }
        else if(c>0&&c<6&&(discs[row-1][c+1].equals("B")&&discs[row-1][c-1].equals("B"))&& (usedRowPositions[c+2])==rowIndex)
        {
            System.out.println("BIB_");
            columnIndex = c+2; //BIB_
        }
        else if(c>1 && c<6&&(discs[row-1][c+1].equals("B")&&discs[row-1][c-1].equals("B"))&& (usedRowPositions[c-2])==rowIndex)
        {
            System.out.println("_BIB");
            columnIndex = c-2; //_BIB
        }
        else if((c<4&&c>0)&&(discs[row-1][c+1].equals("B")&&discs[row-1][c-1].equals("B"))&& (usedRowPositions[c+2])==rowIndex)
        {
            System.out.println("_IBB");
            columnIndex = c+2; //_IBB
        }
        else if((c>1&&c<6)&&(rowIndex<4 && rowIndex>0)&&(discs[rowIndex+2][c-2].equals("B")&&discs[rowIndex-1][c+1].equals("B"))&& (usedRowPositions[c-1])==rowIndex) //diagonal
        { //forward diagonal B_IB
            System.out.println("forward diagonal B_IB");
            columnIndex = c-1;
        }
        else if(c>2 &&(rowIndex<3)&&(discs[rowIndex+2][c-2].equals("B")&&discs[rowIndex+3][c-3].equals("B"))&& (usedRowPositions[c-1])==row) //diagonal
        { //forward diagonal I_BB
            System.out.println("forward diagonal I_BB");
            columnIndex = c-1;
        }
        else if(c<4 && rowIndex>2 && (discs[rowIndex-1][c+1].equals("B")&&discs[rowIndex-3][c+3].equals("B"))&& (usedRowPositions[c+2])==rowIndex-3) //diagonal
        { //forward diagonal B_BI
            System.out.println("forward diagonal B_BI");
            columnIndex = c+2;
        }
        else if((c>1&&c<6)&&(rowIndex<4 && rowIndex>1)&&(discs[rowIndex+1][c+1].equals("B")&&discs[rowIndex-2][c-2].equals("B"))&& (usedRowPositions[c-1])==rowIndex) //diagonal
        { //backward diagonal B_IB
            System.out.println("Backward diagonal B_IB");
            columnIndex = c-1;
        }
        else if(c<4 &&(rowIndex<3)&&(discs[rowIndex+2][c+2].equals("B")&&discs[rowIndex+3][c+3].equals("B"))&& (usedRowPositions[c+1])==row) //diagonal
        { //backward diagonal I_BB
            System.out.println("Backward diagonal I_BB");
            columnIndex = c+1;
        }
        else if(c>2 && rowIndex>2 && (discs[rowIndex-1][c-1].equals("B")&&discs[rowIndex-3][c-3].equals("B"))&& (usedRowPositions[c-1])==rowIndex-2) //diagonal
        { //backward diagonal B_BI
            System.out.println("Backward diagonal B_BI");
            columnIndex = c-1;
        }

        /*else if((c>0&&c<5)&&(rowIndex<5 && rowIndex>1)&&(discs[rowIndex-2][c+2].equals("B")&&discs[rowIndex+1][c-1].equals("B"))&& (usedRowPositions[c-1])==rowIndex) //diagonal
        { //forward diagonal B_IB (bottom to top)
            System.out.println("forward diagonal B_IB");
            columnIndex = c-1;
        }
        else if(c>2 &&(rowIndex<3)&&(discs[rowIndex+2][c-2].equals("B")&&discs[rowIndex+3][c-3].equals("B"))&& (usedRowPositions[c-1])==row) //diagonal
        { //forward diagonal I_BB (bottom to top)
            System.out.println("forward diagonal I_BB");
            columnIndex = c-1;
        }
        else if(c<4 && rowIndex>2 && (discs[rowIndex-1][c+1].equals("B")&&discs[rowIndex-3][c+3].equals("B"))&& (usedRowPositions[c+2])==rowIndex-3) //diagonal
        { //forward diagonal B_BI (bottom to top)
            System.out.println("forward diagonal B_BI");
            columnIndex = c+2;
        }

        else if((c>1&&c<6)&&(rowIndex<5 && rowIndex>0)&&(discs[rowIndex+2][c-2].equals("B")&&discs[rowIndex+1][c-1].equals("B"))&& (usedRowPositions[c+1])==rowIndex) //diagonal
        { //forward diagonal _IBB (bottom to top)
            System.out.println("forward diagonal B_IB");
            columnIndex = c+1;
        }
        else if(c>1 &&c<6 &&(rowIndex<6 && rowIndex>1)&&(discs[rowIndex+1][c-1].equals("B")&&discs[rowIndex-1][c+1].equals("B"))&& (usedRowPositions[c+2])==rowIndex-2) //diagonal
        { //forward diagonal _BIB (bottom to top)
            System.out.println("forward diagonal I_BB");
            columnIndex = c+2;
        }
        else if(c<4 && rowIndex>2 && (discs[rowIndex-1][c+1].equals("B")&&discs[rowIndex-2][c+2].equals("B"))&& (usedRowPositions[c+3])==rowIndex-3) //diagonal
        { //forward diagonal _BBI (bottom to top)
            System.out.println("forward diagonal B_BI");
            columnIndex = c+3;
        }

        else if((c>1 &&c<5)&&(rowIndex<4 && rowIndex>0)&&(discs[rowIndex+2][c-2].equals("B")&&discs[rowIndex-1][c+1].equals("B"))&& (usedRowPositions[c-1])==rowIndex) //diagonal
        { //forward diagonal BI_B (bottom to top)
            System.out.println("forward diagonal B_IB");
            columnIndex = c-1;
        }
        else if(c>2 &&(rowIndex<3)&&(discs[rowIndex+3][c-3].equals("B")&&discs[rowIndex+1][c-1].equals("B"))&& (usedRowPositions[c-2])==rowIndex+2) //diagonal
        { //forward diagonal IB_B (bottom to top)
            System.out.println("forward diagonal I_BB");
            columnIndex = c-2;
        }
        else if(c<4 && rowIndex>2 && (discs[rowIndex-2][c+2].equals("B")&&discs[rowIndex-3][c+3].equals("B"))&& (usedRowPositions[c+1])==rowIndex-1) //diagonal
        { //forward diagonal BB_I (bottom to top)
            System.out.println("forward diagonal B_BI");
            columnIndex = c+1;
        }

        else if((c>1&&c<6)&&(rowIndex<4 && rowIndex>0)&&(discs[rowIndex-1][c+1].equals("B")&&discs[rowIndex+1][c-1].equals("B"))&& (usedRowPositions[c-2])==rowIndex+2) //diagonal
        { //forward diagonal BIB_ (bottom to top)
            System.out.println("forward diagonal BIB_");
            columnIndex = c-2;
        }
        else if(c>2 &&(rowIndex<3)&&(discs[rowIndex+1][c-1].equals("B")&&discs[rowIndex+2][c-2].equals("B"))&& (usedRowPositions[c-3])==rowIndex+3) //diagonal
        { //forward diagonal IBB_ (bottom to top)
            System.out.println("forward diagonal IBB_");
            columnIndex = c-3;
        }
        else if(c<4 && rowIndex>2 && (discs[rowIndex-1][c+1].equals("B")&&discs[rowIndex-2][c+2].equals("B"))&& (usedRowPositions[c-1])==rowIndex+1) //diagonal
        { //forward diagonal BBI_ (bottom to top)
            System.out.println("forward diagonal BBI_");
            columnIndex = c-1;
        }
        
        /*else if((c>1&&c<6)&&(rowIndex<4 && rowIndex>1)&&(discs[rowIndex+1][c+1].equals("B")&&discs[rowIndex-2][c-2].equals("B"))&& (usedRowPositions[c-1])==rowIndex) //diagonal
        { //backward diagonal B_IB
            System.out.println("Backward diagonal B_IB");
            columnIndex = c-1;
        }
        else if(c<4 &&(rowIndex<3)&&(discs[rowIndex+2][c+2].equals("B")&&discs[rowIndex+3][c+3].equals("B"))&& (usedRowPositions[c+1])==row) //diagonal
        { //backward diagonal I_BB
            System.out.println("Backward diagonal I_BB");
            columnIndex = c+1;
        }
        else if(c>2 && rowIndex>2 && (discs[rowIndex-1][c-1].equals("B")&&discs[rowIndex-3][c-3].equals("B"))&& (usedRowPositions[c-1])==rowIndex-2) //diagonal
        { //backward diagonal B_BI
            System.out.println("Backward diagonal B_BI");
            columnIndex = c-1;
        } */
        else if(rowIndex>1 && rowIndex<6 && usedRowPositions[c]>1 && discs[rowIndex-2][c].equals("B")&&discs[rowIndex-1][c].equals("B"))
        {
            System.out.println("Vertical Block");
            columnIndex = verticalBlock();
        }
        else if( (c>0&&c<6) && (discs[row-1][c-1].equals("B")|| discs[row-1][c+1].equals("B") ) )
        {
            System.out.println("Horizontal Block");
            columnIndex = horizontalBlock(1);
        }
        else if((c>1&&c<5)&&(discs[row-1][c-2].equals("B")||discs[row-1][c+2].equals("B")))
        {
            System.out.println("Horizontal Block");
            columnIndex = horizontalBlock(2);
        }
        else
        {
            columnIndex = (int)(6*Math.random());
            System.out.println("Else Vertical Block random..."+c);
        }
        //columnIndex = verticalBlock();
        return columnIndex;
    }
    public int verticalBlock()
    {
        int c = this.columnIndex,i=0,tmp,columnIndex;
        if(usedRowPositions[c]>2)
        {
            System.out.println("Vertical Block used row positions in column "+c+" are"+usedRowPositions[c]);
            if(discs[rowIndex-2][c].equals("B")&&discs[rowIndex-1][c].equals("B"))
            {
                columnIndex = c;
                System.out.println("Vertical Block same column..."+c);
            }
            else    
            {
                c = (int)(6*Math.random());
                System.out.println("Vertical Block random..."+c);
            }
        }
        else
        {
            c = (int)(6*Math.random());
            System.out.println("Vertical Block random..."+c);
        }
        return c;
    }
    //Horizontal Block
    public int horizontalBlock(int step)
    {
        int c = this.columnIndex,i=0,tmp=c,front=0,back=0;
        String s="";
        if(step>0)
        {
            while(tmp>=0&&tmp<7)
            {
                if(discs[row-1][tmp].equals("B"))
                {
                    front++;
                }
                tmp++;
            }
            tmp=c;
            while(tmp>=0&&tmp<7)
            {
                if(discs[row-1][tmp].equals("B"))
                {
                    back++;
                }
                tmp--;
            }
            if(back>1)
            {
                tmp = c+1;
                if(tmp<7 && discs[row-1][tmp].equals(""))
                {
                    if(usedRowPositions[tmp]==row-1)
                    {
                        columnIndex=tmp;
                    }
                }
                else
                {
                    c = (int)(6*Math.random());
                }
            }
            else if(front>1)
            {
                tmp = c-1;
                if(tmp<7 && discs[row-1][tmp].equals(""))
                {
                    if(usedRowPositions[tmp]==row-1)
                    {
                        columnIndex=tmp;
                    }
                }
                else
                {
                    c = (int)(6*Math.random());
                }
            }
            else
            {
                c = (int)(6*Math.random());
            }
        }
        else
        {
            c = (int)(6*Math.random());
        }
        return columnIndex;
    }
    public int diagonalBlock()
    {
        int columnIndex = this.columnIndex,c=columnIndex;
        if(row<5&&c<5&&discs[row+1][c+1].equals("B")&&discs[row+2][c+2].equals(""))
        {
            columnIndex = c+2;
        }
        else if(row<5&&c>2&&discs[row+1][c-1].equals("B")&&discs[row+2][c-2].equals(""))
        {
            columnIndex = c-2;
        }
        return columnIndex;
    }
    public Validator validateColumn(int column)
	{
		if(column <1 || column>7)
			return new Validator(false,"Column out of Range");
		if(usedRowPositions[column-1] == 6)
			return new Validator(false,"All Rows filled in this Column");
		return new Validator(true,"Column Available");

    }
    public static void main(String args[])
    {
        Connect4Game cg = new Connect4Game();
    }
}
class Validator
{
	boolean valid;
	String message;
	public Validator(boolean valid,String message)
	{
		this.valid = valid;
		this.message = message;
	}
};