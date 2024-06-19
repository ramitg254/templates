package templates;

class Node {
    char ch;
    boolean output;
    Node[] next;
    int count;

    public Node(char data) {
        this.ch = data;
        output = false;
        next = new Node[26];
        count = 0;
    }
}

public class Trie {
    Node root;
    int wordCount;

    public Trie() {
        root = new Node('\0');
        wordCount = 0;
    }

    private void add( Node root,String word,int i) {
        if (i==word.length()) {
            root.output = true;
            return;
        }
        char c=word.charAt(i);
        int ind =c-'a' ;
        Node child = root.next[ind];

        if (child == null) {
            child = new Node(c);
            root.next[ind]=child;
            root.count++;
        }
        add(child,word,i+1);
    }

    public void add(String word) {
        add(root,word,0);
        wordCount++;
    }

    private boolean search(Node root,String word,int i) {
        if (i==word.length()) {
            return root.output;
        }
        char c=word.charAt(i);
        int ind =c-'a' ;
        Node child = root.next[ind];
        if (child == null) {
            return false;
        }
        return search(child,word,i+1);
    }

    public boolean search(String word) {
        return search(root,word,0);
    }

    private void remove(Node root,String word,int i) {
        if (i==word.length()) {
            root.output = false;
            return;
        }
        char c=word.charAt(i);
        int ind =c-'a' ;
        Node child = root.next[ind];
        if (child == null) {
            return;
        }
        remove(child,word,i+1);
        if (!child.output && child.count == 0) {
            root.next[ind] = null;
            child = null;
            root.count--;
        }
    }

    public void remove(String word) {
        if (search(word)) {
            remove( root,word,0);
            wordCount--;
        }
    }
}
