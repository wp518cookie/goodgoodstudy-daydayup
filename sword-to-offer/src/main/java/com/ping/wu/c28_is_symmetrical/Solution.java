package com.ping.wu.c28_is_symmetrical;

/**
 * @author wuping
 * @date 2019/2/12
 */

public class Solution {
    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return isEqual(pRoot.left, pRoot.right);
    }

    public boolean isEqual(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return isEqual(t1.left, t2.right) && isEqual(t1.right, t2.left);
    }

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
