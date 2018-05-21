package ad;

import static java.lang.System.out;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Arrays;

public class combine {
	public static final int BUFSIZE = 2048 * 8;
	public static void mergeFiles(String outFile, String[] files) {
        FileChannel outChannel = null;
        out.println("合并 " + Arrays.toString(files) + " into " + outFile);
        try {
            outChannel = new FileOutputStream(outFile).getChannel();
            for(String f : files){
                Charset charset=Charset.forName("GBK");
                CharsetDecoder chdecoder=charset.newDecoder();
                CharsetEncoder chencoder=charset.newEncoder();
                FileChannel fc = new FileInputStream(f).getChannel(); 
                ByteBuffer bb = ByteBuffer.allocate(BUFSIZE);
                CharBuffer charBuffer=chdecoder.decode(bb);
                ByteBuffer nbuBuffer=chencoder.encode(charBuffer);
                while(fc.read(nbuBuffer) != -1){
                     
                    bb.flip(); 
                    nbuBuffer.flip();
                    outChannel.write(nbuBuffer);
                    bb.clear();
                    nbuBuffer.clear();
                }
                fc.close();
            }
            out.println("合并成功!! ");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {if (outChannel != null) {outChannel.close();}} catch (IOException ignore) {}
        }
    }
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
    //合并文件到out\\fen.txt
		String infiles="out\\stopci4";
		ArrayList<String> outfilelist=NLP.readDirs(infiles);
		int size=outfilelist.size();
	    String[] array = (String[])outfilelist.toArray(new String[size]);
		String out="out\\computerstop3.txt";
		mergeFiles(out,array);
	}
}
