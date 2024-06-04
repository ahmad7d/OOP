/**
 * The RendererFactory class is responsible for creating instances of different renderers based on input.
 */
public class RendererFactory {

    /**
     * Builds and returns a renderer instance based on the specified renderer type and size.
     *
     * @param type The type of renderer to be created.
     * @param size The size of the game board for rendering purposes.
     * @return A renderer instance corresponding to the specified type and size.
     */
    public Renderer buildRenderer(String type, int size) {
        String rendererType = type.toLowerCase();

        if (rendererType.equals(Constants.CONSOLE_MODE)) {
            return new ConsoleRenderer(size);
        } else if (rendererType.equals(Constants.NON_CONSOLE_MODE)) {
            return new VoidRenderer();
        }
        return null;
    }
}