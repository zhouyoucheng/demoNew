package util;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import kipList.SkipList;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author supaur
 * @date 调用应用工具类
 */
public class ExcelUtils {

    public static List<List<Object>> importData(String fileName) {
        ExcelReader reader = ExcelUtil.getReader(fileName);
        List<List<Object>> read = reader.read();
        read.remove(1);
        return read;
    }

    public static void main(String[] args) {
        ConcurrentSkipListSet set = new ConcurrentSkipListSet();
        set.add(1);
        set.add(3);
        System.out.println(set);
    }

}
