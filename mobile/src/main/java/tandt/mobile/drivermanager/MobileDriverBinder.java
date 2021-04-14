//package tandt.mobile.drivermanager;
//
//import com.google.inject.AbstractModule;
//import com.google.inject.name.Names;
//
//public class MobileDriverBinder extends AbstractModule {
//    @Override
//    protected void configure() {
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_ANDROID)).to(AndroidDriverManager.class);
//        bind(DriverManager.class).annotatedWith(Names.named(Constants.DRIVER_TYPE_IOS)).to(IosDriverManager.class);
//    }
//
//}
