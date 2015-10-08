package cbnu.ailab.library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CSV {
	private ArrayList<String> attributes = new ArrayList<String>();
	private int attrSize = 0;
	
	public CSV(String doc){
		StringTokenizer stn = new StringTokenizer(doc, "\n");
		String line = stn.nextToken();
		StringTokenizer comma = new StringTokenizer(line, ",");
		this.attrSize = comma.countTokens();
		while(comma.hasMoreTokens()){
			this.attributes.add(comma.nextToken());
		}
		while(stn.hasMoreTokens()){
			
		}
	}
	public CSV(File file){
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
		} catch (FileNotFoundException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
			System.exit(0);
		}
	}
	public void main(String[] args) {
		System.out.println("test");
	}
}
