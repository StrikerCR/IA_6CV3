package laboratorio1_1_minimos;

/**
 *
 * @author Rafita Coder
 * @author Paolita Coder
 *
 *
 */
public class minimos {

    public static void main(String[] args) {

        // Variables para minimos
        double xm = 0.0f, ym = 0.0f;

        // variables de prueba
        double x = 0.0f, y = 0.0f;

        // variable de resultado minimo
        double resm = 0;

        // variable de resultado de prueba
        double resAux = 0;

        // variable bandera para determinar la primer iteración
        boolean ban = true;

        // Contador
        int i = 0;

        while (i < 100) {

            System.out.println("-------------------------------------------------");

            x = -4.5 + (Math.random() * 9);
            y = -4.5 + (Math.random() * 9);
            resAux = funcion(x, y);

            System.out.println("x: " + x);
            System.out.println("y: " + y);
            System.out.println("resultado: " + resAux);

            if (ban == true) {
                resm = resAux;
                xm = x;
                ym = y;
            } else {
                if (resAux < resm) {
                    resm = resAux;
                    xm = x;
                    ym = y;
                }
            }
            
            ban = false;
            i++;
            
            System.out.println("-------------------------------------------------");

        }

        System.out.println("x minimo:\t" + xm);
        System.out.println("y minimo:\t" + ym);
        System.out.println("resultado minimo:\t" + resm);

    }

    public static double funcion(double x, double y) {

        double res;

        res = Math.pow((1.5 - x + (x * y)), 2) + Math.pow((2.25 - x + (x * Math.pow(y, 2))), 2) + Math.pow((2.625 - x + (x * Math.pow(y, 3))), 2);

        return res;

    }

}
