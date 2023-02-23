package com.yiran.nuomi.component;

import com.yiran.nuomi.common.ErrorCode;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author SuperZhang
 * @version 1.Bootstrap
 * @description: 用来做 实体 的那个 @NotNull 之类的校验的，
 * @date 2021-09-17 20:55
 */
@Component
public class ObjectValidator implements ErrorCode {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public Map<String, String> validate(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        Set<ConstraintViolation<Object>> set = validator.validate(obj);
        if (set != null && set.size() > 0) {
            for (ConstraintViolation cv : set) {
                result.put(cv.getPropertyPath().toString(), cv.getMessage()); // map里存 有问题的属性和对应的消息
            }
        }
        return result;
    }

}
