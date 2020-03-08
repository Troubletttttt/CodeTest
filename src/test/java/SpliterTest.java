import com.sun.org.apache.xml.internal.security.Init;
import dictionary.Dictionary;
import dictionary.MyDictionary;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class SpliterTest {

    @Test
    public void SplitTest() throws IOException {
        //测试能否从文件加载字典，并且能否正确查找到对应的单词，正确的错误的情况都要
        Dictionary dictionary = new MyDictionary();
        //获取路径
        String resourcePath = getClass().getResource("initDict.txt").getPath();
        //读取文件
        dictionary.loadDictionary(resourcePath);

        WordSpliter spliter = new WordSpliter();
//        spliter.addDictionary(dictionary);
        List<String> result = spliter.split("ilikesamsungmobile", dictionary);
        assertEquals(2, result.size());
        assertEquals("i like sam sung mobile", result.get(0));
        assertEquals("i like samsung mobile", result.get(1));

        result = spliter.split("ilikeicecreamandmango", dictionary);
        assertEquals(1, result.size());
        assertEquals("i like ice cream and man go", result.get(0));

//        for(String s : result){
//            System.out.println(s);
//        }
    }

    @Test
    public void dictionaryMergeTest() throws Exception {
        Dictionary dictionary = new MyDictionary();
        //获取路径
        String resourcePath = getClass().getResource("initDict.txt").getPath();
        //读取文件
        dictionary.loadDictionary(resourcePath);

        Dictionary dictionary1 = new MyDictionary();
        dictionary1.addWord("new");

        dictionary.mergeDictionary(dictionary1);
        assertEquals(1, dictionary.isWord("new"));
        assertEquals(1, dictionary.isWord("like"));
    }

    @Test
    public void loadDictionaryTest() throws IOException {
        //测试能否从文件加载字典，并且能否正确查找到对应的单词，正确的错误的情况都要
        Dictionary dictionary = new MyDictionary();
        //获取路径
        String resourcePath = getClass().getResource("initDict.txt").getPath();
        //读取文件
        dictionary.loadDictionary(resourcePath);
        //普通的测试
        assertEquals(1, dictionary.isWord("like"));
        //单个字母为单词的情况
        assertEquals(2, dictionary.isWord("i"));
        assertEquals(-1, dictionary.isWord("a"));
        //大小写不一致的情况
        assertEquals(1, dictionary.isWord("LIKE"));
        //缺少一部分的情况
        assertEquals(0, dictionary.isWord("lik"));
        //有前缀相同的情况
        assertEquals(2, dictionary.isWord("sam"));
        assertEquals(1, dictionary.isWord("sung"));
        assertEquals(1, dictionary.isWord("samsung"));
        assertEquals(0, dictionary.isWord("sams"));
        //多出后缀的情况
        assertEquals(-1, dictionary.isWord("likee"));
    }

}
