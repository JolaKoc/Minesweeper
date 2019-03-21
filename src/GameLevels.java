package minesweeper;

public class GameLevels {
    private int width;
    private int height;
    private int mines;
        
        public GameLevels(String level){
            switch(level){
                case "SMALL": SmallLevel();
                break;
                case "MEDIUM": MediumLevel();
                break;
                case "LARGE": LargeLevel();
                break;
                default:
                    SmallLevel();
            }
        }
      //for costum level  
        public GameLevels(int w,int h,int m){
            width=w;
            height=h;
            mines=m;
        }

    private void SmallLevel() {
        this.width=10;
        this.height=10;
        this.mines=10;
    }

    private void MediumLevel() {
        this.width=15;
        this.height=15;
        this.mines=15;
    }

    private void LargeLevel() {
        this.width=25;
        this.height=25;
        this.mines=25;
    }
    
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    public int getMines(){
        return mines;
    }
    
    
}