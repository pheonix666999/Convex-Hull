package sample;

public class Points {
    //Simple points class that contain x and y coordinates.
    public double x;
    public double y;
    public Points(double x, double y){
        this.x = x;
        this.y = y;
    }
    public void display(){
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
    }
}
