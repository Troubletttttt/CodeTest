package dictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyong
 * 作为字典中某个单词中的某个字母的一个节点
 */

public class WordNode {

    private boolean isWord;     //该单词到当前字母是否是一个单词
    private char ch;            //当结点表示字母，从a-z
    private Map<Character, WordNode> nextChar;  //可能的下一个字符，


    public boolean isWord(){
        return isWord;
    }

    public WordNode getNextNode(char c){
        if(nextChar != null){
            return nextChar.get(c);
        }else {
            return null;
        }
    }

    public char getValue(){
        return ch;
    }
    public void setValue(char c){
        ch = c;
    }

    protected void setIsWord(boolean isWord){
        this.isWord = isWord;
    }

    protected void addNextNode(char ch){
        if(nextChar == null){
            nextChar = new HashMap<Character, WordNode>();
        }
        WordNode node = new WordNode();
        node.setIsWord(false);
        node.ch = ch;
        nextChar.put(ch, node);
    }

    protected boolean hasNext(){
        return nextChar != null;
    }

}
