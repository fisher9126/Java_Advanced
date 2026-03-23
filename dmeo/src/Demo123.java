import java.util.ArrayList;
import java.util.List;

public class Demo123 {
    private static Demo123 instance;
    private List<Integer> list;
    private Demo123(){
        list = new ArrayList<>();
    }
    public static Demo123
    getInstance(){
        if(instance == null){
            instance = new Demo123();
        }
        return instance;
    }
}
