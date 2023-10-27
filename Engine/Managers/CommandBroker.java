package Engine.Managers;

import Engine.Commands.RegistrationCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandBroker {

    private List<RegistrationCommand> commandList;

    public CommandBroker() {
        commandList = new ArrayList<>();
    }

    public void AddCommand(RegistrationCommand cmd) {
        commandList.add(cmd);
    }

    public void ExecuteCommands() {
        for (RegistrationCommand cmd : commandList) {
            cmd.Execute();
        }
        commandList.clear();
    }
}
