# COMP250-FINAL-TESTER
# Disclaimers
- Please only add TEST CODE here, not actual assignment code. If you accidentally pushed your assignment code, please delete ASAP to avoid any inconveniences.
- Otherwise, happy coding and debugging!

# How to Use
- Currently the tester is under construction. As soon as the official tester is out, more tests will be added. 
- However there are some Sodokus we found online. This will save you time from typing.


# How to Start
- You may clone this repo to your Intellij or your preferred IDE. If you aren't familiar with Git, our amazing TA Sasha made a tutorial https://www.youtube.com/playlist?list=PLFvevpoGcNCvjyTjOfPhzqjgb-L_WdX8r
- Worst case scenario, you may also copy paste the code onto a .java file under assignment1. It's not recommended since you might miss something, but it's quicker than the last method.
- You may also add new tests and create pull requests. I will check my inbox every 2 hours from 9am EST to 1159pm EST. 
- Happy coding and debugging!

# Installing
For IntelliJ IDEA (recommended)
VCS -> Get from Version Control... -> Paste the URL of this repository

For Eclipse
File -> Import -> Git -> Projects from Git (With Smart Import) -> Clone URI -> paste URL of this repository into the URI box -> Click next a bunch, setting directory at your own discression, Master branch from origin. All else default -> Finish

For both:
Drag your .java files from the assignment into the tester package.
In case your IDE does not automatically set package name, change your package name to ______.
Regularly update the tester (pull the repository). Your assignment files will be ignored.

testing code backup : `String localDir = System.getProperty("user.dir");`
 InputStream in = new FileInputStream(localDir + "/src/medium3x3_fiveSolutions.txt");
