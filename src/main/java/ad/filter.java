package ad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//前后2个词性的过滤
public class filter {
	 public static void main(String[] args) throws FileNotFoundException, IOException {
		String file=new String("out\\cixing\\1.txt");
	    String sentence =NLP.readFile(file);
	    String[] words = sentence.split(" "); 
	    BufferedReader b1 = new BufferedReader(new FileReader("in\\stop.txt"));
	    //BufferedWriter bw = new BufferedWriter(new FileWriter("out\\stopci2\\"+fileName));
	    String stop;
	    ArrayList<String[]> wordList = new ArrayList<String[]>();
	    ArrayList<String[]> stopList =new ArrayList<String[]>();
	    wordList=get(words);
	    ArrayList<String> stopword=new ArrayList<String>();
	     //把找到的违法关键词放在stopword数组中
	       while((stop=b1.readLine())!=null){
	          if(sentence.contains(stop))
	        	  stopword.add(stop);
            	  //System.out.println(stop);
	       } 
	    
	      stopList=getcixing(wordList,stopword);
	      for(int i=0;i<stopList.size();i++){
	    	boolean flag;
	        String[] stopword1=stopList.get(i);
	        flag=judgement(stopword1);
            if(flag)
            	System.out.println(stopword1[0]);
            	//bw.write(stopword1[0]);
	        }
	      //bw.close();
	        System.out.println("运行完毕");
	    } 
	 //判断前后两个词的词性
	 private static boolean judgement(String[] stopword1) {
		// TODO Auto-generated method stub
		     String cixing=stopword1[1];
		     boolean flag=false;
		     String a="";
		     Pattern p1 = null; //正则表达式
		     Matcher m = null; //操作的字符串
		     boolean b = false;
		    
		     System.out.println("判断完毕");
			return flag;
		
	}//此函数的
	// 将分好的词每5个一组存到数组中  
	 public static ArrayList<String[]> get(String[] words){
		 int length = words.length;  
         ArrayList<String[]> wordList = new ArrayList<String[]>();  
	        for (int i = 0; i < length; i++) { 
	        	//System.out.println(words[i]);
	            String[] wordSeg = new String[5];  
	            if (i == 0) {  
	                //wordSeg[0] ="null";  
	               // wordSeg[1] = "null";  
	                wordSeg[2] = words[i];
	                wordSeg[3] = words[i + 1];
	                wordSeg[4] = words[i + 2];            
	            }else if(i==1)	{
	             
	                wordSeg[1] = words[i-1];  
	                wordSeg[2] = words[i];
	                wordSeg[3] = words[i + 1];
	                wordSeg[4] = words[i + 2];
	            }else if(i==length-1){
	            	wordSeg[0] = words[i-2]; 
	                wordSeg[1] = words[i-1];  
	                wordSeg[2] = words[i];
	                 	
	            }else if(i==length-2){
	            	wordSeg[0] = words[i-2]; 
	                wordSeg[1] = words[i-1];  
	                wordSeg[2] = words[i];
	                wordSeg[3] = words[i+1];
	                 	
	            }else{
	            	wordSeg[0] = words[i-2]; 
	                wordSeg[1] = words[i-1];  
	                wordSeg[2] = words[i];
	                wordSeg[3] = words[i+1];
	                wordSeg[4] = words[i+2];   	
	            }
	            wordList.add(wordSeg);    	
	            }
	        return wordList;	       
	 }

	//获得违法关键字前2个后2个的词性
	 public static ArrayList<String[]> getcixing(ArrayList<String[]> wordList,ArrayList<String> stopword){	 
		 ArrayList<String[]> stopList=new ArrayList<String[]>();
		 //遍历一个广告文本中违规的字符
	  for(String stop:stopword){
	    //遍历每5个字符的二维数组
		  for(int j=0;j<wordList.size();j++){
	      String[] wordSegStr = wordList.get(j);
	      int index = wordSegStr[2].indexOf('/');
	      String xing = wordSegStr[2].substring(index+1,wordSegStr[2].length());//这个词性
	      String word=wordSegStr[2].substring(0, index);	    
	    	 if(word.equals(stop)){ 
	    		String[] stoplist = new String[2];
	    		char ci[]=new char [5];
	    		ci[2]=xing.charAt(0);			
	    		int index_3,index_4,index_0,index_1;
	    		if(wordSegStr[0]==null&&wordSegStr[0]==null){
	    			index_3= wordSegStr[3].indexOf('/');
	    			index_4= wordSegStr[4].indexOf('/');
	    			ci[0]=' ';
	    			ci[1]=' ';
	    			ci[3]=wordSegStr[3].substring(index_3+1,wordSegStr[3].length()).charAt(0);
	    			ci[4]=wordSegStr[4].substring(index_4+1,wordSegStr[4].length()).charAt(0);
	    			System.out.println("处于第一位置");
	    		}else if(wordSegStr[0]==null&&wordSegStr[1]!=null){
	    			index_1= wordSegStr[1].indexOf('/');
	    			index_3= wordSegStr[3].indexOf('/');
	    			index_4= wordSegStr[4].indexOf('/');
	    			ci[0]=' ';
	    			ci[1]=wordSegStr[1].substring(index_1+1,wordSegStr[1].length()).charAt(0);
	    			ci[3]=wordSegStr[3].substring(index_3+1,wordSegStr[3].length()).charAt(0);
	    			ci[4]=wordSegStr[4].substring(index_4+1,wordSegStr[4].length()).charAt(0);
	    			System.out.println("处于第二位置");
	    		}else if(wordSegStr[3]==null&&wordSegStr[4]==null){
	    			index_0= wordSegStr[0].indexOf('/');
	    			index_1= wordSegStr[1].indexOf('/');
	    			ci[0]=wordSegStr[0].substring(index_0+1,wordSegStr[0].length()).charAt(0);
	    			ci[1]=wordSegStr[1].substring(index_1+1,wordSegStr[1].length()).charAt(0);
	    			ci[3]=' ';
	    			ci[4]=' ';
	    			System.out.println("处于最后位置");
	    		}else if(wordSegStr[4]==null&&wordSegStr[3]!=null){
	    			index_0= wordSegStr[0].indexOf('/');
	    			index_1= wordSegStr[1].indexOf('/');
	    			index_3= wordSegStr[3].indexOf('/');
	    			ci[0]=wordSegStr[0].substring(index_0+1,wordSegStr[0].length()).charAt(0);
	    			ci[1]=wordSegStr[1].substring(index_1+1,wordSegStr[1].length()).charAt(0);
	    			ci[3]=wordSegStr[3].substring(index_3+1,wordSegStr[3].length()).charAt(0);
	    			System.out.println("处于倒数第二位置");
	    			ci[4]=' ';  			    	 
	    		}else{
	    			index_0= wordSegStr[0].indexOf('/');
	    			index_1= wordSegStr[1].indexOf('/');
	    			index_3= wordSegStr[3].indexOf('/');
	    			index_4= wordSegStr[4].indexOf('/');
	    			ci[0]=wordSegStr[0].substring(index_0+1,wordSegStr[0].length()).charAt(0);
	    			ci[1]=wordSegStr[1].substring(index_1+1,wordSegStr[1].length()).charAt(0);
	    			ci[3]=wordSegStr[3].substring(index_3+1,wordSegStr[3].length()).charAt(0);
	    			ci[4]=wordSegStr[4].substring(index_4+1,wordSegStr[4].length()).charAt(0);	
	    			System.out.println("正常位置");
	    		}
	    			    stoplist[0]=stop;
	    			    stoplist[1]=new String(ci);
	    			    stopList.add(stoplist);
	    			   
	    			    System.out.println(stoplist[0]);
	    			    System.out.println(stoplist[1]);        
	    		  }//if的括号
		    }//wordList
		 }//stop的循环
	    System.out.println("返回");		
		return stopList;		  
	 }//此函数的
	
}//主class的	
   
	
