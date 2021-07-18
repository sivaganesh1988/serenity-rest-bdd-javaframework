# Profanity Filter API Test Automation

Test Automation project to demonstrate the automation of a webservice which helps in filtering profanity, obscenity and other unwanted text

## Objective

To demonstrate the ability to create an API automation framework from scratch which uses BDD format scenarios and follows SOLID principles for implementing design pattern

## Framework/Tools used

- Java 1.8
- Cucumber 4
- Serenity 2.4.2
- Junit
- Github Actions for CI
- Github Pages for reporting

## Execution Instructions

- Using Github Actions

Github Actions is already enabled for this repo. Build can be triggered by any one of the below options.

   1. Edit any file and commit directly to the main branch. This will auto-trigger the github action build.

2. Open any existing build in github actions tab and re-run the jobs.

Report will be auto-updated in the below github pages URL after performing any of the above step.

[Access the test execution report here](https://sivaganesh1988.github.io/profanity-filter-test/)

- Using local machine

Please ensure git and maven are already installed before trying the below commands

```bash
# run the below commands from command prompt

git clone https://github.com/sivaganesh1988/profanity-filter-test.git
cd profanity-filter-test
mvn clean verify
```

Report can be found in your project folder under the below location /target/site/serenity/index.html

### Useful links

- API documentation - [https://www.purgomalum.com/](https://www.purgomalum.com/)
- Github actions  - [https://github.com/features/actions](https://github.com/features/actions)
