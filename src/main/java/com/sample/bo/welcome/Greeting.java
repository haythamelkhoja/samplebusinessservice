package com.sample.bo.welcome;

import com.sample.shared.Constants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component(Constants.Beans.GREETING)
public class Greeting {

    public String getMessage(String name) {

        if (StringUtils.isEmpty(name)) {
            name = Constants.Literals.NAME;
        }

        StringBuilder lOBBuilder = new StringBuilder();

        lOBBuilder.append(Constants.Literals.HI)
                .append(name)
                .append(Constants.Literals.END);

        // Hi {name}! WelcomeService to our workshop.
        return lOBBuilder.toString();
    }
}
