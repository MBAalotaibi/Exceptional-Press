

public class Book {
    private String title;
    private String author;
    private String content;
    private int edition;

    public Book(String title,String author,String content,int edition) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.edition = edition;
    }

    public String toString() {
        String result="Title: "+title;
        result+="\nAuthor: "+author;
        result+="\nEdition: "+edition;
        return result;
    }
    public int getPages() {
        int size=content.length()-1;
        double result=(double) (content.length()-1)/700;
        return (int) Math.ceil(result);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public int getEdition() {
        return edition;
    }
}
