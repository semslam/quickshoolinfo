package api.util;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Created by Ibrahim Olanrewaju Abdulsemiu DEV on 22/12/2016.
 */
public class StringUtil {

	public static String cutLastChar(StringBuffer sb){
		return cutLastChar(sb.toString());
	}
	

	public static String cutLastChar(String str){
		if (isEmpty(str)) return "";
		return str.substring(0, str.length()-1);
	}
	
	/**
	 * @param str
	 * @param num
	 * @return  
	 */
	public static String subLastString(String str,int num) {
		if(str==null) return "";
		
		return str.substring(str.length()-num);
		
	}
	/**
	 * @param str
	 * @param num
	 * @return  
	 */
	public static String subCutLastString(String str,int num) {
		if(str==null) return "";
		
		return str.substring(0,str.length()-num);
	}
	
	public static String cutStr(String str , int length){
		return cutStrWithDot(str , length,"");
	}

	public static String cutStrWithDot(String str , int length){
		return cutStrWithDot(str , length,"...");
	}

	public static String cutStrWithDot(String str , int length,String dot){
		if (str==null) return "";
		if (dot==null) dot = "";
		if (length<=0) return str;
		if (length<=dot.length()) return dot;
		
		if (str.length()>length)
			return str.substring(0,length - dot.length()) + dot;
		else 
			return str;
	}

	public static String parseString(String str){
		return parseString(str,"8859_1","utf-8");
	}

	public static String parseString(String str,String charsetFrom, String charsetTo){
		String result = "";
		if (str == null) return "";
		try{
			result = new String(str.getBytes(charsetFrom),charsetTo);
		}catch (Exception e){}
		return result;
	}

	public static String urlDecode(String str) {
		String result = "";
		try {
			result = URLDecoder.decode(str,"8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return parseString(result,"8859_1","utf-8");
	}
	
	/**
	 * url encode
	 * @param para
	 * @return
	 */
	public static String urlEncode(String para){
		return urlEncode(para,"UTF-8");
	}

	public static String urlEncode(String para,String charset){
		String result = "";
		try{
			result = URLEncoder.encode(para,charset);
		}catch(Exception e){}
		
		return result;
	}
	

	public static boolean isEmpty(String ...str){
		if (str == null) return true;
		for (int i=0;i<str.length;i++)
			if (str[i]!=null && !"".equals(str[i]))return false;
		
		return true;
	}

	public static boolean hasEmpty(String ...str){
		if (str == null) return true;
		for (int i=0;i<str.length;i++)
			if (str[i]==null || "".equals(str[i]))return true;
		
		return false;
	}

	public static int parseInt(String str){
		return parseInt(str , 0);
	}

	public static int parseInt(String str , int defaultValue){
		int result=defaultValue;
		if (str==null) return result;
		try{
			result = Integer.parseInt(str.trim());
		}catch (Exception e){}
		return result;
	}

	public static long parseLong(String str){
		long result=0;
		if (str==null) return result;
		try{
			result = Long.parseLong(str.trim());
		}catch (Exception e){}
		return result;
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0, nLen = src.length();
		char ch;
		while (lastPos < nLen) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					if (pos + 6 > nLen) break; //add by wxb
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = nLen;
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String upperFirst(String str){
		if (isEmpty(str)) return "";
		return str.substring(0,1).toUpperCase() + str.substring(1,str.length());
	}

	public static String parseClearStr(String str){
		String result = parseStr(str);
		if (result.length()>2)result = result.substring(1,result.length()-1);
		return result;
	}

	public static String parseStr(String str){
		if (str == null) return "";
				
		return listToString(strToList(str));
	}

	public static List<Integer> strToList(String str){
		List<Integer> result = new ArrayList<Integer>();
		if (str == null || "".equals(str)) return result;
		
		List<Integer> mid = new ArrayList<Integer>();
		String[] strs = str.split(",");
		for (int i=0;i<strs.length;i++)
			if (!"".equals(strs[i].trim()))
				try {
					mid.add(Integer.parseInt(strs[i]));
				}catch (Exception e){}
		
		if (mid.size()>0){
			java.util.Collections.sort(mid);
			int tmpInt = mid.get(0);
			result.add(tmpInt);
			for (int i=1; i<mid.size(); i++) {
				if (tmpInt != mid.get(i))
					result.add(mid.get(i));
				tmpInt = mid.get(i);
			}
		}
		
		return result;
	}

	public static List<Integer> strToListNoSort(String str){
		List<Integer> result = new ArrayList<Integer>();
		if (str == null || "".equals(str)) return result;
		
		String[] strs = str.split(",");
		for (int i=0;i<strs.length;i++)
			if (!"".equals(strs[i].trim()))
				try {
					int tmpInt = Integer.parseInt(strs[i]); 
					if (!result.contains(tmpInt))
						result.add(tmpInt);
				}catch (Exception e){}
		
		return result;
	}

	public static String collectionToString(Collection<Integer> list){
		String result = "";
		if (list == null || list.size() == 0) return result;
		
		StringBuffer sb = new StringBuffer();
		for (int i : list)
			sb.append(i).append(",");
		return cutLastChar(sb);
	}

	public static String listToString(List<Integer> list){
		String result = "";
		if (list == null || list.size() == 0) return result;
		
		java.util.Collections.sort(list);
		result = "," + String.valueOf(list.get(0)) + ",";
		int tmpInt = list.get(0);
		for (int i=1;i<list.size();i++){
			if (tmpInt!=list.get(i))
				result += String.valueOf(list.get(i)) + ",";
			tmpInt = list.get(i);
		}
		
		return result;
	}

	public static String join( int[] o , String flag ){
		StringBuffer str_buff = new StringBuffer();

		for(int i=0 , len=o.length ; i<len ; i++){
			str_buff.append( String.valueOf( o[i] ) );
			if(i<len-1)str_buff.append( flag );
		}

		return str_buff.toString();
	}

	public static String join( Object[] o , String flag ){
		StringBuffer str_buff = new StringBuffer();

		for(int i=0 , len=o.length ; i<len ; i++){
			str_buff.append( String.valueOf( o[i] ) );
			if(i<len-1)str_buff.append( flag );
		}

		return str_buff.toString();
	}
	

	public static String formatNum(int seq,String format){
		DecimalFormat  formatter = new DecimalFormat(format);
		 return formatter.format(seq);
	}

	public static String classToXmlString(Object object){
		String request = "";

		try {
			JAXBContext context = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			Writer writer = new StringWriter();
			marshaller.marshal(object, writer);
//            marshaller.marshal(bj,System.out);
			String str = writer.toString();
			request = str;
			writer.flush();
			writer.close();
//			System.out.println(str);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		catch (IOException ie){
			ie.printStackTrace();
		}
		return request;
	}


	public static Object parseXMLString(String xmlStr,Class iClass){
		Object result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(iClass);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			result = unmarshaller.unmarshal(new StringReader(xmlStr));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static void main(String[] args){
		System.out.println(StringUtil.formatNum(20,"000"));
	}
}