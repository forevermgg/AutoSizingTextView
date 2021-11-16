# AutoSizingTextView
## 查漏补缺
+ https://stackoverflow.com/questions/46618881/autosize-textview-is-not-working
![](https://cdn.jsdelivr.net/gh/forevermgg/CentForeverPicGo/img/2021-11/auto.png)

+ In my case using only fixed dimension or match_parent didn't work. I had to add "maxLines=1" and "lines=1". Don't know why is this not made obvious in the documentation.
