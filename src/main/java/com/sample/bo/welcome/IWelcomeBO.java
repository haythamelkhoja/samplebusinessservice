package com.sample.bo.welcome;

import com.sample.bo.IBaseBO;
import com.sample.shared.welcome.WelcomePojo;

public interface IWelcomeBO extends IBaseBO {

	public WelcomePojo welcome(String pSTName);

	public WelcomePojo welcomeId(Integer pINId);
}
