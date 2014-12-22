/**
 * Created by student on 22.12.2014.
 */
import javax.swing.*;
public class manager {

    public class Manager {
        public   void main(String[] args) {
            Realization operation = new Realization();
            int a;
            do {
                String text = "С помощью данной программы вы можете вычислить СЛАУ методом Гаусса или Крамера." + "\n" + "Выберите способ решения:" + "\n" + "1 - Метод Гаусса" + "\n" + "2 - Метод Крамера" + "\n" + "\n" + "0 - Выход из программы";
                a = Integer.parseInt(JOptionPane.showInputDialog(text));
// Метод Гаусса
                if (a == 1) {
                    operation.gauss();
                }
// Метод Крамера
                if (a == 2) {
                    operation.kramer();
                }
                if (a != 0 && a != 1 && a != 2) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Введите цифру повторно.");
                }
            } while (a != 0);
            JOptionPane.showMessageDialog(null, "Спасибо за использование!");
        }
    }

}
