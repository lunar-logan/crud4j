package com.crud4j.util;

import java.io.*;

/**
 * Created by Dell on 22-04-2017.
 */
public class FileUtil {
    private static final int MAX_CHARS_TO_READ = 2 * 1024 * 1024;

    private static final int INTERNAL_BUFFER_SIZE = 1024;

    public static String read(String name) {
        CharArrayWriter writer = new CharArrayWriter(INTERNAL_BUFFER_SIZE);
        char[] buf = new char[INTERNAL_BUFFER_SIZE];
        try (Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(name)))) {
            while (true) {
                int read = reader.read(buf);
                if (writer.size() + read >= MAX_CHARS_TO_READ) {
                    throw new RuntimeException("Limit reached");
                }
                if (read > -1) writer.write(buf, 0, read);
                else break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}
