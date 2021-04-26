import java.util.Scanner;

public class TriangleArea {
    public static final double EPSILON = 0.00001;

    public static void main(String[] args) {
        double[][] trianglePoints = getTrianglePoints();
        printTriangleInfo(trianglePoints);
    }

    private static double[] getPointCoordinates() {
        Scanner scanner = new Scanner(System.in);

        double[] coordinates = new double[2];
        boolean isRightInput = false;

        while (!isRightInput) {
            String inputString = scanner.nextLine().replaceAll("\\s+", " ").trim();
            String[] parsedString = inputString.split("\\s");
            try {
                coordinates[0] = Double.parseDouble(parsedString[0]);
                coordinates[1] = Double.parseDouble(parsedString[1]);
                isRightInput = true;
            } catch (Exception e) {
                System.out.println("Неверный ввод, попробуйте снова!");
            }
        }

        return coordinates;
    }

    private static double[][] getTrianglePoints() {
        System.out.println("Введите координаты вершин треугольника");
        System.out.println("в виде двух чисел, разделенных пробелами,");
        System.out.println("например: 5.2  3.6");

        double[][] triangleCoordinates = new double[3][2];

        for (int i = 1; i <= 3; i++) {
            System.out.println("Введите координаты вершины номер " + i);
            triangleCoordinates[i - 1] = getPointCoordinates();
        }

        return triangleCoordinates;
    }

    private static double getTriangleArea(double[][] trianglePoints) {
        double[] triangleSidesLengths = getTriangleSidesLengths(trianglePoints);

        double semiPerimeter = (triangleSidesLengths[0] + triangleSidesLengths[1] + triangleSidesLengths[2]) / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - triangleSidesLengths[0]) * (semiPerimeter - triangleSidesLengths[1]) * (semiPerimeter - triangleSidesLengths[2]));
    }

    private static double[] getTriangleSidesLengths(double[][] trianglePoints) {
        double x1 = trianglePoints[0][0];
        double y1 = trianglePoints[0][1];

        double x2 = trianglePoints[1][0];
        double y2 = trianglePoints[1][1];

        double x3 = trianglePoints[2][0];
        double y3 = trianglePoints[2][1];

        double[] triangleSidesLengths = new double[3];

        triangleSidesLengths[0] = getLineLength(x1, y1, x2, y2);
        triangleSidesLengths[1] = getLineLength(x2, y2, x3, y3);
        triangleSidesLengths[2] = getLineLength(x1, y1, x3, y3);

        return triangleSidesLengths;
    }

    private static boolean isStraightLineCoordinates(double[][] trianglePoints) {
        double x1 = trianglePoints[0][0];
        double y1 = trianglePoints[0][1];

        double x2 = trianglePoints[1][0];
        double y2 = trianglePoints[1][1];

        double x3 = trianglePoints[2][0];
        double y3 = trianglePoints[2][1];

        double leftExpression = getDifferenceTwoPrimitiveDoubles(y3, y1) * getDifferenceTwoPrimitiveDoubles(x2, x1);
        double rightExpression = getDifferenceTwoPrimitiveDoubles(x3, x1) * getDifferenceTwoPrimitiveDoubles(y2, y1);

        return  isPrimitiveDoublesEqual(leftExpression, rightExpression);
    }


    private static void printTriangleInfo(double[][] trianglePoints) {
        if (isStraightLineCoordinates(trianglePoints)) {
            System.out.println("Данные точки расположены на одной линии.");
        } else {
            double triangleArea = getTriangleArea(trianglePoints);
            System.out.printf("Площадь треугольника равна %f", triangleArea);
        }
    }

    private static double getLineLength (double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    private static boolean isPrimitiveDoublesEqual (double leftNumber, double rightNumber){
        return Math.abs(leftNumber - rightNumber) <= EPSILON;
    }

    private static double getDifferenceTwoPrimitiveDoubles(double leftNumber, double rightNumber){
        return isPrimitiveDoublesEqual(leftNumber, rightNumber)  ? 0: leftNumber - rightNumber;
    }
}