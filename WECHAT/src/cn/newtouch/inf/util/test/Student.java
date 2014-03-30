package cn.newtouch.inf.util.test;
import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("Student")
public class Student{
	private String name;
	private String age;
	
	public Student(){
		
	}
	
	public Student(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
}