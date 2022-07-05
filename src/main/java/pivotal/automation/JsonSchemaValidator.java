package pivotal.automation;

import io.restassured.response.Response;

import java.io.File;

public class JsonSchemaValidator {

    private static String SCHEMAS_PATH = "src/test/resources/schemas/";

    public static void validateJsonSchema(String schemaName, Response response) {
        String schemaFile = SCHEMAS_PATH + schemaName;
        File schemaContent = new File(schemaFile);
        response.then()
                .assertThat()
                .body(io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema(schemaContent));
    }
}
