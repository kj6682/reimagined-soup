This repository serves to get back on coding.

Let's make three profiles: one for each of the packages.

## greeting
This profile is to use the greeting basic beans

## calculator
This profile is to get into the beans for the calculation exercise

## book
This profile is the basic library that we have always dreamt of

The only way I found to execute profile specific tests is to use the profile on command line.
As an example
''' ./mvnw clean  test  -Dspring.profiles.active=greeting '''

On each test use the BeforeAll annotation to chech the current profile.

'''
    @BeforeAll
    public static void setup() {
        logger.info("Running test with profile: " + System.getProperty("spring.profiles.active"));
        assumeTrue("greeting".equals(System.getProperty("spring.profiles.active")));
    }
'''
## All tests must pass
Now all tests must pass.

To use all the profiles > /mvnw clean test spring-boot:run -Dspring.profiles.active=calculator,greeting,book

