package cn.newtouch.inf.util.test;

import com.thoughtworks.xstream.XStream;

public class XStramTest {
	public static void main(String[] args) {
		XStream x = new XStream();
		x.processAnnotations(Student.class);
		System.out.println(x.toXML(new Student("小样","32")));
		System.out.println(x.toXML(new Student("小样","32")));
		System.out.println(x.toXML(new Student("小样","33")));
	}
}


