/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：tech14Memcached
 * 描述信息: 
 * 创建日期：2015年12月18日 上午10:26:04 
 * @author malitao
 * @version 
 */
package m1.bean;

import java.io.Serializable;

/** 
 *  
 * 创建日期：2015年12月18日 上午10:26:04 
 * @author malitao
 */
public class TestBean implements Serializable{  
	private static final long serialVersionUID = 5344571864700659321L;  
    private String name;  
    private Integer age;  
    //get、set方法略  
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}  
