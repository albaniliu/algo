
public class Codec {

	public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (root.val == key) {
        	if (root.right != null) {
        		TreeNode nRoot = root.right;
        		
        		if (root.left == null) {
        			return root.right;
        		}
        		
        		if (nRoot.left != null) {
        			TreeNode mostRight = findRight(root.left);
        			mostRight.right = nRoot.left;
        		}
        		
        		nRoot.left = root.left;
        		return nRoot;
        	} else {
        		return root.left;
        	}
        }
        
        
        TreeNode father = null;
        TreeNode cur = root;
        
        while (cur != null && cur.val != key) {
        	father = cur;
        	if (cur.val < key) {
        		cur = cur.right;
        	} else {
        		cur = cur.left;
        	}
        }
        if (cur == null) return root;
        
        if (cur.right != null) {
        	if (cur.left == null) {
        		build(father, cur, cur.right);
        	} else {
        		TreeNode nCur = cur.right;
        		if (nCur.left != null) {
        			TreeNode mostRight = findRight(cur.left); 
        			mostRight.right = nCur.left;
        		}
        		nCur.left = cur.left;
    			build(father, cur, nCur);
        	}
        } else {
        	build(father, cur, cur.left);
        }
        
        return root;
    }
	
	private void build(TreeNode father, TreeNode cur, TreeNode node) {
		if (father.right == cur) {
			father.right = node;
		} else {
			father.left = node;
		}
	}

	private TreeNode findRight(TreeNode root) {
		TreeNode res = root;
		while (res.right != null) {
			res = res.right;
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
