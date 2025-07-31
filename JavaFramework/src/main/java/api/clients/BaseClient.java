package api.clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Method;

import java.util.Map;

public abstract class BaseClient {

    protected final String baseUrl;

    protected BaseClient(String baseUrl){
        this.baseUrl = baseUrl;
        RestAssured.baseURI = baseUrl;
    }

    protected RequestSpecification createRequest(String endpoint, Method method, Map<String, String> headers){
        RequestSpecification request = RestAssured
                .given()
                .relaxedHTTPSValidation() //Used for test environments, if you need certificate validation comment this
                .basePath(endpoint)
                .log().method()
                .log().uri();

        if(headers != null){
            request.headers(headers);
        }

        return  request;
    }

    protected <T> RequestSpecification addJsonBody(RequestSpecification request, T body){
        return request.body(body);
    }

    protected Response executeRequest(RequestSpecification request, Method method){
        return request.request(method);
    }

    protected void ensureSuccessStatusCode(Response response, int expectedStatusCode){
        if(response.statusCode() != expectedStatusCode){
            throw new RuntimeException(
                    String.format("Expected status code %d but got %d. Response: %s",
                            expectedStatusCode, response.statusCode(), response.getBody().asPrettyString())
            );
        }
    }

    protected Response executeRequestWithLogs(RequestSpecification request, String endpoint, Method method) {
        System.out.println("Request: " + method.name() + " " + baseUrl + endpoint);

        request.log().all();

        Response response = request.request(method);

        System.out.println("Response Status: " + response.statusCode());
        response.then().log().all();

        return response;
    }
}
