public class CalDrob extends Calculator {

    public double c,d;

    public CalDrob(double c, double d){
        this.c = c;
        this.d = d;
    }

    CalDrob(){
        this.c = 1;
        this.d = 1;
    }

    public double Del(){
        return (c/d);
    }
    public double Umn(){
        return (c*d);
    }

}
