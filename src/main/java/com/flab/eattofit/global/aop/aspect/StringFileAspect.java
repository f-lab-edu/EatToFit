package com.flab.eattofit.global.aop.aspect;

import com.flab.eattofit.global.aop.annotations.StringFile;
import com.flab.eattofit.global.aop.exception.FieldAccessException;
import com.flab.eattofit.global.aop.exception.FileNotFoundException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

@Component
@Aspect
public class StringFileAspect {

    private static final String EATTOFIT_BEAN = "execution(* com.flab.eattofit..*(..)) && target(bean)";

    @Before(value = EATTOFIT_BEAN)
    public void injectStringFile(final Object bean) {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(StringFile.class)) {
                StringFile stringFile = field.getAnnotation(StringFile.class);
                String content = readFile(stringFile.location());

                field.setAccessible(true);
                try {
                    field.set(bean, content);
                } catch (IllegalAccessException e) {
                    throw new FieldAccessException();
                }
            }
        }
    }

    private String readFile(final String location) {
        Resource resource = new ClassPathResource(location);
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new FileNotFoundException(location);
        }

        return contentBuilder.toString().trim();
    }
}
