package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
	 private static final long serialVersionUID = 1L;
	
	  private static long idCounter = 1;
	  
	 
	private long userId;
	private String name;
	private String eglName;
	private String email;
	private String password;
	private String phone;
	private LocalDate birth; 
    private Gender gender;
    private List<Account> accounts;
    
    
    public User() {
    }
    
    
    

    public User(long userId, String name, String eglName, String email, String password, String phone, LocalDate birth,
			Gender gender) {
		super();
		this.userId = generateUniqueId();
		this.name = name;
		this.eglName = eglName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.birth = birth;
		this.gender = gender;
		 this.accounts = new ArrayList<>();
	}




	private synchronized long generateUniqueId() {
        return idCounter++;
    }
    
    

    public void setUserId(long userId) {
		this.userId = userId;
	}


	public long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEglName() {
        return eglName;
    }

    public void setEglName(String eglName) {
        this.eglName = eglName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    // 添加帳戶到 User 的方法
    public void addAccount(Account account) {
        this.accounts.add(account);
        account.setUser(this);
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", eglName=" + eglName +
                ", email=" + email + ", password=" + password + ", phone=" + phone +
                ", birth=" + birth + ", gender=" + gender + "]";
    }
    

}
