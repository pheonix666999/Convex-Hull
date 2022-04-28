package sample;

import java.util.*;

public class JarvisMarch {

    //Used to get the Clockwise and Anti-Clockwise direction of the turing of the vectors.
    public int Valid(Points a, Points b, Points c){
        double area = (b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y);
        if (area == 0.0)
            return 0;
        return (area > 0.0)? 2:1;
    }

    //Gives the points in a hull.
    public ArrayList<Points> points_in_hull(ArrayList<Points> points, int length){

        //if there are less than three than it makes a line of a point.
        if (length < 3){
            return points;
        }

        // Final Result
        ArrayList<Points> result = new ArrayList<Points>();
        int leftmost = 0, i;

        //Finding the leftmost point in the list.
        for (i = 0;i < length;i++){
            if (points.get(i).x < points.get(leftmost).x){
                leftmost = i;
            }
        }
        int p = leftmost, temppoint;

        //Checking if the vector rotates anti-clockwise than adding it to the result other wise not.
        do {
            result.add(points.get(p));
            temppoint = (p + 1) % length;
            for (i = 0;i < length;i++){
                if (Valid(points.get(p), points.get(i), points.get(temppoint)) == 2){
                    temppoint = i;
                }
            }
            p = temppoint;
        } while (p != leftmost);
        return result;
    }
}
