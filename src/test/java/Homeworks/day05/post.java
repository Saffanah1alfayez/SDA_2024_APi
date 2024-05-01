package Homeworks.day05;

import base_urls.PetStoreBaseUrl;
import org.testng.annotations.Test;

public class post  extends PetStoreBaseUrl {

    @Test
    public void createUser() {

        //set url
        spec.pathParam("1","user");

        //set expected data
        createUserPojo payload = new createUserPojo(1 , "sara1", "sara" , "doe" , "s123@gmail.com" ,"123123" ,"055555" ,0);
        System.out.println("payload = " + payload);

    }
}
