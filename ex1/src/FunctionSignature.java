import java.lang.reflect.*;

public clasFunctionSignature
{
    private String name;
    private String[] parametersTypes;
    private String returnValue;

    public FunctionSignature(String name, String[] parametersTypes, String returnValue)
    {
        this.name = new String(name);
        this.parametersTypes = new String[parametersTypes.length];
        for(int i = 0; i < parametersTypes.length; i++)
        {
            this.parametersTypes[i] = new String(parametersTypes[i]);
        }
        if(returnValue == null)
        {
            this.returnValue = null;
        }
        else
        {
            this.returnValue = new String(returnValue);
        }
    }

    public FunctionSignature(String name, String returnValue)
    {
        this(name, new String[0], returnValue);
    }

    public FunctionSignature(String name)
    {
        this(name, (String) null);
    }

    public FunctionSignature(String name, String[] parametersType)
    {
        this(name, parametersType, null);
    }

    public FunctionSignature(Method method)
    {
        this.name = method.getName();
        Class<?>[] parameters = method.getParameterTypes();
        this.parametersTypes = new String[parameters.length];
        for(int i = 0; i < this.parametersTypes.length; i++)
        {
            this.parametersTypes[i] = parameters[i].getName();
        }
        this.returnValue = method.getReturnType().getName();
    }

    public FunctionSignature(Constructor<?> constructor)
    {
        this.name = constructor.getName();
        Class<?>[] parameters = constructor.getParameterTypes();
        this.parametersTypes = new String[parameters.length];
        for(int i = 0; i < this.parametersTypes.length; i++)
        {
            this.parametersTypes[i] = parameters[i].getName();
        }
        this.returnValue = null;
    }

    @Override
    public String toString()
    {
        String toReturn = "";
        if(this.returnValue != null)
        {
            toReturn = this.returnValue + " ";
        }
        toReturn = toReturn + this.name + "(";
        if(this.parametersTypes.length != 0)
        {
            for(int i = 0; i < this.parametersTypes.length - 1; i++)
            {
                toReturn = toReturn + this.parametersTypes[i] + ", ";
            }
            toReturn = toReturn + this.parametersTypes[this.parametersTypes.length - 1];
        }
        toReturn = toReturn + ")";
        return toReturn;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof FunctionSignature))
        {
            return false;
        }

        FunctionSignature signature = (FunctionSignature) obj;
        if(!this.name.equals(signature.name))
        {
            return false;
        }
        if(this.returnValue != null)
        {
            if(!this.returnValue.equals(signature.returnValue))
            {
                return false;
            }
        }
        if(this.parametersTypes.length != signature.parametersTypes.length)
        {
            return false;
        }
        for(int i = 0; i < this.parametersTypes.length; i++)
        {
            if(!this.parametersTypes[i].equals(signature.parametersTypes[i]))
            {
                return false;
            }
        }
        return true;
    }
}