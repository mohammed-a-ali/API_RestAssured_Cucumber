package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

public class RestAssuredExtension {
    String baseURL = LoadProperties.environment.getProperty("APIURL");
    private RequestSpecBuilder builder = new RequestSpecBuilder();
    private String method;
    private String url;
    private String token;

    public RestAssuredExtension(String uri, String method, String token) {
        this.url = baseURL + uri;
        this.method = method;
        this.token = token;

        if (token != null)
            builder.addHeader("Authorization", "Bearer " + token);
    }

    public ResponseOptions<Response> ExecuteAPI() {
        RequestSpecification requestSpecification = builder.build();
        RequestSpecification request = RestAssured.given();
        request.contentType(ContentType.JSON);
        request.spec(requestSpecification);

        if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.POST))
            return request.post(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.GET))
            return request.get(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.DELETE))
            return request.delete(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.PUT))
            return request.put(this.url);

        return null;
    }

    public ResponseOptions<Response> ExecuteWithHeaderAndBody(Map<String, String> header, Object body) {
        builder.addHeaders(header);
        builder.setBody(body);
        return ExecuteAPI();
    }

    public ResponseOptions<Response> ExecuteWithBody(Object body) {
        builder.setBody(body);
        return ExecuteAPI();
    }

    public ResponseOptions<Response> ExecuteWithParams(Map<String, Object> params) {
        builder.addParams(params);
        return ExecuteAPI();
    }

    public ResponseOptions<Response> ExecuteWithPathParam(Object params) {
        builder.addPathParam("{/id}", params);
        return ExecuteAPI();
    }

    public ResponseOptions<Response> ExecuteMultiPartWithFile(String path) {

        MultiPartSpecBuilder specBuilder = new MultiPartSpecBuilder(new File(path));
        specBuilder.build();

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        specBuilder.header("Accept", "application/json");
        specBuilder.header("Content-Type", "multipart/form-data");

        request.multiPart("file", new File(path));

        if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.POST))
            return request.post(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.GET))
            return request.get(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.DELETE))
            return request.delete(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.PUT))
            return request.put(this.url);

        return null;
    }

    public ResponseOptions<Response> ExecuteMultiPartWithText(String key, int value) {

        MultiPartSpecBuilder specBuilder = new MultiPartSpecBuilder(key);
        specBuilder.build();

        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer " + token);
        specBuilder.header("Accept", "application/json");
        specBuilder.header("Content-Type", "multipart/form-data");

        request.multiPart(key, value);

        if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.POST))
            return request.post(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.GET))
            return request.get(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.DELETE))
            return request.delete(this.url);
        else if (this.method.equalsIgnoreCase(APIConstant.ApiMethods.PUT))
            return request.put(this.url);

        return null;
    }
}
