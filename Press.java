import java.io.File;
import java.io.IOException;
import java.util.*;


public class Press {
    private Map<String, List<Book>> shelf;
    private int shelfSize;
    private Map<String, Integer> edition;

    public Press(String pathToBooKDir, int shelfSize){
        shelf = new HashMap<>();
        edition = new HashMap<>();
        this.shelfSize=shelfSize;
        File directoryPath = new File(pathToBooKDir);
        File filesList[] = directoryPath.listFiles();

        for(File file : filesList) {
            List<Book> temp = new ArrayList<Book>();
            shelf.put(file.getName(), temp);
            edition.put(file.getName(), 0);
        }
    }

    public List<String> getCatalogue(){
        List<String> temp = new ArrayList<String>(shelf.keySet());
        return temp;
    }



    public Book print(String bookID, int edition) throws IOException {
        if(!shelf.containsKey(bookID)){
            throw new IllegalArgumentException();
        }
        else{
            File prc = new File(bookID);
            Scanner fileLink = new Scanner(prc);

            int flag0=0;
            int flag1=0;
            String contents="";
            String title="";
            String author = "";

            while(fileLink.hasNext()){
                String line=fileLink.nextLine();
                contents+=line+"\n";
                if(line.contains("Title: ")){
                    flag0=1;
                    String[] temp=line.split(": ");
                    title=temp[1];

                }
                if(line.contains("Author: ")){
                    flag1=1;
                    String[] temp=line.split(": ");
                    author=temp[1];
                }
            }


            if(flag0==0 || flag1==0){
                throw  new IOException();
            }

            Book book=new Book(title,author,contents,edition);

            shelf.get(bookID).add(book);

            this.edition.put(bookID, edition);

            return book;
        }
    }

    public List<Book> request(String bookID, int amount) {
        List<Book>result=new ArrayList<>();

        if(!shelf.containsKey(bookID)){
            throw new IllegalArgumentException();
        }
        else{
            List<Book>temp=shelf.get(bookID);
            for(int i=0;i<amount;i++){
                if(temp.size()!=0) {
                    Book b= temp.remove(0);
                    result.add(b);
                }
                else{
                    try{
                        while (temp.size()!=shelfSize)
                            print(bookID,this.edition.get(bookID)+1);

                    } catch (IOException e) {
                        List<Book>empty=new ArrayList<>();
                        return empty;
                    }
                }
            }
        }
        return result;
    }




}
