package library;
import java.lang.*;
import java.util.*;


public class Book implements Comparable{
    private int catalogueNumber;//고유번호
    private String author;//저자
    private String title;//제목
    private Borrower borrower;//대출중인사람

    public Book(int catalogueNumber, String author, String title){
        this.catalogueNumber = catalogueNumber;
        this.author = author;
        this.title = title;
        this.borrower = null;
    }

    public Book(){   
        this.catalogueNumber = 0;
        this.author = " ";
        this.title = " ";
        this.borrower = null;
    }

    public String toString(){
        return "고유번호 :"+ catalogueNumber+"\n 제목 :" +title+"\n 작가 : "+ author;
    }

    public void display(){
        System.out.println( this.toString());
    }

    public boolean attachBorrower(Borrower borrower){//대출할때 책과 대출자를 연결해준다
        boolean result;
        this.borrower = borrower;
        ///성공여부
        if(borrower == null)
            result = false;
        else
            result= true;
        return result;
    }

    public boolean detachBorrower() { //반납할때  책과 대출자를 연결을 해지
        boolean result;
        this.borrower = null;
        ///성공여부
        if(borrower != null)
            result = false;
        else
            result= true;
        return result;
    }

    public Borrower getBorrower(){
        return this.borrower;
    }

    public int getCatalogueNumber(){
        return this.catalogueNumber;
    }

    public int compareTo(Object obj){
        Book book = (Book) obj;
        int bookCatalogueNumber = book.catalogueNumber;

        int result=0;
        if(catalogueNumber < bookCatalogueNumber)
            result = 1;
        else if(catalogueNumber ==bookCatalogueNumber)
            result = 0;
        else if(catalogueNumber >bookCatalogueNumber)
            result = -1;
        return result;
    }

    public boolean equals(Object obj){
        return this.compareTo(obj) == 0;
    }

}
