package day06;

public class C30_UsingPluginPojo {

    /*
Given
    https://gorest.co.in/public/v1/users?id=6743944
When
    User sends GET request
Then
    HTTP Status Code should be 200
And
    Response body should be like:
    {
        "meta": {
            "pagination": {
                "total": 1,
                "pages": 1,
                "page": 1,
                "limit": 10,
                "links": {
                    "previous": null,
                    "current": "https://gorest.co.in/public/v1/users?page=1",
                    "next": null
                }
            }
        },
        "data": [
            {
                "id": 6743944,
                "name": "Aagneya Asan",
                "email": "aagneya_asan@ryan.test",
                "gender": "female",
                "status": "inactive"
            }
        ]
    }
 */


}
