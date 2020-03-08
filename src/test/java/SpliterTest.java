import com.sun.org.apache.xml.internal.security.Init;
import dictionary.Dictionary;
import dictionary.MyDictionary;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SpliterTest {

    @Test
    public void loadDictionaryTest() throws IOException {
        //测试能否从文件加载字典，并且能否正确查找到对应的单词，正确的错误的情况都要
        Dictionary dictionary = new MyDictionary();
        //获取路径
        String resourcePath = getClass().getResource("initDict.txt").getPath();
        //读取文件
        dictionary.loadDictionary(resourcePath);
        //普通的测试
        assertTrue(dictionary.isWord("like"));
        //单个字母为单词的情况
        assertTrue(dictionary.isWord("i"));
        assertFalse(dictionary.isWord("a"));
        //大小写不一致的情况
        assertTrue(dictionary.isWord("LIKE"));
        //缺少一部分的情况
        assertFalse(dictionary.isWord("lik"));
        //有前缀相同的情况
        assertTrue(dictionary.isWord("sam"));
        assertTrue(dictionary.isWord("sung"));
        assertTrue(dictionary.isWord("samsung"));
        assertFalse(dictionary.isWord("sams"));
        //多出后缀的情况
        assertFalse(dictionary.isWord("likee"));
    }

}
