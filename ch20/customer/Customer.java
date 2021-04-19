package customer;
import java.sql.Date;
public class Customer {
	private String id; 
	private String pass;
	private String email; 
	private String name;
	private Date reg_date;
	
	Customer() {}
	Customer(String id,String pass, String email, String name) {
		this.id = id; this.pass = pass; this.email = email; this.name = name;
	}
	public String toString() {
		return "아이디:"+id+", 암호:"+pass+", 이메일:"+email+", 이름:"+name+", 가입일:"+reg_date;
	}
	public String getId() {			return id;	}
	public void setId(String id) {	this.id = id;	}
	public String getPass() {		return pass;	}
	public void setPass(String pass) {		this.pass = pass;	}
	public String getEmail() {		return email;	}
	public void setEmail(String email) {	this.email = email;	}
	public String getName() {		return name;	}
	public void setName(String name) {		this.name = name;	}
	public Date getReg_date() {		return reg_date;	}
	public void setReg_date(Date reg_date) {	this.reg_date = reg_date;	}
	
}