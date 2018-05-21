package ad;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

//去除词性标注里面的标点符号
//输入文件夹out//fencixing
//输出文件夹out//cixing
public class remove {
	public static void main(String[] args) throws Exception {
		String files="out\\fencixing";
		ArrayList<String> filelist = NLP.readDirs(files);
		 for(int i=0;i<filelist.size();i++){
			 String fName=filelist.get(i);
			 System.out.println(fName);
			 String word=NLP.readFile(fName);//文件夹里的内容
			 File tempFile =new File(fName.trim());  
		     String fileName = tempFile.getName();    
		     File outfile = new File("out\\cixing\\"+fileName);
	         FileWriter fw = new FileWriter(outfile);
			 String ra[]=word.split(" ");
			 for(int j=0;j<ra.length;j++){
				 int index=ra[j].indexOf("/");
				 if(index!=-1){
				 String cixing=ra[j].substring(index+1,ra[j].length());
				 char ci=cixing.charAt(0);
				 if(ci=='w'){
			     fw.write("");
				}else{
					fw.write(ra[j]+" ");
				}
				 }else{
					 fw.write(ra[j]);
				 }
			 }                         	        
	          System.out.println(outfile);
	          System.out.println("写入成功！");
	  		  fw.close(); 
		 }
		 System.out.println("完毕");
	}
	
}