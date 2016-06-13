package interview.mobiquinty.com.productcatalog.exceptions;

/**
 * Created by Veaceslav Munteanu on 6/13/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 */
public class ExceptionManagerSingleton {
    private static ExceptionManagerSingleton ourInstance = new ExceptionManagerSingleton();

    public static ExceptionManagerSingleton getInstance() {
        return ourInstance;
    }

    private ExceptionHandlerAbstract exceptionHandler;

    private ExceptionManagerSingleton() {
    }

    public void setExceptionHandler(ExceptionHandlerAbstract handler){
        this.exceptionHandler = handler;
    }

    public ExceptionHandlerAbstract getExceptionHandler(){
        return this.exceptionHandler;
    }
}
