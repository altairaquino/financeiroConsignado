package com.grupoexata.auditoria.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		int max = 500;
		for (int i = 0; i < max; i++) {
			int a = (int)(Math.random()* (max));
			int index = Collections.binarySearch(list, a); 
			while(index > -1){
				a = (int)(Math.random()* (max));
				index = Collections.binarySearch(list, a);
			}
			list.add(a);
			Collections.sort(list);
		}
		StringBuffer buffer = new StringBuffer();/**/
		buffer.append("Numeros: ");
		for (Integer i : list) {
			buffer.append(i + ",");
		}/**/
		System.out.println(buffer.toString() + "****");
	}

}
