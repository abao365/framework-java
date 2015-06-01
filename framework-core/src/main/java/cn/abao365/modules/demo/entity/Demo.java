package cn.abao365.modules.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * @author ChengWenFeng
 * @date 2014年8月20日 下午2:02:08
 *
 */
public class Demo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String uuid;
	
	private String userName;
	
	private String password;
	
	private String sex;
	
	private int age;
	
	private Date createTime;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "User [uuid=" + uuid + ", userName=" + userName + ", password="
				+ password + ", sex=" + sex + ", age=" + age + ", createTime="
				+ createTime + "]";
	}
	
	

}
