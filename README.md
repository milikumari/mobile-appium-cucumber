**Automation of 'Whats App' native mobile application using Cucumber & Appium**
```bash
This project will run on Android Emulator ( test will fail in emulator as support to access OTP via Api using twilio or other service is not yet implemented
) or on Android device connected to local PC. I have executed this test
on Emulator (OTP step was manually done), running on my window 10 laptop. 

* In Android mobile device Whats App can read OTP automatically if permission is granted to whats app**
* OR Appium can read OTP  from notification panel.
```
**Pre-condition**
```bash
* JAVA_HOME
* ANDROID_HOME 
* M2_HOME (maven home) 
* Using java 8 or higher version
* Appium & node is installed, and you are able to run Appium Server
 ```
**Instructions:**
```bash
Checkout branch from git
Branch name: https://github.com/milikumari/mobile-appium-cucumber/tree/Feature-WhatsAppRegistration]
```
**Before running update capabilities & config details in env config properties file**  

**Android Device:** (if using physical device)
```bash
 path to config file :src/test/resources/config.properties
 **please enable developer mode and uSB debugging n device

    android-device-name=  {provide your phone name}
    android-platform-version={provide software version of android Phone}
    android-udid= {provide device id of your phone }   
    platform-os-type=android
    device-type=device
```
**Emulator (if running test on emulator update following  cap with your emulator details)**
```bash
    android-udid= {provide udid of Emulator}
    android-device-name=  {provide your Emulator name}
    android-platform-version={provide software version of android Emulator}
    platform-os-type=android
    device-type=emulator
```
**Update following prop value to start Appium**
[appium.js.path={provide path from yr local pc}
[eg:#appium.js.path=C:/Users/Niku/node_modules/appium/build/lib/main.js]


**Update test data props in testData.json available here in the project path:**
```bash
"/src/test/resources/testData/local/testData.json" 
```
```JSON
{
    "profileName": "SmartName",
    "whatsAppNumberToRegister": "7926421289" ,
    "countryCode": "+44",   
    "newContactPersonMobileNumber": "00447921880054",
    "newContactPersonName": "testUserNameFromLocal",
    "attachmentType": "documents"
}  
```
**To run all test  via command line run below command line:**
open terminal and navigate to project directory or just open terminal from IDE  & run->

```bash
   mvn test
```

**Report will be generated at in project target folder:** 

```bash
  "/target/AndroidReports/cucumber-html-reports/overview-features.html"
```
cucumber report plugin;
**please add this as plugin option in cucumber runner class**


```bash
Features are availbel in 
```bash
"de.monochromata.cucumber.report.PrettyReports:target/AndroidReports"
for Usage Example: see AndroidTestRunnerClass
,plugin={"json:target/report/cucumber-json.json","html:target/report/cucumber-html.html",
                "de.monochromata.cucumber.report.PrettyReports:target/AndroidReports",
                "pretty"}
```
**Mobile useful tips:**
To get open activity name of installed app run following command when android mobile is connected and debug mode is on)]**
For Mac/Linux:** adb shell dumpsys window | grep -E 'mCurrentFocus'
For Windows:** adb shell dumpsys window | find "mCurrentFocus"

**References I used:**

* https://support.testsigma.com/support/solutions/articles/32000023784-using-appium-desktop-to-inspect-android-ios-apps-locally
* https://appiumpro.com/editions/15-testing-ios-push-notifications
* https://github.com/saikrishna321/VodQaAdvancedAppium/blob/master/src/test/java/com/appium/gesture/GestureTest.java
* https://github.com/AppiumTestDistribution/appium-gestures-plugin
* https://github.com/vikramvi/AppiumSerenityPOC
* https://github.com/sunilpatro1985/AppiumTest_Java_And_iOS 

**TODO list :**
* Add support to run test in Cloud (BrowserStack/Sauce Lab)
* Move common objects into single pages CommonObjectPage (  buttons on pop up screen, Next button etc) 
and extend this class where this buttons present. also update object elements name to be more consistence 
* work on reporting & log Appium actions in log files for debugging & improve on waiting time before action.
* move to Serenity: 

https://serenity-bdd.github.io/theserenitybook/latest/index.html
http://www.thucydides.info/docs/serenity/#_serenity_with_cucumber]
