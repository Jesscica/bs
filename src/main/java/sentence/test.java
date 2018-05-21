package sentence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import ad.NLP;

public class test {
/*主函数流程
1.对一个分好词的文本分句，句子按行test.java
2.对每行句子进行词性标注和句法分析
3.写入进去
*/	
	public static void  main(String[] args) throws FileNotFoundException, IOException {
		String filename="out\\fen";		
		ArrayList<String> filelist = NLP.readDirs(filename);
		for(int i=0;i<filelist.size();i++){	
		  String word=NLP.readFile(filelist.get(i));		
		  String fName=filelist.get(i);
		   File tempFile =new File(fName.trim());  
	      String fileName = tempFile.getName();    
		  BufferedWriter bw = new BufferedWriter(new FileWriter("out\\sentence\\"+fileName));
		     String[] adlist=word.split("[，。；？！、:,.;?!]");
	          for(int j=0;j<adlist.length;j++){
	        	 bw.write(adlist[j]+"\n"); 
	          
	          }
		
	          bw.close();	             
} 
	System.out.println("运行完毕");	
	}
}