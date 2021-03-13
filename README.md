# mobile-appium-cucumber
**To run all test  via command line run below command line:**
(open terminal and navigate to project directory or just open terminal from IDE ) & run->
mvn test -Dcucumber.options="--tag @cucumberTest"

cucumber report plugin;
**please add this as plugin option in cucumber runner class**
**"de.monochromata.cucumber.report.PrettyReports:target/AndroidReports"**
for Usage Example: see AndroidTestRunnerClass
,plugin={"json:target/report/cucumber-json.json","html:target/report/cucumber-html.html",
                "de.monochromata.cucumber.report.PrettyReports:target/AndroidReports",
                "pretty"}
`

references ->
https://support.testsigma.com/support/solutions/articles/32000023784-using-appium-desktop-to-inspect-android-ios-apps-locally
Mobile useful tips;
(to get open activity name of installed app run following 
command when android mobile is connected and debug mode is on)
For Mac/Linux:
adb shell dumpsys window | grep -E 'mCurrentFocus' 
For Windows:
adb shell dumpsys window | find "mCurrentFocus"

There is lots of scope for improvement in this project.
