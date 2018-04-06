# AkotlinPrelude 

**The purpose of this dependency is to promote faster development, by providing generic Component such as TabActivity, MVP design interface, extension onto existing Android framework.**



## Version 
* 0.0.1 Prelude init
* 0.2.0 Multi-Plane UI 
* 0.2.1
* 0.2.3 (latest)

## Features 
- **TabActivity**
- **PlainFragment**, **PlainActivity** (Multi-Plane UI)
- **MVP Design/Architecture interface**
- **Extensions**
- **Generic RecyclerView**

## Examples 
*you'll find examples* [here](https://github.com/LamourBt/AkotlinPrelude/tree/dev1/app/src/main/java/com/lamour/akotlinpreludeexample)

## Install 
- **Step 1**. Add the JitPack repository to your build file (root/project build.gradle)
  
```
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
- **Step 2.** Add the dependency
 
```
  implementation 'com.github.LamourBt:AkotlinPrelude:0.2.3'
```



## Multi-Plane UI 

> 	 It's when you're manipulating multiple fragments onto a single Activity. Let's say that you're implementing this following onbording flow **BasicInfo > Create Profile > Upload User Image** (assuming each step is a fragment that is loaded). So by deriving from `PlainFragment` for your fragments and `PlainActivity` for this single Activity, your fragments have the ability to customize (such as title) this PlainActivity, and you can invoke delegates to pass information from one fragment to another within the Activity.  

 
  
## TabActivity
 
>  An abstract class that contains Generic ViewPager, Adapter, TabLayout. With less than 2 functions call, you'd build TabBarController like UI.


## Questions, Issues or Suggestions
Feel free to open a github issue. 

## Author
lamour 
	 
## License
AkotlinPrelude is available under the MIT license. See the LICENSE file for more info.

 






