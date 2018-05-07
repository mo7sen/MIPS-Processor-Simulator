package core;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InstructionLine
{
    public static ObservableList<InstructionLine> instructionLines = FXCollections.observableArrayList();
    StringProperty instructionAssembly, instructionBinary;

    public InstructionLine(String ina, String inb)
    {
        instructionAssembly = new SimpleStringProperty(ina);
        instructionBinary = new SimpleStringProperty(inb);
    }

    public StringProperty instructionAssemblyProperty()
    {
        return this.instructionAssembly;
    }

    public StringProperty instructionBinaryProperty()
    {
        return this.instructionBinary;
    }
}
