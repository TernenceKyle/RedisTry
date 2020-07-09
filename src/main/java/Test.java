import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;
import redis.clients.jedis.Jedis;

public class Test {
    public static void main(String[] args) {
        Jedis myjedis = new Jedis();
        myjedis.select(1);
        ObjectMapper mapper = new ObjectMapper();
        User newUser = new User(1,"Ezzra","004325","Los Angles");
        try {
            String s = mapper.writeValueAsString(newUser);
            System.out.println(s);
            myjedis.set("user:1",s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
