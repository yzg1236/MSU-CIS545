import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxTemperature 
{
	public static void main(String[] args) throws Exception {
		if (args.length < 2) {
			System.err.println("Usage: MaxTemperature <input path> <output path> <y,n(optional: do or do not use combiner)>");
			System.exit(-1);
		}
		
		boolean useCombiner = false;
		
		if (args.length > 2) {
			useCombiner = (args[2].toLowerCase() == "y");
		}
		
		Job job = new Job();
		
		System.out.println("job created");
		
		job.setJarByClass(MaxTemperature.class);
		
		job.setJobName("Max temperature");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));		
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(MaxTemperatureMapper.class);
		job.setReducerClass(MaxTemperatureReducer.class);
		
		if (useCombiner) {
			job.setCombinerClass(MaxTemperatureCombiner.class);
			System.out.println("Combiner is set (MaxTemperatureCombiner.class)");
		}else System.out.println("No combiner is set"); 
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}