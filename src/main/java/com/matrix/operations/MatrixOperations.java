package com.matrix.operations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Этот класс выполняет операции с матрицами: сложение, вычитание, умножение, вычисление детерминанта.
 * Вся логика работы с матрицами и логирование результатов выполняются в статических методах.
 * Конструкторы по умолчанию не используются.
 */
public class MatrixOperations {

    private static final Logger logger = LogManager.getLogger(MatrixOperations.class);

    /**
     * Основной метод программы, который выполняет операции с матрицами.
     * Ожидает два аргумента — пути к файлам с матрицами.
     *
     * @param args аргументы командной строки: пути к файлам с матрицами.
     */
    public static void main(String[] args) {
        // Логирование начала работы программы
        logger.info("Программа запущена.");

        // Проверяем количество аргументов
        if (args.length != 2) {
            logger.error("Необходимо указать два файла для чтения матриц.");
            System.out.println("Необходимо указать два файла для чтения матриц.");
            return;
        }

        // Прочитаем два файла с матрицами
        double[][] matrix1;
        double[][] matrix2;

        try {
            matrix1 = readMatrixFromFile(args[0]);
            matrix2 = readMatrixFromFile(args[1]);
        } catch (IOException e) {
            logger.error("Ошибка при чтении матриц.", e);
            System.out.println("Ошибка при чтении матриц.");
            return;
        }

        // Проверка на null после чтения матриц
        if (matrix1 == null || matrix2 == null) {
            logger.error("Одна или обе матрицы не были загружены.");
            System.out.println("Ошибка при чтении матриц.");
            return;
        }

        // Выполним операции
        double[][] sum = addMatrices(matrix1, matrix2);
        double[][] difference = subtractMatrices(matrix1, matrix2);
        double[][] product = multiplyMatrices(matrix1, matrix2);
        double determinant1 = calculateDeterminant(matrix1);
        double determinant2 = calculateDeterminant(matrix2);

        // Логирование выполнения операций
        logger.info("Сложение матриц выполнено.");
        logger.info("Вычитание матриц выполнено.");
        logger.info("Умножение матриц выполнено.");
        logger.info("Вычисление определителей завершено.");

        // Выведем результаты
        printMatrix("Sum", sum);
        printMatrix("Difference", difference);
        printMatrix("Product", product);
        System.out.println("Determinant of matrix 1: " + determinant1);
        System.out.println("Determinant of matrix 2: " + determinant2);

        // Логирование завершения работы программы
        logger.info("Программа завершила выполнение.");
    }

    /**
     * Читает матрицу из файла.
     *
     * @param filename путь к файлу, который содержит матрицу.
     * @return двумерный массив, представляющий матрицу.
     * @throws IOException если файл не найден или возникли другие ошибки при чтении.
     */
    public static double[][] readMatrixFromFile(String filename) throws IOException {
        try {
            logger.info("Чтение матрицы из файла: " + filename);
            Scanner scanner = new Scanner(new File(filename));
            int rows = 0;
            int columns = 0;

            // Считаем размеры матрицы
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split("\\s+");
                columns = values.length;
                rows++;
            }
            scanner.close();

            // Создадим матрицу с размерами
            double[][] matrix = new double[rows][columns];
            scanner = new Scanner(new File(filename));

            int rowIndex = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split("\\s+");
                for (int colIndex = 0; colIndex < values.length; colIndex++) {
                    matrix[rowIndex][colIndex] = Double.parseDouble(values[colIndex]);
                }
                rowIndex++;
            }
            scanner.close();
            return matrix;

        } catch (FileNotFoundException e) {
            logger.error("Файл не найден: " + filename, e);
            throw new IOException("Файл не найден: " + filename, e);
        } catch (NumberFormatException e) {
            logger.error("Ошибка в данных в файле: " + filename, e);
            throw new IOException("Ошибка в данных в файле: " + filename, e);
        }
    }

    /**
     * Складывает две матрицы.
     *
     * @param matrix1 первая матрица.
     * @param matrix2 вторая матрица.
     * @return результат сложения матриц.
     * @throws IllegalArgumentException если матрицы имеют несовместимые размеры.
     */
    public static double[][] addMatrices(double[][] matrix1, double[][] matrix2) {
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            logger.error("Ошибка: матрицы имеют разные размеры для сложения.");
            throw new IllegalArgumentException("Матрицы имеют разные размеры для сложения.");
        }
        double[][] result = new double[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    /**
     * Вычитает вторую матрицу из первой.
     *
     * @param matrix1 первая матрица.
     * @param matrix2 вторая матрица.
     * @return результат вычитания.
     * @throws IllegalArgumentException если матрицы имеют несовместимые размеры.
     */
    public static double[][] subtractMatrices(double[][] matrix1, double[][] matrix2) {
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            logger.error("Ошибка: матрицы имеют разные размеры для вычитания.");
            throw new IllegalArgumentException("Матрицы имеют разные размеры для вычитания.");
        }
        double[][] result = new double[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }

    /**
     * Умножает две матрицы.
     *
     * @param matrix1 первая матрица.
     * @param matrix2 вторая матрица.
     * @return результат умножения двух матриц.
     * @throws IllegalArgumentException если количество столбцов первой матрицы не совпадает с количеством строк второй матрицы.
     */
    public static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
        if (matrix1[0].length != matrix2.length) {
            logger.error("Ошибка: матрицы имеют несовместимые размеры для умножения.");
            throw new IllegalArgumentException("Матрицы имеют несовместимые размеры для умножения.");
        }
        double[][] result = new double[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Вычисляет определитель матрицы.
     * Для матрицы размером больше 2x2 используется разложение по минору.
     *
     * @param matrix матрица, для которой нужно вычислить определитель.
     * @return определитель матрицы.
     * @throws IllegalArgumentException если матрица не является квадратной.
     */
    public static double calculateDeterminant(double[][] matrix) {
        // Проверка, что матрица квадратная
        if (matrix.length != matrix[0].length) {
            logger.error("Ошибка: матрица не является квадратной.");
            throw new IllegalArgumentException("Матрица не является квадратной.");
        }

        // Для матрицы 2x2 применяем простую формулу
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        // Для матрицы больше 2x2 используем разложение по минору
        double determinant = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            determinant += Math.pow(-1, i) * matrix[0][i] * calculateDeterminant(getMinor(matrix, 0, i));
        }
        return determinant;
    }

    /**
     * Получает минор матрицы, удаляя заданную строку и столбец.
     *
     * @param matrix исходная матрица.
     * @param row индекс удаляемой строки.
     * @param col индекс удаляемого столбца.
     * @return минор матрицы.
     */
    public static double[][] getMinor(double[][] matrix, int row, int col) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];
        int minorRow = 0;

        for (int i = 0; i < matrix.length; i++) {
            if (i == row) continue; // пропускаем строку row
            int minorCol = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == col) continue; // пропускаем столбец col
                minor[minorRow][minorCol] = matrix[i][j];
                minorCol++;
            }
            minorRow++;
        }
        return minor;
    }

    /**
     * Печатает матрицу на экран.
     *
     * @param label название операции, для которой выводится результат (например, "Сложение").
     * @param matrix матрица, которая будет выведена.
     */
    public static void printMatrix(String label, double[][] matrix) {
        if (matrix == null) {
            logger.error(label + ": Ошибка выполнения операции.");
            System.out.println(label + ": Ошибка выполнения операции.");
            return;
        }
        System.out.println(label + ":");
        for (double[] row : matrix) {
            for (double val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}
