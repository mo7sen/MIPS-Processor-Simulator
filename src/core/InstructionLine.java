package core;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InstructionLine
{
    public static ObservableList<InstructionLine> instructionLines = FXCollections.observableArrayList();
    IntegerProperty index;
    StringProperty instructionAssembly, instructionBinary;

    public InstructionLine(int i, String ina, String inb)
    {
        index = new SimpleIntegerProperty(i);
        instructionAssembly = new SimpleStringProperty(ina);
        instructionBinary = new SimpleStringProperty(inb);
    }

    public IntegerProperty indexProperty() { return this.index; }

    public StringProperty instructionAssemblyProperty()
    {
        return this.instructionAssembly;
    }

    public StringProperty instructionBinaryProperty()
    {
        return this.instructionBinary;
    }
}
