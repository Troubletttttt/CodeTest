package dictionary;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary implements Dictionary {

    private Map<Character, WordNode> wordNodeMap;

    public MyDictionary(){
        wordNodeMap = new HashMap<Character, WordNode>();
    }

    //用用户所给的单词列表初始化字典
    public MyDictionary(String... words){
        wordNodeMap = new HashMap<Character, WordNode>();
        for(String word:words){
            addWord(word);
        }
    }


    public boolean isWordHasNext(String word) {
        return false;
    }

    public void addWord(String word) {
        if(word == null || word.isEmpty()){
            return;
        }
        word = word.toUpperCase();
        char[] chs = word.toCharArray();
        WordNode node = wordNodeMap.get(chs[0]);
        //如果没有节点，增加节点
        if(node == null){
            node = new WordNode();
            node.setIsWord(false);
            node.setValue(chs[0]);
            wordNodeMap.put(chs[0], node);
        }


        for(int i = 1; i < chs.length; ++i){
            WordNode nextNode = node.getNextNode(chs[i]);
            if(nextNode == null){
                node.addNextNode(chs[i]);
                nextNode = node.getNextNode(chs[i]);
            }
            node = nextNode;
        }

        node.setIsWord(true);
    }

    public boolean isWord(String word) {
        if(word == null || word.isEmpty()){
            return false;
        }
        word = word.toUpperCase();
        char[] chs = word.toCharArray();
        WordNode node = wordNodeMap.get(word.charAt(0));
        for(int i = 1; i < chs.length; ++i){
            if(node == null){
                return false;
            }
            node = node.getNextNode(chs[i]);
        }
        if(node == null || !node.isWord()){
            return false;
        }
        return true;
    }

    public void loadDictionary(String path) throws IOException {
        File file = new File(path);

        BufferedReader reader = new BufferedReader(new FileReader(path));
        String word;
        while ((word = reader.readLine())!= null){
            addWord(word);
        }
        reader.close();
    }
}
