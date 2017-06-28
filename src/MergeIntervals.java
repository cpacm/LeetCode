import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpacm on 2017/6/27.
 */
public class MergeIntervals {

    public static void main(String[] args) {
        List<Interval> arrays = new ArrayList<>();
        arrays.add(new Interval(1, 3));
        arrays.add(new Interval(8, 10));
        arrays.add(new Interval(15, 18));
        arrays.add(new Interval(15, 18));
        arrays.add(new Interval(5, 18));
        arrays.add(new Interval(2, 6));
        arrays.add(new Interval(19, 26));
        arrays.add(new Interval(2, 6));
        arrays.add(new Interval(2, 6));
        System.out.println(merge(arrays));
    }

    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) return intervals;
        quickSort(0, intervals.size() - 1, intervals);
        List<Interval> res = new ArrayList<>();
        for (Interval interval : intervals) {
            if (res.size() == 0) {
                res.add(interval);
                continue;
            }
            Interval pastInterval = res.get(res.size() - 1);
            if (interval.start > pastInterval.end) {
                res.add(interval);
            } else {
                pastInterval.end = Math.max(pastInterval.end, interval.end);
            }
        }
        return res;
    }

    public static void quickSort(int start, int end, List<Interval> intervals) {
        if (start >= end) return;
        int startTemp = start;
        int endTemp = end;
        Interval value = intervals.get(start);
        while (start < end) {
            while (intervals.get(end).start >= value.start && end > start)
                end--;
            intervals.set(start, intervals.get(end));
            while (intervals.get(start).start <= value.start && end > start)
                start++;
            intervals.set(end, intervals.get(start));
        }
        intervals.set(start, value);
        quickSort(startTemp, end, intervals);
        quickSort(end + 1, endTemp, intervals);
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
