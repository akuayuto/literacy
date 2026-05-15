package enshu7a;

//import java.awt.*;

public class Coord1 {
    double x,y;
    Coord1(){
        x = y =0;
    }

public void move(double dx, double dy){
    x += dx;
    y += dy;
}
public void moveto(double x, double y){
    this.x = x;
    this.y = y;
}
//public void paint(Graphics2D g){}
}
