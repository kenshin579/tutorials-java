//package com.advenoh;
//
//import org.mockito.Answers;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.stubbing.Answer;
//
//import static org.mockito.Answers.CALLS_REAL_METHODS;
//import static org.mockito.Answers.RETURNS_DEFAULTS;
//import static org.mockito.cglib.core.TypeUtils.isAbstract;
//
//public class AbstractMethodMocker implements Answer<Object> {
//	@Override
//	public Object answer(InvocationOnMock invocation) throws Throwable {
//		Answers answer;
//		if (isAbstract(invocation.getMethod().getModifiers()))
//			answer = RETURNS_DEFAULTS;
//		else
//			answer = CALLS_REAL_METHODS;
//		return answer.answer(invocation);
//	}
//}