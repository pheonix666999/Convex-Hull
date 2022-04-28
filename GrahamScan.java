package sample;

import javafx.scene.shape.*;

import java.util.*;
import java.lang.Math;
public class GrahamScan {
    //Stack used for final processing of the points.
    Stack<Points> stack = new Stack<Points>();
    int count = 0, i = 0;

    //Valid Function assists in the orientation of the vectors.
    public int Valid(Points a, Points b, Points c){
        double area = (b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y);
        if (area == 0.0)
            return 0;
        return (area > 0.0)? 2:1;
    }

    //Gives the Distance between points a, b
    public double distance(Points a, Points b){
        return Math.sqrt(Math.pow((b.x - a.x), 2) + Math.pow((b.y - a.y), 2));
    }

    //Used to compare the vectors with respect to the reference.
    public boolean compare(Points a, Points b, Points low){
        if (a == b || a.equals(b)){
            return true;
        }
        if (Valid(low, a, b) == 0)
            return (distance(low, b) >= distance(low, a))? false: true;
        if (Valid(low, a, b) == 2)
            return true;
        else
            return false;
    }

    //Gives the points second in the stack.
    public Points next_to_top(Stack<Points> stack){
        Points p = stack.peek();
        stack.pop();
        Points required = stack.peek();
        stack.push(p);
        return required;
    }

    //gives the points in the convex hull.
    public ArrayList<Points> points_in_hull(ArrayList<Points> points, int length){
        if (length < 3){
            return points;
        }
        //Array having the points in a hull.
        ArrayList<Points> result = new ArrayList<Points>();

        //Array that also contains the reference with all the sorted elemets.
        ArrayList<Points> temp = new ArrayList<Points>();

        //Array sorted with respect to the polar angle.
        ArrayList<Points> sorted = new ArrayList<Points>();
        Points reference;
        int lowest = 0, i, rightmost = 0, j;
        int count = 0;

        //Finding the lowest.
        for (i = 0;i < length;i++){
            if (points.get(i).y > points.get(lowest).y){
                lowest = i;
            }
        }


        reference = points.get(lowest);
        points.remove(lowest);

        //Sorting the array with respect to polar angle.
        while(!points.isEmpty()) {
            for (i = 0; i < points.size(); i++) {
                for (j = 0; j < points.size(); j++) {
                    if (!compare(points.get(j), points.get(i), reference)) {
                        count++;
                    }
                }
                if (count == points.size() - 1) {
                    sorted.add(points.get(i));
                    points.remove(i);
                }
                count = 0;
            }
        }
        count = 0;
        temp.add(reference);

        //Adding the refernce to the temp array along with all the other elements.
        for (i = 0;i < sorted.size();i++){
            temp.add(sorted.get(i));
        }
        if (temp.size() < 3){
            return temp;
        }

        //pushing the three elemetns in the stack.
        stack.push(temp.get(0));
        stack.push(temp.get(1));
        stack.push(temp.get(2));

        //Pushing all the points that are on the hull on stack.
        for (i = 3;i < temp.size();i++){
            while (!stack.empty() && Valid(next_to_top(stack), stack.peek(), temp.get(i)) != 2){
                stack.pop();
            }
            stack.push(temp.get(i));
        }

        //Conofiguring the final resutl
        while (!stack.empty()){
            result.add(stack.peek());
            stack.pop();
        }
        System.out.println("RESULT: " + result.size());
        return result;
    }
}
