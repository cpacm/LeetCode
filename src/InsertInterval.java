import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpacm on 2017/6/28.
 */
public class InsertInterval {

    public static void main(String[] args) {
        List<Interval> arrays = new ArrayList<>();
        arrays.add(new Interval(1, 2));
        arrays.add(new Interval(3, 5));
        arrays.add(new Interval(6, 7));
        arrays.add(new Interval(8, 10));
        arrays.add(new Interval(12, 16));
        System.out.println(insert(arrays, new Interval(4, 9)));
    }

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals.size() <= 0) {
            intervals.add(newInterval);
            return intervals;
        }
        int position = -1;
        for (int i = 0; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (interval.start <= newInterval.start && interval.end >= newInterval.start) {
                position = intervals.indexOf(interval);
                interval.end = Math.max(interval.end, newInterval.end);
                break;
            } else if (interval.start >= newInterval.start && interval.start <= newInterval.end) {
                position = intervals.indexOf(interval);
                interval.start = newInterval.start;
                interval.end = Math.max(interval.end, newInterval.end);
                break;
            } else if (interval.start > newInterval.start) {
                int index = intervals.indexOf(interval);
                intervals.add(index, newInterval);
                break;
            } else if (i == intervals.size() - 1) {
                intervals.add(newInterval);
                break;
            }
        }
        if (position != -1) {
            int index = position + 1;
            Interval interval = intervals.get(position);
            while (true) {
                if (index >= intervals.size()) {
                    break;
                }
                Interval lastInterval = intervals.get(index);
                if (lastInterval.start > interval.end) {
                    break;
                }
                interval.end = Math.max(interval.end, lastInterval.end);
                intervals.remove(lastInterval);
            }
        }
        return intervals;
    }


    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }
}
