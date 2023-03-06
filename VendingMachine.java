import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    private List<Book> shelf=new ArrayList<Book>();
    private double locationFactor;
    private double cassette;
    private double safe;
    private String password;

    public VendingMachine(double locationFactor, String password) {
        this.locationFactor = locationFactor;
        this.password = password;
        this.cassette=0;
        this.safe=0;
    }

    public double getCassette(){
        return cassette;
    }
    public void insertCoin(double coin){
        double a[]={0.01,0.02,0.05,0.1,0.2,0.5,1,2 };
        if(arrayContains( a,coin)){
           cassette+=coin;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public boolean arrayContains(double arr[],double coin){
        for(double i:arr){
            if(i==coin)
                return true;
        }
        return false;
    }

    public double returnCoins(){
        double t=cassette;
        cassette=0;
        return t;
    }


    public void restock(List<Book> books, String password){
        if(this.password.equals(password)){
            shelf=books;
        }
        else{
            throw new InvalidPasswordException();
        }
    }

    public double emptySafe(String password){
        if(this.password.equals(password)){
            double t=safe;
            safe=0;
            return  t;
        }
        else{
            throw new InvalidPasswordException();
        }

    }
    public List<String> getCatalogue(){
        List<String> t=new ArrayList<>();
        for(Book i:shelf){
            t.add(i.toString());
        }
        return t;
    }

    public double getPrice(int index){
        if(index<0 || index>=shelf.size()){
            throw new IndexOutOfBoundsException();
        }
        else{
            return shelf.get(index).getPages()*locationFactor;
        }
    }

    public Book buyBook(int index){
        double price=getPrice(index);

        if(price>cassette){
            throw new CassetteException();
        }

        Book t=shelf.get(index);
        shelf.remove(index);
        cassette-=price;
        safe+=price;

        return t;
    }
}

