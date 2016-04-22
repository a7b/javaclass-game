import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class Words{
	public Words() throws IOException{
		//Process wc = Runtime.getRuntime().exec("wc -l /usr/share/dict/words");
		//final int wordcount = wc.getInputStream().;
		int wordCount = countLines("/usr/share/dict/words");
		System.out.println(wordCount);
	}
	public static void main(String[] args) throws IOException{
		new Words();
	}
	public static int countLines(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}
}
