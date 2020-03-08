import dictionary.Dictionary;
import dictionary.WordNode;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class WordSpliter {

    List<Dictionary> dicts;

    public List<String> split(String str){
        List<String> result = new ArrayList<String>();
        for(Dictionary dictionary : dicts){
            result.addAll(split(str, dictionary));
        }
        return result;
    }

    private List<String> split(String str, Dictionary dictionary){
        //存放结果
        List<String> result = new ArrayList<String>();
        //作为回滚的栈，即单词可能出现后缀，会有多个分支,类似于树的遍历，用栈记录状态
        Stack<SplitRecord> rollBackStack = new Stack<SplitRecord>();
        //初始状态
        char[] chs = str.toCharArray();
        rollBackStack.push(new SplitRecord(0, "", ""));
        StringBuilder tmpWord;
        while(!rollBackStack.empty()){
            SplitRecord record = rollBackStack.pop();
            tmpWord = new StringBuilder(record.tmpWord);
            StringBuilder stringBuilder = new StringBuilder(record.currStr);
            int state = 0;
            for(int i = record.nextPos; i < chs.length; ++i){
                tmpWord.append(chs[i]);
                state = dictionary.isWord(tmpWord.toString());
                if(state == -1){
                    break;
                }else if(state > 0){
                    stringBuilder.append(tmpWord).append(" ");
                    //找到单词，且单词可能有后续
                    if(state == 2){
                        rollBackStack.push(new SplitRecord(i + 1, tmpWord.toString(),
                                stringBuilder.substring(0, stringBuilder.length() - 1 - tmpWord.length())));
                    }
                    tmpWord.delete(0, tmpWord.length());
                }
            }

            if(state == -1){
                continue;
            }
            result.add(stringBuilder.substring(0, stringBuilder.length() - 1));
        }

        return  result;
    }

    //作为记录Split调用过程中需要回滚的信息
    //类似于作用类似于函数调用栈所记录的数据
    //由于是内部类，为方便使用，所有属性都为public，类似C中Struct
    class SplitRecord{
        int nextPos;
        String tmpWord;
        String currStr;

        SplitRecord(int nextPos, String tmpWord, String currStr){
            this.nextPos = nextPos;
            this.tmpWord = tmpWord;
            this.currStr = currStr;
        }
    }

    //为分离器添加一个字典
    public void addDictionary(Dictionary dict){
        if(dicts == null){
            dicts = new ArrayList<Dictionary>();
        }
        dicts.add(dict);
    }
    //清空已有的字典
    public void cleanDicts(){
        dicts = new ArrayList<Dictionary>();
    }
}
