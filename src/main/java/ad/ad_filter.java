package ad;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
public class ad_filter {
/*分析240个案例句法，并提取规则
  1.把目录合并成一个，分词标注词性combine.java输出 fen.txt
  2.把其分成一句话一行的形式 split.java 输出sentence.txt
  3.输出含STOPWORDSS集中的行；有个问题就是有的不含关键词的  也违规了 因为分词技术的限制，比如说：卖爆了  而分词分成 卖 爆 了 三个词
  4.统计前后词的词性？ ad_filter1.java
  5.统计STOPWORDSS集中的词性？
  */
public static void main(String[] args) throws FileNotFoundException, IOException {
	try {		
		File inputfile = new File("out\\sentence.txt");
		RandomAccessFile raf = new RandomAccessFile(inputfile, "rw");
		//违反关键词库
		File inputfile1 = new File("in\\stop.txt");
		FileReader fr = new FileReader(inputfile1);
		BufferedReader reader = new BufferedReader(fr);
		//输出含违反关键词的句子
		File outfile = new File("out\\fen1.txt");
		FileWriter fw = new FileWriter(outfile);

		String sentence;
		String stop;
		while ((stop = reader.readLine()) != null) {
			//System.out.println(stop);
		while ((sentence = raf.readLine()) != null) {
			String words=new String(sentence.getBytes("ISO8859-1"),"UTF-8");
			//System.out.println(words);
			if(words.contains(stop)){
			fw.write(words+"\n");
			System.out.println(words);
			System.out.println("写入成功");
		}	else{
			
			//System.out.println("匹配不成功");
		}
			
			}
		 raf.seek(0);
		}
		System.out.println("写入完毕");
		fw.flush();
		fw.close();
		reader.close();
		fr.close();
		raf.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	}
}