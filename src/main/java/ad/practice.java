package ad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//本函数得到所有的违规词的前后2个词性，放在stopcixing。txt中
public class practice {
	public static void main(String[] args) throws FileNotFoundException, IOException {
	String filename=new String("out\\cixing");
	ArrayList<String> filelist = NLP.readDirs(filename);
    for(int i=0;i<filelist.size();i++){
		String name=NLP_filter.getFileNameNoEx(filelist.get(i));
		String sentence =NLP.readFile(filelist.get(i));
	    String[] words = sentence.split(" "); 
	    BufferedReader b1 = new BufferedReader(new FileReader("in\\stop.txt"));
	    String fName=filelist.get(i);
		File tempFile =new File(fName.trim());  
        String fileName = tempFile.getName();    
		BufferedWriter bw = new BufferedWriter(new FileWriter("out\\stopci3\\"+fileName));
	    String stop;
	    ArrayList<String[]> wordList = new ArrayList<String[]>();
	    ArrayList<String[]> stopList =new ArrayList<String[]>();
	    wordList=filter.get(words);
	    //bw.write(name+" ");
	    ArrayList<String> stopword=new ArrayList<String>();
	     //把找到的违法关键词放在stopword数组中
	    while((stop=b1.readLine())!=null){
	          if(sentence.contains(stop))
	        	  stopword.add(stop);
            	  //System.out.println(stop);
	       } 
	       if(stopword.size()!=0){
	       stopList=filter.getcixing(wordList,stopword);
	       for(int j=0;j<stopList.size();j++){    	
	        String[] stopword1=stopList.get(j);          	   
	        	  bw.write(stopword1[1]+"\n");
	       }//for的括号
	       }else{
	    	  bw.write(0); 
	       }
	      bw.close();
	      b1.close();
		}//文件列表循环的
	        System.out.println("运行完毕");
	    }//主函数的 
}//此classd的
