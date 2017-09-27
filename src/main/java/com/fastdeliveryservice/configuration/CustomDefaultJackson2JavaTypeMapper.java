package com.fastdeliveryservice.configuration;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

/**
 * Created by Martina on 27/09/2017.
 */
    @Configuration
    public class CustomDefaultJackson2JavaTypeMapper extends DefaultJackson2JavaTypeMapper {

    private String typeIdHeader;

    public String getTypeIdHeader() {
        return typeIdHeader;
    }

    private void setTypeIdHeader(String typeIdHeader) {
        this.typeIdHeader = typeIdHeader;
    }

    @Override
    public void fromJavaType(JavaType javaType, MessageProperties properties) {
        addHeader(properties, getClassIdFieldName(), javaType.getRawClass());

        if (javaType.isContainerType() && !javaType.isArrayType()) {
            addHeader(properties, getContentClassIdFieldName(), javaType.getContentType().getRawClass());
        }

        if (javaType.getKeyType() != null) {
            addHeader(properties, getKeyClassIdFieldName(), javaType.getKeyType().getRawClass());
        }
    }

    @Override
    public JavaType toJavaType(MessageProperties properties) {
        boolean hasInferredTypeHeader = hasInferredTypeHeader(properties);
        if (hasInferredTypeHeader && super.getTypePrecedence().equals(TypePrecedence.INFERRED)) {
            JavaType targetType = fromInferredTypeHeader(properties);
            if ((!targetType.isAbstract() && !targetType.isInterface())
                    || targetType.getRawClass().getPackage().getName().startsWith("java.util")) {
                return targetType;
            }
        }

        String typeIdHeader = retrieveHeaderAsString(properties, getClassIdFieldName());
        setTypeIdHeader(typeIdHeader);

        if (typeIdHeader != null) {

            JavaType classType =  getClassIdType(typeIdHeader);
            if (!classType.isContainerType() || classType.isArrayType()) {
                return classType;
            }

            JavaType contentClassType = getClassIdType(retrieveHeader(properties, getContentClassIdFieldName()));
            if (classType.getKeyType() == null) {
                return TypeFactory.defaultInstance()
                        .constructCollectionLikeType(classType.getRawClass(), contentClassType);
            }

            JavaType keyClassType = getClassIdType(retrieveHeader(properties, getKeyClassIdFieldName()));
            return TypeFactory.defaultInstance()
                    .constructMapLikeType(classType.getRawClass(), keyClassType, contentClassType);
        }

        if (hasInferredTypeHeader) {
            return fromInferredTypeHeader(properties);
        }

        return TypeFactory.defaultInstance().constructType(Object.class);
    }
    @Override
    public String getClassIdFieldName() {
        return "type";
    }

    private JavaType getClassIdType(String classId) {
        if (super.getIdClassMapping().containsKey(classId)) {
            return TypeFactory.defaultInstance().constructType(super.getIdClassMapping().get(classId));
        }

        try {
            return TypeFactory.defaultInstance()
                    .constructType(ClassUtils.forName(classId, super.getClassLoader()));
        }
        catch (ClassNotFoundException e) {
            throw new MessageConversionException("failed to resolve class name. Class not found [" + classId + "]", e);
        }
        catch (LinkageError e) {
            throw new MessageConversionException("failed to resolve class name. Linkage error [" + classId + "]", e);
        }
    }
}
