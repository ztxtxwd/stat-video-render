package xyz.tapeless.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    public static List<String[]> 读人气数据(String filePath){
        try {
            // 打开csv文件
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // 读取文件中的数据
            String line = null;
            List<String[]> 读出的数据 = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                // 处理csv文件中的数据
                读出的数据.add(data);
            }

            // 关闭文件
            reader.close();
            return 读出的数据;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        try {
            // 打开csv文件
            BufferedReader reader = new BufferedReader(new FileReader("data.csv"));

            // 读取文件中的数据
            String line = null;
            List<String[]> 读出的数据 = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                // 处理csv文件中的数据
                读出的数据.add(data);
            }

            // 关闭文件
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}