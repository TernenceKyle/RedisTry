# RedisTry

#### tips
 + *jedis.select(int index)* index指具体哪个库，如果不设置则为默认，往设置的库中添加数据，如果想要渠道相同的数据则需要设置库的index。
 + *Redis* 提供了Set 集合 交集(inter) 并集(union) 补集(diff)等 方法
 + *Redis* provides incr decr incrby decrby等自增、减命令.
 + ***List*** RPopLPush source destination 从src list 右侧弹出再存入 destList 左压栈 一次一个
 + LINSERT key BEFORE|AFTER pivot value 其中 pivot 指的是具体存在的Value，Jedis中 要用枚举 ListPositon.BEFORE OR ListPosition.AFTER
