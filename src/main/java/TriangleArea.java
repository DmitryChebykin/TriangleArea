import java.util.Scanner;

public class TriangleArea {
    public static void main(String[] args) {
        double[][] trianglePoints = getTrianglePoints();
        printTriangleInfo(trianglePoints);
    }

    private static double[] getPointCoordinates() {
        Scanner scanner = new Scanner(System.in);

        String inputString = scanner.nextLine().replaceAll("\\s+", " ").trim();
        String[] parsedString = inputString.split("\\s");

        double[] coordinates = new double[2];
        boolean isRightInput = false;

        while (!isRightInput) {
            try {
                coordinates[0] = Double.parseDouble(parsedString[0]);
                coordinates[1] = Double.parseDouble(parsedString[1]);
                isRightInput = true;
            } catch (Exception e) {
                System.out.println("Неверный ввод, попробуйте снова!");
                scanner.nextLine();
            }
        }

        return coordinates;
    }

    private static double[][] getTrianglePoints() {
        System.out.println("Введите координаты вершин треугольника");
        System.out.println("в виде двух чисел, разделенных пробелами,");
        System.out.println("например: 5.2  3.6");

        boolean isRightInput = false;
        double[][] triangleCoordinates = new double[3][2];

        while (!isRightInput) {

            for (int i = 1; i <= 3; i++) {
                System.out.println("Введите координаты вершины номер " + i);
                triangleCoordinates[i - 1] = getPointCoordinates();
            }

            isRightInput = true;
        }

        return triangleCoordinates;
    }

    private static double triangleArea(double[][] trianglePoints) {
        double[] triangleSizeLength = getTriangleSizesLength(trianglePoints);

        double semiPerimeter = (triangleSizeLength[0] + triangleSizeLength[1] + triangleSizeLength[2]) / 2;
        return Math.sqrt(semiPerimeter * (semiPerimeter - triangleSizeLength[0]) * (semiPerimeter - triangleSizeLength[1]) * (semiPerimeter - triangleSizeLength[2]));
    }

    private static double[] getTriangleSizesLength(double[][] trianglePoints) {
        double[] triangleSizeLength = new double[3];

        double x1 = trianglePoints[0][0];
        double y1 = trianglePoints[0][1];

        double x2 = trianglePoints[1][0];
        double y2 = trianglePoints[1][1];

        double x3 = trianglePoints[2][0];
        double y3 = trianglePoints[2][1];

        triangleSizeLength[0] = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        triangleSizeLength[1] = Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2));
        triangleSizeLength[2] = Math.sqrt(Math.pow((x3 - x1), 2) + Math.pow((y3 - y1), 2));

        return triangleSizeLength;
    }

    private static boolean isStraightLineCoordinates(double[][] trianglePoints) {
        double x1 = trianglePoints[0][0];
        double y1 = trianglePoints[0][1];

        double x2 = trianglePoints[1][0];
        double y2 = trianglePoints[1][1];

        double x3 = trianglePoints[2][0];
        double y3 = trianglePoints[2][1];

        if ((x1 == x2 && x2 == x3) || (y1 == y2 && y2 == y3)) {
            return true;

        } else if (x2 != x1 && y2 != y1) {
            return (y3 - y1) / (y2 - y1) == (x3 - x1) / (x2 - x1);

        } else {
            return (y2 - y1) / (y3 - y1) == (x2 - x1) / (x3 - x1);
        }
    }

    private static void printTriangleInfo(double[][] trianglePoints) {
        if (isStraightLineCoordinates(trianglePoints)) {
            System.out.println("Данные точки расположены на одной линии.");

        } else {
            double triangleArea = triangleArea(trianglePoints);
            System.out.printf("Площадь треугольника равна %f", triangleArea);
        }
    }
}