package CollinearPoints;

import java.util.Comparator;

public class Point {
    // constructs the point (x, y)
    private int x, y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    // draws this point
    public void draw(){

    }

    // draws the line segment from this point to that point
    public void drawTo(Point that){

    }

    // string representation
    public String toString(){
        return "(" + this.getX() + ", " + this.getY() + ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // compare two points by y-coordinates, breaking ties by x-coordinates
    public int compareTo(Point that){
        if(that.getY() > this.getY()) return -1;
        else if(that.getY() < this.getY()) return 1;
        else{
            if(that.getX() > this.getX()) return -1;
            else if(that.getX() < this.getX()) return 1;
        }
        return 0;
    }

    // the slope between this point and that point
    public double slopeTo(Point that){
        double slope = (double)(this.getY() - that.getY()) / (this.getX() - that.getX());
        return slope;
    }

    // compare two points by slopes they make with this point
    public Comparator<Point> slopeOrder(){

    }
}
