public class GameField{
    private int[][] field1;
    private int[][] field2;
    private boolean flag = false;

    public GameField(int row, int col){

        field1 = new int[row][col];
        field2 = new int[row][col];
    }

    public int[][] getField1(){
        return field1;
    }
    
    public int[][] getField2(){
        return field2;
    }

    public boolean getFlag(){
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
