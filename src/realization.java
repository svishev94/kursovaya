/**
 * Created by student on 22.12.2014.
 */
import javax.swing.*;


    class Realization {
        static int input() { //Ввод размера матрицы
            JOptionPane.showMessageDialog(null, "Далее вам будет предложено сначала ввести размер матрицы, а потом заполнить ее.");
            // Считываем размер вводимой матрицы
            int size = Integer.parseInt(JOptionPane.showInputDialog("Введите размер матрицы, состоящей из коэффициентов системы при х1, х2,..., х(n)"));
            return size;
        }
        static double[][] fillingGauss(int size) { //Заполнение матрицы для метода Гаусса
            double[][] matrix = new double[size][size + 1];
            JOptionPane.showMessageDialog(null, "Заполните матрицу, включая столбец свободных членов.");
            return filling(matrix);
        }
        static double[][] fillingKramer(int size) { //Заполнение матрицы для метода Крамера
            double[][] matrix = new double[size][size];
            JOptionPane.showMessageDialog(null, "Заполните матрицу, состоящую из коэффициентов системы при х1, х2,..., х(n):");
            return filling(matrix);
        }
        static double[][] filling(double[][] matrix) { //отдельный код для заполнения матрицы
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = Double.parseDouble(JOptionPane.showInputDialog("Введите элемент: (" + (i + 1) + ";" + (j + 1) + ")="));
                }
            }
            return matrix;
        }
        void outcome(String conclusion, double[][] matrix) { //вывод матрицы
            for (int i = 0; i < matrix.length; i++) {
                conclusion += "\n";
                for (int j = 0; j < matrix[0].length; j++) {
                    conclusion += matrix[i][j] + " ";
                }
            }
            JOptionPane.showMessageDialog(null, conclusion);
        }

        static double determinant(double[][] a) { //вычисление определителя матрицы
            double det = 0;
            if (a.length <= 1) {
                det = a[0][0];
            } else {
                for (int k = 0; k < a.length; k++) {
                    double[][] subAr = new double[a.length - 1][a.length - 1];
                    for (int i = 0; i < subAr.length; i++) {
                        for (int j = 0; j < k; j++) {
                            subAr[i][j] = a[i + 1][j];
                        }
                        for (int j = k; j < subAr.length; j++) {
                            subAr[i][j] = a[i + 1][j + 1];
                        }
                    }
                    det += a[0][k] * determinant(subAr) * ((int) Math.pow(-1, k + 2));
                }
            }
            return det;
        }

        // Вычисление СЛАУ методом Гаусса:
        public void gauss() {
            int size = input(); //размер матрицы
            double[][] matrix = fillingGauss(size); //заполнение
            //вывод матрицы на экран
            String conclusion = "Получившаяся матрица со свободными коэффициентами" + ":" + "\n";
            outcome(conclusion, matrix);
            //Преобразовываем систему так, чтобы получилась верхнетреугольная матрица
            double x[] = new double[matrix.length];
            double m;
            for (int k = 1; k < matrix.length; k++) {
                for (int j = k; j < matrix.length; j++) {
                    m = matrix[j][k - 1] / matrix[k - 1][k - 1];
                    for (int i = 0; i <= matrix.length; i++) {
                        matrix[j][i] = matrix[j][i] - m * matrix[k - 1][i];
                    }
                }
            }
            //вывод преобразованной матрицы
            String completion = "Преобразованая матрица" + ":" + "\n";
            outcome(completion, matrix);
            //обратный ход
            for (int i = matrix.length - 1; i >= 0; i--) {
                for (int j = i + 1; j < matrix.length; j++) x[i] += matrix[i][j] * x[j];
                x[i] = (matrix[i][matrix.length] - x[i]) / matrix[i][i];
            }
            //Вывод результата
            int t = (int) 'x';
            for (int i = 0; i < x.length; i++) {
                JOptionPane.showMessageDialog(null, ((char) t++) + " = " + x[i]);
            }
        }

        // Метод Крамера:
        public void kramer() {
            int size = input(); //размер матрицы
            double[][] matrix = fillingKramer(size); //заполнение
            //вывод матрицы на экран
            String conclusion = "Получившаяся матрица из коэффициентов при х1, х2,..., х(n):" + "\n";
            outcome(conclusion, matrix);
            double[][] freeRates = new double[size][1]; //матрица свободных коэффициентов
            JOptionPane.showMessageDialog(null, "Введите коэффициенты столбца свободных членов.");
            // Матрица будет иметь размер (size) x (1)
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < 1; j++) {
                    freeRates[i][j] = Double.parseDouble(JOptionPane.showInputDialog("Введите элемент: (" + (i + 1) + ";" + (j + 1) + ")="));
                }
            }
            //вывод матрицы на экран
            String completion = "Получившаяся матрица свободных коэффициентов" + ":" + "\n";
            outcome(completion, freeRates);
            double x0 = determinant(matrix); //нахождение определителя основной матрицы
            //нахождения определителей дополнительных матриц и вывод ответа
            for (int n = 0; n < matrix.length; n++) {
                double[][] a = new double[matrix.length][matrix[0].length];
                JOptionPane.showMessageDialog(null, "Введите матрицу, заменив " + (n + 1) + " столбец системы столбцом из коэффициентов свободных членов.");
                filling(a);
                //вывод матрицы на экран
                String out = "Получившаяся матрица" + ":" + "\n";
                outcome(out, a);
                double x1 = determinant(a); //нахождение определителя матрицы
                double x = x1 / x0; //нахождение ответа
                JOptionPane.showMessageDialog(null, "Ответ:" + "\n" + "x" + (n + 1) + "= " + x);
            }
        }
    }

