package temperature2;

import java.util.HashMap;
import java.lang.Math;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;

import java.io.IOException;
public class MMReducer extends Reducer <Text, IntWritable,Text, IntWritable >
{
	public HashMap<Text, Integer> map = new HashMap<>();
public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException,InterruptedException
{
int max_temp = 0;
int count = 0;
for (IntWritable value : values)
{
max_temp = Math.max(max_temp,value.get());
map.put(key, max_temp);
}
context.write(new Text("mean of max of months uptil now"), new IntWritable(calculate_average()));
}

public int calculate_average() {
	int sum0 = 0;
	for (Integer val : map.values()) {
		sum0+=val;
	}
	return sum0/map.size();
}
}