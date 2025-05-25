package org.example;

public class AATreeOperations {

    static class AANode {
        int value;
        int level;
        AANode left;
        AANode right;

        AANode(int value) {
            this.value = value;
            this.level = 1;
            this.left = null;
            this.right = null;
        }
    }


    // хранить статистики операций
    public static class OperationStats {
        long time; // время в наносек
        int operations; // колво операций

        public OperationStats(long time, int operations) {
            this.time = time;
            this.operations = operations;
        }
    }

    static class AATree {
        AANode root;
        int operationCount; // счет операций

        AATree() {
            root =null;
            operationCount = 0;
        }

        AANode skew(AANode node) {
            if (node == null || node.left == null) return node;
            operationCount++;
            if (node.left.level == node.level) {
                operationCount += 2;
                AANode left = node.left;
                node.left = left.right;
                left.right = node;
                return left;
            }
            return node;
        }


        AANode split(AANode node) {
            if (node == null || node.right ==null || node.right.right == null) return node;
            operationCount +=2;
            if (node.right.right.level == node.level) {
                operationCount +=3;
                AANode right = node.right;
                node.right = right.left;
                right.left = node;
                right.level++;
                return right;
            }
            return node;
        }

        AANode insert(AANode node, int value) {
            if (node == null) {
                operationCount++;
                return new AANode(value);
            }
            operationCount++;
            if (value < node.value) {
                node.left = insert(node.left, value);
            } else if (value > node.value) {
                node.right = insert(node.right, value);
            } else {
                return node;
            }
            node = skew(node);
            node = split(node);
            return node;
        }

        void insert(int value) {
            operationCount = 0;
            root = insert(root, value);
        }

        AANode find(AANode node, int value) {
            if (node == null) {
                operationCount++;
                return null;
            }
            operationCount++;
            if (value < node.value) {
                return find(node.left, value);
            } else if (value > node.value) {
                return find(node.right, value);
            }
            return node;
        }

        boolean find(int value) {
            operationCount = 0;
            return find(root,value) != null;
        }

        AANode delete (AANode node,  int value) {
            if (node == null) {
                operationCount++;
                return null;
            }
            operationCount++;
            if (value < node.value) {
                node.left = delete(node.left, value);
            } else if (value > node.value) {
                node.right = delete(node.right, value);
            } else {
                operationCount++;
                if (node.left == null && node.right == null) {
                    return null;
                }
                if (node.left == null) {
                    operationCount++;
                    return node.right;
                }
                if (node.right == null) {
                    operationCount++;
                    return node.left;
                }
                operationCount +=2;
                AANode succesor = getMin(node.right);
                node.value = succesor.value;
                node.right = delete(node.right, succesor.value);
            }
            node = decreaseLevel(node);
            node = skew(node);
            node.right =skew(node.right);
            if (node.right !=null) {
                node.right.right = skew(node.right.right);
            }
            node = split(node);
            node.right = split(node.right);
            return node;
        }

        AANode getMin(AANode node) {
            operationCount++;
            if (node == null || node.left == null) return node;
            return getMin(node.left);
        }

        void delete(int value) {
            operationCount = 0;
            root = delete(root,value);
        }


        AANode decreaseLevel(AANode node) {
            if (node == null) return null;
            int shouldBe = Math.min(
                    node.left != null ? node.left.level : 0,
                    node.right != null ? node.right.level : 0
            ) + 1;
            operationCount+= 2;
            if (shouldBe < node.level) {
                node.level = shouldBe;
                operationCount++;
                if (node.right != null && node.right.level > shouldBe) {
                    node.right.level = shouldBe;
                    operationCount++;
                }
            }
            return node;
        }

    }


}
