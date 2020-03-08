package dictionary;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Dictionary {

    /**
     * 判断所给字符串是否在字典中
     * @param word 要查找的单词
     * @return 在则返回true，否则返回false
     */
    boolean isWord(String word);

    /**
     * 向字典中添加一个单词
     * @param word 要添加的单词
     */
    void addWord(String word);

    /**
     * 判断所给单词是否有可能加后续，如字典中有单词 sam,samsung，输入sam返回true,输入samsung返回false
     * @param word
     * @return
     */
    boolean isWordHasNext(String word);

    /**
     * 从所给路径中读取字典文件
     * @param path
     */
    void loadDictionary(String path) throws IOException;
}
