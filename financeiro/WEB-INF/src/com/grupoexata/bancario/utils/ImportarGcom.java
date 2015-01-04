package com.grupoexata.bancario.utils;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.grupoexata.bancario.dao.ModelLayout;
import com.grupoexata.bancario.struts.bean.BeanGcomimport;
import com.grupoexata.bancario.struts.bean.BeanLayoutitem;

public class ImportarGcom {
	
	private static final boolean DEBUG = false;
	public static int cont;	

	@SuppressWarnings("unchecked")
	public static Object createObject(String className){
		Object object = null;
		try {
			Class class0 = Class.forName(className);
			object = class0.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> void setValue(Class classObj, Object object, Class<T> classNameAtributte, String atributte, T value){
		Utils.setAtributo(object,classObj, atributte, classNameAtributte, value);
	}
	
	private static Object getValue(Class<?> classAtributo,String format, String value) throws ParseException{
		Object obj = null;
		if(classAtributo == java.util.Date.class || classAtributo == java.sql.Date.class || classAtributo == java.sql.Timestamp.class){
			obj =  Utils.strBRToDate(format, value);
		}else if(classAtributo == Float.class || classAtributo == Double.class || classAtributo == Long.class){
			obj = Utils.objectToStrBR(format, value);
		}else if(classAtributo == Integer.class){
			obj = Integer.parseInt(value);
		}else if(classAtributo == String.class){
			if(format == null || format.equals(""))
			obj = value;
			else{				
				if(value.contains("-")){
					value = value.replace("-", "");
					value = "-" + value;
				}
				value = value.trim();
				obj = Utils.objectToStrBR(format,new BigDecimal(value));
			}
		}
		return obj;
	}
public static /*Map<String, Object>*/ int process1(String str,List<BeanLayoutitem> l, List<BeanLayoutitem> lc, int cont){
		String tipo = str.substring(0,1);
		String dia = str.substring(37,39);
		String mes = str.substring(35,37);
		String ano = str.substring(31,35);		
		String data = dia+"/"+mes+"/"+ano;
		System.out.print(cont++ +  " " );		
		if (tipo.equals("0")){
			System.out.println(data);
		}		
		String contrato = str.substring(1,16);
		String cpf = str.substring(20,31);
		String cliente = str.substring(31,71);
		String cod_atendente = str.substring(219,229);
		String cod_vendedor = str.substring(209,219);
		String perc_exata_i = str.substring(140,142);
		String perc_exata_d = str.substring(142,144);
		String valor_finan_i = str.substring(103,107);
		String valor_finan_d = str.substring(107,109);
		String plano = str.substring(131,133);
		String produto = str.substring(128,130);
		String tabela = str.substring(136,139);
		
		if (tipo.equals("1")){
			System.out.println("Ordem: "+ Utils.zerosEsquerda(4, ""+cont) +" Tipo: "+tipo+" Contrato:"+contrato +" CPF:"+cpf+" "+cliente+" Perc:"+perc_exata_i+","+perc_exata_d+" Atend: "+cod_atendente+" Vend: "+cod_vendedor+" Vlr Finan:"+valor_finan_i+","+valor_finan_d + " " +plano+tabela+produto);
		}
		return cont;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> process(String str,List<BeanLayoutitem> l, List<BeanLayoutitem> lc, int cont){
		Map<String, Object> map = new HashMap<String, Object>();
		if(l !=null){
			for (BeanLayoutitem b : lc) {
					map.put(b.getLitnidcla(), createObject(b.getLitcnmcla()));
			}
			for (BeanLayoutitem b : l) {
				Object o = map.get(b.getLitnidcla());
				try {
					Class classAtributte = Class.forName(b.getLitcnmcla());
					boolean ok = false;
					if(!b.getLitcvald().equals("'") || classAtributte == java.lang.String.class){
					}
						Pattern padrao = Pattern.compile(b.getLitcvald());
						String value = b.get(str);
						if( padrao.matcher(collate_br(value)).matches()){
							Object obj = getValue(classAtributte, b.getLitcform(), value);
							if(obj != null){
								setValue(o.getClass(), o, classAtributte, b.getLitcnmatr(), obj);
								ok = true;
							}
						}					
					if(DEBUG){
						b.imprimir(cont,str);
						System.out.print("[" + (ok ? "X" : " ") + "]");
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Erro:");
					b.imprimir(cont,str);
				}
			}
			if(DEBUG){
				BeanGcomimport o = (BeanGcomimport)map.get("2");
				o.imprimir();
			}
		}
		return map;
	}
	
	private final static String acentuado = "Á«·ÈÌÛ˙˝¡…Õ”⁄›‡ËÏÚ˘¿»Ã“Ÿ„ıÒ‰ÎÔˆ¸ˇƒÀœ÷‹√’—‚ÍÓÙ˚¬ Œ‘€";
	private final static String semAcento = "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";
	private final static char[] tabela;
	
	static {
		tabela = new char[256];
		for (int i = 0; i < tabela.length; ++i) {
			tabela[i] = (char) i;
		}
		for (int i = 0; i < acentuado.length(); ++i) {
			tabela[acentuado.charAt(i)] = semAcento.charAt(i);
		}
	}

	public static String remover(final String s) {
		char[] cs = s.toCharArray();
		for (int i = 0; i < cs.length; ++i) {
			char c = cs[i];
			if (c < 256) {
				if(c > 127)
					cs[i] = ' ';
				else
					cs[i] = tabela[c];
			}
		}
		return new String(cs);
	}
	
	
	public static String collate_br(String str){
		return remover(str);
	}
	
	
	public static String getTipo(String str,Map<String,List<BeanLayoutitem>> maptipo){
		Set<String> keys = maptipo.keySet();
		Iterator<String> it = keys.iterator();
		boolean ret = false;
		String tipo = null;
		while(it.hasNext() && !ret){
			int i = 0;
			ret = true;
			String t = it.next();
			List<BeanLayoutitem> l = maptipo.get(t);
			while(i < l.size() && ret) {
				BeanLayoutitem b = l.get(i);
				try {
					Pattern padrao = Pattern.compile(b.getLitcvald());
					String value = b.get(str);
					ret = ( padrao.matcher(value).matches());
				} catch (Exception e) {
					e.printStackTrace();
				}
				i++;
			}
			if(ret){
				tipo = t;
			}
		}
		return tipo;
	}
	public static Map<String,List<BeanLayoutitem>> getMapTipo(ModelLayout m){
		List<BeanLayoutitem> ltipo = m.getLayoutitemsLitlinit();
		Map<String,List<BeanLayoutitem>> maptipo = new HashMap<String, List<BeanLayoutitem>>();
		List<BeanLayoutitem> tipo = new ArrayList<BeanLayoutitem>();
		String t = "";
		for (BeanLayoutitem beanLayoutitem : ltipo) {
			if(!t.equals(beanLayoutitem.getLitnidtip())){
				t = beanLayoutitem.getLitnidtip();
				tipo = new ArrayList<BeanLayoutitem>();
				maptipo.put(t, tipo);
			}
			tipo.add(beanLayoutitem);
		}
		return maptipo;
	}
	public static Map<String,List<BeanLayoutitem>> getMapItem(ModelLayout m,Map<String,List<BeanLayoutitem>> maptipo){
		Set<String> keys = maptipo.keySet();
		Iterator<String> it = keys.iterator();
		Map<String,List<BeanLayoutitem>> mapitem = new HashMap<String, List<BeanLayoutitem>>();
		while(it.hasNext()){
			String t = it.next();
			mapitem.put(t, m.getLayoutitems(t));
		}
		return mapitem;
	}
	
	public static void importar(File file) throws Exception {
		try {
			cont = 0;
			ModelLayout m = new ModelLayout();
			List<BeanLayoutitem> lc = m.getLayoutclasses();
			Map<String,List<BeanLayoutitem>> maptipo = getMapTipo(m);
			Map<String,List<BeanLayoutitem>> mapItem = getMapItem(m, maptipo);			
			BufferedReader in = new BufferedReader(new FileReader(file));
			String str;
			String tipo = null;
			List<Map<String, Object>> list =  new ArrayList<Map<String,Object>>();
			while (in.ready()) {
				str = in.readLine();
				tipo = getTipo(str, maptipo);
	            list.add(process(str, mapItem.get(tipo), lc, ++cont));
			}
			in.close();
			List<BeanGcomimport> gcomimports = new ArrayList<BeanGcomimport>();
			for (Map<String, Object> map : list) {
				Collection<Object> maps = map.values();
				for (Object object : maps) {
					if(object instanceof BeanGcomimport){
						gcomimports.add((BeanGcomimport) object);
					}
				}
			}
			for (BeanGcomimport gcomimport : gcomimports) {
				gcomimport.setGciyvalr();
				gcomimport.setGcinperc();
			}
			m.addGcomimports(gcomimports);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		PrintStream console = System.out;
		PrintStream out = new PrintStream(new BufferedOutputStream(
				new FileOutputStream("test.out")));
		System.setOut(out);
		System.setErr(out);
		try {
			cont = 0;
			ModelLayout m = new ModelLayout();
			List<BeanLayoutitem> lc = m.getLayoutclasses();
			Map<String,List<BeanLayoutitem>> maptipo = getMapTipo(m);
			Map<String,List<BeanLayoutitem>> mapItem = getMapItem(m, maptipo);			
			BufferedReader in = new BufferedReader(new FileReader("D:/workspace/teste/retorno/0694090515163759.txt"));
			String str;
			String tipo = null;
			List<Map<String, Object>> list =  new ArrayList<Map<String,Object>>();
			while (in.ready()) {
				str = in.readLine();
				tipo = getTipo(str, maptipo);
	            list.add(process(str, mapItem.get(tipo), lc, ++cont));
	            out.flush();
//				cont = process1(str, l, lc, cont);
			}
			in.close();
			List<BeanGcomimport> gcomimports = new ArrayList<BeanGcomimport>();
			for (Map<String, Object> map : list) {
				Collection<Object> maps = map.values();
				for (Object object : maps) {
					if(object instanceof BeanGcomimport){
						gcomimports.add((BeanGcomimport) object);
					}
				}
			}
			System.out.println();
			System.out.println();
			System.out.println();
			for (BeanGcomimport gcomimport : gcomimports) {
				System.out.println("====================");
				gcomimport.setGciyvalr();
				gcomimport.setGcinperc();
				gcomimport.imprimir();
			}
			m.addGcomimports(gcomimports);
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.close(); // Remember this!
		System.setOut(console);
	}
}
