package cz.educanet.tranformations;

import java.util.Arrays;

public class Matrix implements IMatrix {

    private final double[][] rawArray;

    public Matrix(double[][] rawArray) {
        this.rawArray = rawArray;
    }

    @Override
    public int getRows() {
        return rawArray.length;
    }

    @Override
    public int getColumns() {
        if (getRows() > 0)
            return rawArray[0].length;

        return 0;
    }

    @Override
    public IMatrix times(IMatrix matrix) {
        double[][] newArray = new double[rawArray.length][matrix.getColumns()];

        if (rawArray.length != matrix.getColumns())
        {
            throw new ArithmeticException();
        }
        else
        for (int i=0; i < rawArray.length; ++i)
            for (int j=0; j < matrix.getColumns(); ++j)
                for (int k=0; k < rawArray[0].length; ++k)
                    newArray[i][j] += rawArray[i][k] * matrix.get(k, j);

        return new Matrix(newArray);

    }

    @Override
    public IMatrix times(Number scalar) {
        return new Matrix(Arrays.stream(rawArray).map(double[]::clone)
                .map(x -> Arrays.stream(x).map(y -> y * scalar.doubleValue()).toArray())
                .toArray(double[][]::new));
    }

    @Override
    public IMatrix add(IMatrix matrix) {
        double[][] newArray = Arrays.stream(rawArray).map(double[]::clone).toArray(double[][]::new);

        for (int x = 0; x < rawArray.length; x++)
            for (int y = 0; y < rawArray[x].length; y++)
                newArray[x][y] += matrix.get(x, y);
        System.out.println(Arrays.deepToString(newArray));

        return new Matrix(newArray);
    }

    @Override
    public double get(int n, int m) {
        return rawArray[n][m];
    }

    //region Optional
    @Override
    public IMatrix transpose() {
        return null;
    }

    @Override
    public double determinant() {
        return 0;
    }

    //endregion
    //region Generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return Arrays.equals(rawArray, matrix.rawArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rawArray);
    }
    //endregion
}
