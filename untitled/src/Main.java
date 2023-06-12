abstract class Figura {
    public abstract double area() throws Exception;
    public abstract double perimetro();
}

class Rectangulo extends Figura {
    private double lado1;
    private double lado2;


    public Rectangulo(double lado1, double lado2) throws Exception {
        if (lado1 < 0) throw new Exception("Los lados deben ser positivos");
        this.lado1 = lado1;
        if (lado2 < 0) throw new Exception("Los lados deben ser positivos");
        this.lado2 = lado2;
    }

    @Override
    public double area() {
        return lado1 * lado2;
    }

    @Override
    public double perimetro(){
        return (lado1 * 2) + (lado2 * 2);
    }
}

class Cuadrado extends Rectangulo {
    private double lado;

    public Cuadrado(double lado) throws Exception {
        super(lado, lado);
        if (lado < 0) throw new Exception("Los lados deben ser positivos");
    }
}

class TrianguloRectangulo extends Figura {
    private double base;
    private double altura;

    public TrianguloRectangulo(double base, double altura) throws Exception {
        if (base < 0) throw new Exception("Los lados del triángulo deben ser positivos");
        this.base = base;
        if (altura < 0) throw new Exception("Los lados del triángulo deben ser positivos");
        this.altura = altura;
    }

    public double getAltura() {
        return altura;
    }

    @Override
    public double area() {
        return (base * altura) / 2;
    }

    @Override
    public double perimetro(){
        double hipotenusa = Math.sqrt((Math.pow(base,2)) + (Math.pow(altura,2)));
        return base + altura + hipotenusa;
    }
}

class TrianguloEquilatero extends TrianguloRectangulo {

    public TrianguloEquilatero(double base, double altura) throws Exception {
        super(base / 2, altura);
    }

    @Override
    public double area() {
        return super.area() * 2;
    }

    @Override
    public double perimetro() {
        return super.perimetro() - getAltura() - getAltura();
    }
}

class Circunferencia extends Figura {
    private double radio;

    public Circunferencia (double radio) throws Exception {
        if (radio < 0) throw new Exception("El radio debe ser positivo");
        this.radio = radio;
    }

    public double area() {
        return Math.PI * Math.pow(radio, 2);
    }

    public double perimetro(){
        return 2 * Math.PI * radio;
    }
}

class Semicirculo extends Circunferencia {
    public Semicirculo(double radio) throws Exception {
        super(radio);
    }

    @Override
    public double area() {
        return super.area() / 2;
    }

    @Override
    public double perimetro() {
        return super.perimetro() / 2;
    }
}

class Parcela {
    private Figura[] figuras;

    public Parcela(Figura[] figuras) {

        this.figuras = figuras;
    }


    public double area() throws Exception {
        double resultado = 0;
        for(int i = 0; i < this.figuras.length; i++) {
            resultado += this.figuras[i].area();
        }
        return resultado;
    }

    public double perimetro() {
        double resultado = 0;
        for(int i = 0; i < this.figuras.length; i++) {
            resultado += this.figuras[i].perimetro();
        }
        return resultado;
    }

    public double precioParcela(double area, double preciometro){
        double precio = area * preciometro;
        return precio;
    }
}

public class Main {
    public static void main(String[] args) {

        try {
            Parcela AreaLaParcela = new Parcela(new Figura[] {new TrianguloRectangulo(2, 3),
                    new Cuadrado(4), new Rectangulo(8, 4), new Semicirculo(2),
                    new Semicirculo(2), new TrianguloEquilatero(4, 6)});

            double areaFinal = AreaLaParcela.area();
            double precioLaParcela = AreaLaParcela.precioParcela(areaFinal,32);


            System.out.println("El área de la parcela es: " + areaFinal);
            System.out.println("El precio de la parcela es: " + precioLaParcela);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {

            Parcela PerimetroLaParcela = new Parcela(new Figura[] {new TrianguloRectangulo(2,3),
                    new Cuadrado(4), new Rectangulo(8, 4), new Semicirculo(2), new Semicirculo(2),
                    new TrianguloEquilatero(4, 6)});

            double perimetroFinal = sacarLados(PerimetroLaParcela.perimetro());
            System.out.println("El perímetro de la parcela es: " + perimetroFinal);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static double sacarLados(double perimetro) {
        double perimetroFinal;
        perimetroFinal = perimetro - 2 - 4 - 4 - 4 - 4;
        return perimetroFinal;
    }
}