import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class RESTAssuredEmployeeJSONTests {
    public int empId;

    @Before
    public void setUp() throws Exception {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=4000;
        empId=10;
    }
    
    public Response getEmployeeList() {
        Response response=RestAssured.get("/employees/list");
        return response;
    }

    @Test
    public void onCallingListReturnEmployeeList() {
      Response response=getEmployeeList();
        System.out.println("AT FIRST: "+response.asString());
        response.then().body("id", Matchers.hasItems(2,3,4,5,6));
        response.then().body("name",Matchers.hasItems("Lisa"));

    }

    @Test
    public void givenEmployee_OnPost_ShouldReturnAddEmployee() {
        Response response=RestAssured.given()
                                        .contentType(ContentType.JSON)
                                        .accept(ContentType.JSON)
                                        .body("{\"name\":\"Jitendra\",\"salary\":\"99000\"}")
                                        .when()
                                        .post("/employees/create");
        String resAsString=response.asString();
        JsonObject jsonObject=new Gson().fromJson(resAsString,JsonObject.class);
       int id=jsonObject.get("id").getAsInt();
       response.then().body("id",Matchers.any(Integer.class));
       response.then().body("name",Matchers.is("Jitendra"));


    }

    @Test
    public void givenEmployee_OnUpdate_ShouldReturnUpdatedEmployee() {
        Response response=RestAssured.given()
                                    .contentType(ContentType.JSON)
                                    .accept(ContentType.JSON)
                                    .body("{\"name\": \"Jitendra B\",\"salary\":\"123456\"}")
                                    .when()
                                    .put("/employees/update/"+empId);
        String resAsStr=response.asString();
        response.then().body("id",Matchers.any(Integer.class));
        response.then().body("name",Matchers.is("Jitendra B"));
        response.then().body("salary",Matchers.is("123456"));

    }

    @Test
    public void givenEmployeeId_OnSuccessfulDelete_ShouldReturn_SuccessStatus() {
        Response response=RestAssured.delete("employees/delete/"+empId);
        String resAsStr=response.asString();
        int statusCode=response.getStatusCode();
        System.out.println(statusCode);
        MatcherAssert.assertThat(statusCode, CoreMatchers.is(200));
        response=getEmployeeList();
        System.out.println("AT END:" +response.asString());
        response.then().body("id",Matchers.not(empId));
    }
}

