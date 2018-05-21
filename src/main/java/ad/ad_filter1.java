package ad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//直接 按规则过滤  不训练 模型 
public class ad_filter1 {
//合成computerstop2.txt
	public static void main(String[] args) throws FileNotFoundException, IOException {
	try{
	//String word=NLP.readFile(file);
	String filename="out\\cixing";
	
	ArrayList<String> filelist = NLP.readDirs(filename);
	for(int i=0;i<filelist.size();i++){
		boolean flag=false;//判断是否可以输出违法关键字
		String name=NLP_filter.getFileNameNoEx(filelist.get(i));
		BufferedReader b1 = new BufferedReader(new FileReader("in\\stop (2).txt"));
		String fName=filelist.get(i);
		File tempFile =new File(fName.trim());  
        String fileName = tempFile.getName();    
		BufferedWriter bw = new BufferedWriter(new FileWriter("out\\stop2\\"+fileName));
	 String word=NLP.readFile(filelist.get(i));
	//着了存放的是广告分词，标注词性的内容
	String ra[]=word.split(" ");
	bw.write(name+" ");
	String stop;
	int size=0;
	Set<String> set = new HashSet<String>();
    while((stop=b1.readLine())!=null){
    	flag=compare(stop,ra);
    	//System.out.println("标志是"+flag);   	
    	if(flag)
    	 set.add(stop);
    	 //size=size+1;
    	 //bw.write(stop+" ");
       // System.out.println(stop+"是违法关键词");
    	
    		//System.out.println(stop+"不是违法关键词");  	
    }
    for(String s:set){
    	size=set.size();
    	bw.write(s+" ");
    	//System.out.println(s+"是违法关键词");
    }
    System.out.println("违法关键词个数"+size);
    bw.write(Integer.toString(size));
    bw.close();
	b1.close();
	    
	}
	} catch (IOException e) {
		e.printStackTrace();
	}
	System.out.println("运行完毕");
    
	}
	public static boolean bejudgment(char before){
		String be=String.valueOf(before);
		boolean flag=false;
		Pattern p = Pattern.compile("[dbmcranvuqp]"); //正则表达式
		Matcher m = p.matcher(be);//操作的字符串
		System.out.println(be);
		flag = m.matches();
		 System.out.println("前面词性判断");
		return flag;
		
	}
   public static boolean afjudgment(char after){
	   String af=String.valueOf(after);
		boolean flag=false;
		Pattern p = Pattern.compile("[dbacnmvuqp]"); //正则表达式
		Matcher m = p.matcher(af);//操作的字符串
		 System.out.println(af);
		 flag = m.matches();
		 System.out.println("后面词性判断");
		return flag;
		
	}
//词性判断函数
	public static boolean judgment1(char before,char ci){
	System.out.println("before的进行词性判断");
	boolean flag2=true;
		if(ci=='n'){//名词
	if(before=='a'||before=='v'||before=='n'||before=='b'||before=='u')
		flag2=true;
	else
		flag2=false;
	}
	else if(ci=='a'){//形容词
		if(before=='v'||before=='n'||before=='a'||before=='u'||before=='d')
			flag2=true;
			else
				flag2=false;
			}
	else if(ci=='v'){//动词
		if(before=='n'||before=='v'||before=='a')
			flag2=true;
			else
				flag2=false;
			}
	else if(ci=='d')//副词 时刻、即刻
	{
		if(before=='n'||before=='a'||before=='v')
		     flag2=true;
		else
			flag2=false;
		}else if(ci=='t')//时间词 分钟
		{
			if(before=='n'||before=='v')
				 flag2=true;
			else
				flag2=false;
			}
		else if(ci=='q')//数词  第一等
		{
			if(before=='m')
				 flag2=true;
			else
				flag2=false;
			}
	return flag2;
	}
    public static boolean judgment2(char ci,char after){
    	System.out.println("after的进行词性判断");
    	System.out.println(ci);
    	System.out.println(after);
    	boolean flag1=true;
    	if(ci=='n'){//名词
    		if(after=='a'||after=='v'||after=='n')
    		 flag1=true;
    		
    		else
    			 flag1=false;
    		}
    		else if(ci=='a'){//形容词
    			if(after=='n'||after=='a'||after=='v')//
    				flag1=true;
        		else
        			 flag1=false;
        		}
    		else if(ci=='v'){//动词
    			if(after=='v'||after=='n'||after=='a'||after=='u')
    				flag1=true;
        		else
        			 flag1=false;
        		}
    		else if(ci=='d')//副词 时刻、即刻
    		{
    			if(after=='n'||after=='a'||after=='v')
    				flag1=true;
        		else
        			 flag1=false;
        		}else if(ci=='q')//时间词 
    			{
    				if(after=='v'||after=='a')
    					flag1=true;
    	    		else
    	    			 flag1=false;
    	    		}
    		return flag1;
	}
//关键字与广告内容比较
    public static boolean compare(String stop,String[] ra){
    	char before = ' ';//定义为违反关键词前面的词性
		char after=' ';//定义为违反关键词后面的词性
		boolean bejudg=false;
		boolean afjudg=false;
    	int i=0;
    	int le=ra.length;//每个字符的长度
    	//System.out.println(le);
    	for(i=0;i<le;i++){
    	int index = ra[i].indexOf('/');
		if(index!=-1){
			String cixing = ra[i].substring(index+1,ra[i].length());//这个词性
			char ci=cixing.charAt(0);
			//System.out.println(ci);
			String temp = ra[i].substring(0, index);//这个词		    			
			if(stop.equals(temp)){//如果找到违反关键词
				System.out.println(stop);	
				int be=i-1;
			    int af=i+1;
			    //System.out.println(be);
			    //System.out.println(af);
			 if(be>=0){
				int index1 = ra[be].indexOf('/');
				before=ra[be].substring(index1+1,ra[be].length()).charAt(0);
				bejudg=judgment1(before,ci);
				//bejudg=bejudgment(before);
				System.out.println(bejudg);
			 }else{ 
			 System.out.println("处于第一个位置");
			 } 
			if(af<=le-1){
				int index2 = ra[af].indexOf('/');
				after=ra[af].substring(index2+1,ra[af].length()).charAt(0);
				//afjudg=afjudgment(after);
				afjudg=judgment2(ci,after);
				System.out.println(afjudg);
			 }else 
			 System.out.println("处于最后一个位置");
			 } 	
		}
		//System.out.println("查询下一个词");
		}
    	// System.out.println(afjudg);	
		if(bejudg||afjudg)				 
			return true;//stop是违法关键字
			else return false;//stop不是违法关键字	
        		
	}
    
}
