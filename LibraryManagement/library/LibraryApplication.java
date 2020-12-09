package library;
import java.util.*;
import java.lang.String;


public class LibraryApplication
{
    private int getSelection(){
    	
        Scanner s = new Scanner(System.in);
        System.out.println("=========sunmoon Library=======");
        System.out.println("0 : 종료 (Quit)");
        System.out.println("1 : 이용자 등록 (Register one borrower)");
        System.out.println("2 : 책 등록 (Register one book)");
        System.out.println("3 : 대출 가능 목록 (Display books for loan)");
        System.out.println("4 : 대출 중인 목록 (Display books on loan)");
        System.out.println("5 : 대출 (Lend one book)");
        System.out.println("6 : 반납 (Return one book)");
        System.out.println();
        System.out.print("번호를 입력하시오>>");

        int num =99999999;//입력한 숫자를 반환
        try {
            num= s.nextInt();
            //s.nextLine();
        }catch (InputMismatchException e){//숫자 이외의 문자열을 입력했을때
            System.out.println("    숫자를 입력하세요");
        }
        if(num< 0 || num >6){//0~6이외의 번호를 입력했을때
            System.out.println("    0~6사이의 숫자를 입력하세요");
        }
        return num;
    }

    public void run(){
        int num;
        boolean nameresult = true;
        boolean result = false;
        Scanner s = new Scanner(System.in);
        Scanner ss = new Scanner(System.in);
        Library sunmoon = new Library("Sunmoon Libray");//Library 에서 객체 생성 
        do{
            num=getSelection();
            int aaa=0;
            switch(num){
            
                case 0: //종료
                System.out.println("-------------프로그램을 종료합니다.-------------");
                break;

                case 1: //이용자 등록 
                System.out.print("  등록할 이용자 이름을 입력하세요>>>");
                String name = s.next(); //숫자나 특수문자를 받을 경우 등록불가
                
                for(int i = 0 ; i < name.length(); i++){
                    if((name.charAt(i) >= 65 && name.charAt(i)<=90 )||(name.charAt(i) >= 97 && name.charAt(i)<=122)){
                        //소문자 대문자
                        nameresult = true;
                    }else if (name.charAt(i)== ' '){
                        nameresult = true;//공백
                    }else{
                        nameresult = false; //숫자및 특수문자
                    }
                }

                if(nameresult==false){
                    System.out.println("   숫자 및 특수문자는 사용이 불가능합니다.");
                    break;
                }

                result= sunmoon.registerOneBorrower(name);//리턴
                
                if(result == true){
                    System.out.println("        등록 성공!");
                }else if(result ==false ){
                    System.out.println("        등록 실패");
                }
                break;

                case 2://책 등록

                System.out.print("  등록할 책 이름을 입력하세요>>>");
                String bname = s.next();
                System.out.print("  등록할 책 저자를 입력하세요>>>");
                String author = ss.next();
                System.out.print("  등록할 책 고유번호를 입력하세요>>>");
                int cn;
                try {
                    cn= s.nextInt();
                }catch (InputMismatchException e)
                {
                    System.out.println("고유번호가 잘못되었습니다.");
                    System.out.println("    등록 실패");
                                 aaa=1;
                    break;
                }
                result = sunmoon.registerOneBook(cn,author,bname);//리턴 예외처리
                if(result == true){
                    System.out.println("        등록 성공!");
                }else if(result == false){
                    System.out.println("        등록 실패");
                }
                break;

                case 3://대출 가능한 책 출력

                sunmoon.displayBookForLoan();
                break;

                case 4://대출중 출력

                sunmoon.displayBookOnLoan();
                break;

                case 5://대출

                System.out.print("  대출할 책의 고유번호를 입력하세요>>>");
                int lendcn;
                try {
                    lendcn = s.nextInt();
                }catch (InputMismatchException e)//숫자 이외의 값 들어왔을때
                {
                    System.out.println("    고유번호가 잘못되었습니다.");
                    System.out.println("        대출 실패");
                  aaa=1;
                    break;
                }
                System.out.print("  대출자 이름을 입력하세요>>>");
                String borrwername=s.next();

                Book lendbook = null;
                lendbook=sunmoon.lendOneBook(borrwername,lendcn);//

                if(lendbook == null){
                    break;
                }
                System.out.println("---빌리는 책의 정보---");
                System.out.println(lendbook.toString()); //책이없을경우 예외처리

                break;

                case 6://반납
                System.out.print("  반납할 책의 고유번호를 입력하세요>>>");
                int returncn;
                try {
                    returncn = s.nextInt();
                }catch (InputMismatchException e)//숫자가 아닐때
                {
                    System.out.println("    고유번호가 잘못되었습니다.");
                    System.out.println("        반납 실패");
                    
                    break;
                }
                
                boolean returnresult=  sunmoon.returnOneBook(returncn);
                if(returnresult == true  ) {//리턴
                    System.out.println("        반납 완료");
                }else if(returnresult==false) {
                    System.out.println("        반납 실패");
                }
                break;
            }
            
            if(aaa==1)
           s.next();
        }

        while(0!=num);
    }

}
