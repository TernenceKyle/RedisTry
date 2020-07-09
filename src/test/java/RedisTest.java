import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.Set;

public class RedisTest {
    private Jedis myJedis = new Jedis();
    private  ObjectMapper myMapper = new ObjectMapper();
    @Test
    public void testlist(){
        myJedis.select(1);
        String s = myJedis.get("user:1");
        try {
            User user = myMapper.readValue(s, User.class);
            System.out.println(user.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }
        myJedis.close();
    }
    @Test
    public void testSet(){
        myJedis.select(1);
        Set<String> keys = myJedis.keys("*");
        System.out.println(keys);
    }
    @Test
    public void testJson(){

    }
    @Test
    public void testHash(){

    }
    @Test
    public void testList(){

    }
    @Test
    public void testSortedList(){

    }
}
