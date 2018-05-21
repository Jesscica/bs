package ad;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//处理stopcixing.txt中，
public class count {
	public static void main(String[] args) throws FileNotFoundException, IOException {
    String file="out\\stopcixing1.txt";
    BufferedReader b1 = new BufferedReader(new FileReader(file));
	String word;
	ArrayList<String> stopword=new ArrayList<String>();
    while((word=b1.readLine())!=null){
      stopword.add(word);
    }
    cixingcount(stopword);
    b1.close();
    System.out.println("运行完毕");
	}//主函数的

	private static void cixingcount(ArrayList<String> stopword) {
		// TODO Auto-generated method stub
		int ncount=0;
		int number=0;
		Set<String> set = new HashSet<String>(); 
		//违法关键词前面的1个词性105中情况放入set中
		for(String stop:stopword){
			String mid1=stop.substring(3, 4);
			set.add(mid1);
			//System.out.println(mid1);
		}//for循环的
		//查找105种情况中 每种的可能
		for(String a:set){	
			number++;
			int total=0;
			for(String stop:stopword){
				String midd=stop.substring(3, 4);
				if(a.equals(midd)){
				total=total+1;
				System.out.println(a+" "+total);
				}
				
			}//for循环的
			//System.out.println(a);	
		}
		System.out.println(number);
	}//此函数的
}//此class