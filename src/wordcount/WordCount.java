import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import javax.naming.Context;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;
import org.w3c.dom.Text;

public class WordCount {
    public static class SingleMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
        private final IntWritable num = new IntWritable(1);
        private Text texto;

        public void map(LongWritable key, Text txt, OutputCollector<Text, IntWritable> out, Reporter report)
                throws IOException {
            String[] array = txt.toString().split(" ");
            for (int i = 0; i < array.length; i++) {
                texto.setData(array[i]);
                out.collect(texto, num);
            }

        }
    }

    public static class SingleReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
        public void reduce(Text key, Iterable<IntWritable> values, OutputCollector<Text, IntWritable> output,
                Reporter reporter) throws IOException, InterruptedException {
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next();
            }
            IntWritable sum2 = new IntWritable(sum);
            output.collect(key, sum2);
        }
    }

    public static void main() {

    }
}
