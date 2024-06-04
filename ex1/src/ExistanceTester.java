import java.lang.reflect.*;
import javax.naming.NameNotFoundException;

public abstract class ExistanceTester
{
    public static void checkConstructorExistance(String className, FunctionSignature requestedConstructor) throws Exception
    {
        Class<?> _class = Class.forName(className);
        boolean found = false;
        for(Constructor<?> constructor : _class.getDeclaredConstructors())
        {
            FunctionSignature constructorSignature = new FunctionSignature(constructor);
            if(requestedConstructor.equals(constructorSignature))
            {
                found = true;
                break;
            }
        }

        if(!found)
        {
            throw new NameNotFoundException("Requested constructor '" + requestedConstructor + "' for class " + className + " was not found");
        }
    }

    public static void checkMethodExistance(String className, FunctionSignature requestedMethod) throws Exception
    {
        Class<?> _class = Class.forName(className);
        boolean found = false;
        for(Method method : _class.getDeclaredMethods())
        {
            FunctionSignature methodSignature = new FunctionSignature(method);
            if(requestedMethod.equals(methodSignature))
            {
                found = true;
                break;
            }
        }

        if(!found)
        {
            throw new NameNotFoundException("Requested method '" + requestedMethod + "' for class " + className + " was not found");
        }
    }

    public static void checkFieldExistance(String className, String requestedField) throws Exception
    {
        Class<?> _class = Class.forName(className);
        boolean found = false;
        for(Field field : _class.getDeclaredFields())
        {
            String fieldName = field.getType().getName() + " " + field.getName();
            if(requestedField.equals(fieldName))
            {
                found = true;
                break;
            }
        }

        if(!found)
        {
            throw new NameNotFoundException("Requested field '" + requestedField + "' for class " + className + " was not found");
        }
    }

    public static void checkConstructors(String className, FunctionSignature[] constructors) throws Exception
    {
        for(FunctionSignature constructor : constructors)
        {
            checkConstructorExistance(className, constructor);
        }
    }

    public static void checkMethods(String className, FunctionSignature[] methods) throws Exception
    {
        for(FunctionSignature method : methods)
        {
            checkMethodExistance(className, method);
        }
    }

    public static void checkFields(String className, String[] fields) throws Exception
    {
        for(String field : fields)
        {
            checkFieldExistance(className, field);
        }
    }
}
