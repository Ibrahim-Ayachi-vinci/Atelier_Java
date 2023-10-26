package be.vinci.services;


import jakarta.json.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Class analyzer. It saves a class into attribute, from a constructor, and
 * gives a lot of convenient methods to transform this into a JSON object
 * to print the UML diagram.
 */
public class ClassAnalyzer {

    private Class aClass;

    public ClassAnalyzer(Class aClass) {
        this.aClass = aClass;
    }

    /**
     * Create a JSON Object with all the info of the class.
     * @return
     */
    public JsonObject getFullInfo() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", aClass.getSimpleName());
        objectBuilder.add("fields", getFields());
        objectBuilder.add("methods", getMethods());
        return objectBuilder.build();
    }
    /**
     * Get a field, and create a Json Object with all field data.
     * Example :
     * {
     * name: "firstname",
     * type: "String",
     * visibility : "private"  // public, private, protected, package
     * isStatic: false,
     * }
     */
    public JsonObject getField(Field f) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        // TODO add missing info
        objectBuilder.add("name",f.getName());
        objectBuilder.add("type",f.getType().getSimpleName());
        objectBuilder.add("visibility", getFieldVisibility(f));
        objectBuilder.add("isStatic", isFieldStatic(f));
        return objectBuilder.build();
    }

    public JsonObject getMethod(Method m) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("name", m.getName());
        objectBuilder.add("returnType", m.getReturnType().getSimpleName());
        objectBuilder.add("visibility", Modifier.toString(m.getModifiers()));
        objectBuilder.add("parameters", tri(m));
        objectBuilder.add("isStatic", Modifier.isStatic(m.getModifiers()));
        objectBuilder.add("isAbstract", Modifier.isAbstract(m.getModifiers()));
        return objectBuilder.build();
    }

    private JsonArray tri (Method m){
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for (Parameter parameter : m.getParameters()){
                arrayBuilder.add(parameter.getType().getSimpleName());
            }

            return arrayBuilder.build();

    }

    /**
     * Get fields, and create a Json Array with all fields data.
     * Example :
     * [ {}, {} ]
     * This method rely on the getField() method to handle each field one by one.
     */
    public JsonArray getFields() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // TODO Add all fields descriptions to array (use the getField() method above)
        Field[] listeField = aClass.getDeclaredFields();

        for (int i = 0; i<listeField.length; i++){
            arrayBuilder.add(getField(listeField[i]));
        }

        return arrayBuilder.build();
    }

    public JsonArray getMethods() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // TODO Add all fields descriptions to array (use the getField() method above)
        Method[] listeMethod = aClass.getDeclaredMethods();

        for (int i = 0; i<listeMethod.length; i++){
            arrayBuilder.add(getMethod(listeMethod[i]));
        }

        return arrayBuilder.build();
    }

    /**
     * Return whether a field is static or not
     *
     * @param f the field to check
     * @return true if the field is static, false else
     */
    private boolean isFieldStatic(Field f) {
         // TODO
        return Modifier.isStatic(f.getModifiers());
    }

    /**
     * Get field visibility in a string form
     *
     * @param f the field to check
     * @return the visibility (public, private, protected, package)
     */
    private String getFieldVisibility(Field f) {
        // TODO
        return Modifier.toString(f.getModifiers());
    }

}
