# AkotlinPrelude 

This contains basic fragment manipulation, generics UI (tabBar), generic MVP interface for Kotlin Android.


## Version 
* 0.0.1 (Prelude init with fragment, button extensions)
* 0.2.0 Multi-Plane-UI (manipulating multiple fragments onto a single Activity)

## Install 
- **Step 1**. Add the JitPack repository to your build file

* Add it in your root build.gradle at the end of repositories:
  
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
	    implementation 'com.github.LamourBt:AkotlinPrelude:0.2.0'
 ```
## Documentation
```Kotlin 
/* 
	 Multi-Plane-UI 
	(manipulating multiple fragments onto a single Activity)
	
	 Any Activity that wishes to become a middleman
	 would conform to `IActivityMiddleman` interface
	 
	 Any Fragment that wishes to customize this middleman Activity
	 would conform to `IPlainFragment` interface 
	 
	 However there is two bases classes that take care of the interfaces 
	 above, PlainFragment and PlainActivity (has a default layout thus you don't need provide a layoutView)
	 
	 Note: the code below is just an overView check the example project for more elaborate codes. 

  */

 
 class MainActivity: PlainActivity() { 
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFrameLayoutContainer() // <--- set the default layoutView  
        
     }
     
     // the methods below makes this Activity a middleman 
     
     override fun setActivityTitle(string: String) { 
     		 supportActionBar?.title = string
     }
     
     override fun <A> broadcasting(owner: Fragment, item: A) {}
     
     override fun <A> broadcasting(owner: Fragment, items: List<A>) {}
     
     override fun shouldBeReplaced(current: Fragment) {}
 
 }
 
 class TestAFragment: PlainFragment() {
	    override var title: String? = null
	    override var middlemanListener: WeakReference<IActivityMiddleman>? = null
	
	
	    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
	        super.onViewCreated(view, savedInstanceState)
	     
	        setFragmentTitle() //<-- set fragment title would also set its activity's title 
	
			 /*
			  Assuming there is a button in this fragment,
			   and once it's pressed it'd emit/broadcast data to its host Activity 
			  in order for other fragments can used. 
			  
			  Or wants to tell his host to present another fragment
			  
			 */
	        button?.setOnClickListener {
	            middlemanListener?.get()?.broadcasting(this, 21) 
	            
	            middlemanListener?.get()?.shouldBeReplaced(this)
	        }
	    }
	
	
	    override fun onAttach(context: Context?) {
	        super.onAttach(context)
	        setMiddlemanListener(activity) //<-- set listener 
	    }
	
	    override fun onDetach() {
	        super.onDetach()
	        releaseMiddleListener() // <-- remove listener 
	    }
}
```




