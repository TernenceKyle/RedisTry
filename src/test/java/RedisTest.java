import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;
import org.junit.After;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.Tuple;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisTest {
    private JedisPool JedisPool = new JedisPool();
    private  ObjectMapper myMapper = new ObjectMapper();
    private Jedis myJedis = JedisPool.getResource();
    @Test
    public void testlist(){
        myJedis.select(1);
//        myJedis.lpush("myList","l1","l2","l3","l4","l5","l6","l7");
//        myJedis.rpush("myList","l1","l2","l3","l4","l5","l6","l7");
//        List<String> myList = myJedis.lrange("myList", 0, -1);
//        myJedis.lpushx("myList","12315452561sdasdas");
//        myJedis.lrem("myList",0,"0");
//        myJedis.lpop("myList");
//        myJedis.ltrim("myList",3,5);
//        System.out.println(myJedis.llen("myList"));
//        myJedis.rpush("myList","newItem1","newItem2","newItem3","newItem4");
        myJedis.lpush("myList","point");
        myJedis.linsert("myList", ListPosition.AFTER,"point","NewUnit");
        myJedis.close();
    }
    @Test
    public void testSet(){
        myJedis.select(1);
        myJedis.set("num","1");
        myJedis.incr("num");
        myJedis.incrBy("num",5);
        myJedis.decrBy("num",6);
        myJedis.append("num","isNumber");
        System.out.println(myJedis.strlen("num"));
        myJedis.close();
    }
    @Test
    public void setFunCMethods(){
        myJedis.select(1);
//        myJedis.sadd("numSet1","1");
//        myJedis.sadd("numSet1","2");
//        myJedis.sadd("numSet1","3");
//        myJedis.sadd("numSet1","4");
//        myJedis.sadd("numSet1","5");
//        myJedis.sadd("numSet2","4");
//        myJedis.sadd("numSet2","5");
//        myJedis.sadd("numSet2","6");
//        myJedis.sadd("numSet2","7");
//        myJedis.sadd("numSet2","8");
//        Set<String> sunion = myJedis.sunion("numSet1", "numSet2");
//        System.out.println("Union of w(numSet1,numset2) : "+sunion);
//        Set<String> sinter = myJedis.sinter("numSet1", "numSet2");
//        System.out.println("Inter set of (numSet1,numset2) : "+sinter);
//        Set<String> sdiff = myJedis.sdiff("numSet2","numSet1");
//        System.out.println("Difference of (numSet1,numset2) : "+sdiff);
//        myJedis.spop("numSet1");
//        Set<String> numSet1 = myJedis.smembers("numSet1");
//        System.out.println(numSet1);
        Long numSet2 = myJedis.scard("numSet2");
        System.out.println(numSet2);
        myJedis.close();
    }
    @Test
    public void testJson(){

    }
    @Test
    public void testObject2Json(){
        myJedis.select(1);
        ObjectMapper mapper = new ObjectMapper();
        User newUser = new User(1,"Ezzra","004325","Los Angles");
        try {
            String s = mapper.writeValueAsString(newUser);
            System.out.println(s);
            myJedis.set("user:1",s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        myJedis.close();
    }
    @Test
    public void testJson2Object(){
        myJedis.select(1);
        String json = myJedis.get("user:1");
        ObjectMapper mapper = new ObjectMapper();
        try {
            User user = mapper.readValue(json, User.class);
            System.out.println(user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        myJedis.close();

    }
    @Test
    public void testHash(){
        myJedis.select(1);
//        String hget = myJedis.hget("info", "name");
        Map<String,String> map =new HashMap<String,String>();
        map.put("address","LA");
        myJedis.hset("info",map);
        System.out.println();
        myJedis.close();
    }
    @Test
    public void testSortedSet(){
        myJedis.select(1);
//        myJedis.zadd("benchMark",5494.6,"AMD Ryzen 4800H 2.9Ghz-4.2Ghz 8c16t.");
//        myJedis.zadd("benchMark",3701,"INTEL Core i7-8700K 3.7GHz 6c12t.");
//        myJedis.zadd("benchMark",5423,"INTEL Core i9-9900KF 3.6GHz 8c16t.");
        System.out.println(myJedis.zrevrange("benchMark", 0, 1));
//        System.out.println(myJedis.zscore("benchMark", "AMD Ryzen 4800H 2.9Ghz-4.2Ghz 8c16t."));
//        Set<Tuple> benchMark = myJedis.zrevrangeWithScores("benchMark", 0, -1);
//        System.out.println(benchMark);
        myJedis.close();
    }
    @Test
    public void testSecond()
    {
        myJedis.select(1);
        myJedis.set("SecondToBuy","Lenovo Legion R7000 r74800H 5599");
        myJedis.expire("SecondToBuy",30);
        myJedis.close();
    }
}
