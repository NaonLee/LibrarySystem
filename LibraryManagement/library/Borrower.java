package library;
import java.util.*;


public class Borrower implements Comparable{

    private String name;
    private LinkedList<Book> borroweredBooks; //이용자가 빌린책들 목록
    
    public Borrower(String name) {
        this.name = name ;
        borroweredBooks = new LinkedList<Book>();
    }
 
    public void attachBook(Book book){	//대출
        //링크드리스트에 add
        borroweredBooks.add(book);
    }

    public void detachBook(Book book){	//반납
        //링크드리스트remove(book);
        borroweredBooks.remove(book);
    }

    public void display(){
        System.out.println(this.toString());
    }

    public int compareTo(Object obj){
        Borrower borrower = (Borrower) obj;
        String pname = borrower.getName();
        return name.compareTo(pname);
    }

    public boolean equals(Object obj){
        return this.compareTo(obj)==0;
    }

    public int hashCode(){
        return name.hashCode();
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return "이용자 이름: "+ name;
    }
}
