package com.example.url.shortner.bdd;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "com.example.url.shortner.bdd.steps" })
public class CucumberAT {

}