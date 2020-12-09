package library;
import java.util.*;

public class Library{

    private String name;
    TreeSet<Book> registeredBook;//책 목록
    HashSet<Borrower> registeredBorrower;//이용자 목록
    
    public Library(String name){
        this.name =name;
        registeredBook = new TreeSet<Book>();
        registeredBorrower = new HashSet<Borrower>();
    }

    public boolean registerOneBorrower(String name){//이용자 등록 SD1
        Iterator iter = registeredBorrower.iterator();
        while(iter.hasNext()){//끝까지 탐색
            Borrower borrower=(Borrower)iter.next();
            if(name == borrower.getName()){
                System.out.println("    동일한 이름의 이용자가 존재합니다");
                return false;  
            }
        }
        Borrower borrower = new Borrower(name); //1.1 new()
        boolean result = registeredBorrower.add(borrower); //1.2 add(borrower : Borrower) HashSet책목록에추가
        return result;                                   //1.2.1 return registerBorrowerState : boolean
    }

    public boolean registerOneBook(int catalogueNumber, String author, String title){//책 등록 SD2
        Iterator iter = registeredBook.iterator();
        while(iter.hasNext()){//끝까지 탐색
            Book book = (Book)iter.next();
            if(catalogueNumber == book.getCatalogueNumber()){
                System.out.println("    동일한 고유번호의 책이 존재합니다");
                return false;  
            }
        }
        Book book = new Book(catalogueNumber, author, title);       //1.1 new()
        boolean result = registeredBook.add(book);                //1.2 add(book : Book) Treeset이용자 목록에 추가
        return result;                                              //1.2.1 return registerBookState : boolean
    }

    public void displayBookForLoan(){                           //대출 가능 책 display SD3
        Iterator iter = registeredBook.iterator();
        System.out.println(" ---   대출 가능 목록   --- ");
        int i =1;
        
        while(iter.hasNext()==true) {                    //책set 끝까지 탐색
            Book book = (Book)iter.next();
            if(book.getBorrower()==null) {                //이용자와 연결이 안되어있는 경우
            
                System.out.println("<"+i+" 번째 책>");
                book.display();                         //display
                System.out.println();

                i++;
            }
            
        }
        if(i == 1){
            System.out.println("        Empty");
        }
    }

    public void displayBookOnLoan() {                            //대출 중인 책 display SD4
        Iterator iter = registeredBook.iterator();
        System.out.println("---   대출 중인 목록   --- ");
        int i =1;
        
        while(iter.hasNext()==true) {                     //책set 끝까지 탐색
            Book book = (Book)iter.next();
            if(book.getBorrower()!=null) {                //이용자와 연결이 되어있는경우
              System.out.println("<"+i+" 번째 책>");
                book.display();                         //display
                System.out.println();
                i++;
            }
        }
        if(i == 1){
            System.out.println("        Empty");
        }
    }

    public Book lendOneBook(String name, int catalogueNumber) {       //책 대출 SD5
    
        Book book = this.findBook(catalogueNumber);             //고유번호에 해당하는 책 찾기
        Borrower borrower = this.findBorrower(name);   //이름으로 이용자 찾기

        if(book ==null){
            System.out.println("    책이 존재하지 않습니다.");
            return null;
        }else if(borrower == null){
            System.out.println("    이용자가 존재하지 않습니다.");
            return null;
        }

        if(book.getBorrower() != null ){//대출중
            System.out.println("    책이 대출중입니다.");
            return null;
        }

        book.attachBorrower(borrower);                          //책에 이용자 연결
        borrower.attachBook(book);                              //이용자에 책 연결

        return book;                                        //성공할 경우 대출 책 정보 리턴 
    }

    public boolean returnOneBook(int catalogueNumber) {                    //책 반납 SD6
        Book book = this.findBook(catalogueNumber);             //고유번호에 해당하는 책 찾기
        
        if(book ==null){
            System.out.println("    대출중인 책이 아닙니다.");
            return false;
        }
        Borrower borrower = book.getBorrower();                 //book에 연결된 이용자
         book.detachBorrower();                                 //책에 이용자 끊기
         borrower.detachBook(book);                              //이용자&책 끊기
        return true;
    }

    private Book findBook(int catalogueNumber) {                       //책 찾기
    
        Book foundBook = null;       //찾은책                         
        Iterator iter = registeredBook.iterator();                
        while(iter.hasNext() == true) {                             //끝까지 탐색
            Book book = (Book)iter.next();
            int bookCatalogueNumber = book.getCatalogueNumber();    //비교하기 위해 고유번호 변수에 저장
            if(catalogueNumber == bookCatalogueNumber){             //입력받은 책번호와 현재 책 번호 비교
            
                foundBook = book;                                   //같은 경우 foundBook에 책정보 넣기
                break;                   
            }
        }
        return foundBook;    //찾은 책 정보 리턴
    }

private Borrower findBorrower(String name) {                       //이용자 찾기
        Borrower foundBorrower = null;                      	//처음에는 null
        Iterator iter = registeredBorrower.iterator();
        
        while(iter.hasNext() == true) {                         //끝까지 탐색
            Borrower borrower = (Borrower)iter.next();
            String borrowerName = borrower.getName();      //비교하기 위해 이름 변수에 저장    
            
            if(borrowerName.equals(name)){
                foundBorrower = borrower;
                break;          //찾은 이용자 정보 리턴
            }
        }
        return foundBorrower;  
    }

}
