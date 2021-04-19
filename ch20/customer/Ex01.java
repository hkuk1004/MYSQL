package customer;
import java.util.Scanner;
public class Ex01 {
	private static CustomerServiceImpl cs = new CustomerServiceImpl();
	private static Scanner sc;
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		while(true) {
			help();
			String command = sc.nextLine();
			if (command.equals("6")) break;
			else if (command.equals("1")) insert();
		}
		sc.close();
		System.out.println("프로그램 종료");
	}
	private static void insert() {
		System.out.println("id를 입력하세요");
		String id = sc.nextLine();
		System.out.println("이메일을 입력하세요");
		String email = sc.nextLine();
		System.out.println("이름을 입력하세요");
		String name = sc.nextLine();
		System.out.println("암호를 입력하세요");
		String pass = sc.nextLine();
		System.out.println("암호확인을 입력하세요");
		String confirmPass = sc.nextLine();
		Customer customer = new Customer(id, pass, email, name);
		if (!pass.equals(confirmPass)) {
			System.out.println("암호와 암호확인이 다릅니다");
			return;
		}
		int result = cs.insert(customer);
	}
	private static void help() {
		System.out.println("다음 명령어 중에서 선택 하시오");
		System.out.println("1. 입력");
		System.out.println("2. 수정");
		System.out.println("3. 조회");
		System.out.println("4. 삭제");
		System.out.println("5. 목록");
		System.out.println("6. 종료");
		System.out.println("명령번호");
	}
	
}