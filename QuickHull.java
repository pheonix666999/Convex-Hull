package sample;

import java.util.*;

public class QuickHull{
    ArrayList<Points> result = new ArrayList<Points>();

    //Function to see the points lies on which side of the line.
    public int findSide(Points p1, Points p2, Points p){
        double value = (p.y - p1.y) * (p2.x - p1.x) - (p2.y - p1.y) * (p.x - p1.x);
        if (value > 0)
            return 1;
        if (value < 0)
            return -1;
        return 0;
    }

    //Calculate the diatance of a point to the line.
    public double lineDist(Points p1, Points p2, Points p){
        return Math.abs((p.y - p1.y) * (p2.x - p1.x) - (p2.y - p1.y) * (p.x - p1.x));
    }

    //the fucntion to set the final result.
    //uses the concept of divide and conquer.
    public void quickhull(ArrayList<Points> points, int length, Points p1, Points p2, int side){
        int ind = -1;
        double max_dist = 0;
        for (int i = 0; i < length;i++){
            double temp = lineDist(p1, p2, points.get(i));
            if (findSide(p1, p2, points.get(i)) == side && temp > max_dist){
                ind = i;
                max_dist = temp;
            }
        }

        if (ind == -1){
            result.add(p1);
            result.add(p2);
            return;
        }
        quickhull(points, points.size(), points.get(ind), p1, -findSide(points.get(ind), p1, p2));
        quickhull(points, points.size(), points.get(ind), p2, -findSide(points.get(ind), p2, p1));
    }
    //Function return the final result.
    public ArrayList<Points> points_in_a_hull(ArrayList<Points> points, int n){
        if (n < 3){
            return points;
        }
        int min_x = 0, max_x = 0;
        for (int i = 1;i < n;i++){
            if (points.get(i).x < points.get(min_x).x){
                min_x = i;
            }
            if (points.get(i).x > points.get(max_x).x){
                max_x = i;
            }
        }
        quickhull(points, n, points.get(min_x), points.get(max_x), 1);
        quickhull(points, n, points.get(min_x), points.get(max_x), -1);
        for (int i = 0;i < result.size();i++){
            result.get(i).display();
        }
        int i, j;
        System.out.println("SIZE: " + result.size());
        ArrayList<Points> temp = new ArrayList<Points>();
        int cond = 0;
        for (i = 0;i < result.size();i++){
            for (j = 0;j < temp.size();j++){
                if (temp.get(j) == result.get(i)){
                    cond = 1;
                }
            }
            if (cond == 0){
                temp.add(result.get(i));
            }
            cond = 0;
        }

        Points temporary = new Points(0, 0);
        System.out.println("SORTED: " + temp.size());
        for (i = 0;i < temp.size();i++){
            for (j = 0;j < temp.size();j++){
                if (j + 1 < temp.size()) {
                    if (temp.get(j).x > temp.get(j + 1).x && temp.get(j).y > temp.get(j + 1).y) {
                        temporary.x = temp.get(j).x;
                        temp.get(j).x = temp.get(j + 1).x;
                        temp.get(j + 1).x = temporary.x;
                        temporary.y = temp.get(j).y;
                        temp.get(j).y = temp.get(j + 1).y;
                        temp.get(j + 1).y = temporary.y;
                    }
                }
            }
        }
        System.out.println();
        for (i = 0;i < temp.size();i++){
            temp.get(i).display();
        }
        return temp;
    }
}
