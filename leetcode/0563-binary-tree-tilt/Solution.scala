/**
 * Definition for a binary tree node.
 * class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
 *   var value: Int = _value
 *   var left: TreeNode = _left
 *   var right: TreeNode = _right
 * }
 */
object Solution {
    def findTiltHelper(root: TreeNode): (Int, Int) = {
        if (root == null) {
            (0, 0)
        } else {
            // First element of tuple is sum of values
            // Second element of tuple is running sum of tilts
            findTiltHelper(root.left) match {
                case (leftSum, leftTilt) =>
                    findTiltHelper(root.right) match {
                        case (rightSum, rightTilt) => 
                            (leftSum + rightSum + root.value, (leftSum - rightSum).abs + leftTilt + rightTilt)
                    }
            }
        }
    }
    
    def findTilt(root: TreeNode): Int = {
        findTiltHelper(root)._2
    }
}
