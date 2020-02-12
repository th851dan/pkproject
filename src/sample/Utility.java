package sample;

public class Utility {
    public static double calculateLpDistance(double[] x, double[] y, int p) { //Task 2
        if (x.length != y.length)
            throw new RuntimeException("The two arrays must have the same amount of elements!");
        double sum = 0;
        for (int i = 0; i < x.length; i++)
            sum += Math.pow(x[i] - y[i], p);
        return Math.pow(sum, 1.0 / p);
    }

    public static double calculateLin(double v, double max, double min) { //Task 3
        return (v - min) / (max - min);
    }

    public static MyList<Point> grahamScan(MyList<Point> input) {
        //find cornerPoint
        Point cornerPoint = input.get(0);
        for(int i = 1; i < input.size(); i++) {
            Point nextPoint = input.get(i);
            if (cornerPoint.getY() > nextPoint.getY()
                    || (cornerPoint.getY() == nextPoint.getY() && cornerPoint.getX() > nextPoint.getX()))
                cornerPoint = nextPoint;
        }

        //sort input
        MyList<Point> sortedList = sortList(input, cornerPoint);
        for (int i = 1; i < sortedList.size() - 1; i++) {
            if (i == 3)
                ;
            if (cw(sortedList.get(i + 1), sortedList.get(i - 1), sortedList.get(i)) < 0) {
                sortedList.remove(sortedList.get(i));
                i = i - 2;
            }
        }
        return sortedList;
    }

    private static MyList<Point> sortList(MyList<Point> input, Point cornerPoint) {
        MyList<Point> unsortedList = input.clone();
        int size = unsortedList.size();
        //sort the list increasingly
        for (int i = 0; i < size; i++)
            for (int j = 1; j < size - i; j++) {
                Point p1 = unsortedList.get(j - 1);
                Point p2 = unsortedList.get(j);
                if (p1.equals(cornerPoint) || p2.equals(cornerPoint))
                    continue;
                int compareResult = cw(cornerPoint, p1, p2);
                if (compareResult < 0) {
                    double tempX = p1.getX();
                    double tempY = p1.getY();
                    p1.setX(p2.getX());
                    p1.setY(p2.getY());
                    p2.setX(tempX);
                    p2.setY(tempY);
                }

                //Keep the further point, when two points are collinear with corner point.
                if (compareResult == 0) {
                    if (p1.getY() <= p2.getY())
                        unsortedList.remove(p1);
                    else
                        unsortedList.remove(p2);
                    size--;
                    j--;
                }
            }
        return unsortedList;
    }

    private static int cw(Point a, Point b, Point c) //clockwise compare
    {
        //Cross product
        double area2 = (b.getX() - a.getX()) * (c.getY() - a.getY()) - (b.getY() - a.getY()) * (c.getX() - a.getX());
        if (area2 < 0) // ab and ac are counterclockwise
            return -1;
        else if (area2 > 0)
            return 1; // ab and ac are clockwise
        else
            return 0; // a, b, c are collinear.
    }
}
