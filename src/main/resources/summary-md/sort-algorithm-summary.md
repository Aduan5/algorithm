# 排序算法总结

首先贴一张各类排序算法的时空复杂度对比图：

![各类算法对比](..\images\常见排序算法时空复杂度.png)

## 一、插入排序

- 思想：每步将一个数字插入数组前面已经排序的序列的合适位置，直到全部插入排序完为止。

- 关键问题：在前面已经排好序的序列中找到合适的插入位置。

  

###  1.  直接插入排序

- 基本思想：规定待排序数组第一个是有序的，从第二个元素开始和第一个比较，若比第一个小这插入第一个之前，这样前面两个就是有序的。以此类推比较第三个元素，直至全部比较插入完。

- 排序过程：待排序数组：[3，2，5，4，1，6]

  ​	第一轮：假定第一个元素 3 有序，从 2 开始。

  ​				   记录 temp = 2，由于 temp < 3，因此把 3 赋值到 2 的位置，3 前面已没有可比较的元素了，把 temp 复制到原先 3 的位置。

  ​					排序过后组数为：[2，3，5，4，1，6] ，因此前 2 位已经是有序的。

  ​	第二轮：temp = 5，temp > 3 ，因此相当于 5 就应该插入3 的后面，即保持不变。

  ​       			 排序过后数组为：[2，3，5，4，1，6] ，因此前 3 位已经是有序的。

  ​    第三轮：temp = 4，temp < 5，把 5 赋值到 4 的位置，数组变为：[2，3，5，5，1，6]。

  ​				  继续 temp > 3，这把 temp 插入3 的后面，数组变为：[2，3，4，5，1，6]

  ​				  排序过后数组为：[2，3，5，4，1，6] ，因此前 4 位已经是有序的。

  ​			。。。。。

  ​    以此类推完成全部完成排序。。。

- 代码实现：[插入排序代码实现和分析](../java/com/aduan/study/algorithmsort/InsertionSort.java)

  

###  2. 二分插入排序

​    TODO -- 待完成

###  3. 希尔排序

​    TODO -- 待完成

## 二、选择排序



## 三、交换排序



## 四、归并排序



## 五、基数排序













转载请注明出处：
http://blog.csdn.net/gane_cheng/article/details/52652705
http://www.ganecheng.tech/blog/52652705.html （浏览效果更好）