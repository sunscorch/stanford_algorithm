package course1.week2.cloestPairs;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class CloestPairs {

   // private Point[] pointsY;

    private double distanceOf2Points(Pair<Point,Point> p){
        Point x = p.getKey();
        Point y = p.getValue();
        return  x.distance(y);

    }
    private  Pair<Point, Point> bruteForceFind(Point[] points){

        int size = points.length;
       // assert(size > 0 && size <=3);

        double distnace = Double.MAX_VALUE;
        Pair<Point,Point> res = null;
        for (int i = 0; i < size; i++){
            for(int j = i+1; j < size; j++){
                Point x = points[i];
                Point y = points[j];

                Double curDistance = x.distance(y);
                if(curDistance < distnace) {
                    distnace = curDistance;
                    res = new Pair<>(x,y);

                }

            }
        }
        return res;
    }

    //find the splitPair
   // find points in x-axis , where range is points[mid] -d and points[mid]+d
    private Point[]  findStip(Point[] points, double d) {
        ArrayList<Point> res= new ArrayList<>();
        int mid = points.length / 2;
        Point midPoint = points[mid];

        for (int i = 0; i < points.length; i++) {
            if (Math.abs(points[i].x - midPoint.x) < d) {
                res.add(points[i]);
            }
        }

        return (Point[]) res.toArray(new Point[res.size()]);
    }

    //
    //only need to check 7 points each iteration
    //so the complexity = 7*O(n) => O(N)
    //d means range away from x mid
    private Pair<Point,Point> findtheMinInStrip(Point[] pointsY, double d) {
        int size = pointsY.length;
        Pair<Point, Point> res = null;
        double minD = Double.MAX_VALUE;
        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size && (pointsY[j].y - pointsY[i].y) < d; ++j) {
                if (pointsY[i].distance(pointsY[j]) < minD) {
                    minD = pointsY[i].distance(pointsY[j]);
                    res = new Pair<>(pointsY[i], pointsY[j]);
                }
            }
        }
        return res;
    }

    //need to sort points array based on x axis and y axis
    //pointsX =>  points sort with x axis
    //pointsY =>  points sort with y axis
    //pass the whole sorted ponts sorted with y axis for easy understanding(further thinking:
    // this can be optimized by extracting sorted points base on pointX)
    Pair<Point, Point> findCloestPair(Point[] pointsX, Point[] pointsY){

        if(pointsX.length <= 3){
            return bruteForceFind(pointsX);
        }

        int mid = (pointsX.length)/2;


        Point[] leftX =  Arrays.copyOfRange(pointsX,0,mid);
        Point[] rightX =  Arrays.copyOfRange(pointsX,mid,pointsX.length);


        Pair<Point, Point> leftMinPair = findCloestPair(leftX,pointsY);


        Pair<Point, Point> rightMinPair = findCloestPair(rightX,pointsY);



        //the  range we can vary from mid
        double d = Math.min(distanceOf2Points(leftMinPair),
                distanceOf2Points(rightMinPair));


        Point[] stripX= findStip(pointsX, d);
        //put x in a set , then we can filter out stripY in O(n)
        Set<Integer> xSet = Arrays.stream(stripX).map(p -> p.x).collect(Collectors.toSet());


        Point[] stripY = Arrays.stream(pointsY)
                .filter(p -> xSet.contains(p.x))
                .toArray(Point[]::new);

        Pair<Point, Point> minInStrp =  findtheMinInStrip(stripY, d);

        if(minInStrp != null && distanceOf2Points(minInStrp) < d) return minInStrp;
        else
            return distanceOf2Points(leftMinPair) <
                distanceOf2Points(rightMinPair)? leftMinPair:rightMinPair;


    }

    public Pair<Point, Point>  findCloestPair(Point[] px) throws Exception {
        if(px.length < 2) throw new Exception("more than 2 elements need in a array");
        Point[] py = px.clone();
        Arrays.sort(px, Comparator.comparingInt(a -> a.x));
        Arrays.sort(py, Comparator.comparingInt(a -> a.y));

        return findCloestPair(px, py);
    }

    public static void main(String[] args) throws Exception {

        //do some test
        System.out.println("test........");
        Random r = new Random();
        int size = 100;
        Point[] p = new Point[size];
        for(int i = 0; i<size; i++){
            p[i] = new Point(r.nextInt(1000), r.nextInt(1000));
        }
        Pair<Point, Point> n1 = new CloestPairs().findCloestPair(p);
        Pair<Point, Point> n2 = new CloestPairs().bruteForceFind(p);
        System.out.println(Arrays.toString(p));
        System.out.println(n1);
        System.out.println(n2);


    }
}
