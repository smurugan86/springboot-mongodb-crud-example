package net.guides.springboot.crud.utils;

import java.util.Collections;
import java.util.Objects;

import org.apache.tomcat.util.buf.StringUtils;

public class smruntest {
	public static void main(String org[]) {	
		System.out.println("testing");
		String a = "";
			//if(Collections.nonNull(a)) {
			//if(org.apache.tomcat.util.codec.binary.StringUtils.is)	
				
			//}
		if(a!=null) {
			System.out.println("String null check");
		}
		
		if(Objects.nonNull(a)) {
			System.out.println("Object is not empty check");
		} else {
			System.out.println("Object is null and empty");
		}
		
		if(a!=null && a.isEmpty()) {
			System.out.println("String is not empty check");
		} else {
			System.out.println("String is null or empty ");
		}
	}
}