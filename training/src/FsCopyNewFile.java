import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FsCopyNewFile {
	public static void main(String[] args) throws Exception {
		String uri = args[0];
		Configuration conf = new Configuration();
		
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		FSDataOutputStream fout = (FileSystem.get(conf)).create(new Path("hdfs:///user/cloudera/input/FsCopyNewFile.out"));
		
		BufferedReader r = null;
		BufferedWriter writer = null;
		InputStream in = null;
		try {
			in = fs.open(new Path(uri));
			
			r = new BufferedReader(new InputStreamReader(in));
			writer = new BufferedWriter(new OutputStreamWriter(fout));
						
			String line=null;
			while ((line=r.readLine()) != null) {
				String sub = line.substring(3, 5);
				writer.write(sub);
				writer.newLine();
			}
			IOUtils.copyBytes(in, System.out, 4096, false);
		} finally {
			r.close();
			writer.close();
		}
	}
}

