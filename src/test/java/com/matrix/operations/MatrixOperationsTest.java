package com.matrix.operations;  // Пакет должен совпадать с пакетом в основном классе

import org.junit.Test;
import static org.junit.Assert.*;

public class MatrixOperationsTest {

    @Test
    public void testAddMatrices() {
        double[][] matrix1 = {
                {1, 2},
                {3, 4}
        };
        double[][] matrix2 = {
                {5, 6},
                {7, 8}
        };
        double[][] expected = {
                {6, 8},
                {10, 12}
        };

        double[][] result = MatrixOperations.addMatrices(matrix1, matrix2);

        // Проверка, что результат сложения матриц совпадает с ожидаемым
        assertArrayEquals(expected, result);
    }

    @Test
    public void testSubtractMatrices() {
        double[][] matrix1 = {
                {5, 6},
                {7, 8}
        };
        double[][] matrix2 = {
                {1, 2},
                {3, 4}
        };
        double[][] expected = {
                {4, 4},
                {4, 4}
        };

        double[][] result = MatrixOperations.subtractMatrices(matrix1, matrix2);

        // Проверка, что результат вычитания матриц совпадает с ожидаемым
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMultiplyMatrices() {
        double[][] matrix1 = {
                {1, 2},
                {3, 4}
        };
        double[][] matrix2 = {
                {5, 6},
                {7, 8}
        };

        double[][] expected = {
                {19, 22},
                {43, 50}
        };

        double[][] result = MatrixOperations.multiplyMatrices(matrix1, matrix2);

        // Проверка, что результат умножения матриц совпадает с ожидаемым
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCalculateDeterminant() {
        double[][] matrix = {
                {1, 2},
                {3, 4}
        };

        double expectedDeterminant = -2.0;  // Ожидаемый детерминант для матрицы 2x2
        double result = MatrixOperations.calculateDeterminant(matrix);

        // Проверка, что вычисленный детерминант совпадает с ожидаемым
        assertEquals(expectedDeterminant, result, 0.0001);
    }
}
