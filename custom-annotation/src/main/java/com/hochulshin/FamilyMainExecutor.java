package com.hochulshin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Scanner;

public class FamilyMainExecutor {
    private static final Logger log = LoggerFactory.getLogger(FamilyMainExecutor.class);

    /**
     * 출력결과
     * 3
     * SENIOR 75
     * Senior Member
     * Spend: 75
     * Budget Left: 25
     * JUNIOR 45
     * Junior Member
     * Spend: 45
     * Budget Left: 5
     * SENIOR 40
     * Senior Member
     * Spend: 40
     * Budget Left: 60
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        while (testCases > 0) {
            String role = in.next();
            int spend = in.nextInt();
            try {
                Class annotatedClass = FamilyMember.class;
                Method[] methods = annotatedClass.getMethods();
                log.info("methods: {}", methods);
                for (Method method : methods) {
                    /* Annotation 정보 획 */
                    if (method.isAnnotationPresent(FamilyBudget.class)) {
                        FamilyBudget family = method
                                .getAnnotation(FamilyBudget.class);
                        String userRole = family.userRole();
                        int budgetLimit = family.budgetLimit();
                        if (userRole.equals(role)) {
                            if (spend <= budgetLimit) {
                                method.invoke(FamilyMember.class.newInstance(),
                                        budgetLimit, spend);
                            } else {
                                System.out.println("Budget Limit Over");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            testCases--;
        }
    }
}
