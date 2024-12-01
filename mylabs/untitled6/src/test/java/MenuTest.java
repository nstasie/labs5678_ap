import org.example.views.Command;
import org.example.views.Menu;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class MenuTest {
    @Test
    void testMenuSelection() {
        Command command1 = Mockito.mock(Command.class);
        Command command2 = Mockito.mock(Command.class);
        Command command3 = Mockito.mock(Command.class);

        Command[] commands = {command1, command2, command3};
        Menu menu = new Menu(commands);

        menu.selectOption(1);
        verify(command1, times(1)).execute();

        menu.selectOption(2);
        verify(command2, times(1)).execute();

        menu.selectOption(4);
        verify(command3, never()).execute();
    }
}

