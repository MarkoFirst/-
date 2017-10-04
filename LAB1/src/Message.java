public class Message implements MesList{

    public String mes;

    public void message(String mes){
        this.mes = mes;
    }
    public void play(){
        System.out.println(mes);
    }
}
