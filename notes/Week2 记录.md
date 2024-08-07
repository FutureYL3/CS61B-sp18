# Week2 记录

本周主要学习与链表和数组有关的内容，详细如下：

- Java 中的值类型和引用类型
- 盒子指针标记法
- 参数传递以及相等黄金法则（GRoE）
- 裸递归链表 `IntList`
- 改进裸递归链表 —— `SLList` 即单链表
- 改进单链表 —— `DLList` 即双链表
- Java 中的数组基础知识

## Java 中的值类型和引用类型

计算机中的所有信息都以 1 和 0 的序列存储在*内存中。以下是一些示例：*

- 72 通常存储为 01001000
- 205.75 通常存储为 01000011 01001101 11000000 00000000
- 字母 H 通常存储为 01001000（与 72 相同）
- 真实值通常存储为 00000001

在本课程中，我们不会花太多时间讨论具体的二进制表示，例如，为什么 205.75 会存储为上面看似随机的 32 位字符串。理解具体表示是 [CS61C](http://www-inst.eecs.berkeley.edu/~cs61c/)（61B 的后续课程）的主题。

虽然我们不会学习二进制语言，但了解其背后发生的事情也是很有益处的。

一个有趣的观察是，72 和 H 都存储为 01001000。这就引发了一个问题：**一段 Java 代码如何知道如何解释 01001000？**

答案是==通过类型！==

## 平等的黄金法则（GRoE）

当你写入 `y = x` 时，你正在告诉 Java 解释器：将 `x` 的位（比特）复制给 `y`。

> **对于基本类型，这意味着拷贝值；对于引用类型，这意味着拷贝地址。**
>
> 因为基本类型变量直接存储**值的二进制表示**，引用类型变量存储的是**堆中值的地址**。

## 裸递归链表 `IntList`

它的实现：

```java
public class IntList {
    public int first;
    public IntList rest;        

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }
}
```

## 改进裸递归链表 —— `SLList`

改进步骤：

### 1. 简单地重命名所有内容并丢弃辅助方法

```java
public class IntNode {
    public int item;
    public IntNode next;

    public IntNode(int i, IntNode n) {
        item = i;
        next = n;
    }
}
```

### 2. 创建一个单独的类 SLList 供用户交互

```java
public class SLList {
    public IntNode first;

    public SLList(int x) {
        first = new IntNode(x, null);
    }
}
```

### 3. 成员变量私有化

```java
public class SLList {
    private IntNode first;
...
```

### 4. 嵌套节点类

```java
public class SLList {
       public static class IntNode {
            public int item;
            public IntNode next;
            public IntNode(int i, IntNode n) {
                item = i;
                next = n;
            }
       }

       private IntNode first; 

       public SLList(int x) {
           first = new IntNode(x, null);
       } 
...
```

### 5. 缓存链表大小

```java
public class SLList {
    ... /* IntNode declaration omitted. */
    private IntNode first;
    private int size;

    public SLList(int x) {
        first = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x) {
        first = new IntNode(x, first);
        size += 1;
    }

    public int size() {
        return size;
    }
    ...
}
```

### 6. 添加哨兵节点避免空指针

为解决如下代码可能产生的空指针异常：

```java
public void addLast(int x) {
    size += 1;
    IntNode p = first;
    // first可能为null，导致访问p.next抛出空指针异常
    while (p.next != null) {
        p = p.next;
    }

    p.next = new IntNode(x, null);
}
```

添加哨兵：我们可以通过创建一个始终存在的特殊节点来实现这一点，我们将其称为**哨兵节点**。哨兵节点将保存一个我们不关心的值。

![image-20240807212656688](./Week2%20%E8%AE%B0%E5%BD%95.assets/image-20240807212656688.png)

添加哨兵后改进的代码：

```java
public void addLast(int x) {
    size += 1;
    IntNode p = sentinel;
    while (p.next != null) {
        p = p.next;
    }

    p.next = new IntNode(x, null);
}
```

免去了增加 `if` 判断。

## 改进单链表 —— `DLList`

### 改进添加末尾元素时间复杂度

单链表对于增加节点到链表末尾的时间复杂度是线性的，因此改进其，增加一个 `last` 成员变量指向末尾节点。

```java
public class SLList {
    private IntNode sentinel;
    private IntNode last;
    private int size;    

    public void addLast(int x) {
        last.next = new IntNode(x, null);
        last = last.next;
        size += 1;
    }
    ...
}
```

### 改进移除末尾元素时间复杂度

改进移除最后一个元素需要顺序查找倒数第二个元素的窘境，使用双链表：

```java
public class IntNode {
    public IntNode prev;
    public int item;
    public IntNode next;
}
```

改进后大小为 0 和大小为 2 的双链表图示：

![image-20240807213236537](./Week2%20%E8%AE%B0%E5%BD%95.assets/image-20240807213236537.png)

### 改进可能发生异常情况

由于 `last` 指向具体节点而不是哨兵，为了避免其像之前一样发生特殊异常情况，改进之：

1. 采用双哨兵

   ![image-20240807213435857](./Week2%20%E8%AE%B0%E5%BD%95.assets/image-20240807213435857.png)

2. 采用循环链表

   ![image-20240807213502480](./Week2%20%E8%AE%B0%E5%BD%95.assets/image-20240807213502480.png)

### 改进适用范围

为了拓展链表的适用范围，可以采用==泛型==来声明类：

```java
public class DLList<BleepBlorp> {
    private IntNode sentinel;
    private int size;

    public class IntNode {
        public IntNode prev;
        public BleepBlorp item;
        public IntNode next;
        ...
    }
    ...
}
```

## 数组基础知识

### 数组与类

数组和类都可用于组织一堆记忆盒。在这两种情况下，记忆盒的数量都是固定的，即数组的长度不能改变，就像类字段不能添加或删除一样。

数组和类中的记忆盒之间的主要区别：

- 数组框使用符号 `[]` 进行编号和访问，类框使用点符号进行命名和访问。
- 数组框必须全部为同一类型。类框可以为不同类型。

这些差异的一个特别显著的影响是，`[]` 符号允许我们在**运行时**指定我们想要的索引。例如，考虑下面的代码：

```java
int indexOfInterest = askUserForInteger();
int[] x = {100, 101, 102, 103};
int k = x[indexOfInterest];
System.out.println(k);
```

如果我们运行此代码，我们可能会得到类似这样的结果：

```
$ javac arrayDemo
$ java arrayDemo
What index do you want? 2
102
```

相比之下，我们在运行时不会指定类中的字段。例如，考虑下面的代码：

```java
String fieldOfInterest = "mass";
Planet p = new Planet(6e24, "earth");
double mass = p[fieldOfInterest];
```

如果我们尝试编译它，我们会得到一个语法错误。

```
$ javac classDemo
FieldDemo.java:5: error: array required, but Planet found
        double mass = earth[fieldOfInterest];        
                               ^
```

如果我们尝试使用点符号，也会出现同样的问题：

```java
String fieldOfInterest = "mass";
Planet p = new Planet(6e24, "earth");
double mass = p.fieldOfInterest;
```

编译后我们会得到：

```
$ javac classDemo
FieldDemo.java:5: error: cannot find symbol
        double mass = earth.fieldOfInterest;        
                           ^
  symbol:   variable fieldOfInterest
  location: variable earth of type Planet
```

虽然这不是你经常遇到的限制，但是为了学术研究的需要，还是值得指出。

值得一提的是，有一种在运行时指定所需字段的方法，称为*反射*，但对于典型的程序来说，它被认为是非常糟糕的编码风格。你可以[在此处](https://docs.oracle.com/javase/tutorial/reflect/member/fieldValues.html)阅读有关反射的更多信息。记住，**你永远不应该在任何 61B 程序中使用反射**，我们也不会在课程中讨论它。

一般来说，编程语言的设计在一定程度上限制了程序员的选择，使代码更易于理解。通过将这些功能限制在特殊的 Reflections API 中，Java 程序将更易于阅读和解释。