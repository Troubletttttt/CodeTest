import dictionary.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class WordSpliter {

    List<Dictionary> dicts;

    public List<String> split(String str){
        List<String> result = new ArrayList<String>();

        return  result;
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
