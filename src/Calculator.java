public class Calculator {

    private int a;
    private int b;

    public Calculator(int a, int b){
        this.a = a;
        this.b = b;
    }
    Calculator(){
        this.a = 1;
        this.b = 1;
    }

    public int plus(int a, int b){
        return (a+b);
    }
    public int min(int a, int b){
        return (a-b);
    }
}
