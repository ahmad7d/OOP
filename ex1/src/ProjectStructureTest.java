public class ProjectStructureTest
{
    public ProjectStructureTest()
    {}

    private void testExistance(String className, FunctionSignature[] requestedConstructors, FunctionSignature[] requestedMethods, String[] requestedFields) throws Exception
    {
        ExistanceTester.checkConstructors(className, requestedConstructors);
        ExistanceTester.checkMethods(className, requestedMethods);
        ExistanceTester.checkFields(className, requestedFields);
    }

    private void testGameClass() throws Exception
    {
        String _class = "Game";
        FunctionSignature[] constructors =
                {
                        new FunctionSignature("Game", new String[]{"Player", "Player", "Renderer"}),
                        new FunctionSignature("Game", new String[]{"Player", "Player", "int", "int", "Renderer"})
                };
        FunctionSignature[] methods =
                {
                        new FunctionSignature("run", "Mark"),
                        new FunctionSignature("getWinStreak", "int"),
                        new FunctionSignature("getBoardSize", "int")
                };
        String[] fields = {};
        this.testExistance(_class, constructors, methods, fields);
    }

    private void testTournamentClass() throws Exception
    {
        String _class = "Tournament";
        FunctionSignature[] constructors =
                {
                        new FunctionSignature("Tournament", new String[]{"int", "Renderer", "Player", "Player"})
                };
        FunctionSignature[] methods =
                {
                        new FunctionSignature("main", new String[]{String[].class.getName()}, "void"),
                        new FunctionSignature("playTournament", new String[]{"int", "int", String.class.getName(), String.class.getName()}, "void")
                };
        String[] fields = {};
        this.testExistance(_class, constructors, methods, fields);
    }

    private void testBoardClass() throws Exception
    {
        String _class = "Board";
        FunctionSignature[] constructors =
                {
                        new FunctionSignature("Board"),
                        new FunctionSignature("Board", new String[]{"int"})
                };
        FunctionSignature[] methods =
                {
                        new FunctionSignature("getSize", "int"),
                        new FunctionSignature("putMark", new String[]{"Mark", "int", "int"}, "boolean"),
                        new FunctionSignature("getMark", new String[]{"int", "int"}, "Mark")
                };
        String[] fields = {};
        this.testExistance(_class, constructors, methods, fields);
    }

    private void testPlayerInterface() throws Exception
    {
        String _class = "Player";
        FunctionSignature[] constructors = {};
        FunctionSignature[] methods =
                {
                        new FunctionSignature("playTurn", new String[]{"Board", "Mark"}, "void")
                };
        String[] fields = {};
        this.testExistance(_class, constructors, methods, fields);
    }

    private void testRendererInterface() throws Exception
    {
        String _class = "Renderer";
        FunctionSignature[] constructors = {};
        FunctionSignature[] methods =
                {
                        new FunctionSignature("renderBoard", new String[]{"Board"}, "void")
                };
        String[] fields = {};
        this.testExistance(_class, constructors, methods, fields);
    }

    private void testPlayerClasses() throws Exception
    {
        for(String _class : new String[]{"HumanPlayer", "GeniusPlayer", "CleverPlayer", "WhateverPlayer"})
        {
            FunctionSignature[] consturctors = {new FunctionSignature(_class)};
            FunctionSignature[] methods =
                    {
                            new FunctionSignature("playTurn", new String[]{"Board", "Mark"}, "void")
                    };
            String[] fields = {};
            this.testExistance(_class, consturctors, methods, fields);
        }
    }

    private void testRendererClasses() throws Exception
    {
        String consoleRenderer = "ConsoleRenderer";
        FunctionSignature[] consoleRendererConstructors = {new FunctionSignature("ConsoleRenderer", new String[]{"int"})};
        FunctionSignature[] consoleRendererMethods =
                {
                        new FunctionSignature("renderBoard", new String[]{"Board"}, "void")
                };
        String[] consoleRendererFields = {};
        this.testExistance(consoleRenderer, consoleRendererConstructors, consoleRendererMethods, consoleRendererFields);

        String voidRenderer = "VoidRenderer";
        FunctionSignature[] voidRendererConstructors = {new FunctionSignature("VoidRenderer")};
        FunctionSignature[] voidRendererMethods =
                {
                        new FunctionSignature("renderBoard", new String[]{"Board"}, "void")
                };
        String[] voidRendererFields = {};
        this.testExistance(voidRenderer, voidRendererConstructors, voidRendererMethods, voidRendererFields);
    }

    private void testPlayerFactory() throws Exception
    {
        String _class = "PlayerFactory";
        FunctionSignature[] constructors = {new FunctionSignature("PlayerFactory")};
        FunctionSignature[] methods =
                {
                        new FunctionSignature("buildPlayer", new String[]{String.class.getName()}, "Player")
                };
        String[] fields = {};
        this.testExistance(_class, constructors, methods, fields);
    }

    private void testRendererFactory() throws Exception
    {
        String _class = "RendererFactory";
        FunctionSignature[] constructors = {new FunctionSignature("RendererFactory")};
        FunctionSignature[] methods =
                {
                        new FunctionSignature("buildRenderer", new String[]{String.class.getName(), "int"}, "Renderer")
                };
        String[] fields = {};
        this.testExistance(_class, constructors, methods, fields);
    }

/*
    private void testMark() throws Exception
    {
        String _class = "Mark";
        FunctionSignature[] methods =
        {
            new FunctionSignature("toString", "String")
        };
        String[] fields = {};
        this.testExistance(_class, constructors, methods, fields);
    }
*/

    public static void main(String[] args)
    {
        try
        {
            ProjectStructureTest test = new ProjectStructureTest();
            test.testTournamentClass();
            test.testGameClass();
            test.testBoardClass();
            test.testPlayerInterface();
            test.testPlayerClasses();
            test.testPlayerFactory();
            test.testRendererInterface();
            test.testRendererClasses();
            test.testRendererFactory();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            System.err.println("AUTO.unimplemented_api");
        }
    }

}
