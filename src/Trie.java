
public class Trie {

	class Node {
		String value;
		Node[] children;
		Node(String w) {
			value = w;
			children = new Node[26];
		}
	}
	
	Node root;
	
	public Trie() {
        root = new Node(null);
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
        	int index = word.charAt(i) - 'a';
        	if (cur.children[index] == null) {
        		cur.children[index] = new Node(null);
        	}
        	cur = cur.children[index];
        }
        cur.value = word;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
        	int index = word.charAt(i) - 'a';
        	if (cur.children[index] == null) return false;
        	cur = cur.children[index];
        }
        return cur.value != null;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
    	 Node cur = root;
         for (int i = 0; i < prefix.length(); i++) {
         	int index = prefix.charAt(i) - 'a';
         	if (cur.children[index] == null) return false;
         	cur = cur.children[index];
         }
         return true;
    }
    
	public static void main(String[] args) {
		Trie t = new Trie();
		t.insert("a");
		System.out.println(t.search("a"));
	}

}
