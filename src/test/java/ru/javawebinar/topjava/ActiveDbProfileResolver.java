package ru.javawebinar.topjava;

import org.springframework.test.context.ActiveProfilesResolver;

//http://stackoverflow.com/questions/23871255/spring-profiles-simple-example-of-activeprofilesresolver
public class ActiveDbProfileResolver implements ActiveProfilesResolver {

    @Override
    public String[] resolve(Class<?> aClass) {
        String repositoryImplementation = null;
        switch (aClass.getSimpleName()) {
            case "MealDataJPATest":
            case "UserDataJPATest":
                repositoryImplementation = Profiles.DATAJPA;
                break;
            case "MealJPATest":
            case "UserJPATest":
                repositoryImplementation = Profiles.JPA;
                break;
            case "MealJDBCTest":
            case "UserJDBCTest":
                repositoryImplementation = Profiles.JDBC;
                break;
        }
        return new String[]{Profiles.getActiveDbProfile(), repositoryImplementation};
    }
}