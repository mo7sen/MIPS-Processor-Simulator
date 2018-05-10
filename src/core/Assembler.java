package core;

import java.util.ArrayList;
import java.util.Arrays;

public class Assembler
{
	static ArrayList<Label> labels = new ArrayList<>();
	static ArrayList<String> codeLines = new ArrayList<>();
	static ArrayList<String> directiveLines = new ArrayList<>();
	static ArrayList<String> pseudoIns = new ArrayList<>(Arrays.asList(new String[]{"move","clear","li","la","b","bgt","blt","bge","ble","bgtu","beqz","beq","bne","mul","div","rem","not","nop"}));
	

	
	public static Label findLabel(String name) // finds the addressProperty of a label
	{
		for(Label lbl: labels)
		{
			if(lbl.getName().equals(name))
				return lbl;
		}
		return null;
	}

	private static boolean isPseudo(String s)
	{
		for(String in: pseudoIns)
			if(s.equals(in))
				return true;
		return false;
	}

	static void assembleProgram(String s)
	{
		for(int i = 0; i < MasterController.PC.get(); i++)
			InstructionMemory.add("00000000000000000000000000000000");
		if(s.contains(".text"))
		{
			String[] fn = s.split(".data");
			directiveLines = new ArrayList<>(Arrays.asList(fn[0].trim().split("\\n+")));
			codeLines = new ArrayList<>(Arrays.asList(fn[1].trim().replaceAll(":", ":\n").split("\\n+")));    //Labels now take a whole line for themselves
		}
		else if(!s.contains(".data"))
		{
			codeLines = new ArrayList<>(Arrays.asList(s.trim().replaceAll(":", ":\n").split("\\n+")));
		}
		labels.clear();
//		removeComments();
		scanForDirectives();
		replacePseudo();
		scanForLabels();
		assembleLines();
	}

//	private static void removeComments()
//	{
//		for (String codeLine : codeLines)
//			if(codeLine.trim().charAt(0)=='#')
//				codeLine = "\n";
//	}

	static void scanForLabels()
	{
		for (int i = MasterController.PC.get(); i < codeLines.size();)
		{
			if(codeLines.get(i).contains(":"))
			{
				labels.add(new Label(codeLines.get(i).trim().split(":")[0].trim(), Integer.toBinaryString(i * 4)));
				codeLines.remove(i);
			}
			else
			{
				i++;
			}
		}
	}

	static void replacePseudo()
	{
		for(int i=0;i<codeLines.size();i++)
		{
			String[] pseudoData;
			String newLine =codeLines.get(i).trim();
			String[] sliced = newLine.split("\\s+", 2);
			if(isPseudo(sliced[0]))
				switch(sliced[0])
				{
					case "move":
						pseudoData = sliced[1].split(",");
						codeLines.set(i,"addiu "+pseudoData[0]+","+pseudoData[1]+", 0");
						break;
					case "clear":
						pseudoData = sliced[1].split("\\s+");
						codeLines.set(i,"add "+pseudoData[0]+"$zero,$zero");
						break;
					case "li":

						pseudoData = sliced[1].replaceAll(","," ").trim().split("\\s+");
						String immediateIn;
						if(Integer.parseInt(pseudoData[1].trim()) >= 0)
							immediateIn = SignExtend.extendUnsigned(Integer.toBinaryString(Integer.parseUnsignedInt(pseudoData[1].trim())),32);
						else
							immediateIn = SignExtend.extendUnsigned(Integer.toBinaryString(Integer.parseInt(pseudoData[1].trim())), 32);

						int immediateHi = BinaryParser.parseUnsigned(immediateIn.substring(0,16)),
								immediateLo = BinaryParser.parseUnsigned(immediateIn.substring(16));
						codeLines.set(i , "ori " + pseudoData[0]+ ", " + pseudoData[0] + ", " + immediateLo);
						codeLines.add(i , "lui " + pseudoData[0] + "," +immediateHi);
						break;
					case "la":
						pseudoData = sliced[1].split(",");
						String varAddress = Memory.findVariable(pseudoData[1].trim()).address.toString();
						int Address_Hi = BinaryParser.parseUnsigned(varAddress.substring(0, 16)),
								Address_Lo = BinaryParser.parseUnsigned(varAddress.substring(16));
						codeLines.set(i , "ori " + pseudoData[0]+ ", " + pseudoData[0] + ", " + Address_Lo);
						codeLines.add(i , "lui " + pseudoData[0] + "," +Address_Hi);
						break;
					case "b":
						pseudoData = sliced[1].split("\\s+");
						codeLines.set(i,"beq $zero, $zero,"+pseudoData);
						break;
					case "bgt":
						pseudoData = sliced[1].split(",");
						codeLines.set(i,"bne $at, $zero,"+pseudoData[2]);
						codeLines.add(i,"slt $at,"+pseudoData[1]+","+pseudoData[0]);
						break;
					case "blt":
						pseudoData = sliced[1].split(",");
						codeLines.set(i,"bne $at, $zero,"+pseudoData[2]);
						codeLines.add(i,"slt $at,"+pseudoData[0]+","+pseudoData[1]);

						break;
					case "bge":
						pseudoData = sliced[1].split(",");
						codeLines.set(i,"bne $at, $zero,"+pseudoData[2]);
						codeLines.add("slt $at,"+pseudoData[0]+","+pseudoData[1]);
						break;
					case "ble":
						pseudoData=sliced[1].split(",");
						codeLines.set(i,"beq $at,$zero,"+pseudoData[2]);
						codeLines.add(i,"slt $at,"+pseudoData[1]+','+pseudoData[0]);
						break;
					case "bgtu":
						pseudoData=sliced[1].split(",");
						codeLines.set(i,"bne $at, $zero,"+pseudoData[2]);
						codeLines.add(i,"sltu $at,"+pseudoData[1]+','+pseudoData[0]);
						break;
					case "beqz":
						pseudoData=sliced[1].split(",");
						codeLines.set(i,"beq"+pseudoData[0]+",$zero,"+pseudoData[1]);
						break;
					case "beq":
						pseudoData=sliced[1].split(",");
						if(pseudoData[1].trim().matches("-?\\d+"))
						{
							codeLines.set(i, "beq " + pseudoData[0] + ", $at," + pseudoData[2]);
							codeLines.add(i, "ori $at, $zero, " + pseudoData[1]);
						}
						break;
					case "bne":
						pseudoData = sliced[1].split(",");
						if(pseudoData[1].trim().matches("-?\\d+"))
						{
							codeLines.set(i, "bne " + pseudoData[0] + ", $at," + pseudoData[2]);
							codeLines.add(i, "ori ,$at, $zero" + pseudoData[1]);
						}
						break;
					case "mul":
						pseudoData=sliced[1].split(",");
						codeLines.set(i,"mflo "+pseudoData[0]);
						codeLines.add(i,"mult "+pseudoData[1]+","+pseudoData[2]);
						break;
					case "div":
						pseudoData=sliced[1].split(",");
						if(pseudoData.length > 2)
						{
							codeLines.set(i, "mflo " + pseudoData[0]);
							codeLines.add(i, "div " + pseudoData[1] + "," + pseudoData[2]);
						}
						break;
					case "rem":
						pseudoData=sliced[1].split(",");
						codeLines.set(i,"mfhi "+pseudoData[0]);
						codeLines.add(i,"div "+pseudoData[1]+","+pseudoData[2]);
						break;
					case "not":
						pseudoData=sliced[1].split(",");

						codeLines.set(i,"nor "+pseudoData[0]+","+pseudoData[1]+",$zero");
						break;
					case "nop":
						codeLines.set(i,"sll $zero, $zero, 0");
				}

		}
	}

static void scanForDirectives()
{
	for(int j=0; j<directiveLines.size();j++)
	{
		String directiveType = null;
		String varName = null;
		String varData = null;

		String newLine = directiveLines.get(j).trim();
		if(newLine.contains(":"))
		{
			varName = newLine.split(":")[0].trim();

			if (newLine.contains("."))
				directiveType = newLine.split("\\.", 2)[1].split("\\s+")[0].trim();

			switch (directiveType)
			{
				case "ascii":
					varData = newLine.split("\\.", 2)[1].split("\"")[1];
					Memory.saveString(varData, varName, false);
					break;
				case "asciiz":
					varData = newLine.split("\\.", 2)[1].split("\"")[1];
					Memory.saveString(varData, varName, true);
					break;
				case "byte":
					varData = newLine.split("\\.", 2)[1].split("\\s+", 2)[1];
					for (String b : varData.split(","))
						Memory.saveB(varName, b.trim());
					break;

				case "half":
					varData = newLine.split("\\.", 2)[1].split("\\s+", 2)[1];
					for (String b : varData.split(","))
						Memory.saveH(varName, b.trim());
					break;
				case "space":
					varData = newLine.split("\\.", 2)[1].split("\\s+", 2)[1];
					int value = Integer.parseInt(varData);
					Memory.saveArrayEmpty(value, varName);
					break;

				case "word":
					varData = newLine.split("\\.", 2)[1].split("\\s+", 2)[1];
					for (String b : varData.split(","))
						Memory.saveW(varName, b.trim());
					break;
				default:
					System.out.println(directiveType);

			}
		}
	}

}
	private static void assembleLines()
	{
		for(int i = 0; i < codeLines.size(); i++)
		{
			String str = codeLines.get(i).trim();
			String s, t, d, imm, a;
			s = t = d = imm = a = null;
			String[] ss = str.replaceAll(",\\s+|\\s+|,", " ").split("\\s+");
			Instruction instruction = Instruction.searchInstruction(ss[0].trim());
			if (instruction != null)
			{
				String syn = instruction.getSyntaxAsString(instruction);
				syn = syn.replaceAll("f+|o+", instruction.opc);
				switch (instruction.syn)
				{
					case ArithLog:
					case JumpR:
					case DivMult:
					case MoveFrom:
					case syscall:
					case MoveTo:
					case Shift:
						s = (instruction.syn == Syntax.JumpR || instruction.syn == Syntax.DivMult || instruction.syn == Syntax.MoveTo) ? RegisterFile.findRegister(ss[1]).addressProperty.get() : (instruction.syn != Syntax.MoveFrom && instruction.syn != Syntax.syscall) ? RegisterFile.findRegister(ss[2]).addressProperty.get() : "00000";
						t = (instruction.syn == Syntax.JumpR || instruction.syn == Syntax.MoveFrom || instruction.syn == Syntax.MoveTo || instruction.syn == Syntax.Shift || instruction.syn == Syntax.syscall) ? "00000" : (instruction.syn == Syntax.DivMult) ? RegisterFile.findRegister(ss[2]).addressProperty.get() : RegisterFile.findRegister(ss[3]).addressProperty.get();
						d = (instruction.syn == Syntax.JumpR || instruction.syn == Syntax.DivMult || instruction.syn == Syntax.MoveTo || instruction.syn == Syntax.syscall) ? "00000" : RegisterFile.findRegister(ss[1]).addressProperty.get();
						a = (instruction.syn == Syntax.Shift) ? Integer.toBinaryString(Integer.parseInt(ss[3])) : "00000";
						break;
					case LoadI:	//Immediate
					case ArithLogI:	//Immediate
						t = RegisterFile.findRegister(ss[1]).addressProperty.get();
						s = (instruction.syn == Syntax.LoadI) ? "00000" : RegisterFile.findRegister(ss[2]).addressProperty.get();
						imm = (instruction.syn == Syntax.LoadI) ? Integer.toBinaryString(Integer.parseInt(ss[2])) : Integer.toBinaryString(Integer.parseInt(ss[3]));
						break;
					case LoadStore:	//Immediate
						t = RegisterFile.findRegister(ss[1]).addressProperty.get();
						imm = Integer.toBinaryString(Integer.parseInt(ss[2].split("\\(")[0]));
						s = RegisterFile.findRegister(ss[2].split("\\(")[1].split("\\)")[0]).addressProperty.get();
						break;
					case BranchZ:	//Immediate
					case Branch:	//Immediate
						s = RegisterFile.findRegister(ss[1]).addressProperty.get();
						t = RegisterFile.findRegister(ss[2]).addressProperty.get();
						Label l = findLabel(ss[3].trim());
						if (l != null)
							imm = Integer.toBinaryString((Integer.parseInt(l.getAddress(), 2)/4) - (i + 1));
					case Jump:	//Jump
						Label l2 = findLabel(ss[1]);
						if (l2 != null)
							imm = Integer.toBinaryString(BinaryParser.parseUnsigned(l2.getAddress().substring(4, 30)));
						break;
				}

				if (a != null)
					a = SignExtend.extendUnsigned(a, 5);
				if (imm != null)
					imm = SignExtend.extendUnsigned(imm, 16);
				if (imm != null && instruction.syn == Syntax.Jump)
					imm = SignExtend.extendUnsigned(imm, 26);


				if (imm != null)
					syn = syn.replaceAll("i+", imm);
				if (d != null)
					syn = syn.replaceAll("d+", d);
				if (t != null)
					syn = syn.replaceAll("t+", t);
				if (a != null)
					syn = syn.replaceAll("a+", a);
				if (s != null)
					syn = syn.replaceAll("s+", s);
				if (syn != null)
				{
					InstructionMemory.add(syn);
					InstructionLine.instructionLines.add(new InstructionLine(str ,syn));
				}
			}
		}
	}
}
class Label
{
	String name;
	String address;
	public Label(String name, String address)
	{
		this.name = name;
		while(address.length() < 32)
			address = "0" + address;
		this.address = address;
	}
	public String getName()
	{
		return this.name;
	}
	public String getAddress()
	{
		return this.address;
	}
}

enum Encoding
{Register,Immediate,Jump}

enum Syntax
{ArithLog,DivMult,Shift,JumpR,MoveFrom,MoveTo,ArithLogI,LoadI,Branch,BranchZ,LoadStore,Jump,syscall}

