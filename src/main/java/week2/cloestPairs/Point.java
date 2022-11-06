package week2.cloestPairs;

public class Point {
    public int x;
    public int y;
    public double distance(Point that){
        if(this == null || that == null) return Double.MAX_VALUE;
         return Math.sqrt((this.x-that.x)*(this.x-that.x)+
                 (this.y-that.y)*(this.y-that.y));
    }

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ","+this.y + ")";
    }
}
