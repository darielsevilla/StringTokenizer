import java.io.IOException;

import java.io.OutputStream;
import java.util.*;

import javax.naming.Context;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

public class WordCount2 {
    public static class SingleMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
        private final IntWritable num = new IntWritable(1);
        private Text texto = new Text();

        public void map(LongWritable key, Text txt, OutputCollector<Text, IntWritable> out, Reporter report)
                throws IOException {

            String[] array = txt.toString().split(" ");
            for (int i = 0; i < array.length - 1; i++) {
                String word = array[i] + " " + array[i + 1];
                texto.set(word);
                out.collect(texto, num);
            }

        }
    }

    public static class SingleReducer extends MapReduceBase
            implements Reducer<Text, IntWritable, Text, IntWritable> {

        public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output,
                Reporter reporter) throws IOException {
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next().get();
            }
            IntWritable sum2 = new IntWritable(sum);
            output.collect(key, sum2);
        }
    }

    public static void main(String[] argv) {
        JobConf job = new JobConf(WordCount2.class);
        job.setJobName("procesamiento");
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setMapperClass(SingleMapper.class);
        job.setCombinerClass(SingleReducer.class);
        job.setReducerClass(SingleReducer.class);

        job.setInputFormat(TextInputFormat.class);
        job.setOutputFormat(TextOutputFormat.class);
        FileInputFormat.setInputPaths(job, new Path(argv[0]));
        FileOutputFormat.setOutputPath(job, new Path(argv[1]));

        JobConf conf = new JobConf(WordCount2.class);

        try {
            JobClient.runJob(job);
        } catch (Exception e) {

        }
    }
}
