package org.googlecode.tmock.persistent;

import java.io.Serializable;

/**
 * @author zhongfeng
 *
 */
public class Passenger implements Serializable{

	private String name;
	private int age;
	
	/**
     * 
     */
    public Passenger() {
    }
    /**
	 * @param name
	 * @param age
	 */
	public Passenger(String name, int age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [age=" + age + ", name=" + name + "]";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}
