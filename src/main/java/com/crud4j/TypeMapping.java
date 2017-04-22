package com.crud4j;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dell on 22-04-2017.
 */
public class TypeMapping {
    private static final String DEFAULT_TYPE = "text";
    private static final Map<String, String> sqlTypeToInputTypeMap = new HashMap<>();

    static {
        sqlTypeToInputTypeMap.put("int", "text");
        sqlTypeToInputTypeMap.put("long", "text");
        sqlTypeToInputTypeMap.put("varchar", "text");
    }

    public static String getInputType(String sqlType) {
        if (sqlTypeToInputTypeMap.containsKey(sqlType)) {
            return sqlTypeToInputTypeMap.get(sqlType);
        }
        return DEFAULT_TYPE;
    }

    public static void main(String[] args) throws ClassNotFoundException {

        final JavaClassSource javaClass = Roaster.create(JavaClassSource.class);
        javaClass.setPackage("com.company.example").setName("Person");

        javaClass.addInterface(Serializable.class);
        javaClass.addAnnotation(Service.class);
        javaClass.getAnnotation(Service.class).setStringValue("name", "breakerService");
        javaClass.addField()
                .setName("serialVersionUID")
                .setType("long")
                .setLiteralInitializer("1L")
                .setPrivate()
                .setStatic(true)
                .setFinal(true);

        javaClass.addProperty(Integer.class, "id").setMutable(false);
        javaClass.addProperty(String.class, "firstName");
        javaClass.addProperty("String", "lastName");

        javaClass.addMethod()
                .setConstructor(true)
                .setPublic()
                .setBody("this.id = id;")
                .addParameter(Integer.class, "id");

        System.out.println(javaClass);
    }
}
