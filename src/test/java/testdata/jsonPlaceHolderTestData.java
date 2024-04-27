package testdata;

import java.util.HashMap;
import java.util.Map;

public class jsonPlaceHolderTestData {

    public  static Map<String,Object>jsonPlaceHolderMapper(Integer userId , String title , Boolean completed){
        // Integer is  raber class

        Map<String,Object> payLoad = new HashMap<>();

        if(userId!=null) {
            payLoad.put("userId",userId);
        }

        if(title!= null) {

            payLoad.put("title", title);
        }

        if(completed!= null){

            payLoad.put("completed", completed);
        }
        return payLoad;

    }
}
